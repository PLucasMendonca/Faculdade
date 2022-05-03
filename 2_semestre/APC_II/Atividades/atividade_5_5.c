#include <stdio.h>
void troca(int *x,int *y){
	int aux = *x;
	*x = *x;
	*y = aux;
}
int main()
{
int x, y;
printf("Digite um numero:");
scanf("%d",&x);
printf("Digite outro numero:");
scanf("%d", &y);
troca(&x, &y);
printf("Valor de n1 e n2: e %d %d\n", x, y);
	return 0;
}