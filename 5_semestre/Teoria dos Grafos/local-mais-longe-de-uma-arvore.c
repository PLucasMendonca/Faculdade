// https://www.spoj.com/problems/PT07Z/
// Longest path in a tree

#include <stdio.h>
#include <stdlib.h>

typedef struct vertice
{
    int visitado;
    int lista_adj[1000];
    int tam_lista_adj;
    int distancia;
}vertice;


void mostrar_tudo (vertice * vertices, int qtd_vertices);
void dfs(vertice * vertices, int qtd_vertices, int raiz,int distancia);

int main()
{
    int qtd_vertices, qtd_arestas;
    int i,a,b,qtd_cc=0;
    vertice * vertices;
    scanf("%d",&qtd_vertices);
    int garotas[1000];
    int qtd_garotas;

    vertices = (vertice*)calloc(qtd_vertices+1,sizeof(vertice));

    for(i=0;i<qtd_vertices-1;i++)
    {
        scanf("%d %d",&a,&b);
        vertices[a].lista_adj[vertices[a].tam_lista_adj] = b;
        vertices[a].tam_lista_adj++;
        vertices[b].lista_adj[vertices[b].tam_lista_adj] = a;
        vertices[b].tam_lista_adj++;
    }

    dfs(vertices,qtd_vertices,1,0);

    int dist=0;
    int index_mais_longe;
    for(i=1;i<=qtd_vertices;i++)
    {
        if (vertices[i].distancia > dist)
        {
            index_mais_longe = i;
            dist = vertices[i].distancia;
        }
        vertices[i].visitado=0;
        vertices[i].distancia=0;
    }

    dfs(vertices,qtd_vertices,index_mais_longe,0);

    for(i=1;i<=qtd_vertices;i++)
    {
        if (vertices[i].distancia > dist)
        {
            index_mais_longe = i;
            dist = vertices[i].distancia;
        }
    }

    printf("%d\n",dist);
    return 0;
}


void mostrar_tudo (vertice * vertices, int qtd_vertices)
{
    int i,j;

    for(i=1;i<=qtd_vertices;i++)
    {
        printf("\n Vertice: %d",i);
        printf("\n Lista de adjacencias: ");

        for(j=0;j<vertices[i].tam_lista_adj;j++)
        {
            printf("%d ",vertices[i].lista_adj[j]);
        }
    }
}

void dfs(vertice * vertices, int qtd_vertices, int raiz,int distancia)
{
    int i;
    if (vertices[raiz].visitado!=0)
    {
        return;
    }

    // printf("\n %d",raiz);
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