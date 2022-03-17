//10.Faça um programa em C que calcule a decomposição de um número inteiro qualquer em unidade, dezena, centena e milhar (ex.: 5637 é decomposto em 7 unidades, 3 dezenas, 6 centenas e 5 milhares).

#include <stdio.h>

int main(){
int uni, dez, cent, mil, num;
num = 7893;
uni = num % 10;
num = (num - uni)/10;
dez = num % 10;
num = (num - dez)/10;
cent = num % 10;
num = (num - cent)/10;
mil = num % 10;


 printf("%d milhares %d centenas, %d dezenas, %d unidades", mil, cent, dez, uni);
  return 0;
}