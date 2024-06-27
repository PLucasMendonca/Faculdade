package com.example.lca;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lca.model.Lista;
import com.example.lca.model.Item;
import com.example.lca.model.SubCategoria;
import com.example.lca.model.Categoria;
import com.example.lca.controller.CategoriaController;
import com.example.lca.controller.SubCategoriaController;
import com.example.lca.controller.ListaController;
import com.example.lca.controller.ItemController;

import java.util.ArrayList;
import java.util.List;

public class MyDialogFragment extends DialogFragment {
    private EditText editText;
    private List<Item> itens = new ArrayList<>();
    private EditText descricaoItem;
    private EditText dateItem;
    private EditText timeItem;
    private EditText valorItem;
    private Spinner categoriaItem;
    private Spinner subcategoriaItem;
    private Item item;
    private Lista lista;
    private OnItemSavedListener onItemSavedListener;

    // Interface para callback quando um item é salvo
    public interface OnItemSavedListener {
        void onItemSaved();
    }

    public void setOnItemSavedListener(OnItemSavedListener listener) {
        this.onItemSavedListener = listener;
    }

    @SuppressLint("WrongViewCast")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_layout_item, null);

        ImageButton bDisk = view.findViewById(R.id.bDisk);
        ImageButton bLixeira = view.findViewById(R.id.bLixeira);
        ImageButton bX = view.findViewById(R.id.bX);
        editText = view.findViewById(R.id.edit_text);
        descricaoItem = view.findViewById(R.id.descricao_item);
        categoriaItem = view.findViewById(R.id.categoria_item);
        subcategoriaItem = view.findViewById(R.id.subcategoria_item);
        dateItem = view.findViewById(R.id.date_item);
        timeItem = view.findViewById(R.id.time_item);
        valorItem = view.findViewById(R.id.valor_item);
        Bundle args = getArguments();

        if(args !=null) {
            lista = (Lista) args.getSerializable("lista");

            if (lista != null) {
                setFieldVisibility(lista);
                setupSpinners(lista);
            }
        }
        bDisk.setOnClickListener(v -> {
            if(camposValidos()) {
                salvarItem();
                dismiss();
            }else{
                Toast.makeText(getContext(), "Preencha todos os campos necessários.", Toast.LENGTH_SHORT).show();
            }
        });

        bLixeira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
            //função deletarItem(id)
        });

        bX.setOnClickListener(v -> {
            dismiss();
        });
        adicionarItensNaLista(itens);

        builder.setView(view);

        return builder.create();
    }
    private void adicionarItensNaLista(List<Item> itens){


    }
    private boolean camposValidos() {
        String nome = editText.getText().toString().trim();
        String descricao = descricaoItem.getText().toString().trim();
        String date = dateItem.getText().toString().trim();
        String time = timeItem.getText().toString().trim();
        String valor = valorItem.getText().toString().trim();

        // Verifique se o nome do item está preenchido
        if (nome.isEmpty()) {
            editText.setError("Nome do item é obrigatório.");
            return false;
        }
        return true;
    }


    private void salvarItem(){
        String nome = editText.getText().toString();
        String descricao = descricaoItem.getText().toString();
        String date = dateItem.getText().toString();
        String time = timeItem.getText().toString();
        double valor = 0;
        String val = valorItem.getText().toString();
        if (!val.isEmpty()) {
            valor = Double.parseDouble(val);
        }
        // Aqui você deve implementar a lógica para salvar o item no banco de dados
        if (lista != null) {
            Context context = getActivity();
            try (ItemController controller = new ItemController(context)) {
               item = controller.inserirItem(
                       lista.getIdLista(), nome, descricao, false, 0, 0, date, time, valor);
                if (onItemSavedListener != null) {
                    onItemSavedListener.onItemSaved();
                }
            } catch (Exception e) {
                // Trate qualquer exceção que possa ocorrer ao usar o ItemController
                e.printStackTrace();
                // Ou lance a exceção novamente, dependendo do seu fluxo de exceção
            }
        }
    }

    private void setFieldVisibility(Lista lista) {
        descricaoItem.setVisibility(lista.getLoDescricaoItem() ? View.VISIBLE : View.GONE);
        categoriaItem.setVisibility(lista.getLoCategoriaItem() ? View.VISIBLE : View.GONE);
        subcategoriaItem.setVisibility(lista.getLoSubCategoriaItem() ? View.VISIBLE : View.GONE);
        dateItem.setVisibility(lista.getLoDateItem() ? View.VISIBLE : View.GONE);
        timeItem.setVisibility(lista.getLoTimeItem() ? View.VISIBLE : View.GONE);
        valorItem.setVisibility(lista.getLoValorItem() ? View.VISIBLE : View.GONE);
    }

    private void setupSpinners(Lista lista) {
        Context context = requireContext();

        if (lista.getLoCategoriaItem()) {
            // Configurar o Spinner de Categoria (se necessário)
            List<Categoria> categorias = new CategoriaController(context).buscarCategorias();
            ArrayAdapter<Categoria> categoriaAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, categorias);
            categoriaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categoriaItem.setAdapter(categoriaAdapter);
        } else {
            categoriaItem.setVisibility(View.GONE);
        }

        if (lista.getLoSubCategoriaItem()) {
            // Adicionar uma subcategoria fixa para teste
            List<SubCategoria> subcategorias = new ArrayList<>();
            subcategorias.add(new SubCategoria(1, 1, "Subcategoria Teste")); // Substitua os parâmetros pelos valores desejados

            ArrayAdapter<SubCategoria> subcategoriaAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, subcategorias);
            subcategoriaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            subcategoriaItem.setAdapter(subcategoriaAdapter);
        } else {
            subcategoriaItem.setVisibility(View.GONE);
        }
    }

    private Item encontrarItemPorId(int itemId){
        for (Item item : this.itens){
            if(item.getIdItem() == itemId){
                return item;
            }
        }
        return null;
    }

    private int getIndex(Spinner spinner, String value){
        for (int i = 0;i < spinner.getCount(); i++){
            if(spinner.getItemAtPosition(i).equals(value)){
                return i;
            }
        }
        return 0;
    }

}
