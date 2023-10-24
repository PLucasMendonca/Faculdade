/*
Baseado no modelo de lista encadeada aprendido em sala,
1. Implemente a seguinte estrutura de dados:
a. Fila: todas as inclusões são feitas numa extremidade e as exclusões em
outra. Sempre desenfileiramos o primeiro item enfileirado. Esse sistema é
chamado de FIFO. (First In First Out).

Considere a seguinte abstração de fila:
typedef struct no {
int info; // elemento da lista
struct no *prox;
} t_no;
typedef struct lista {
t_no *primeiro;
t_no *ultimo;
} t_fila;
Implemente as seguintes operações:
enfileira: void enfileira(t_fila *fila, int item) - que insere um nó no fim
da lista.
desenfileira: t_no *desenfileira(t_fila *fila) - que retira um nó início da
lista e o retorna.

b. Pilha: Todas as inclusões e exclusões de nós são feitos por uma única
extremidade, o topo. Sempre desempilhamos primeiro o último nó
empilhado. Esse sistema é chamado de FILO. (First In Last Out)
Considere a seguinte abstração de pilha:

typedef struct no {
int info; // elemento da lista
struct no *prox;
} t_no;
typedef struct lista {
t_no *topo;
} t_pilha;

Implemente as seguintes operações:
empilha: void empilha(t_pilha *pilha, int item) - que insere um nó no
topo da lista.
desempilha: t_no *desempilha(t_pilha *pilha) - que retira um nó do
topo da lista e o retorna.
topo: t_no *topo(t_pilha pilha) - que retorna o nó que está no topo.

*/
#include <stdio.h>
#include <stdlib.h>
typedef struct no {
int info;
struct no *prox;
} t_no;
typedef struct lista {
t_no *primeiro;
t_no *ultimo;
} t_fila;
void ordem(t_fila *fila, int item) {
t_no *novo_no = (t_no*) malloc(sizeof(t_no));
novo_no->info = item;
novo_no->prox = NULL;
if (fila->primeiro == NULL) {
fila->primeiro = novo_no;
} else {
fila->ultimo->prox = novo_no;
}
fila->ultimo = novo_no;
printf("Ordem da fila: %d\n", novo_no->info);
}
t_no *desenfileira(t_fila *fila) {
t_no *no_para_deletar = fila->primeiro;
if (no_para_deletar != NULL) {
fila->primeiro = no_para_deletar->prox;
printf("Mudando a fila: %d\n", no_para_deletar->info);
free(no_para_deletar);
}
return no_para_deletar;
}
int main() {
t_fila lista = {NULL, NULL};
ordem(&lista, 8);
ordem(&lista, 1);
ordem(&lista, 4);
ordem(&lista, 3);
desenfileira(&lista);
desenfileira(&lista);
desenfileira(&lista);
return 0;
}
//B) Para pilhas:
#include <stdio.h>
#include <stdlib.h>
typedef struct no {
int info;
struct no *prox;
} t_no;
typedef struct lista {
t_no *topo;
} t_pilha;
void empilha(t_pilha *pilha, int item) {
t_no *novo_no = (t_no*) malloc(sizeof(t_no));

novo_no->info = item;
novo_no->prox = pilha->topo;
pilha->topo = novo_no;
}
t_no *desempilha(t_pilha *pilha) {
if (pilha->topo == NULL) {
printf("Pilha vazia.\n");
return NULL;
}
t_no *no_deletado = pilha->topo;
pilha->topo = pilha->topo->prox;
printf("No removido: %d\n", no_deletado->info);
free(no_deletado);
return no_deletado;
}
t_no *topo(t_pilha pilha) {
if (pilha.topo == NULL) {
printf("Pilha vazia.\n");
return NULL;
}
printf("No do topo: %d\n", pilha.topo->info);
return pilha.topo;
}
int main() {
t_pilha nova_pilha;
nova_pilha.topo = NULL;
empilha(&nova_pilha, 10);
topo(nova_pilha);
empilha(&nova_pilha, 2);
topo(nova_pilha);
empilha(&nova_pilha, 8);
topo(nova_pilha);
desempilha(&nova_pilha);
topo(nova_pilha);
desempilha(&nova_pilha);
topo(nova_pilha);
desempilha(&nova_pilha);
topo(nova_pilha);
return 0;
}