/* a) Defina uma estrutura data com os seguintes atributos:
dia: inteiro
mes: inteiro
ano: inteiro
Utilize typedef e defina o tipo de dado t_data, a partir da estrutura acima.
 b) Defina uma estrutura turma com os seguintes atributos:
nome: string
codigo: string
numero_alunos: float
data_inicio: t_data
sala: string
Utilize typedef e defina o tipo de dado t_turma, a partir da estrutura acima.
c) Crie uma variável da estrutura turma e, em seguida, solicite ao usuário fornecer os dados da turma.
d) Utilizando um ponteiro, modifique o valor do número de sala, previamente declarado e preenchido
pelo usuário (itens a) e b)).
e) Considere que já possuímos uma variável denominada apc_ii do tipo estrutura de turma especificado
no item a). Escreva o algoritmo necessário para verificar a quantidade total de alunos e verificar os seguintes
casos:
i. Caso numero_alunos for maior que 40, a sala deve ser JA4/3,
ii. Caso numero_alunos for menor ou igual a 40, a sala deve ser JA1
iii. Caso numero_alunos for menor que 10, não será fechado turma, logo sala deve ser “Não definido”.*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
// Aluno:Lucas dos Santos Vasco Mendonça
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