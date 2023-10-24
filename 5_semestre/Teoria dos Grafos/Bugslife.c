#include <stdio.h>
#include <stdlib.h>

typedef struct{
  int visitado, cor, tam_lista_adj;
  int lista_adj [2001];
}Vertice;

int dfs(Vertice * vertices, int total_vertices, int raiz, int cor){
  vertices[raiz].visitado = 1;
  vertices[raiz].cor = cor;
  int i;
  for(i = 0; i<vertices[raiz].tam_lista_adj; i++){
    int vizinho = vertices[raiz].lista_adj[i];
    if(vertices[vizinho].visitado == 0){
    if(!dfs(vertices, total_vertices, vizinho, cor * -1)){
      return 0;
    }
    }else{
      if(vertices[raiz].cor == vertices[vizinho].cor){
        return 0;
      }
    }
    return 1;
  }
}
int main(void){
  int cenarios,i;
  scanf("%d", &cenarios);
  for(i = 0; i< cenarios; i++){
    int num_bugs, num_interacoes;
    scanf("%d &d", &num_bugs, &num_interacoes);

    Vertice bugs[num_bugs + 1];
    
  }
}