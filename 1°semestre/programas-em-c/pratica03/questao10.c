//10.Faça um programa em C que leia um número decimal e o converta em binário (ex.: 9 => 1001).

#include <stdio.h>

  
int main(){

int num;
printf("Digite um numero para ser transformado em numero binario: ");
scanf("%d", &num);

void binario(int num){
  if(num == 0)
  printf("%d", num);
  else{
    binario(num/2);
    printf("%d", num % 2);
  }
}
binario(num);

  return 0;
}