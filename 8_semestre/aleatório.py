import random
import numpy as np
from typing import List, Union

class AlgoritmoGenetico:
    def __init__(self, tamanho_populacao: int = 10, num_cromossomos: int = 3):
        self.tamanho_populacao = tamanho_populacao
        self.num_cromossomos = num_cromossomos
        
    def criar_populacao_inicial(self, min_valor: float = -10.0, max_valor: float = 10.0) -> List[List[float]]:
        return [
            [random.uniform(min_valor, max_valor) for _ in range(self.num_cromossomos)]
            for _ in range(self.tamanho_populacao)
        ]

def obj_function(x: List[float]) -> float:
    if not x:
        raise ValueError("A lista de cromossomos não pode estar vazia")
        
    Ncrom = len(x)
    obj_value = 10

    for j in range(Ncrom):
        obj_value -= x[j] ** 2

    return obj_value

def fitness(pop: List[List[float]]) -> List[float]:
    if not pop:
        raise ValueError("A população não pode estar vazia")
        
    Nind = len(pop)
    fitness_values = []

    for i in range(Nind):
        try:
            fitness_value = obj_function(pop[i])
            fitness_values.append(fitness_value)
        except Exception as e:
            print(f"Erro ao calcular fitness do indivíduo {i}: {str(e)}")
            fitness_values.append(float('-inf'))

    return fitness_values

def roleta_selection(population: List[List[float]], fitness_values: List[float], 
                    num_selections: int) -> List[List[float]]:
    if not population or not fitness_values or num_selections <= 0:
        raise ValueError("Parâmetros inválidos para seleção por roleta")
    
    if len(population) != len(fitness_values):
        raise ValueError("O tamanho da população e a quantidade de valores de fitness devem ser iguais")

    min_fitness = min(fitness_values)
    adjusted_fitness = [f - min_fitness + 1 if min_fitness < 0 else f + 1 for f in fitness_values]
    
    total_fitness = sum(adjusted_fitness)
    probabilities = [f / total_fitness for f in adjusted_fitness]
    
    selected_indices = np.random.choice(
        len(population), 
        size=num_selections, 
        p=probabilities, 
        replace=True
    )
    
    return [population[i] for i in selected_indices]

def main():
    ag = AlgoritmoGenetico(tamanho_populacao=5, num_cromossomos=3)
    
    populacao = ag.criar_populacao_inicial()
    print("\n=== População Inicial ===")
    for i, ind in enumerate(populacao):
        print(f"Indivíduo {i + 1}: {[f'{x:.2f}' for x in ind]}")
    
    fitness_values = fitness(populacao)
    print("\n=== Valores de Fitness ===")
    for i, f in enumerate(fitness_values):
        print(f"Indivíduo {i + 1}: {f:.2f}")
    
    num_selecoes = 3
    selecionados = roleta_selection(populacao, fitness_values, num_selecoes)
    print(f"\n=== {num_selecoes} Indivíduos Selecionados pela Roleta ===")
    for i, ind in enumerate(selecionados):
        print(f"Selecionado {i + 1}: {[f'{x:.2f}' for x in ind]}")

if __name__ == "__main__":
    try:
        main()
    except Exception as e:
        print(f"Erro durante a execução: {str(e)}")
