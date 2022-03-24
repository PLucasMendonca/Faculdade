#include <stdio.h>
#include <stdlib.h>

/*Utilizando notação de ponteiros, escreva um algoritmo que percorra uma string e imprima o comprimento dessa string.*/
int main(){

char s[] = "Uma string!";

for(int i = 0; *(s+i) != '\0'; i++){

printf("s[%d]: %d\n", s, i);
}

return 0;
}