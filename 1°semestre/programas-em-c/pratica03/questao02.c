//02.Faça um programa em C que calcule o valor do desconto sobre um valor bruto lido com base na escala: até de 100.00 aplica 1%; de 100.01 a 500.00 aplica 5%; acima de 500.00 aplica 10%.

#include <stdio.h>

int main()
{
  float valor_bruto,valor_desc, valor_final;
  printf("Digite o valor bruto: ");
  scanf("%f", &valor_bruto);

  if (valor_bruto <= 100.00){
  valor_desc = valor_bruto * 0.01;
  valor_final = valor_bruto - valor_desc; 

  printf("Valor com desconto é %f", valor_final);

  }else if (valor_bruto >= 100.01 && valor_bruto <= 500.00){
  valor_desc = valor_bruto * 0.05;
  valor_final = valor_bruto - valor_desc; 

  printf("Valor com desconto é %f", valor_final );
  }else if (valor_bruto > 500.00){
  valor_desc = valor_bruto * 0.1;
  valor_final = valor_bruto - valor_desc;

  printf("Valor com desconto é %f", valor_final );
  }

  return 0;
}