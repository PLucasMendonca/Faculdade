
/*Implemente as seguintes funções utilizando apenas operações de ponteiros
char * strcpy (char * dest, char * orig) que copia a string orig para a string dest.
char *strcpy(char *dest, char *orig) */

#include <stdio.h>

int main (){

char *strcpy(char *dest, char *orig);

char orig[] = "Menssagem";

char dest[15];
strcpy(dest, orig);

printf("String copiada: %s\n", dest);


    return 0;
}
