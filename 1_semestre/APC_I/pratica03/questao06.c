//06.Faça um programa em C que liste todos os múltiplos de 3, entre 1 e 100.

#include <stdio.h>

int main(){

int num;

for(num = 1; num <= 100; num++){
  if(num % 3 ==0)
  printf("%3d", num);
}
  return 0;
}