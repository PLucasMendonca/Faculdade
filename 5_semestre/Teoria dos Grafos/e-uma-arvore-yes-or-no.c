// https://www.spoj.com/problems/PT07Z/
// Longest path in a tree

#include <stdio.h>
#include <stdlib.h>

typedef struct vertice
{
    int visitado;
    int lista_adj[1000];
    int tam_lista_adj;
}vertice;

void dfs(vertice * vertices, int qtd_vertices, int raiz)
{
    int i;
    if (vertices[raiz].visitado!=0)
    {
        return;
    }
  
    vertices[raiz].visitado=1;

    for(i=0;i<vertices[raiz].tam_lista_adj;i++)
    {
        if (vertices[vertices[raiz].lista_adj[i]].visitado==0)
        {
            dfs(vertices,qtd_vertices,vertices[raiz].lista_adj[i]);
        }
    }
}

int main()
{
    int qtd_vertices, qtd_arestas;
    int i,a,b;
    int c = 0;
    vertice * vertices;
    scanf("%d %d",&qtd_vertices, &qtd_arestas);

    vertices = (vertice*)calloc(qtd_vertices+1,sizeof(vertice));

    for(i=0;i<qtd_vertices;i++)
    {
        scanf("%d %d",&a,&b);
        vertices[a].lista_adj[vertices[a].tam_lista_adj] = b;
        vertices[a].tam_lista_adj++;
        vertices[b].lista_adj[vertices[b].tam_lista_adj] = a;
        vertices[b].tam_lista_adj++;
    }

    for(i=1;i<=qtd_vertices;i++)
    {
        if (vertices[i].visitado==0)
        {
          dfs(vertices,qtd_vertices,i);
          c++;
        }
    }
    if (c == 1 && qtd_vertices - 1 == qtd_arestas)
      printf("YES\n");
    else
      printf("NO\n");
    return 0;
}
