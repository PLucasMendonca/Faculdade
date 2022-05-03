

#include <stdio.h>

int main()
{
int horas,min,seg;
    long int result_em_segundos;
    
    printf("\nQuantas horas, minutos e segundos voce quer para saber o total de segundos: ");
    scanf("%d%d%d",&horas,&min,&seg);
    
    horas=60*60*horas;
    min=60*min;
    
    printf("O resultado em segundos e de %d \n",horas+min+seg);
return 0;
}