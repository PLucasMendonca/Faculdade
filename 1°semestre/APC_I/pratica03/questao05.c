//05.Faça um programa em C que leia um ano e verifique se ele é bissexto (é múltiplo de 4 e não é múltiplo de 100, ou é múltiplo de 400).


#include <stdio.h>

int main(){

int ano;
printf("Digite um ano, para saber se ele é bissexto ou não:");
scanf("%d", &ano);

if(ano%4==0 || ano%100==0 || ano%400==0){
  printf("O ano é bissexto!");
}else
printf("O ano não é bisexto");

  return 0;
}