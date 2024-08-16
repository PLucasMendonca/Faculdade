//09.Faça um programa em C que leia um número inteiro e imprima o caractere correspondente na tabela ASCII.

#include <stdio.h>

int main(){
  char caracter;
  printf("Digite um caracter: \n");
  scanf("%c", &caracter);

  printf("\n Caracter digitado foi %c, Codigo ASCII: %d ", caracter , caracter);


  return 0;
}