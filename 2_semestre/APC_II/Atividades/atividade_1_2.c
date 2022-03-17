#include <stdio.h>
#include <stdlib.h>

/* Escreva um algoritmo na linguagem C que percorra um array de 10 inteiros e apresente na tela o menor e o maior valor. */
int main() {

int vet [] = {7,3,14,8,37,1,2,11,9,4};

int maior = vet[0];
int menor = vet[0];
for (int i=1; i < 10; i++ ) {
	if(vet[i] > maior)
	maior = vet[i];
	if (vet[i] < menor)
	menor = vet[i];
}
printf("O menor valor e: %d\n", menor);
printf("O maior valor e: %d\n", maior);

return 0;
}