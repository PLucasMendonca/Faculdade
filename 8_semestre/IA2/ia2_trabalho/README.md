# Sistema de Avaliação e Tutoria com Lógica Fuzzy Simplificada

**Matrícula: 2122130042**

Este projeto implementa um sistema simplificado de avaliação de exercícios e tutoria personalizada utilizando conceitos de lógica fuzzy. O sistema é composto por dois módulos principais:

1. **Avaliador de Exercícios** (`avaliador.py`)
2. **Agente Tutor** (`agente_tutor.py`)

## Avaliador de Exercícios

O avaliador recebe um JSON com questões, respostas corretas e respostas do aluno, e calcula a taxa de acerto por tipo de conteúdo (texto, imagem, vídeo). Se o aluno tiver uma taxa de acerto menor que 40%, o avaliador chama o agente tutor para recomendar métodos de aprendizagem personalizados.

### Formato de Entrada (JSON)

```json
{
  "questoes": [
    {"tipo": "texto", "resposta_correta": "A", "resposta_aluno": "A"},
    {"tipo": "imagem", "resposta_correta": "B", "resposta_aluno": "X"},
    {"tipo": "video", "resposta_correta": "C", "resposta_aluno": "C"}
  ]
}
```

### Formato de Saída (JSON)

Para aluno aprovado:
```json
{
  "acertos": {"texto": 1, "imagem": 0, "video": 1},
  "erros": {"texto": 0, "imagem": 1, "video": 0},
  "nota": 66.7,
  "aprovado": true
}
```

Para aluno reprovado (com recomendações do tutor):
```json
{
  "acertos": {"texto": 1, "imagem": 0, "video": 0},
  "erros": {"texto": 1, "imagem": 2, "video": 2},
  "nota": 16.7,
  "aprovado": false,
  "partes": {
    "parte1": "texto",
    "parte2": "texto",
    "parte3": "imagem"
  }
}
```

## Agente Tutor

O agente tutor implementa uma lógica fuzzy simplificada para determinar qual método de aprendizagem o aluno tem mais facilidade. Ele recebe dados de acertos e erros para cada método e retorna recomendações personalizadas.

### Regras Fuzzy Simplificadas

1. Se taxa de acerto em vídeo é alta e as demais são baixas, então preferência é vídeo
2. Se taxa de acerto em texto é alta e as demais são baixas, então preferência é texto
3. Se taxa de acerto em imagem é alta e as demais são baixas, então preferência é imagem
4. Se todas as taxas de acerto são baixas, então preferência é texto
5. Se todas as taxas de acerto são altas, então preferência é texto

### Formato de Entrada (JSON)

```json
{
  "nu_acertos_texto": 25,
  "nu_erros_texto": 5,
  "nu_acertos_imagem": 10,
  "nu_erros_imagem": 10,
  "nu_acertos_video": 8,
  "nu_erros_video": 12
}
```

### Formato de Saída (JSON)

```json
{
  "partes": {
    "parte1": "texto",
    "parte2": "texto",
    "parte3": "imagem"
  }
}
```

## Como Usar

### Avaliador

```python
from avaliador import avaliar_respostas, formatar_saida
import json

# Carregar dados de exercícios
with open("exemplo_exercicios.json", "r", encoding="utf-8") as f:
    dados_exercicios = f.read()

# Avaliar respostas
resultado = avaliar_respostas(dados_exercicios)

# Formatar saída
saida = formatar_saida(resultado)

# Exibir resultado
print(json.dumps(saida, indent=2))
```

### Agente Tutor (uso independente)

```python
from agente_tutor import processar_dados
import json

# Dados de exemplo
dados = {
    "nu_acertos_texto": 25,
    "nu_erros_texto": 5,
    "nu_acertos_imagem": 10,
    "nu_erros_imagem": 10,
    "nu_acertos_video": 8,
    "nu_erros_video": 12
}

# Processar dados
resultado = processar_dados(json.dumps(dados))

# Exibir resultado
print(json.dumps(resultado, indent=2))
```

## Arquivos Incluídos

- `avaliador.py`: Implementação do avaliador de exercícios
- `agente_tutor.py`: Implementação do agente tutor com lógica fuzzy simplificada
- `exemplo_exercicios.json`: Exemplo de arquivo JSON com questões e respostas
- `README.md`: Este arquivo de documentação

## Observações

- O sistema não depende de bibliotecas externas complexas, apenas da biblioteca padrão do Python.
- A lógica fuzzy foi simplificada e implementada diretamente com condições baseadas em limites de taxas de acerto.
- O formato JSON foi escolhido para facilitar a integração com outros sistemas.
