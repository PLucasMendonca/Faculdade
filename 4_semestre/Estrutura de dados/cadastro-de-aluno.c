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
    scanf("%c", &turma.nome);

    printf("Codigo:\n");
    scanf("%c", &turma.codigo);

    printf("Sala:");
    scanf("%c", &turma.sala);

    return 0;
}