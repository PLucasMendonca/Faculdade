//07.Escrever um programa em C que liste os 10 primeiros números da série de Fibonacci.

#include <stdio.h>

int main(){

int i, n, t1 = 0, t2 = 1, prox;
printf("Digite a quantidade de numeros da sequencia: ");
scanf("%i", &n);

printf("\n SERIE DE FIBONACI\n");

for(i = 1; i <= n; i++){
  printf("%i ", t1);
  prox = t1 + t2;
  t1 = t2;
  t2 = prox;

}
  return 0;
}