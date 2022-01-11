//06.Faça um programa em C que calcule as raízes de uma equação do 2° grau através da fórmula de Bhaskara (-b +/- raiz_quadrada(b² - 4ac)/2a).

#include <stdio.h> 
#include <math.h>


int main()
{
  float a=1, b=4, c=-21;

float r1 = (-b+(sqrt((b*b)-(4*a*c))))/(2*a);
float r2 = (-b-(sqrt((b*b)-(4*a*c))))/(2*a);
  printf("Raiz 1 = %f\n Raiz2 = %f", r1,r2);
  return 0;

}