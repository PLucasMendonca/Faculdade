#include <stdio.h>
#include <stdlib.h>

/*Percorra uma lista string e mostre toda ela e aonde é seu endereço de memoria.*/
int main(){
/*Criando variáveis*/
char s[] = "OlaOleOli";
char *ptr = s;
/*Condição para percorrer toda a string e parar no '\0' depois printando cada um*/
for(int i = 0; s[i] != '\0'; i++){
    printf("s[%d]: %c (end: %d)\n", i, s[i], &s[i]);
}
printf("s = %d\n", s);
/*Buscando "Vogais" */
/*"A"*/
printf("*(ptr + 2): %c (end: %d)\n", *(ptr + 2), (ptr + 2));
/*"E"*/
printf("*(ptr + 2): %c (end: %d)\n", *(ptr + 5), (ptr + 5));
/*"I"*/
printf("*(ptr + 2): %c (end: %d)\n", *(ptr + 8), (ptr + 8));

printf("*(s + 2): %c (end: %d)\n", *(s + 2), (s + 2));

printf("*(s + 5): %c (end: %d)\n", *(s + 5), (s + 5));

printf("*(s + 8): %c (end: %d)\n", *(s + 8), (s + 8));


printf("*(ptr[2]): %c (end: %d)\n", *(ptr + 2), (ptr + 2));

printf("*(ptr[5]): %c (end: %d)\n", *(ptr + 5), (ptr + 5));

printf("*(ptr[8]): %c (end: %d)\n", *(ptr + 8), (ptr + 8));

/*OS 3 CODIGOS A CIMA FAZEM A MESMA COISA  */
    return 0;
}