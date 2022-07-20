
#include <stdio.h>

void preencheVetor(int n, int vet[n]) {
	for(int i = 0; i < n; i++){
		printf("vetor[%d]: ", i);
		scanf("%d", &vet[i]);
	}
}

void imprimeVetor(int n, int vet[n]){
	for(int i = 0; i < n; i++){
		printf("Vetor[%d]: %d\n", i, vet[i]);
	}
}
void preencheMatriz(int n, int m, int mat [n][m]){
	for(int i=0; i<n; i++){
    for(int j = 0; j< m; j++){
      printf("mat[%d] [%d]: ", i,j);
      scanf("%d", &mat[i][j]);
      
    }
	}
}
void imprimeMatriz(int n, int m, int mat[n][m]){
  for(int i = 0; i < n; i++){
    for(int j = 0; j <m; j++){
      printf("mat[%d][%d]: %d\n", i, j, mat[i][j]);
    }
  }
}

int strlen2(char *str){

  int i =0;
  while(str[i] != '\0')
    i++;
  return i;
}



int main(void){

printf("tamanho da string '%s': %d\n",
            "Tamanho",strlen2("Tamanho"));
  printf("tamanho da string '%s': %d\n",
            "Ana",strlen2("Ana"));

  
	int vetor[5];
	int mat2x2[2][2];
  int mat3x2[3][2];


  printf("Matriz 2x2:\n");
  preencheMatriz(2,2, mat2x2);
  printf("Impressão:\n");
  imprimeMatriz(2,2,mat2x2);
  
  printf("Matriz 3x2:\n");
  preencheMatriz(3,2, mat3x2);
  printf("Impressão:\n");
  imprimeMatriz(3,2,mat3x2);
	return 0;
}