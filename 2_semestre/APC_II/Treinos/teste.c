#include <stdio.h>
#include <stdlib.h>


int main(){
    int valor, numero, vetor[10]={0}, i = 0, n;
    while(i < 10){
        printf("Valor: ");
        scanf("%d", &valor);
        for(n = numero = 0; n < i; n++)
        if(vetor[n] == valor){
             numero = 1;
        }
        if(numero){
            printf("o valor %d ja existe\n", valor);
            }
        else{
        vetor[i++] = valor;
        }
        }
        printf("\nVetor:\n\n");
        for(i = 0; i < 10; i++){
            printf("%d\n", vetor[i]);
        }

return 0;}