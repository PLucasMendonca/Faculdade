//05.Faça um programa em C que leia uma string e a imprima em maísculo e em minúsculo.

#include <stdio.h>
#include <string.h>

int main() {

  char frase[200], maiusculo [200], minusculo [200];
  int i, n;

  printf("Digite uma frase para obtê-la em maiúsculo e minúsculo: ");
  scanf("%[^\n]s", frase);

  n = strlen(frase);

  for (i=0;i<200;i++){

if ((frase[i]>= 'A') && (frase[i]<='Z')){

  maiusculo[i] = frase [i];
  minusculo[i] = frase [i] + 32;

} else if ((frase[i]>= 'a') && (frase[i]<='z')) {

 maiusculo[i] = frase [i] - 32;
  minusculo[i] = frase [i];

} else {

   maiusculo[i] = frase [i];
  minusculo[i] = frase [i];

}

  }

  maiusculo[i] = '\0';
  minusculo[i] = '\0';

  printf("\nOriginal:         %s\n", frase);
  printf("Maiúscula:        %s\n", maiusculo);
  printf("Minúscula:        %s\n\n", minusculo);

return 0;
}