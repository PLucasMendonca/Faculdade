#include <stdlib.h>
#include <math.h>
#include "Numero.h"

struct num{
  double x;
};
Numero *Numero_cria(){
  Numero *num = malloc(sizeof(Numero));
  if (num != NULL){
    num -> x = 0;
  }
  return num;
}

void destruir_num(Numero *num){
  free(num);
}

void exibir_num(Numero *num){
  printf("%.f\n", num -> x);
}
void zerar_num(Numero *num){
  num -> x = 0;
}
void adiciona_num(Numero *num, double n){
  num -> x += n;
}
void sub_num(Numero *num, double n){
  num -> x -= n;
}
void mult_num(Numero *num, double n){
  num -> x *= n;
}
void div_num(Numero *num, double n){
  num -> x /= n;
}