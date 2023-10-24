#define MAX 100

// dado que esta sendo guardado na lista
struct aluno{
  int matricula;
  char nome[30];
  float n1,n2,n3;
};
 // dando o nome pra lista
typedef struct lista Lista;

// Serve para criar a lista
Lista* cria_lista();
