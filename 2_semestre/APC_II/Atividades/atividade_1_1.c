#include <stdio.h>
#include <stdlib.h>

/* Escreva um algoritmo na linguagem C que percorra um array de 5 notas: 7, 3, 6, 8, 5 e apresente na tela a média dessas notas. */
int main()
{

	int arr[] = {7, 3, 6, 8, 5};
	int n = 5, soma = 0;
	for (int i = 0; i < n; i++)
	{
		soma = soma + arr[i];
	}
	float media = (float)soma / n;

	printf("media das notas: %f\n", media);
	return 0;
}