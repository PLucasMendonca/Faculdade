//04.Faça um programa em C que leia uma tecla pressionada e determine se ela é uma letra, um dígito ou um caractere especial.

#include <stdio.h>

int main(){

char variavel;
printf("Digite um caractere:");
scanf("%c", &variavel);


if (variavel >= 48 && variavel < 57){
  printf("Seu caractere é um número.");
}else if(variavel >= 97 && variavel <= 122){
  printf("Seu caractere é uma letra.");
}else{ 
printf("Seu caractere e um caractere especial.");  
}

  return 0;

}