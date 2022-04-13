#include <stdio.h>
#include <stdlib.h>

/*Utilizando notação de ponteiros, escreva um algoritmo que percorra uma string e substitua vogais por #.*/
int main(){

char s[] = "Uma string!";

for(int i = 0; *(s + i) != '\0'; i++){
    switch(*(s+i))
        case 'A':
        case 'a':
        case 'E':
        case 'e':
        case 'I':
        case 'i':
        case 'O':
        case 'o':
        case 'U':
        case 'u':
            *(s + i) = '#';
    }
    printf("s: %s\n",s);
return 0;
}