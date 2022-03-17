//03.Faça um programa em C que leia a quantidade e o preço de 10 produtos de um nota fiscal e imprima o valor total de cada produto e o valor total da nota.

#include <stdio.h>

int main() {
	int quantidade[10];
	float preco[10];
	for(int i = 0; i < 10; i++) {
		printf("Insira a quantidade do %d° item: ", i + 1);
		scanf("%d", &quantidade[i]);

		printf("Insira o preço do %d° item: ", i + 1);
		scanf("%f", &preco[i]);
	}
	float valor_total = 0;
	float valor_item = 0;
	for(int i = 0; i < 10; i++) {
		valor_item = preco[i] * quantidade[i];
		valor_total += valor_item;

		printf("O preço total do %d item é %g\n", i + 1, valor_item);
	}

	printf("O preço total da compra é de: %g\n", valor_total);
	return 0;
}