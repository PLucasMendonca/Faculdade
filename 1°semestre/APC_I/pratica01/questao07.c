//07.Faça um programa em C que calcule a altura alcançada por um avião após ter percorrido uma certa distância (seno(ângulo) = altura/distância). Considere o ângulo de inclinação do avião menor ou igual a 45°.

#include <stdio.h>
#include <math.h>

int main()
{
  float distancia, seno, angulo, altura;

  printf("Distância percorrida em metros: ");
  scanf("%f" , &distancia);
  printf("Angulação: ");
  scanf("%f", &angulo);

  if (angulo > 45){
    printf("Insira um valor menor ou igual a 45°");
  }else {
    angulo = angulo * (M_PI/180);
    seno = sin(angulo);
    altura = distancia * seno;
    printf("O avião está a %0.02f metros do solo", altura);

  }
return 0;


}