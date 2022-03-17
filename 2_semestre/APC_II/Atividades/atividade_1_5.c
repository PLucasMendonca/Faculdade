#include <stdio.h>
#include <stdlib.h>

/* Escreva um algoritmo na linguagem C que verifica se um array de caracteres contém números.*/ 


int main() {

  char str[] = "Esta 5 string 8 contem 3 numeros 2 88 90!";

  int i = 0;
  int contem_numeros = 0;
  while(str[i] != '\0') {
    if(str[i] >= '0' && str[i] <= '9') {
      contem_numeros = 1;
      break;
    }
    i++;
  }

  if(contem_numeros)
      printf("'%s' contem numeros!\n",str);
  return 0;
}