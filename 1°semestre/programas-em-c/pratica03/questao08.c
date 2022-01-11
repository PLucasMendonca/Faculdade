//08.Faça um programa em C que calcule o fatorial de um número inteiro.

#include <stdio.h>

int main(){

int num, i, fat = 1;
printf("Digite um numero para ser fatorado: ");
scanf("%d", &num);

for (i=num ;i >1; i--){
  fat = fat*i;
}
printf("O valor fatorado é: %d", fat);

  return 0;
}