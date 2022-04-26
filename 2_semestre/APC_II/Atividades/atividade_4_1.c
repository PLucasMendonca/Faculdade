/*Considere a string char str[] = "Teste avaliativo". Utilizando apenas notação de ponteiros:
1) escreva um algoritmo que solicite um caractere (ch) ao usuário
2) em seguida, percorra o array de caracteres e apresente o endereço da última ocorrência de ch em str. 
Caso ch não puder ser encontrado em str, apresente o valor -1.*/
#include <stdio.h>
#include <stdlib.h>

int main(){

    char str[] = "Teste avaliativo";
    char ch;
    char posicao = NULL;
    printf("Digite um caractere: ");
    scanf("%c", &ch);

    int i = 0;
    while(str[i] != '\0'){
        if(str[i] == ch){
            posicao = &str[i];
        }
    }
    if(posicao != NULL){
        printf("endereço encontrado: %p\n", posicao);
    }
    else{
        printf('-1\n');
    }

    return 0;
}