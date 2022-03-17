#include <stdio.h>
#include <stdlib.h>

/* Escreva um algoritmo na linguagem C que verifica se um array de caracteres contém vogais.*/

int main() {
  char *s = "Marcos";

  int i = 0;
  int e_vogal = 0;
  while(s[i] != '\0') {
    switch(s[i]) {
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
        e_vogal = 1;
    }
    if(e_vogal) {
      break;
    }
    i++;
  }

  printf("'%s' tem vogal/? %d", s, e_vogal);
  
  return 0;
}