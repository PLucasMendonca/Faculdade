//04.Faça um programa em C que leia uma frase de até 30 caracteres preenchendo uma matriz 6x5 e depois imprima a matriz percorrendo coluna por coluna.

#include <stdio.h>
#include <string.h>

int main() {

char frase[31];

  printf("Coloque sua frase: "); fgets(frase, 31, stdin);

  int size_s = sizeof(frase) / sizeof (frase[0]);

char matriz[6][5];

   memmove(matriz, frase, size_s);

int a, b;

  for (a = 0; a < 6; a++){
    for (b = 0; b < 5; b++){
       if (matriz[a][b] != '\0') printf("%c\t", matriz[a][b]);
       }
       printf("\n");
   }
   return 0;

}