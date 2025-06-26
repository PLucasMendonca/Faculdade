import time

# Exercício 1: Fibonacci com Programação Dinâmica
# Implementação recursiva simples - vai demorar muito pra valores grandes
def exercicio_1_1(n):
    if n <= 1:
        return n
    return exercicio_1_1(n-1) + exercicio_1_1(n-2)

# Implementação com memoização - top-down
def exercicio_1_2(n, memo=None):
    if memo is None:
        memo = {}
    if n in memo:
        return memo[n]
    if n <= 1:
        return n
    memo[n] = exercicio_1_2(n-1, memo) + exercicio_1_2(n-2, memo)
    return memo[n]

# Implementação iterativa - bottom-up
def exercicio_1_3(n):
    if n <= 1:
        return n
    
    fib = [0] * (n + 1)
    fib[0] = 0
    fib[1] = 1
    
    for i in range(2, n + 1):
        fib[i] = fib[i-1] + fib[i-2]
    
    return fib[n]

# Exercício 2: Problema da Mochila 0/1
def exercicio_2(items, W):
    n = len(items)
    # Tabela DP - linhas são os itens, colunas são os pesos
    dp = [[0 for _ in range(W + 1)] for _ in range(n + 1)]
    
    # Preenchendo a tabela
    for i in range(1, n + 1):
        weight, value = items[i-1]
        for w in range(W + 1):
            if weight <= w:
                # Pego o máximo entre não incluir o item ou incluir
                dp[i][w] = max(dp[i-1][w], dp[i-1][w-weight] + value)
            else:
                # Não posso incluir o item, peso excede capacidade
                dp[i][w] = dp[i-1][w]
    
    # Reconstruindo a solução - quais itens foram escolhidos
    selected_items = []
    w = W
    for i in range(n, 0, -1):
        if dp[i][w] != dp[i-1][w]:
            selected_items.append(i-1)
            w -= items[i-1][0]
    
    return dp[n][W], selected_items

# Exercício 3: Maior Subsequência Comum (LCS)
def exercicio_3(str1, str2):
    m, n = len(str1), len(str2)
    
    # Matriz para DP
    dp = [[0 for _ in range(n + 1)] for _ in range(m + 1)]
    
    # Preenchendo a matriz
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if str1[i-1] == str2[j-1]:
                dp[i][j] = dp[i-1][j-1] + 1
            else:
                dp[i][j] = max(dp[i-1][j], dp[i][j-1])
    
    # Reconstruindo a subsequência
    lcs = []
    i, j = m, n
    while i > 0 and j > 0:
        if str1[i-1] == str2[j-1]:
            lcs.append(str1[i-1])
            i -= 1
            j -= 1
        elif dp[i-1][j] > dp[i][j-1]:
            i -= 1
        else:
            j -= 1
    
    lcs.reverse()
    return dp[m][n], ''.join(lcs)

# Exercício 6: Distância de Edição (Levenshtein)
def exercicio_6(str1, str2):
    m, n = len(str1), len(str2)
    
    # Matriz para DP
    dp = [[0 for _ in range(n + 1)] for _ in range(m + 1)]
    
    # Inicialização - custo para transformar em string vazia
    for i in range(m + 1):
        dp[i][0] = i
    for j in range(n + 1):
        dp[0][j] = j
    
    # Preenchendo a matriz
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if str1[i-1] == str2[j-1]:
                dp[i][j] = dp[i-1][j-1]  # Sem custo se caracteres iguais
            else:
                # Mínimo entre remoção, inserção e substituição
                dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])
    
    # Reconstruindo as operações
    operations = []
    i, j = m, n
    
    while i > 0 or j > 0:
        if i > 0 and j > 0 and str1[i-1] == str2[j-1]:
            # Caracteres iguais
            i -= 1
            j -= 1
        elif i > 0 and j > 0 and dp[i][j] == dp[i-1][j-1] + 1:
            # Substituição
            operations.append(f"Substituir '{str1[i-1]}' por '{str2[j-1]}'")
            i -= 1
            j -= 1
        elif i > 0 and dp[i][j] == dp[i-1][j] + 1:
            # Remoção
            operations.append(f"Remover '{str1[i-1]}'")
            i -= 1
        elif j > 0 and dp[i][j] == dp[i][j-1] + 1:
            # Inserção
            operations.append(f"Inserir '{str2[j-1]}'")
            j -= 1
    
    operations.reverse()
    return dp[m][n], operations

def main():
    # Teste para o Exercício 1: Fibonacci
    fibonacci_values = [5, 10, 20, 30, 40, 45]
    
    print("Exercício 1: Fibonacci com Programação Dinâmica")
    print("-" * 50)
    
    for n in fibonacci_values:
        print(f"\nTeste para n = {n}:")
        
        # Versão recursiva simples é muito lenta para valores grandes
        if n <= 30:
            start_time = time.time()
            result = exercicio_1_1(n)
            end_time = time.time()
            print(f"1.1 Recursiva simples: Fib({n}) = {result}, Tempo: {end_time - start_time:.6f} segundos")
        else:
            print(f"1.1 Recursiva simples: Muito lento para n = {n}, pulando...")
        
        # Versão com memoização
        start_time = time.time()
        result = exercicio_1_2(n)
        end_time = time.time()
        print(f"1.2 Recursiva com memoização: Fib({n}) = {result}, Tempo: {end_time - start_time:.6f} segundos")
        
        # Versão iterativa
        start_time = time.time()
        result = exercicio_1_3(n)
        end_time = time.time()
        print(f"1.3 Iterativa: Fib({n}) = {result}, Tempo: {end_time - start_time:.6f} segundos")
    
    # Teste para o Exercício 2: Problema da Mochila
    print("\n\nExercício 2: Problema da Mochila 0/1")
    print("-" * 50)
    
    # Itens no formato [(peso, valor)]
    items = [(2, 3), (3, 4), (4, 5), (5, 8), (9, 10)]
    max_weight = 20
    
    max_value, selected_items = exercicio_2(items, max_weight)
    print(f"Capacidade máxima da mochila: {max_weight}")
    print(f"Itens disponíveis (peso, valor): {items}")
    print(f"Valor máximo obtido: {max_value}")
    print(f"Itens selecionados (índices): {selected_items}")
    print(f"Itens selecionados (peso, valor): {[items[i] for i in selected_items]}")
    
    # Teste para o Exercício 3: LCS
    print("\n\nExercício 3: Maior Subsequência Comum (LCS)")
    print("-" * 50)
    
    str1 = "AGGTAB"
    str2 = "GXTXAYB"
    
    lcs_length, lcs_string = exercicio_3(str1, str2)
    print(f"String 1: {str1}")
    print(f"String 2: {str2}")
    print(f"Comprimento da LCS: {lcs_length}")
    print(f"LCS: {lcs_string}")
    
    # Teste para o Exercício 6: Distância de Edição
    print("\n\nExercício 6: Distância de Edição (Levenshtein)")
    print("-" * 50)
    
    str1 = "kitten"
    str2 = "sitting"
    
    distance, operations = exercicio_6(str1, str2)
    print(f"String 1: {str1}")
    print(f"String 2: {str2}")
    print(f"Distância de edição: {distance}")
    print("Operações:")
    for op in operations:
        print(f"  - {op}")

if __name__ == "__main__":
    main()
