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
#include <stdio.h>
#define LIM 11

int main(){
float notas[LIM],soma=0.0, media;
int i=0;

for (i=1; i<LIM; i++) {
printf("Digite a nota do aluno %d: ",i);
scanf("%f",&notas[i]);
soma+=notas[i];
}
media = soma/LIM;
printf("\n A media dos alunos é de : %.2f \n",media);
for (i=1; i<LIM; i++) {
if (notas[i]>= media)
printf("O aluno %d tem nota %.2f maior que a media.\n",i,notas[i]);
}
return 0;
}
#include <stdio.h>
#define LIM 11

int main(){
float notas[LIM],soma=0.0, media;
int i=0;

for (i=1; i<LIM; i++) {
printf("Digite a nota do aluno %d: ",i);
scanf("%f",&notas[i]);
soma+=notas[i];
}
media = soma/LIM;
printf("\n A media dos alunos é de : %.2f \n",media);
for (i=1; i<LIM; i++) {
if (notas[i]>= media)
printf("O aluno %d tem nota %.2f maior que a media.\n",i,notas[i]);
}
return 0;
}