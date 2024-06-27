import threading
import random

def merge_sort_concorrente(array):
    if len(array) <= 1:
        return array

    meio = len(array) // 2
    parte_esquerda = array[:meio]
    parte_direita = array[meio:]

    thread_esquerda = threading.Thread(target=merge_sort_concorrente, args=(parte_esquerda,))
    thread_direita = threading.Thread(target=merge_sort_concorrente, args=(parte_direita,))

    thread_esquerda.start()
    thread_direita.start()

    thread_esquerda.join()
    thread_direita.join()

    return merge(array, parte_esquerda, parte_direita)

def merge(array, parte_esquerda, parte_direita):
    indice_esquerda = indice_direita = indice_merged = 0

    while indice_esquerda < len(parte_esquerda) and indice_direita < len(parte_direita):
        if parte_esquerda[indice_esquerda] < parte_direita[indice_direita]:
            array[indice_merged] = parte_esquerda[indice_esquerda]
            indice_esquerda += 1
        else:
            array[indice_merged] = parte_direita[indice_direita]
            indice_direita += 1
        indice_merged += 1

    while indice_esquerda < len(parte_esquerda):
        array[indice_merged] = parte_esquerda[indice_esquerda]
        indice_esquerda += 1
        indice_merged += 1

    while indice_direita < len(parte_direita):
        array[indice_merged] = parte_direita[indice_direita]
        indice_direita += 1
        indice_merged += 1

def gerar_vetor_aleatorio(tamanho, minimo, maximo):
    return [random.randint(minimo, maximo) for _ in range(tamanho)]

if __name__ == "__main__":
    vetor_aleatorio = gerar_vetor_aleatorio(20, 1, 100)
    print("Vetor Gerado:", vetor_aleatorio)

    merge_sort_concorrente(vetor_aleatorio)

    print("Vetor Ordenado:", vetor_aleatorio)
