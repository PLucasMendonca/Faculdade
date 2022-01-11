//09.Faça um programa em C que leia matricula e email de 10 alunos, e depois localize o email do aluno a partir de uma matrícula lida.

#include <stdio.h>

int main() {

int matricula[10];
char email[10][90];
int i, mat;

for (i=0;i<10;i++){

printf("Digite a matrícula do %dº aluno: ", i+1);
  scanf("%d", &matricula[i]);

printf("Digite o e-mail do %dº aluno: ", i+1);
scanf("%s", email[i]);

}

printf("\nDigite a matrícula do aluno cujo deseja o e-mail: ");
scanf("%d", &mat);

for (i=0;i<10;i++){

if(mat==matricula[i]){ printf("\nO e-mail do aluno com a matrícula %d é: %s\n", mat, email[i] );
}
}
   return 0;
}