//03.Faça um programa em C que leia uma temperatura em graus Celsius e calcule o valor correspondente em graus Fahrenheit (°F = 9/5°C + 32).

#include <stdio.h>

int main(){

  float grauc, grauf, num;
  printf("Digite um numero em graus Celsius para ser transformado em graus Fahrenheit ");
  scanf("%f", &num);
  grauf = (num * 9/5) + 32;
 
  
printf("%f Graus Fahrenheit" , grauf);


  return 0;
}