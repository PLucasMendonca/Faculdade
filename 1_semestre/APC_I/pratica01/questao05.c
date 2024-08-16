//05.Faça um programa em C que converta um valor qualquer em Gigabytes para um valor em bytes (1GB = 1024³Bytes).

#include <stdio.h>
#include <math.h>

int main()
{
double bytes,gigabytes,transformado;

scanf("%le" ,&gigabytes);

bytes = pow(1024,3);

transformado = (gigabytes * bytes);


printf("O numero transformado em bytes é: %le", transformado);
return 0;

}