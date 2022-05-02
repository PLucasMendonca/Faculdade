#include <stdio.h>
#include <stdlib.h>
//Escreva um programa que solicite dois números ao usuário e apresente na tela o resultado da sua
//soma e o dobro de cada um deles.
void procedimento_soma(int n1, int n2) {
	printf("Soma de %d + %d = %d\n", n1, n2,n1+n2);
 }
 
void procedimento_dobro (int n){
printf("Dobro de %d * 2 = %d\n", n,n * 2);
}

int adicao (int n1, int n2){
	return n1 + n2;
}

int dobro(int n){
	return n * 2;
}

int e_par(int n){
	return (n % 2 == 0);	
}

void troca(int *p_n1,int *p_n2){
	int aux = *p_n1;
	*p_n1 = *p_n2;
	*p_n2 = aux;
}


int main()
{
int n1, n2;
printf("Digite um numero:");
scanf("%d",&n1);
printf("Digite outro numero:");
scanf("%d", &n2);

procedimento_soma(n1,n2);
procedimento_dobro(n1);
procedimento_dobro(n2);

printf("A soma de %d + %d = %d\n", n1, n2,adicao(n1,n2));
printf("dobro de %d: %d\n", n1, dobro(n1));
printf("dobro de %d: %d\n", n2, dobro(n2));
printf("%d e par? %d\n", n1, e_par(n1));
printf("%d e par? %d\n", n2, e_par(n2));


troca(&n1, &n2);
printf("Valor de n1 e n2: e %d %d\n", n1, n2);
	return 0;
}