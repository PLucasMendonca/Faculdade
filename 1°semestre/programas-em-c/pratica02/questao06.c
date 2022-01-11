//06.Faça um programa em C que leia o valor de compra, o ano de fabricação e o ano de depreciação e calcule o valor depreciado de um veículo (depreciação = (ano de depreciação - ano de fabricação) x 0,01 x valor de compra).

#include <stdio.h>

int main(){
float valorc, anof,anod, depreciacao;
printf("Vamos calcular a depreciação de seu veículo\n Digite o valor da compra: ");
scanf("%f", &valorc);
printf("Digite o ano de fabricação: ");
scanf("%f", &anof);
printf("Agora digite o ano de depreciação ");
scanf("%f", &anod);

depreciacao = (anod - anof) * 0.01 * valorc;

printf("A depreciação de seu veículo é de %g", depreciacao);

  return 0;
}