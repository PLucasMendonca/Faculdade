//01.Faça um programa em C que determine se um número inteiro lido é par ou ímpar.

#include <stdio.h>

int main()
{ 
  int num;
printf("Digite um numero: ");
scanf("%d", &num);
if (num % 2 == 0)
  printf("Este numero é par.");
else
  printf("Este numero é impar");

  return 0;
}