#include <stdio.h>

void inserirOrd(int n, char arvore[n], char valor) {
    // Verifica se a árvore está vazia
    if (arvore[0] == '\0') {
        arvore[0] = valor;
        return;
    }

    int pos = 0;
    while (1) {
        if (valor < arvore[pos]) {
            // Navega para o nó filho à esquerda
            pos = 2 * pos + 1;

            if (pos >= n) {
                printf("Não é possível inserir esse valor!\n");
                return;
            }

            if (arvore[pos] == '\0') {
                arvore[pos] = valor;
                return;
            }
        } else {
            // Navega para o nó filho à direita
            pos = 2 * pos + 2;

            if (pos >= n) {
                printf("Não é possível inserir esse valor!\n");
                return;
            }

            if (arvore[pos] == '\0') {
                arvore[pos] = valor;
                return;
            }
        }
    }
}

int main() {
    char arvore[10] = {'\0'};

    inserirOrd(10, arvore, 'D');
    inserirOrd(10, arvore, 'B');
    inserirOrd(10, arvore, 'F');
    inserirOrd(10, arvore, 'A');
    inserirOrd(10, arvore, 'C');
    inserirOrd(10, arvore, 'E');
    inserirOrd(10, arvore, 'H');  // Tentativa de inserir um valor quando a árvore está cheia

    for (int i = 0; i < 10; i++) {
        if (arvore[i] != '\0') {
            printf("%c ", arvore[i]);
        }
    }

    return 0;
}