//08.Faça um programa em C que leia nome e telefone de 10 contatos, e depois imprima todos os contatos no formato "nome - (telefone)".

#include <stdio.h>

typedef struct Contato {
	char nome[256];
	long telefone;
} Contato;

int main() {
	Contato contatos[10];
	for(int i = 0; i < 10; i++) {
		printf("Insira o nome do %dº contato: ", i + 1);
		scanf(" %255[^\n]s", contatos[i].nome);
		printf("Insira o telefone do %dº contato: ", i + 1);
		scanf(" %ld", &contatos[i].telefone);
	}
	for(int i = 0; i < 10; i++) {
		printf("%s - (%ld)\n", contatos[i].nome, contatos[i].telefone);
	}
}