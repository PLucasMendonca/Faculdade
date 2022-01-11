//04.Faça um programa que C que calcule os impostos incluídos no preço de um produto (preço final = (1 + ICMS + COFINS + PIS/PASEP) x preço inicial). Considere ICMS igual a 17%, COFINS igual a 7,6% e PIS/PASEP igual a 1,65%.

#include <stdio.h>

int main(){
  float icms = (17/100);
  float confins = (7.6/100);
  float pis_pasep = (1.65/100);
  float preco_inicial = 40;
float preco_final = ((1 + icms + confins + pis_pasep ) * preco_inicial);

printf("/%f\n", preco_final);

return 0;
}