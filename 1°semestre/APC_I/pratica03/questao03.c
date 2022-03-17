//03.Faça um programa em C que imprima um qualitativo associado a uma nota lida conforme a tabela: 1 = Ruim; 2 = Insuficiente; 3 = Suficiente, 4 = Bom, 5 = Ótimo, e imprima Nota inválida nos demais casos.

#include <stdio.h>

int main(){

int nota;
printf("Digite sua nota:");
scanf("%d", &nota);

if  (nota == 1) {
  printf("Ruim");
}else if(nota == 2){ 
  printf("Insuficiente");
}else if(nota == 3){ 
  printf("Suficiente");
}else if(nota == 4){
  printf("Bom");
}else if(nota == 5){
  printf("Ótimo");
}else {
  printf("Nota inválida");
}

return 0;
} 
