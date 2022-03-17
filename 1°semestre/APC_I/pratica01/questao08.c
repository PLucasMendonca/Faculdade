//08.Faça um programa em C que converta um tempo expresso em segundos para um valor em horas, minutos e segundos (ex.: 1000 segundos corresponde a 0 horas 16 minutos e 40 segundos).

#include <stdio.h>

int main(){
int horas, minutos, segundos, s, resto;
printf("Quantos segundos devem ser convertidos ?: ");
scanf("%d", &segundos);

horas = segundos / 3600;
resto = segundos % 3600;
minutos = resto / 60;
s = resto % 60;
printf("%d: %d: %d\n", horas, minutos, s);
  return 0;
}