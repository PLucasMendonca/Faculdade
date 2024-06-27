/*
package com.example.lca;

import java.util.ArrayList;
import java.util.List;
import com.example.lca.model.Item;
import com.example.lca.model.Lista;

public class TesteArray {

    public List<Lista> testarArray() {
        // Criar uma lista de objetos Lista
        List<Lista> listas = new ArrayList<>();

        // Criar uma lista de itens para cada lista
        List<Item> itens1 = criarItensExemplo();
        List<Item> itens2 = criarItensExemplo();


        // Adicionar alguns dados de exemplo à lista
        listas.add(new Lista(1, "Nome Título 1", true, "Descrição 1", true, true, true, true, true, true, true, itens1));
        listas.add(new Lista(2, "Nome Título Teste para o nome grande da lista", false, "", false, false, true, false, false, true, false, itens2 ));

        return listas;
    }

    private List<Item> criarItensExemplo() {
        List<Item> itens = new ArrayList<>();

        // Criar itens de exemplo e adicioná-los à lista
        itens.add(new Item(1, 1, "TecoTeco", "Uma deliciosa maçã", false, 1, "Categoria A", 1, "Subcategoria A1", "2023-05-27", "10:30", 1.0));
        itens.add(new Item(2, 1, "Banana", "Uma madura banana", true, 1, "Categoria A", 1, "Subcategoria A1", null, "11:00", 0.5));
        itens.add(new Item(3, 1, "Pera", "Uma suculenta pera", false, 1, "Categoria A", 1, "Subcategoria A1", "2023-05-29", null, 1.2));
        itens.add(new Item(1, 3, "Salada de Frutas", "Uma suculenta salada de frutas", true, 1, "Categoria A", 1, "Subcategoria A1", "2023-05-30", "08:30", 3.5));

        return itens;
    }

    public static void main(String[] args) {
        TesteArray teste = new TesteArray();

        // Obter a lista de objetos Lista com os dados fornecidos, incluindo itens
        List<Lista> resultado = teste.testarArray();

        // Imprimir os resultados para verificar
        for (Lista lista : resultado) {
            System.out.println(lista);
        }
    }
}

 */