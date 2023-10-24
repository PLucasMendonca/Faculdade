/*
A) Implemente as seguintes funções: - void inserirOrd(int n, char arvore[n], char valor)
- void deletar_no(int n, char arvore[n], int indice)
-void inserirOrd(int n, char arvore[n], char valor) - Que realiza a inserção ordenada na árvore de acordo com as seguintes regras:
1) Se o nó atual (começando pela raiz) é vazio ‘\0’, realiza a inserção
2) Caso contrário, verifica se realizaremos a inserção à esquerda ou à direita:
2.1) Se o conteúdo do novo nó for menor que o conteúdo do nó atual, navegue para o nó filho à esquerda;
2.2) Senão, navegue para o nó filho à direita. 2.3) Caso o índice do nó filho à direita ou à esquerda for maior que o tamanho do vetor, exibe uma mensagem ao usuário: “Não é possível inserir esse valor!”

B) void deletar_no(int n, char arvore[n], int indice) - Que realiza a deleção de um nó da árvore na posição arvore[indice], atribuindo o valor ‘\0’ (vazio) a esse nó. Ao realizar a deleção, considere as seguintes regras:
1) Se o nó da árvore a ser deletado for um nó folha, realize a deleção apenas desse nó.
2) Caso contrário, sendo um nó interno, realize a deleção do nó interno, juntamente com todos os nós descendentes.
3) Se o índice passado como parâmetro for maior que o tamanho da árvore, apresente ao usuário, que o índice é inválido. Ilustração da função:

*/
#include <stdio.h>
#include <stdlib.h>

#define MAX 15


void inserirOrd(int n, char arvore[n], char valor);
void deletar_no(int n, char arvore[n], int indice);

void inserir(int n, char vet[n], char c);
char obtem_filho_esquerdo(int n, char vet[n], int i);
char obtem_filho_direito(int n, char vet[n], int i);
char obtem_no_pai(int n, char vet[n], int i);
int qtde_nos_arvore_minima(int altura);
int qtde_nos_arvore_maxima(int altura);

int main() {
  char vet[MAX] = {'\0'}; // inicializa todos os índices com '\0'
  
  inserirOrd(15, vet, 'D');
  inserirOrd(15, vet, 'B');
  inserirOrd(15, vet, 'F');
  inserirOrd(15, vet, 'A');
  inserirOrd(15, vet, 'C');
  inserirOrd(15, vet, 'E');
  inserirOrd(15, vet, 'G');
  inserirOrd(15, vet, 'L');
  deletar_no(15,vet,1);
  
  for (int i = 0; i <15; i++){
    if (vet[i] != '\0'){
      printf("%c ", vet[i]);
    }
  }
  return 0;
}
//A)
void inserirOrd(int n, char arvore[n], char valor){
  int i = 0;
  while (arvore[i] != '\0'){
    if (valor < arvore[i]){
      i = 2* i + 1;
    }else {
      i= 2*i +2;
    }
if (i>=n){
  printf("Não é possivel inserir esse valor!\n");
  return 0;
    }
  }
  arvore[i] = valor;
}
//B)
void deletar_no(int n, char arvore[n], int indice) {
    if (indice >= n) {
        printf("Índice inválido!\n");
        return;
    }

    if (arvore[indice] == '\0') {
        printf("O nó já está vazio!\n");
        return;
    }

    if (arvore[2 * indice + 1] == '\0' && arvore[2 * indice + 2] == '\0') {
        arvore[indice] = '\0';
        return;
    }

    arvore[indice] = '\0';
    deletar_no(n, arvore, 2 * indice + 1); // Deleta nó filho à esquerda
    deletar_no(n, arvore, 2 * indice + 2); // Deleta nó filho à direita
}

char obtem_filho_esquerdo(int n, char vet[n], int i) {
  if(i >= n) {
    printf("indice inválido!\n");
    return '\0';
  }
  int index = 2 *(i+1) - 1;
  return vet[index];
}
char obtem_filho_direito(int n, char vet[n], int i) {
  if(i >= n) {
    printf("indice inválido!\n");
    return '\0';
  }
  int index = 2 *(i+1);
  return vet[index];
}

char obtem_no_pai(int n, char vet[n], int i) {
  if(i >= n) {
    printf("indice inválido!\n");
    return '\0';
  }
  return vet[(i-1)/2];
}
int qtde_nos_arvore_minima(int altura) {
  return altura + 1;
}
int qtde_nos_arvore_maxima(int altura) {
  if(altura == 0) return 1;
  int potencia = 2;
  for(int i = 1; i < altura; i++) {
    potencia *= 2;
  }
  return potencia - 1;
}
