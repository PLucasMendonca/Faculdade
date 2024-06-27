package com.example.lca;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView;
import com.example.lca.controller.CategoriaController;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.graphics.Typeface;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import com.example.lca.model.Categoria;

public class CategoriaTela extends AppCompatActivity {
    private List<Categoria> categoryArray;
    private RelativeLayout layout;
    private CategoriaController categoriaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);
        categoriaController = new CategoriaController(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.categoriaId), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        layout = findViewById(R.id.principal1);

        categoryArray = categoriaController.buscarCategorias();

        ImageButton btnAddCategoria = findViewById(R.id.bAddCategoria);
        btnAddCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCategoria();
            }
        });

        // Adicione esta linha para criar os botões de categoria ao carregar a tela
        criarBotoesCategoria();
    }

    private void criarBotoesCategoria() {
        layout.removeAllViews();

        for (int i = 0; i < categoryArray.size(); i++) {
            final int idCategoriaSelecionada = categoryArray.get(i).getIdCategoria();
            final String nomeCategoriaSelecionada = categoryArray.get(i).getNome();
            Categoria category = categoryArray.get(i);

            CardView cardView = new CardView(this);
            RelativeLayout.LayoutParams cardLayoutParams = new RelativeLayout.LayoutParams(
                    900,
                    150
            );
            cardLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            if (i == 0) {
                cardLayoutParams.addRule(RelativeLayout.BELOW, R.id.toolbarTela2);
            } else {
                cardLayoutParams.addRule(RelativeLayout.BELOW, layout.getChildAt(i - 1).getId());
            }

            cardLayoutParams.setMargins(16, 30, 16, 16);
            cardView.setLayoutParams(cardLayoutParams);
            cardView.setRadius(15);
            cardView.setCardElevation(10);
            cardView.setPadding(16, 16, 16, 16);
            cardView.setId(View.generateViewId());

            cardView.setBackgroundResource(R.drawable.border);

            RelativeLayout cardContentLayout = new RelativeLayout(this);
            RelativeLayout.LayoutParams contentLayoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            cardContentLayout.setLayoutParams(contentLayoutParams);

            TextView title = new TextView(this);
            RelativeLayout.LayoutParams titleLayoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            title.setLayoutParams(titleLayoutParams);
            title.setText(category.getNome());
            title.setTextSize(22);
            title.setTextColor(getResources().getColor(android.R.color.black));
            title.setPadding(160, 20, 0, 0);
            title.setTextColor(getResources().getColor(R.color.blue));
            title.setTypeface(null, Typeface.BOLD);
            title.setSingleLine(true);
            title.setEllipsize(TextUtils.TruncateAt.END);
            title.setId(View.generateViewId());

            ImageView imageView = new ImageView(this);
            RelativeLayout.LayoutParams imageLayoutParams = new RelativeLayout.LayoutParams(
                    150,
                    200
            );
            imageLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
            imageLayoutParams.setMargins(15, -10, 0, 0);
            imageView.setLayoutParams(imageLayoutParams);
            imageView.setImageResource(R.drawable.prancheta);

            cardContentLayout.addView(title);
            cardContentLayout.addView(imageView);

            cardView.addView(cardContentLayout);

            layout.addView(cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(CategoriaTela.this, SubCategoria1.class);
                    in.putExtra("idCategoria", idCategoriaSelecionada);
                    in.putExtra("nomeCategoria", nomeCategoriaSelecionada);
                    startActivity(in);
                }
            });
        }
    }

    private void dialogCategoria(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Digite o nome da categoria");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nomeCategoria = input.getText().toString().trim();
                if (!nomeCategoria.isEmpty()) {
                    Categoria novaCategoria = categoriaController.inserirCategoria(nomeCategoria);
                    if(novaCategoria != null) {
                        adicionarCategoria(novaCategoria);
                    }
                } else {
                    // Mostrar mensagem de erro
                }
            }
        });
        builder.setNegativeButton("Sair", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    private void adicionarCategoria(Categoria categoria){
        categoryArray.add(categoria);
        criarBotoesCategoria();
    }

    public void voltarTelaPrincipal(View view) {
        Intent in = new Intent(CategoriaTela.this, MainActivity.class);
        startActivity(in);
    }
}
