//02.Faça um programa em C que leia dois números inteiros e imprima o quociente e o resto da divisão entre eles.



#include <stdio.h>
#include <math.h>

int main(){

int num1 , num2,quociente, resto;

printf("Digite o numero que você quer dividir ");
scanf("%d", &num1);
printf("Digitie o numero correspondete a quanto você quer dividir ");
scanf("%d", &num2);

quociente = num1 / num2;
resto = num1 % num2;


printf("O resultado da divisão é %d, e o que sobra é (%d)" , quociente, resto);
  return 0;
}
// tentei fazer com float que seria mais certo, mas estava dando erro, tentei pesquisar como fazia, mas tbm nao consegui aplicar, ent fiz com numeros inteiros msm.
