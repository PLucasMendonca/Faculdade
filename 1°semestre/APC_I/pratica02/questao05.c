//05.Faça um programa em C que leia o peso e a altura de uma pessoa e calcule o índice de massa corporal (imc = peso / altura²).

#include <stdio.h>

int main(){
float altura,peso,imc;
printf("Vamos calcuar seu imc.\n Digite seu peso: ");
scanf("%f", &peso);
printf("Agora, digite sua altura: ");
scanf("%f", &altura);

imc = peso / (altura*altura);

printf("Seu IMC é %f", imc);

  return 0;
}