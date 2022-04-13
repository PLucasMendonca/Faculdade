#include <stdio.h>
#include <stdlib.h>

/*Utilizando notação de ponteiros, escreva um algoritmo que percorra um array de inteiros e identifique o maior e menor valor desse array.*/
int main(){

int v[6] = {4,2,3,-5,7,1};
int maior = *v;
int menor = *v;
for(int i = 1; i < 6; i++){
    if(*(v+i) > maior)
    maior = *(v+i);
    if(*(v+i) < menor)
    menor = *(v+i);
}

printf("Maior: %d, menor: %d\n", maior, menor);

    return 0;
}