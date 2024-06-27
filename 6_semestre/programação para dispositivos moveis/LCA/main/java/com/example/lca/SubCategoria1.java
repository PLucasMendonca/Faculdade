package com.example.lca;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.lca.model.Categoria;
import com.example.lca.model.SubCategoria;
import com.example.lca.controller.CategoriaController;
import com.example.lca.controller.SubCategoriaController;

import java.util.ArrayList;
import java.util.List;

public class SubCategoria1 extends AppCompatActivity {

    private TextView textViewNomeCategoria;
    private List<SubCategoria> subcategorias = new ArrayList<>();
    private RelativeLayout subcategoriasLayout;
    private int proximoIdSubcategoria = 1;
    private ImageButton btnSave;

    private int idCategoria;
    private String nomeCategoria;
    private CategoriaController categoriaController;
    private SubCategoriaController subCategoriaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);

        categoriaController = new CategoriaController(this);
        subCategoriaController = new SubCategoriaController(this);

        int idCategoria = getIntent().getIntExtra("idCategoria", -1);
        String nomeCategoria = getIntent().getStringExtra("nomeCategoria");

        // Configurando a Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarTela3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        btnSave = findViewById(R.id.bDisk3);
        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(SubCategoria1.this, "Salvo", Toast.LENGTH_SHORT).show();
            }
        });

        textViewNomeCategoria = findViewById(R.id.editTextNomeCategoria); // Ajuste conforme o ID correto do título na Toolbar
        textViewNomeCategoria.setTextColor(Color.BLACK);
        textViewNomeCategoria.setText(nomeCategoria);
        subcategoriasLayout = findViewById(R.id.principal1);
        exibirSubcategorias();

        Button btnExcluirCategoria = findViewById(R.id.btnExcluirCategoria);
        btnExcluirCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                confirmarExclusaoCategoria();
            }
        });
    }

    private void confirmarExclusaoCategoria() {
        new AlertDialog.Builder(this)
                .setTitle("Excluir Categoria")
                .setMessage("Você tem certeza que deseja excluir esta categoria?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluirCategoria();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    private void excluirCategoria() {
        Categoria categoria = new Categoria(idCategoria, nomeCategoria);
        List<SubCategoria> subCategorias = subCategoriaController.buscarSubCategorias(idCategoria);

        // Excluir todas as subcategorias associadas à categoria
        for (SubCategoria subCategoria : subCategorias) {
            subCategoriaController.deletarSubCategoria(subCategoria, categoria);
        }

        // Excluir a categoria
        categoriaController.deletarCategoria(categoria);

        Toast.makeText(this, "Categoria excluída com sucesso", Toast.LENGTH_SHORT).show();

        finish();
    }

    public void adicionarSubcategoria(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Digite o nome da subcategoria:");

        // Configura o campo de entrada de texto
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Configura os botões OK e Cancelar
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nomeSubcategoria = input.getText().toString().trim();
                SubCategoria novaSubcategoria = new SubCategoria(proximoIdSubcategoria++, 0, nomeSubcategoria);
                subcategorias.add(novaSubcategoria);
                exibirSubcategorias();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Mostra o AlertDialog
        builder.show();
    }

    private void exibirSubcategorias() {
        subcategoriasLayout.removeAllViews(); // Limpa o layout antes de adicionar as subcategorias

        for (SubCategoria subcategoria : subcategorias) {
            RelativeLayout itemLayout = new RelativeLayout(this);
            itemLayout.setPadding(0, 16, 0, 16);

            TextView textView = new TextView(this);
            textView.setText("" + subcategoria.getIdSubcategoria() + " | Subcategoria: " + subcategoria.getNome());
            textView.setPadding(16, 16, 16, 16);
            textView.setTextSize(18);
            textView.setBackgroundResource(R.drawable.border);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(32, 0, 0, 0); // Ajuste aqui a margem esquerda desejada
            textView.setLayoutParams(params);// Define a borda personalizada

            Button deleteButton = new Button(this);
            deleteButton.setText("Excluir");
            deleteButton.setTextColor(Color.RED);
            deleteButton.setBackgroundResource(R.drawable.border);


            RelativeLayout.LayoutParams deleteParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            deleteParams.addRule(RelativeLayout.ALIGN_PARENT_END); // Alinha à direita
            deleteParams.addRule(RelativeLayout.CENTER_VERTICAL); // Centraliza verticalmente
            deleteParams.setMargins(0, 0, 32, 0); // Ajuste de margem
            deleteButton.setLayoutParams(deleteParams);

            // Define um identificador único para o botão de exclusão
            deleteButton.setId(View.generateViewId());
            // Define o clique do botão de exclusão
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Remove a subcategoria da lista
                    subcategorias.remove(subcategoria);
                    // Atualiza a exibição das subcategorias
                    exibirSubcategorias();
                }
            });

            // Adiciona TextView e Button ao itemLayout
            itemLayout.addView(textView);
            itemLayout.addView(deleteButton);

            // Adiciona o itemLayout ao subcategoriasLayout
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            if (subcategoriasLayout.getChildCount() > 0) {
                layoutParams.addRule(RelativeLayout.BELOW, subcategoriasLayout.getChildAt(subcategoriasLayout.getChildCount() - 1).getId());
            }
            itemLayout.setLayoutParams(layoutParams);
            itemLayout.setId(View.generateViewId());
            subcategoriasLayout.addView(itemLayout);
        }
    }
}
