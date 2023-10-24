#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <limits.h>

#define MAX_N 10005
#define MAX_M 100005

typedef struct {
    int vertice;
    struct No* proximo;
} No;

typedef struct {
    No* topo;
} Lista;

typedef struct {
    int ida;
    int chegada;
} Aresta;

typedef struct {
    int inicio;
    int fim;
    int tam;
    unsigned tam_max_fila;
    int* arm_elem;
} Fila;

Lista grafo[MAX_N];
bool visitado[MAX_N];

Fila* criarFila(unsigned tam_max_fila) {
    Fila* fila = (Fila*)malloc(sizeof(Fila));
    fila->tam_max_fila = tam_max_fila;
    fila->inicio = fila->tam = 0;
    fila->fim = tam_max_fila - 1;
    fila->arm_elem = (int*)malloc(fila->tam_max_fila * sizeof(int));
    return fila;
}

void enfileirar(Fila* fila, int elemento) {
    if (fila->tam == fila->tam_max_fila) return;
    fila->fim = (fila->fim + 1) % fila->tam_max_fila;
    fila->arm_elem[fila->fim] = elemento;
    fila->tam++;
}

int desenfileirar(Fila* fila) {
    if (fila->tam == 0) return -1;
    int elemento = fila->arm_elem[fila->inicio];
    fila->inicio = (fila->inicio + 1) % fila->tam_max_fila;
    fila->tam--;
    return elemento;
}

void adicionarAresta(int de, int para) {
    No* novoNo = (No*)malloc(sizeof(No));
    novoNo->vertice = para;
    novoNo->proximo = NULL;
    if (grafo[de].topo == NULL) {
        grafo[de].topo = novoNo;
    } else {
        No* atual = grafo[de].topo;
        while (atual->proximo != NULL) {
            atual = atual->proximo;
        }
        atual->proximo = novoNo;
    }
}

int bfs(int inicio, int fim, int n) {
    for (int i = 1; i <= n; i++) {
        visitado[i] = false;
    }

    Fila* fila = criarFila(n);
    enfileirar(fila, inicio);
    visitado[inicio] = true;
    int distancia = 0;

    while (fila->tam > 0) {
        int tamanhoFila = fila->tam;
        for (int i = 0; i < tamanhoFila; i++) {
            int atual = desenfileirar(fila);
            if (atual == fim) {
                free(fila->arm_elem);
                free(fila);
                return distancia;
            }

            No* vizinho = grafo[atual].topo;
            while (vizinho != NULL) {
                if (!visitado[vizinho->vertice]) {
                    enfileirar(fila, vizinho->vertice);
                    visitado[vizinho->vertice] = true;
                }
                vizinho = vizinho->proximo;
            }
        }
        distancia++;
    }

    free(fila->arm_elem);
    free(fila);
    return -1;
}

int main() {
    int T;
    scanf("%d", &T);

    while (T--) {
        int N, M;
        scanf("%d %d", &N, &M);

        for (int i = 1; i <= N; i++) {
            grafo[i].topo = NULL;
        }

        Aresta arestas[MAX_M];
        for (int i = 0; i < M; i++) {
            int X, Y;
            scanf("%d %d", &X, &Y);
            adicionarAresta(X, Y);
            adicionarAresta(Y, X);
            arestas[i].ida = X;
            arestas[i].chegada = Y;
        }

        int movimentosMinimos = bfs(1, N, N);
        printf("%d\n", movimentosMinimos);
    }

    return 0;
}
