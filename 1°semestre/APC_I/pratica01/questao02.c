//02.Faça um programa em C que calcule a área de um triângulo (a = base x altura / 2). Considere base e altura números inteiros maior que 0.

#include <stdio.h>

int main() {
  int base = 15;
  int altura = 35;
  float a = ((base * altura )/ 2);

  printf("%f\n", a);
  return 0;

}