//04.Faça um programa em C que leia a largura e o comprimento de um terreno em metros e calcule a sua área em hectares (1 hectare = 10.000 m²).

#include <stdio.h>

int main(){
float larg, comp, area;
printf("Digite a largura de seu terreno em metros ");
scanf("%f", &larg);
printf("Digite o comprimento de seu terreno em metros ");
scanf("%f", &comp);

area = (larg * comp) /10000;

printf("Sua área em hectares é %g\n ", area);


  return 0;
}