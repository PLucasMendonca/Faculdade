#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
  char nome[100];
  unsigned int idade;
} t_pessoa;

typedef struct No {
  t_pessoa pessoa;
  struct No *prox;
} t_no;

typedef struct {
  t_no *primeiro;
  int tamanho;
} t_lista;

t_lista hash[127] = {{NULL, 0}}; // inicializa todos com NULL e 0.

t_pessoa *proxima_pessoa(FILE *input) {
  t_pessoa *pessoa = malloc(sizeof(t_pessoa));
  if (feof(input) || pessoa == NULL)
    return NULL;

  fscanf(input, "%s", pessoa->nome);
  fscanf(input, "%d\n", &pessoa->idade);

  return pessoa;
}

void exibir_pessoa(t_pessoa *pessoa) {
  printf("(nome: %s, idade: %d)\n", pessoa->nome, pessoa->idade);
}

int calcular_indice_hash(char *nome) {
  // Utilize a primeira letra do nome para calcular o índice da tabela hash
  int indice = nome[0] % 127;
  return indice;
}

void inserir_tabela_hash(t_pessoa *pessoa) {
  int indice = calcular_indice_hash(pessoa->nome);
  t_no *novo_no = malloc(sizeof(t_no));
  novo_no->pessoa = *pessoa;
  novo_no->prox = NULL;

  if (hash[indice].primeiro == NULL) {
    // A lista está vazia, insere o primeiro nó
    hash[indice].primeiro = novo_no;
  } else {
    // Insere o novo nó no início da lista
    novo_no->prox = hash[indice].primeiro;
    hash[indice].primeiro = novo_no;
  }

  hash[indice].tamanho++;
}

void exibir_tabela_hash() {
  printf("\nConteúdo da tabela hash:\n");
  for (int i = 0; i < 127; i++) {
    if (hash[i].tamanho > 0) {
      printf("Hash[%d]: ", i);
      t_no *atual = hash[i].primeiro;
      while (atual != NULL) {
        printf("(nome: %s, idade: %d) ", atual->pessoa.nome, atual->pessoa.idade);
        atual = atual->prox;
      }
      printf("\n");
    }
  }
}

int main(void) {

  FILE *input = fopen("./pessoas.txt", "r");
  t_pessoa *pessoa = NULL;
  while ((pessoa = proxima_pessoa(input)) != NULL) {
    exibir_pessoa(pessoa);
    inserir_tabela_hash(pessoa);
  }

  fclose(input);

  exibir_tabela_hash();

  return 0;
}
