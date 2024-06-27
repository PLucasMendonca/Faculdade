package com.example.lca;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import com.example.lca.controller.ItemController;
import com.example.lca.controller.ListaController;
import com.example.lca.model.Item;
import com.example.lca.model.Lista;

import java.util.List;

public class tela3 extends AppCompatActivity implements MyDialogFragment.OnItemSavedListener{
    private Lista listaSelecionada;
    private RelativeLayout layoutItens;
    private Context context;
    private ItemController itemController;
    private int idLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela3);

        ImageView imageView = findViewById(R.id.imageView3);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir a MainActivity
                Intent intent = new Intent(tela3.this, MainActivity.class);
                startActivity(intent);
                finish(); // Opcional: encerrar a tela atual (tela3)
            }
        });

        context = this;
        layoutItens = findViewById(R.id.itens);
        itemController = new ItemController(context);

        Intent intent = getIntent();
        int idLista = intent.getIntExtra("idLista", -1);

        ListaController listaController = new ListaController(context);
        List<Lista> listas = listaController.listarListas();

        for (Lista lista : listas) {
            if (lista.getIdLista() == idLista) {
                listaSelecionada = lista;
                break;
            }
        }

        if (listaSelecionada != null) {
            ImageButton bAddItem = findViewById(R.id.bAddItem);
            bAddItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Abre o diálogo para adicionar novo item
                    abrirDialogAdicionarItem();
                }
            });

            exibirTituloLista();
            exibirItens(); // Exibe os itens iniciais da lista
        } else {
            Toast.makeText(this, "Lista selecionada não encontrada", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para abrir o diálogo para adicionar um novo item
    private void abrirDialogAdicionarItem() {
        MyDialogFragment dialog = new MyDialogFragment();
        dialog.setOnItemSavedListener(this);
        Bundle args = new Bundle();
        args.putSerializable("lista", listaSelecionada);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "MyDialogFragment");
    }

    // Método para exibir o título da lista
    private void exibirTituloLista() {
        TextView textViewTituloLista = findViewById(R.id.tituloLista);
        textViewTituloLista.setText(listaSelecionada.getNome());
    }

    // Método para exibir os itens da lista
    private void exibirItens() {
        layoutItens.removeAllViews(); // Limpa a exibição atual dos itens

        List<Item> items = itemController.buscarItens(idLista);
        if (items != null && !items.isEmpty()) {
            for (Item item : items) {
                RelativeLayout itemContainer = criarLayoutItem(item);
                layoutItens.addView(itemContainer);
            }
        } else {
            Toast.makeText(this, "A lista de itens está vazia", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para criar o layout de um item
    private RelativeLayout criarLayoutItem(Item item) {
        RelativeLayout itemContainer = new RelativeLayout(context);
        RelativeLayout.LayoutParams containerParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        containerParams.setMargins(16, 35, 16, 0);
        itemContainer.setLayoutParams(containerParams);
        itemContainer.setBackgroundResource(R.drawable.border);

        TextView textViewItem = new TextView(context);
        RelativeLayout.LayoutParams paramsTextView = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        paramsTextView.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        paramsTextView.addRule(RelativeLayout.ALIGN_PARENT_START);
        paramsTextView.setMargins(40, 16, 16, 0);
        textViewItem.setLayoutParams(paramsTextView);
        textViewItem.setTextSize(30);
        textViewItem.setTextColor(Color.BLUE);
        textViewItem.setId(ViewCompat.generateViewId());
        textViewItem.setText(item.getNome());
        textViewItem.setTypeface(null, Typeface.BOLD);

        itemContainer.addView(textViewItem);

        ImageButton bEditar = new ImageButton(context);
        RelativeLayout.LayoutParams btnParams = new RelativeLayout.LayoutParams(
                170,
                170
        );
        btnParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        btnParams.addRule(RelativeLayout.ALIGN_PARENT_END);
        bEditar.setLayoutParams(btnParams);
        bEditar.setImageResource(R.drawable.lapiz); // Substitua pelo seu ícone de editar
        bEditar.setBackgroundColor(Color.TRANSPARENT);
        bEditar.setScaleType(ImageView.ScaleType.FIT_CENTER);
        bEditar.setOnClickListener(v -> abrirDialogEditarItem(item));

        itemContainer.addView(bEditar);

        // Adicione aqui os outros elementos do item, como descrição, categoria, etc.

        return itemContainer;
    }

    // Método para abrir o diálogo para editar um item existente
    private void abrirDialogEditarItem(Item item) {
        MyDialogFragment dialog = new MyDialogFragment();
        dialog.setOnItemSavedListener(this);
        Bundle args = new Bundle();
        args.putSerializable("lista", listaSelecionada);
        args.putSerializable("item", item);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "MyDialogFragment");
    }
    @Override
    public void onItemSaved() {
        // Atualiza a lista de itens após salvar o item
        exibirItens();
    }


}
