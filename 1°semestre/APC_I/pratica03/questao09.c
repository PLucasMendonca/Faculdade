//09.Faça um programa em C que leia dez números e imprima o maior e o menor entre eles.

#include <stdio.h>

int main(){

int maior = 0, menor, numero, contador;

for(contador = 0; contador<10; contador++){
  printf("\n Informe um numero: ");
  scanf("%d", &numero);

  if(numero > maior && numero < menor){
    maior = numero;
    menor = numero;
  }
  if(numero < menor & numero != 0){
    menor = numero;
  }
  if(numero>maior && numero != 0){
    maior = numero;
  }
}
  printf("O maior numero é %d " , maior);
  printf("O menor numero é %d" , menor);


  return 0;
}