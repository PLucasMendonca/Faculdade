
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <stdbool.h>
#define MAX_JOGADORES 4
///////////////// ESTRUTURA DO JOGO ///////////////////
typedef enum cores {
  VERMELHO,
  AZUL,
  AMARELO,
  VERDE
} ECor;

typedef struct carta {
  char valor[50];
  ECor cor;
} TCarta;

typedef struct no_carta {
  TCarta carta;
  struct no_carta *proximo;
} NoCarta;

typedef struct manilha {
  int size;
  NoCarta *proximo;
  NoCarta *anterior;
} TManilha;

typedef struct jogador {
  TManilha manilha;
  char nome[50];
} Jogador;

typedef struct no_jogador {
  Jogador jogador;
  struct no_jogador *proximo;
  struct no_jogador *anterior;
} NoJogador;

typedef struct deck {
  int size;
  NoCarta *topo;
} TDeck;


typedef struct jogada{
  TCarta carta;
  struct Jogador *jogador;
}CartaJogada;

typedef struct mesa{
int size;
NoCarta *topo;
} Mesa;
////////////// FUNÇÕES DO JOGO ///////////////////////
//Criação das cartas
NoCarta* criar_carta(const char* valor, ECor cor) {
  NoCarta* carta = (NoCarta*)malloc(sizeof(NoCarta));
  if (carta != NULL) {
    strcpy(carta->carta.valor, valor);
    carta->carta.cor = cor;
    carta->proximo = NULL;
    }
  return carta;
}
//Inicia o deck
void inicializar_deck(TDeck *deck) {
  deck->size = 0;
  deck->topo = NULL;
  ECor cores[] = {VERMELHO, AZUL, AMARELO, VERDE};
  char valores[][50] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "INVERVER", "+2", "PULAR"};
  int num_cores = sizeof(cores) / sizeof(cores[0]);
  int num_valores = sizeof(valores) / sizeof(valores[0]);
  for (int i = 0; i < num_cores; i++) {
    for (int j = 0; j < num_valores; j++) {
      int quantidade = (strcmp(valores[j], "0") == 0) ? 1 : 2;//compara duas strings e devolve um valor. 
      for (int k = 0; k < quantidade; k++) {
        NoCarta *carta = criar_carta(valores[j], cores[i]);
        carta->proximo = deck->topo;
        deck->topo = carta;
        deck->size++;
      }
    }
  }
  int quantidade_cartas_curinga = 4; // Define a quantidade de cartas curingas e +4
  for (int i = 0; i < quantidade_cartas_curinga; i++) {
    NoCarta* carta_curinga = malloc(sizeof(NoCarta));
    if (!carta_curinga) exit(1);
    strcpy(carta_curinga->carta.valor, "CURINGA");
    carta_curinga->carta.cor = -1; // Valor para indicar um curinga
    carta_curinga->proximo = deck->topo;
    deck->topo = carta_curinga;
    deck->size++;

    NoCarta* carta_mais_quatro = malloc(sizeof(NoCarta));
    if (!carta_mais_quatro) exit(1);

    strcpy(carta_mais_quatro->carta.valor, "+4");
    carta_mais_quatro->carta.cor = -1; // Valor para indicar um +4

    carta_mais_quatro->proximo = deck->topo;
    deck->topo = carta_mais_quatro;
    deck->size++;
  }
}
//Embaralhar o deck
void embaralhar_deck(TDeck *deck){
  srand(time(NULL)); // Inicializa a semente do gerador de números aleatórios
  // Percorre o deck e para cada carta, troca sua posição com outra carta aleatória
  for (NoCarta *carta = deck->topo; carta != NULL; carta = carta->proximo) {
    // Gera um índice aleatório
    int posicao_aleatoria = rand() % deck->size;

    // Encontra a carta na posição aleatória
    NoCarta *carta_aleatoria = deck->topo;
    for (int i = 0; i < posicao_aleatoria; i++) {
      carta_aleatoria = carta_aleatoria->proximo;
    }
    // Troca as cartas de posição
    TCarta temp = carta->carta;
    carta->carta = carta_aleatoria->carta;
    carta_aleatoria->carta = temp;
  }
}
// Função para mostrar as cartas na mesa
void mostrar_carta_na_mesa(TDeck* deck) {
  if (deck->topo != NULL) {
    NoCarta* carta = deck->topo;
    deck->topo = carta->proximo;
    deck->size--;
    //Condição para apenas pegar cartas válidas
    while (carta->carta.cor == -1 || strcmp(carta->carta.valor, "INVERVER") == 0 || strcmp(carta->carta.valor, "+2") == 0 || strcmp(carta->carta.valor, "PULAR") == 0) {
      // Devolve a carta removida ao deck
      carta->proximo = deck->topo;
      deck->topo = carta;
      deck->size++;

      carta = deck->topo;
      deck->topo = carta->proximo;
      deck->size--;
    }
    printf("Carta na mesa:\n");
    printf("Cor: %d | Valor: %s\n", carta->carta.cor, carta->carta.valor);
    // Devolve a carta removida ao deck
    carta->proximo = deck->topo;
    deck->topo = carta;
    deck->size++;
  } else {
    printf("O deck está vazio.\n");
  }
}


//Créditos do projeto
void exibir_creditos() {
  printf("Nome do projeto: Jogo de cartas UNO em Linguagem C!\n");
  printf("Faculdade: IESB Sul\n");
  printf("Integrantes:\n- integrante 1: Ricardo Felipe\n- integrante 2: Lucas de Oliveira\n- integrante 3: Lucas dos Santos\n- integrante 4: Paulo Henrique\n");
  printf("Professor: Daniel Souza\n");
  int sair = -1;
  while (sair != 0) {
    printf("Digite a opção 0 para voltar ao menu: ");
    scanf("%d%*c", &sair);
    if (sair != 0) {
      printf("Opção %d selecionada é inválida, tente novamente:\n", sair);
    } else break;
  }
}
//Regras do Jogo
void regras(){
  printf("Às regras do jogo são:\n");
  printf("1: Cada jogador vai receber 7 cartas.\n");
  printf("2: As cartas a serem jogadas precisam combinar com a carta na mesa em número ou cor.\n");
  printf("3: Carta +2: Quando esta carta é jogada na mesa, o próximo jogador deve comprar duas cartas\n");
  printf("4: Carta Inverter: Representada por duas setas, se esta carta for posta na mesa, o sentido será invertido\n");
  printf("5: Carta Pular: Com a imagem de um sinal de proibido, esta carta permite que o próximo jogador tenha seu turno pulado\n");
  printf("6: Carta Curinga: Uma carta preta com um círculo contendo 4 cores. Ao jogar esta carta o jogador determina qual cor o próximo usuário terá que jogar\n");
  printf("7: Carta Curinga +4: Esta carta permite determinar a próxima cor a ser jogada e força o próximo jogador a comprar 4 cartas.\n");
  printf("8: Vence o jogador que não tiver nenhuma carta em mãos\n");
  int sair = -1;
  while (sair != 0) {
    printf("Digite a opção 0 para voltar ao menu: ");
    scanf("%d%*c", &sair);
    if (sair != 0) {
      printf("Opção %d selecionada é inválida, tente novamente:\n", sair);
    } else break;
  }
}
//Função para pegar a quantidade de jogadores
int obterQuantidadeJogadores(){
  int quantidade;
  printf("Quantos jogadores irão participar (2 a %d)? ", MAX_JOGADORES);
  scanf("%d", &quantidade);
  while (quantidade < 2 || quantidade > MAX_JOGADORES) {
    printf("Quantidade inválida. Informe novamente (2 a %d): ", MAX_JOGADORES);
    scanf("%d", &quantidade);
  }
  return quantidade;
}
// Pegar o nome dos Jogadores
void obterNomesJogadores(int quantidade, Jogador jogadores[]) {
  for (int i = 0; i < quantidade; i++) {
    printf("Digite o nome do jogador %d: ", i + 1);
    scanf("%s", jogadores[i].nome);
  }
}
//Distribui a quantidade de cartas para os jogadores
void distribuir_cartas(TDeck *deck, int num_jogadores, Jogador jogadores[]) {
  NoCarta *carta = deck->topo;
  int jogador_atual = 0;

  while (carta != NULL) {
    if(jogadores[jogador_atual].manilha.size >=7){
      jogador_atual = (jogador_atual + 1) % num_jogadores;
    }
    if(jogadores[jogador_atual].manilha.size < 7){
      NoCarta *proxima_carta = carta->proximo;
      // Adiciona a carta ao jogador atual
      carta->proximo = jogadores[jogador_atual].manilha.proximo;
      jogadores[jogador_atual].manilha.proximo = carta;
      jogadores[jogador_atual].manilha.size++;
      // Atualiza o jogador atual
      jogador_atual = (jogador_atual + 1) % num_jogadores;
      carta = proxima_carta;
    }
    else{
      carta = carta->proximo;
    }
    // Reinicia o topo do deck e seu tamanho
    deck->topo = NULL;
    deck->size = 0;
  }
}
//Imprime as cartas do jogo
void imprimir_cartas_jogadores(int num_jogadores, Jogador jogadores[]) {
  for (int i = 0; i < num_jogadores; i++) {
    printf("Cartas do jogador %d (%s):\n", i + 1, jogadores[i].nome);

    NoCarta *carta = jogadores[i].manilha.proximo;
    while (carta != NULL) {
      printf("Cor: %d | Valor: %s\n", carta->carta.cor, carta->carta.valor);
      carta = carta->proximo;
    }
    printf("\n");
    
     if (i < num_jogadores -1){
       printf("Pressione Enter para exibir o Deck do proximo jogador...\n");
       getchar();
       getchar();
     system("clear");
     }
  }
}
//Exibir o menu do jogo
void exibir_menu() {
  TDeck deck;
  int escolha = 0;
  int quantidadeJogadores;
  Jogador jogadores[MAX_JOGADORES];
  while (escolha != 4) {
    printf("===== MENU =====\n");
    printf("1. Novo Jogo \n");
    printf("2. Exibir Créditos \n");
    printf("3. Regras do jogo\n");
    printf("4. Sair\n");
    printf("================\n");
    printf("Escolha uma opcao: ");
    scanf("%d%*c", &escolha);
    switch (escolha) {
      case 1:{
        inicializar_deck(&deck);
        embaralhar_deck(&deck);
        printf("Jogo iniciado!!\n");
        quantidadeJogadores = obterQuantidadeJogadores();
        obterNomesJogadores(quantidadeJogadores, jogadores);
        distribuir_cartas(&deck, quantidadeJogadores, jogadores);
        imprimir_cartas_jogadores(quantidadeJogadores,jogadores);
        break;
      }
      case 2: {
        printf("======= Créditos ========\n");
        exibir_creditos();
        break;
      }
      case 3: {
        regras();
        break;
      }         
      case 4:{
        printf("Saindo do jogo...\n");
        exit(0);
        break;
      }
      default:{
        printf("Opcao invalida! Tente novamente.\n");
        break; 
      }
    }
  }
}

//Para visualizar as cartas
void imprimir_cartas(TDeck deck) {
  NoCarta* carta = deck.topo;
  printf("Cor: %d | Valor: %s\n", carta->carta.cor, carta->carta.valor);
    while (carta != NULL) {
     printf("Cor: %d | Valor: %s\n", carta->carta.cor, carta->carta.valor);
     carta = carta->proximo;
   }
}

int main() {
  TDeck deck;
  inicializar_deck(&deck);
  embaralhar_deck(&deck);
  //exibir_menu();
  //imprimir_cartas(deck)
  mostrar_carta_na_mesa(&deck);
  return 0;
}
