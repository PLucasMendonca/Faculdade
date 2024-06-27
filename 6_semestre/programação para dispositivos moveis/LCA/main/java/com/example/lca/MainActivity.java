package com.example.lca;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.MenuItem;


import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import java.util.List;

import com.example.lca.controller.ListaController;
import com.example.lca.db.Conexao;
import com.example.lca.model.Lista;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int MENU_CATEGORIA = R.id.action_categoria;
    private static final int MENU_EXCLUIR_LISTA = R.id.action_excluir_lista;
    private List<Lista> array;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Conexao conexao = new Conexao(this);
        SQLiteDatabase db = conexao.getWritableDatabase();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Context context = this;
        ListaController control = new ListaController(context);
        array = control.listarListas();
        if (array != null) {
            // Se houver itens na lista, criar cards para cada item
            criarBotoesLista();
        }
        ImageButton btnEngrenagem = findViewById(R.id.bEngrangem);
        btnEngrenagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });

    }

    @Override
    protected void onResume(){
        super.onResume();

        Context context = this;
        ListaController control = new ListaController(context);
        array = control.listarListas();
        if (array != null) {
            criarBotoesLista();
        }
    }

    private void showPopupMenu(View view){
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == MENU_CATEGORIA) {
                    Intent intent = new Intent(MainActivity.this, CategoriaTela.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == MENU_EXCLUIR_LISTA) {
                    confirmarExclusaoLista();
                    return true;
                }
                return false;
            }
        });
        popupMenu.show();
    }
    private void confirmarExclusaoLista() {
        if (array == null || array.isEmpty()) {
            Toast.makeText(this, "Nenhuma lista para excluir", Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecione a lista para excluir");

        String[] listaNomes = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            listaNomes[i] = array.get(i).getNome();
        }

        builder.setItems(listaNomes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Lista listaParaExcluir = array.get(which);
                excluirLista(listaParaExcluir);
            }
        });

        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }
    private void excluirLista(Lista lista) {
        ListaController control = new ListaController(this);
        control.excluirLista(lista);

        // Atualiza a lista após exclusão
        array = control.listarListas();

        // Atualiza a UI
        if (array != null) {
            criarBotoesLista();
        }

        Toast.makeText(MainActivity.this, "Lista excluída", Toast.LENGTH_SHORT).show();
    }

    private void criarBotoesLista() {
        if(array == null || array.isEmpty()) {
            return;
        }
        // Obtém o layout onde você deseja adicionar os cards
        RelativeLayout layout = findViewById(R.id.principal);
        layout.removeAllViews();

        // Itera sobre os itens da lista
        for (int i = 0; i < array.size(); i++) {
            final int idListaSelecionada = array.get(i).getIdLista();
            // Obtém o item da lista na posição i
            Lista lista = array.get(i);

            CardView cardView = new CardView(this);
            RelativeLayout.LayoutParams cardLayoutParams = new RelativeLayout.LayoutParams(
                    900,
                    150
            );
            // Define as regras de posicionamento do CardView
            cardLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

            // Verifica se há uma descrição e se não está vazia
            if (!TextUtils.isEmpty(lista.getDescricao())) {
                // Se houver descrição e não estiver vazia, define a altura como WRAP_CONTENT
                cardLayoutParams.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
            }

            // Define as regras de posicionamento do CardView
            if (i == 0) {
                cardLayoutParams.addRule(RelativeLayout.BELOW, R.id.toolbarTela1);
            } else {
                cardLayoutParams.addRule(RelativeLayout.BELOW, layout.getChildAt(i - 1).getId());
            }

            // Adiciona margens ao CardView
            cardLayoutParams.setMargins(16, 30, 16, 16);
            cardView.setLayoutParams(cardLayoutParams);
            cardView.setRadius(15);
            cardView.setCardElevation(10);
            cardView.setPadding(16, 16, 16, 16);
            cardView.setId(View.generateViewId());

            cardView.setBackgroundResource(R.drawable.border);

            // Cria um layout para o conteúdo do CardView
            RelativeLayout cardContentLayout = new RelativeLayout(this);
            RelativeLayout.LayoutParams contentLayoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            cardContentLayout.setLayoutParams(contentLayoutParams);

            // Adiciona um TextView ao CardView para o título
            TextView title = new TextView(this);
            RelativeLayout.LayoutParams titleLayoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            title.setLayoutParams(titleLayoutParams);
            title.setText(lista.getNome());
            title.setTextSize(22);
            title.setTextColor(getResources().getColor(android.R.color.black));
            title.setPadding(160, 20, 0, 0);
            title.setTextColor(getResources().getColor(R.color.blue)); // Altera a cor do texto
            title.setTypeface(null, Typeface.BOLD); // Aplica o estilo bold
            title.setSingleLine(true); // Define para uma única linha
            title.setEllipsize(TextUtils.TruncateAt.END); // Adiciona reticências no final
            title.setId(View.generateViewId());

            // Adiciona um TextView ao CardView para a descrição
            TextView description = new TextView(this);
            RelativeLayout.LayoutParams descriptionLayoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            descriptionLayoutParams.addRule(RelativeLayout.BELOW, title.getId());
            descriptionLayoutParams.setMargins(160, 0, 0, 0);
            description.setLayoutParams(descriptionLayoutParams);
            description.setText(lista.getDescricao());
            description.setTextSize(17);
            description.setTextColor(getResources().getColor(android.R.color.holo_blue_light));
            description.setPadding(0,8,0,0);

            // Adiciona um ImageView ao CardView (opcional)
            ImageView imageView = new ImageView(this);
            RelativeLayout.LayoutParams imageLayoutParams = new RelativeLayout.LayoutParams(
                    150,
                    200
            );
            imageLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
            imageLayoutParams.setMargins(15, -10, 0, 0);
            imageView.setLayoutParams(imageLayoutParams);
            imageView.setImageResource(R.drawable.prancheta); // Substitua com sua imagem

            // Adiciona os elementos ao layout do CardView
            cardContentLayout.addView(title);
            cardContentLayout.addView(description);
            cardContentLayout.addView(imageView);

            // Adiciona o layout de conteúdo ao CardView
            cardView.addView(cardContentLayout);

            // Adiciona o CardView ao layout principal
            layout.addView(cardView);

            // Define um listener de clique para o CardView
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(MainActivity.this, tela3.class);
                    in.putExtra("idLista", idListaSelecionada);
                    startActivity(in);
                }
            });
        }
    }
    public void MainActivity(View view) {
        // Implementação do método aqui
        // Por exemplo:
        Toast.makeText(this, "ImageButton clicado!", Toast.LENGTH_SHORT).show();
    }


    public void tela2(View view) {
        Intent in = new Intent(MainActivity.this, tela2.class);
        startActivity(in);
    }
}

