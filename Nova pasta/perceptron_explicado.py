# Atividade de IA - Implementação do Perceptron
# Nome: [Seu Nome]
# RA: [Seu RA]
# Disciplina: Inteligência Artificial
# Prof: [Nome do Professor]

# Importa numpy pra fazer os cálculos mais fácil
import numpy as np

class Perceptron:
    def __init__(self, weights, threshold, learning_rate):
        # Guarda os valores iniciais que o professor pediu
        self.weights = np.array(weights)  # w1=0.4, w2=-0.6, w3=0.6
        self.threshold = threshold        # limiar = 0.5
        self.learning_rate = learning_rate  # taxa aprendizado = 0.4
    
    def activate(self, x):
        # Função degrau que o professor explicou na aula
        # Se for maior que o limiar, retorna +1
        # Se for menor, retorna -1
        return 1 if x >= self.threshold else -1
    
    def predict(self, inputs):
        # Faz o somatório: x1*w1 + x2*w2 + x3*w3
        # np.dot faz essa multiplicação e soma automaticamente
        soma = np.dot(inputs, self.weights)
        return self.activate(soma)
    
    def train(self, input_data, expected_output):
        # Treina a rede usando a regra que tava no slide
        # 1. Calcula a saída
        saida_atual = self.predict(input_data)
        
        # 2. Calcula o erro (diferença entre o que quero e o que tenho)
        erro = expected_output - saida_atual
        
        # 3. Atualiza os pesos se tiver erro
        # Nova fórmula dos pesos: w = w + taxa_aprendizado * erro * entrada
        if erro != 0:
            self.weights += self.learning_rate * erro * input_data
            return True
        return False

# ---- Parte principal do programa ----

# Valores iniciais que o professor deu no exercício
pesos_iniciais = [0.4, -0.6, 0.6]  # w1, w2, w3
limiar = 0.5
taxa_aprendizado = 0.4

# Cria a rede
perceptron = Perceptron(pesos_iniciais, limiar, taxa_aprendizado)

# Dados de treinamento do exercício
# (001, -1) e (110, +1)
dados_treino = [
    (np.array([0, 0, 1]), -1),  # primeiro exemplo
    (np.array([1, 1, 0]), 1)    # segundo exemplo
]

# Treina a rede
print("=== Treinamento ===")
print(f"Pesos que começamos: {perceptron.weights}")

epoca = 1
while True:
    teve_mudanca = False
    for entradas, alvo in dados_treino:
        if perceptron.train(entradas, alvo):
            teve_mudanca = True
        print(f"Época {epoca}, Testando: {entradas}, Deveria dar: {alvo}")
        print(f"Pesos agora são: {perceptron.weights}")
    
    # Se não mudou nada, então já aprendeu!
    if not teve_mudanca:
        break
    epoca += 1

print("\n=== Rede treinada! ===")
print(f"Pesos finais: {perceptron.weights}")

# Testa com os exemplos que o professor pediu
print("\n=== Testando os exemplos do exercício ===")
testes = [
    [1, 1, 1],  # exemplo 111
    [0, 0, 0],  # exemplo 000
    [1, 0, 0],  # exemplo 100
    [0, 1, 1]   # exemplo 011
]

for teste in testes:
    teste = np.array(teste)
    resultado = perceptron.predict(teste)
    print(f"Entrada {teste}: Deu classe {resultado}")
