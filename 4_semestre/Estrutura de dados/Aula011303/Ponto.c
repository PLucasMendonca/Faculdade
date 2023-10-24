#include <stdlib.h>
#include <math.h>
#include "Ponto.h" // inclui os protótipos

struct ponto {
  float x;
  float y;
};

// Criando um ponto
Ponto *Ponto_cria(float x, float y) {
  Ponto *p = malloc(sizeof(Ponto));
  if(p) { // p != NULL
    p->x = x;
    p->y = y;
  }
  return p;
}

// Destruindo um ponto
void Ponto_libera(Ponto *p) {
  free(p);
}

// Acessando conteúdo de um ponto
void Ponto_acessa(Ponto *p, float *x, float *y) {
  if(p) { // p != NULL
    *x = p->x;
    *y = p->y;
  }
}

// Atribuindo valor a um ponto
int Ponto_atribui(Ponto *p, float x, float y) {
  if(!p) // p == NULL
    return 0;
  // else p != NULL
  p->x = x;
  p->y = y;
  return 1;
}

// Calcular distancia de dois pontos
float Ponto_distancia(Ponto *p1, Ponto *p2) {
  if(p1 == NULL || p2 == NULL)
    return -1;
  float dx = p1->x - p2->x;
  float dy = p1->y - p2->y;
  return sqrt(dx*dx + dy*dy);
}