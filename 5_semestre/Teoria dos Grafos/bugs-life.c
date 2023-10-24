#include <stdio.h>

typedef struct {
    int visitado;
    int cor;
    int tam_lista_adj;
    int lista_adj[2001]; // Tamanho máximo para o número de insetos (2000 + 1)
} Vertice;

int dfs(Vertice *vertices, int qtd_vertices, int raiz, int cor) {
    int i;

    vertices[raiz].visitado = 1;
    vertices[raiz].cor = cor;

    for (i = 0; i < vertices[raiz].tam_lista_adj; i++) {
        int vizinho = vertices[raiz].lista_adj[i];

        if (vertices[vizinho].visitado == 0) {
            if (!dfs(vertices, qtd_vertices, vizinho, cor * -1)) {
                return 0;
            }
        } else {
            if (vertices[raiz].cor == vertices[vizinho].cor)
                return 0;
        }
    }
    return 1;
}

int main() {
    int num_scenarios, i;

    scanf("%d", &num_scenarios);

    for (i = 0; i < num_scenarios; i++) {
        int num_bugs, num_interactions, j;

        scanf("%d %d", &num_bugs, &num_interactions);

        Vertice bugs[num_bugs + 1];

        for (j = 1; j <= num_bugs; j++) {
            bugs[j].visitado = 0;
            bugs[j].cor = 0;
            bugs[j].tam_lista_adj = 0;
        }

        for (j = 0; j < num_interactions; j++) {
            int bug1, bug2;

            scanf("%d %d", &bug1, &bug2);

            bugs[bug1].lista_adj[bugs[bug1].tam_lista_adj++] = bug2;
            bugs[bug2].lista_adj[bugs[bug2].tam_lista_adj++] = bug1;
        }

        int is_consistent = 1;

        for (j = 1; j <= num_bugs; j++) {
            if (!bugs[j].visitado) {
                if (!dfs(bugs, num_bugs, j, 1)) {
                    is_consistent = 0;
                    break;
                }
            }
        }
        printf("Cenário #%d:\n", i + 1);
        if (is_consistent) {
            printf("Nenhum bug suspeito encontrado!\n");
        } else {
            printf("Bugs suspeitos encontrados!\n"); // 
        }
    }

    return 0;
}
