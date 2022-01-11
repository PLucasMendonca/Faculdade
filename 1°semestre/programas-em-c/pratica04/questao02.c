//02.Faça um programa em C que leia as notas de 10 alunos, calcule a média da turma e contabilize quantos alunos estão com a nota acima da média.

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