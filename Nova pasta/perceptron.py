import numpy as np

class Perceptron:
    def __init__(self, weights, threshold, learning_rate):
        self.weights = np.array(weights)
        self.threshold = threshold
        self.learning_rate = learning_rate
    
    def activate(self, x):
        # Função de ativação step
        return 1 if x >= self.threshold else -1
    
    def predict(self, inputs):
        # Calcula a soma ponderada e aplica a função de ativação
        weighted_sum = np.dot(inputs, self.weights)
        return self.activate(weighted_sum)
    
    def train(self, input_data, expected_output):
        # Realiza o treinamento para um exemplo
        actual_output = self.predict(input_data)
        error = expected_output - actual_output
        
        # Atualiza os pesos se houver erro
        if error != 0:
            self.weights += self.learning_rate * error * input_data
            return True
        return False

# Inicialização dos parâmetros
initial_weights = [0.4, -0.6, 0.6]
threshold = 0.5
learning_rate = 0.4

# Criação do perceptron
perceptron = Perceptron(initial_weights, threshold, learning_rate)

# Dados de treinamento
training_data = [
    (np.array([0, 0, 1]), -1),  # (001, -1)
    (np.array([1, 1, 0]), 1)    # (110, +1)
]

# Treinamento
print("Treinamento:")
print(f"Pesos iniciais: {perceptron.weights}")

epoch = 1
while True:
    changes = False
    for inputs, target in training_data:
        if perceptron.train(inputs, target):
            changes = True
        print(f"Época {epoch}, Entrada: {inputs}, Alvo: {target}, Pesos atuais: {perceptron.weights}")
    
    if not changes:
        break
    epoch += 1

print("\nTreinamento concluído!")
print(f"Pesos finais: {perceptron.weights}")

# Teste com novos exemplos
test_cases = [
    [1, 1, 1],  # 111
    [0, 0, 0],  # 000
    [1, 0, 0],  # 100
    [0, 1, 1]   # 011
]

print("\nClassificação de novos exemplos:")
for test_input in test_cases:
    test_input = np.array(test_input)
    result = perceptron.predict(test_input)
    print(f"Entrada {test_input}: Classe {result}")
