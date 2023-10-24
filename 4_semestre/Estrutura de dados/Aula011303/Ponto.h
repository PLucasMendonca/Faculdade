typedef struct ponto Ponto;
// Cria um novo ponto
Ponto *Ponto_cria(float x, float y);
// Libera um ponto
void Ponto_Libera(Ponto *p);
// Acessa os valores 'x' e 'y' de um ponto
int Acessa_ponto(Ponto *p, float *x, float *y);
// Atribui valores 'x' e 'y' a um ponto
int Ponto_atribui(Ponto *p, float x, float y);
// Calcula a distância entre dois pontos
float Ponto_distancia(Ponto *p1, Ponto *p2);