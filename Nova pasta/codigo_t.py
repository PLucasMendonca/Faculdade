
#3.1, 3.2, 4.1, 4.2, 9.1, 9.2, 10.1, 10.2, 12.1, 12.2, 13.1, 13.2, 14.1, 14.2

#3.1:

def fatorial(n):
    if n <= 1:
        return 1
    return n * fatorial(n - 1)

#3.2
def fatorial_iterativo(n):
    resultado = 1
    for i in range(1, n + 1):
        resultado *= i
    return resultado

#4.1
def binary_search(arr, target):
    left, right = 0, len(arr) - 1
    while left <= right:
        mid = (left + right) // 2
        if arr[mid] == target:
            return mid
        elif arr[mid] < target:
            left = mid + 1
        else:
            right = mid - 1
    return -1

#4.2
def linear_search(arr, target):
    for i in range(len(arr)):
        if arr[i] == target:
            return i
    return -1

#9.1
def produto_triplo_maximo(arr):
    n = len(arr)
    max_produto = float('-inf')
    for i in range(n):
        for j in range(i + 1, n):
            for k in range(j + 1, n):
                produto = arr[i] * arr[j] * arr[k]
                if produto > max_produto:
                    max_produto = produto
    return max_produto

#9.2
def produto_triplo_maximo_otimizado(arr):
    arr.sort()
    return arr[-1] * arr[-2] * arr[-3]

#10.1
def quick_sort(arr):
    if len(arr) <= 1:
        return arr
    pivo = arr[len(arr) // 2]
    esquerda = [x for x in arr if x < pivo]
    meio = [x for x in arr if x == pivo]
    direita = [x for x in arr if x > pivo]
    return quick_sort(esquerda) + meio + quick_sort(direita)

#10.2
def quick_sort_pior(arr):
    if len(arr) <= 1:
        return arr
    pivo = arr[0]
    left = [x for x in arr[1:] if x < pivot]
    middle = [pivot]
    right = [x for x in arr[1:] if x > pivot]
    return quick_sort_pior(left) + middle + quick_sort_pior(right)

#12.1
def combinacao(n,k, memo={}):
    if k == 0 or k == n:
        return 1
    if (n, k) in memo:
        return memo[(n, k)]
    memo[(n, k)] = combinacao(n - 1, k - 1, memo) + combinacao(n - 1, k, memo)
    return memo[(n, k)]

#12.2
def combinacao_sem_memo(n, k):
    if k == 0 or k == n:
        return 1
    return combinacao_sem_memo(n - 1, k - 1) + combinacao_sem_memo(n - 1, k)

#13.1
def is_prime(n):
    if n <= 1:
        return False
    if n <= 3:
        return True
    if n % 2 == 0 or n % 3 == 0:
        return False
    i = 5
    while i * i <= n:
        if n % i == 0 or n % (i + 2) == 0:
            return False
        i += 6
    return True

#13.2
def is_prime_naive(n):
    if n <= 1:
        return False
    for i in range(2, n):
        if n % i == 0:
            return False
    return True

#14.1
def has_cycle(graph):
    visited = set()
    rec_stack = set()
    def dfs_cycle(node):
        visited.add(node)
        rec_stack.add(node)
        for neighbor in graph[node]:
            if neighbor not in visited:
                if dfs_cycle(neighbor):
                    return True
            elif neighbor in rec_stack:
                return True
        rec_stack.remove(node)
        return False
    for node in graph:
        if node not in visited:
            if dfs_cycle(node):
                return True
    return False

#14.2
from collections import deque

def has_cycle(graph):
    visited = set()
    for start in graph:
        if start not in visited:
            queue = deque([start])
            parent = {start: None}
            while queue:
                node = queue.popleft()
                if node not in visited:
                    visited.add(node)
                    for neighbor in graph[node]:
                        if neighbor not in visited:
                            queue.append(neighbor)
                            parent[neighbor] = node
                        elif parent[node] != neighbor:
                            return True
    return False