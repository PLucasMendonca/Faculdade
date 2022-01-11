//08.Faça um programa em C que leia as coordenadas de dois pontos (x1, y1) e (x2, y2) e calcule a distância entre eles (d = raiz_quadrada((x2-x1)² + (y2-y1)²)).

#include <stdio.h>
#include <math.h>


int main(){

float cordenadas, x1 , x2 ,y1, y2, distancia;
printf("Vamos calcular a distância de dois lugares\n Digite a cordenada do primeiro local, ponto 1: ");
scanf("%f", &x1);
printf("Digite o ponto 2: ");
scanf("%f", &y1);
printf("Agora digite as cordenadas do segundo local, ponto 1: ");
scanf("%f",&x2);
printf("Agora do ponto 2: ");
scanf("%f", &y2);

distancia = sqrt(pow((x2 - x1),2) + pow((y2 - y1),2));

printf("A distancia entre os dois lugares é de %f",distancia);

  return 0;
}