/*
Utilize o código de base em anexo, escreva o algoritmo que realize o preenchimento da tabela hash, ordenado com base nas iniciais dos caracteres de cada pessoa.
*/
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
  t_no *inicio;
  int tamanho;
} t_lista;

t_lista hash[127] = {{NULL, 0}}; // inicializa todos com NULL e 0.

t_pessoa *proxima_pessoa(FILE *input) {
  t_pessoa *pessoa = malloc(sizeof(t_pessoa));
  if(feof(input) || pessoa == NULL)
    return NULL;

  fscanf(input, "%s", pessoa->nome);
  fscanf(input, "%d\n", &pessoa->idade);

  return pessoa;
}

void exibir_pessoa(t_pessoa *pessoa) {
  printf("(nome: %s, idade: %d)\n", pessoa->nome, pessoa->idade);
}

void inserir(t_lista *lista, t_pessoa pessoa) {
  t_no *no = malloc(sizeof(t_no));
  if(no == NULL) {
    printf("Erro ao inserir!\n");
    return;
  }
  no->pessoa = pessoa;
  no->prox = NULL;
  if(lista->inicio == NULL)
    lista->inicio = no;
  else {
    if(lista->tamanho == 1) {
      strcmp(const char *s1, const char *s2)
    }
  }
}

int main(void) {

  FILE *input = fopen("./pessoas.txt", "r");
  t_pessoa *pessoa = NULL;
  while((pessoa = proxima_pessoa(input)) != NULL) {
    exibir_pessoa(pessoa);
    // inserir na tabela hash
    inserir(&hash[pessoa->nome[0]], *pessoa);
  }

  fclose(input);
  return 0;
}
t_lista hash[127] = {{NULL, 0}}; // inicializa todos com NULL e 0.

t_pessoa *proxima_pessoa(FILE *input) {
  t_pessoa *pessoa = malloc(sizeof(t_pessoa));
  if(feof(input) || pessoa == NULL)
    return NULL;

  fscanf(input, "%s", pessoa->nome);
  fscanf(input, "%d\n", &pessoa->idade);

  return pessoa;
}

void exibir_pessoa(t_pessoa *pessoa) {
  printf("(nome: %s, idade: %d)\n", pessoa->nome, pessoa->idade);
}

void inserir(t_lista *lista, t_pessoa pessoa) {
  t_no *no = malloc(sizeof(t_no));
  if(no == NULL) {
    printf("Erro ao inserir!\n");
    return;
  }
  no->pessoa = pessoa;
  no->prox = NULL;
  if(lista->inicio == NULL)
    lista->inicio = no;
  else {
    if(lista->tamanho == 1) {
      strcmp(const char *s1, const char *s2)
    }
  }
}

int main(void) {

  FILE *input = fopen("./pessoas.txt", "r");
  t_pessoa *pessoa = NULL;
  while((pessoa = proxima_pessoa(input)) != NULL) {
    exibir_pessoa(pessoa);
    // inserir na tabela hash
    inserir(&hash[pessoa->nome[0]], *pessoa);
  }

  fclose(input);
  return 0;
}
atividade-teste.txt
Exibindo atividade-teste.txt…
Exercício - Tabela Hash
Daniel da Silva Souza
•
19 de jun.
10 pontos
Utilize o código de base em anexo, escreva o algoritmo que realize o preenchimento da tabela hash, ordenado com base nas iniciais dos caracteres de cada pessoa.
Comentários da turma
Seus trabalhos
Entregue

atividade-teste.txt
Texto
Comentários particulares