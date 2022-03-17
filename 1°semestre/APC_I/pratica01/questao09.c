//09.Faça um programa em C que converta uma idade expressa em anos, meses e dias para um valor em dias. Considere um ano com 365 dias e um mês com 30 dias.

#include <stdio.h>

int main(){
int anos, meses, dias,total_de_dias;
anos= 20;
meses = 10;
dias = 05;

anos=20*365;
meses = 10 * 30;
total_de_dias = anos + meses + dias;

printf("%d", total_de_dias);
  return 0;
}