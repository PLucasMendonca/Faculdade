//01.Faça um programa em C que leia três números reais e imprima a média aritmética destes números.

#include <stdio.h>

int main(){

  int num1,num2,num3,media;
printf("Digite 3 numeros reais, para calcular a média entre eles.Primeiro numero:");
scanf ("%d",&num1);
printf("Segundo numero: ");
scanf("%d", &num2);
printf("Terceiro numero ");
scanf("%d", &num3);
media = (num1 + num2 + num3)/3;

printf("A média dos numeros é %d", media);
  return 0;
}