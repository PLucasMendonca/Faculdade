#include <stdio.h>
#include <stdlib.h>

typedef struct vertice
{
    int visitado, tam_lista_adj,distancia;
    int lista_adj[1000];
}vertice;


void dfs(vertice * vertices, int qtd_vertices, int raiz,int distancia)
{
    int i;
    if (vertices[raiz].visitado!=0)
    {
        return;
    }

    vertices[raiz].visitado=1;
    vertices[raiz].distancia = distancia;

    for(i=0;i<vertices[raiz].tam_lista_adj;i++)
    {
        if (vertices[vertices[raiz].lista_adj[i]].visitado==0)
        {
            dfs(vertices,qtd_vertices,vertices[raiz].lista_adj[i],distancia+1);
        }
    }
}

int main()
{
    int qtd_vertices, qtd_arestas;
    int i,n,m;
    vertice * vertices;
    printf("Digite o número de vertices do grafo: ");
    scanf("%d",&qtd_vertices);

    vertices = (vertice*)calloc(qtd_vertices+1,sizeof(vertice));

    for(i=0;i<qtd_vertices-1;i++)
    {
        scanf("%d %d",&n,&m);
        vertices[n].lista_adj[vertices[n].tam_lista_adj] = m;
        vertices[n].tam_lista_adj++;
        vertices[m].lista_adj[vertices[m].tam_lista_adj] = n;
        vertices[m].tam_lista_adj++;
    }

    dfs(vertices,qtd_vertices,1,0);

    int dist=0;
    int indice_mais_longe;
    for(i=1;i<=qtd_vertices;i++)
    {
        if (vertices[i].distancia > dist)
        {
            indice_mais_longe = i;
            dist = vertices[i].distancia;
        }
        vertices[i].visitado=0;
        vertices[i].distancia=0;
    }

    dfs(vertices,qtd_vertices,indice_mais_longe,0);

    for(i=1;i<=qtd_vertices;i++)
    {
        if (vertices[i].distancia > dist)
        {
            indice_mais_longe = i;
            dist = vertices[i].distancia;
        }
    }

    printf("O comprimento do caminho mais longo em uma linha é de %d\n",dist);
    return 0;
}