import json
from agente_tutor_final import processar_dados

# Matrícula: 2122130042

print("\n" + "*" * 70)
print("*" + " " * 68 + "*")
print("*  EXEMPLO DE ENTRADA E SAÍDA DO AGENTE TUTOR - MATRÍCULA: 2122130042" + " " * 4 + "*")
print("*" + " " * 68 + "*")
print("*" * 70)

# Dados de entrada para um único aluno
dados_entrada = {
    "nu_acertos_texto": 25,
    "nu_erros_texto": 5,
    "nu_acertos_imagem": 10,
    "nu_erros_imagem": 10,
    "nu_acertos_video": 8,
    "nu_erros_video": 12
}

print("\n" + "=" * 70)
print("FORMATO DE ENTRADA (JSON):")
print("=" * 70)
print(json.dumps(dados_entrada, indent=2))

# Processar dados
dados_entrada_json = json.dumps(dados_entrada)
resultado = processar_dados(dados_entrada_json)

print("\n" + "=" * 70)
print("ANÁLISE INTERMEDIÁRIA:")
print("=" * 70)
print(f"Taxas de acerto:")
for metodo, taxa in resultado["taxas_acerto"].items():
    print(f"- {metodo.upper()}: {taxa*100:.1f}%")
print(f"\nPreferência identificada: {resultado['preferencia'].upper()}")

print("\n" + "=" * 70)
print("FORMATO DE SAÍDA (JSON):")
print("=" * 70)
print(json.dumps(resultado["partes"], indent=2))

# Salvar os resultados em arquivos JSON para referência
with open("exemplo_entrada.json", "w", encoding="utf-8") as f:
    json.dump(dados_entrada, f, indent=2)

with open("exemplo_saida.json", "w", encoding="utf-8") as f:
    json.dump(resultado["partes"], f, indent=2)

print("\n" + "*" * 70)
print("*" + " " * 68 + "*")
print("*  Os arquivos exemplo_entrada.json e exemplo_saida.json foram criados" + " " * 3 + "*")
print("*" + " " * 68 + "*")
print("*" * 70)
