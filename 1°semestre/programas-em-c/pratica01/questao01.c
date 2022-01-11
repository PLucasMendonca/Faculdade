//01.Faça um programa em C que calcule a média final a partir da fórmula (0,4 x A1) + (0,6 x A2). Considere A1 e A2 números reais entre 0 a 10.

#include <stdio.h>

int main() {

float a1 = 5.0f ; 
float a2 = 4.5f;
float media = (a1 * 0.4) + (a2 * 0.6);

printf("%f\n" , media);

a1 = 10.0;
a2=5.0;
media = (a1 * 0.4) + (a2 * 0.6);

printf("%f\n" , media);
  return 0;
}