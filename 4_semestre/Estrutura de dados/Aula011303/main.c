#include <stdio.h>
#include "Ponto.h"

int main(void) {
  float d;
  Ponto *p, *q;
  p = Ponto_cria(10, 21);
  q = Ponto_cria(7, 25);
  // q->x = 2; // ERRO
  d = Ponto_distancia(p, q);
  printf("Distancia entre dois pontos: %f\n", d);
  Ponto_libera(p);
  Ponto_libera(q);
  
  return 0;
}