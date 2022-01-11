//07.Faça um programa em C que leia uma estrutura contendo dia, mês e ano e imprima a data tanto no formato BR (dd/mm/aaaa) quanto no formato US (mm/dd/aaaa).

#include <stdio.h>
#include <string.h>

int main() {

int mees, diaa, anoo;

  struct data_t {
int dia;
int mes;
int ano; } data;

  printf("Digite o dia: ");
  scanf("%d", &diaa);

data.dia = diaa;

printf("Digite o mês: ");
  scanf("%d", &mees);

data.mes = mees;

printf("Digite o ano: ");
  scanf("%d", &anoo);

data.ano = anoo;

printf("\nFormato BR:  %.02d/%.02d/%.02d\n", data.dia, data.mes, data.ano);
printf("Formato US:  %.02d/%.02d/%.02d\n\n", data.mes, data.dia, data.ano);

return 0;
}