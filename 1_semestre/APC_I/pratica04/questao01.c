//01.Faça um programa em C que preencha uma matriz com 10 números, depois leia um número e localize a posição dele na matriz.

#include <stdio.h>

int main(){

int i, m, v[10], c=0;
for(i=0; i<10; i++)
{
  printf("Digite o valor da posição %i: " ,i);
  scanf("%i", &v[i]);
}
printf("\n Digite o valor do elemento: ");
scanf("%i", &m);

for(i=0; i<10; i++){

  if(m==v[i]){printf("\nO elemento se encontra na posição %i",i);}
  else{c=c+1;}

}
if(c==10){printf("\n Não existe posições com o valor do elemento M!");} else{};
  return 0;
}