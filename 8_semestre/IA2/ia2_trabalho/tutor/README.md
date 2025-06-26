# Agente Tutor Simplificado com Lógica Fuzzy

**Matrícula:** 2122130042

## Descrição

Este projeto implementa um agente tutor simplificado que utiliza conceitos de lógica fuzzy para analisar o desempenho do aluno em diferentes métodos de aprendizagem (texto, imagem, vídeo) e recomendar uma combinação personalizada. A implementação simplificada não utiliza bibliotecas fuzzy complexas, mas mantém a mesma lógica de regras para determinar as preferências de aprendizagem.

## Estrutura do Projeto

- `agente_tutor_final.py`: Implementação completa do agente tutor simplificado
- `README.md`: Documentação do projeto

## Requisitos

Para executar este projeto, você precisará apenas da biblioteca padrão do Python:

```
json (biblioteca padrão)
```

Não há necessidade de instalar bibliotecas adicionais, tornando o projeto mais leve e fácil de usar.

## Como Usar

1. **Execução do Agente Tutor**

   Para executar o agente tutor com os perfis de exemplo predefinidos:
   
   ```
   python agente_tutor_final.py
   ```

2. **Formato dos Dados de Entrada**

   O agente espera dados no formato JSON com os seguintes campos:
   
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

3. **Processamento de Dados Personalizado**

   Para processar dados personalizados, você pode usar a função `processar_dados`:

   ```python
   import json
   from agente_tutor_final import processar_dados
   
   # Seus dados em formato JSON
   dados_json = '''
   {
     "nu_acertos_texto": 20,
     "nu_erros_texto": 10,
     "nu_acertos_imagem": 15,
     "nu_erros_imagem": 5,
     "nu_acertos_video": 12,
     "nu_erros_video": 18
   }
   '''
   
   # Processar dados
   resultado = processar_dados(dados_json)
   
   # Acessar as partes recomendadas
   partes = resultado["partes"]
   print(json.dumps(partes, indent=2))
   ```

## Formato de Saída JSON

O agente retorna um formato JSON simplificado com as partes numeradas e seus métodos de aprendizagem recomendados:

```json
{
  "parte1": "texto",
  "parte2": "texto",
  "parte3": "imagem"
}
```

Cada parte pode ser um dos três métodos de aprendizagem: `"texto"`, `"imagem"` ou `"video"`. A distribuição é baseada na eficácia de cada método para o aluno específico.

## Lógica Fuzzy Simplificada

O agente implementa uma simplificação da lógica fuzzy com as seguintes regras:

1. Se vídeo é alto e os demais são baixos, então preferência é vídeo
2. Se texto é alto e os demais são baixos, então preferência é texto
3. Se imagem é alto e os demais são baixos, então preferência é imagem
4. Se todas as taxas de acerto são baixas, então preferência é texto
5. Se todas as taxas de acerto são altas, então preferência é texto

Em vez de usar bibliotecas fuzzy complexas, o código implementa diretamente essas regras com condições simples baseadas em limites de taxas de acerto.

## Autor

Este projeto foi desenvolvido como parte do trabalho da disciplina de IA2.

**Matrícula:** 2122130042
