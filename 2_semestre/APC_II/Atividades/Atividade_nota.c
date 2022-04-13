#include <stdio.h>
#include <stdlib.h>

typedef struct{
    int dia, mes, ano;
} t_data;

typedef struct{
    char nome[100];
    char codigo[20];
    char sala[12];
    float numero_alunos;
    t_data data_inicio;
} t_turma;

int main()
{

    t_turma turma;

    printf("Digite a insercao de dados da turma\n");
    printf("Nome:");
    scanf("%[^\n]%*c", turma.nome);

    printf("Codigo:");
    scanf("%[^\n]%*c", turma.codigo);

    printf("Sala:");
    scanf("%[^\n]%*c", turma.sala);

t_turma *ptr_turma;
ptr_turma = &turma;
printf("Digite o numero da nova sala:\n");
scanf("%[^\n]%*c", turma.sala);
printf("A nova sala e: %s \n", turma.sala);

t_turma apc_ii = {
    .codigo = "T1",
    .nome = "APC II",
    .numero_alunos = 35 ,
    .data_inicio = {
        .dia = 15,
        .mes = 03,
        .ano = 2022
    }
};

printf("O numero total de alunos e: %f\n", apc_ii.numero_alunos);

if(apc_ii.numero_alunos > 40){
    printf("A nova sala e: JA4/3  \n");
} 
else if(apc_ii.numero_alunos >= 40){
    printf("A nova sala e: JA1  \n");
}
else if(apc_ii.numero_alunos > 10){
    printf("A nova sala nao foi fechada: Nao definido \n");
}
else{
    printf("Não havera turma aberta");
}

    return 0;
}