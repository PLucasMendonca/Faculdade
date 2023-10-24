/*
Crie uma calculadora 
*/
#include <stdio.h>
#include "Numero.h"

int main(void) {
Numero *teste;
  teste = Numero_cria();
  exibir_num(teste);
  adiciona_num(teste, 5);
  exibir_num(teste);
  mult_num(teste, 2);
  exibir_num(teste);
  div_num(teste,4);
  exibir_num(teste);
}