#include <stdio.h>
#include <stdlib.h>
#include <string.h>
// Aluno:Lucas dos Santos Vasco Mendonça
// Matricula: 2122130042
typedef struct
{
    int dia, mes, ano;
} t_data;

typedef struct
{
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
    scanf("%s%*c", turma.codigo);

    printf("Numero de alunos:");
    scanf("%f%*c", &turma.numero_alunos);

    printf("Data de inicio no seguinte formato dd/mm/aa:");
    scanf("%d/%d/%d%*c", &turma.data_inicio.dia,&turma.data_inicio.mes,&turma.data_inicio.ano);
  
    printf("Sala:");
    scanf("%s%*c", turma.sala);

    char *s = turma.sala;
    *(s) = 'J';
    *(s+1) = 'B';
    *(s+2) = '2';
    *(s+3) = '\0';
    
  // OU 
    *turma.sala = 'I';
    *(turma.sala+1) = 'A';
    *(turma.sala+2) = '4';
    *(turma.sala+3) = '\0';

    t_turma apc_ii = {
        .codigo = "T1",
        .nome = "APC II",
        .numero_alunos = 10,
        .data_inicio = {
            .dia = 15,
            .mes = 03,
            .ano = 2022}};

    if (apc_ii.numero_alunos > 40)
    {
        strcpy(apc_ii.sala, "JA4/3");
    }
    else if (apc_ii.numero_alunos >= 40)
    {
        strcpy(apc_ii.sala, "JA1");
    }
    else (apc_ii.numero_alunos < 10);
    {
      strcpy(apc_ii.sala, "Não definido");
    }
    printf("A sala será: %s", apc_ii.sala);
    return 0;
}