#include <stdio.h>
#include <stdlib.h>

/*Escreva um algoritmo na linguagem C que percorra um array de caracteres e substitua todas as ocorrências de vogais por #. */

int main() {

  char str[] = "Array de caracteres!";

  int i = 0;
  while(str[i] != '\0') {

    switch(str[i]) {
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
        str[i] = '#';
    }
    
    i++;
  }

  printf("Resultado: %s\n", str);

  return 0;
}