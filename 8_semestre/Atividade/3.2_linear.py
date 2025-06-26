import json
from pathlib import Path
from collections import defaultdict
import time
import statistics

# Função para deixar o valor em reais
def formatar_dinheiro(valor):
    return f"R$ {valor:.2f}".replace(".", ",")

# Função principal que lê os arquivos e calcula as medianas
def calcular_medianas_por_categoria():
    pasta = Path(__file__).parent / 'vendas' / 'vendas'
    # Dicionário que vai guardar lista de valores para cada categoria
    vendas_por_categoria = defaultdict(list)
    arquivos_lidos = 0
    
    print("\nLendo arquivos de vendas...")
    print("-" * 30)
    
    for arquivo in pasta.glob('*.json'):
        try:
            with open(arquivo, 'r') as f:
                dados = json.load(f)
                for venda in dados:
                    cat = venda.get('categoria', 'Sem Categoria')
                    val = float(venda.get('valor', 0))
                    vendas_por_categoria[cat].append(val)
                arquivos_lidos += 1
                print(f'Arquivo {arquivo.name} OK!')
        except Exception as e:
            print(f'Erro no arquivo {arquivo.name}!')
    
    # Calcula a mediana para cada categoria
    medianas = {}
    for categoria, valores in vendas_por_categoria.items():
        medianas[categoria] = statistics.median(valores)
    
    return medianas, arquivos_lidos

# Programa principal
if __name__ == '__main__':
    inicio = time.time()

    medianas, total_arquivos = calcular_medianas_por_categoria()
    
    print("\n" + "-" * 50)
    print(f"Arquivos processados: {total_arquivos}")
    print("-" * 50)
    print("MEDIANA DE VENDAS POR CATEGORIA:")
    
    for categoria, mediana in sorted(medianas.items()):
        print(f"{categoria}: {formatar_dinheiro(mediana)}")
    
    print("-" * 50)
    
    fim = time.time()
    tempo = fim - inicio
    print(f"\nTempo de execução: {tempo:.4f} segundos")