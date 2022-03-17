//07.Faça um programa em C que leia o valor da hora de trabalho e o total de horas trabalhadas no mês e calcule o salário bruto, o salário líquido, e os impostos descontados. Considere IR igual a 25% e INSS igual a 11%.

#include <stdio.h>

int main (){
float horast , valorht, salariob, salariol,desconto, ir, inss, impostos;
printf("Vamos calcular seu salário\n Digite a quantidade de  horas trabalhadas no mês: ");
scanf("%f", &horast);
printf("Agora digite o valor recebido por hora: ");
scanf("%f",&valorht);
ir = 25;
inss = 11;
impostos = ir + inss;
salariob = horast * valorht;
desconto = salariob * impostos / 100;
salariol = salariob - desconto;
printf("Seu salário liquido é de %f reais" , salariol);


  return 0;
}