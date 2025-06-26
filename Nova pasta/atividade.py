# Perceptron - IA
# Nome: Lucas dos Santos
# Matrícula: 2122130042

import numpy as np

class Perceptron:
    def __init__(self, weights, threshold, learning_rate):
        self.weights = np.array(weights)
        self.threshold = threshold
        self.learning_rate = learning_rate
    
    def activate(self, x):
        return 1 if x >= self.threshold else -1
    
    def predict(self, inputs):
        soma = np.dot(inputs, self.weights)
        return self.activate(soma)
    
    def train(self, input_data, expected_output):
        saida = self.predict(input_data)
        erro = expected_output - saida
        
        if erro != 0:
            self.weights += self.learning_rate * erro * input_data
            return True
        return False

# Valores iniciais
pesos = [0.4, -0.6, 0.6]
limiar = 0.5
taxa = 0.4

perceptron = Perceptron(pesos, limiar, taxa)

# Dados treino: (001, -1) e (110, +1)
dados_treino = [
    (np.array([0, 0, 1]), -1),
    (np.array([1, 1, 0]), 1)
]

print("Treinamento:")
print(f"Pesos iniciais: {perceptron.weights}")

epoca = 1
while True:
    mudou = False
    for entrada, alvo in dados_treino:
        if perceptron.train(entrada, alvo):
            mudou = True
        print(f"Época {epoca}, Entrada: {entrada}, Alvo: {alvo}")
        print(f"Pesos: {perceptron.weights}")
    
    if not mudou:
        break
    epoca += 1

print("\nPesos finais:", perceptron.weights)

# Testes
testes = [[1,1,1], [0,0,0], [1,0,0], [0,1,1]]
print("\nClassificação:")
for teste in testes:
    teste = np.array(teste)
    result = perceptron.predict(teste)
    print(f"Entrada {teste}: Classe {result}")
