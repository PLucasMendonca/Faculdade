import json
from pathlib import Path
from collections import defaultdict
import time
import statistics

def formatar_dinheiro(valor):
    return f"R$ {valor:.2f}".replace(".", ",")

def calcular_desvio_padrao_por_categoria():
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
                    # Guarda cada valor em uma lista por categoria
                    vendas_por_categoria[cat].append(val)
                arquivos_lidos += 1
                print(f'Arquivo {arquivo.name} OK!')
        except Exception as e:
            print(f'Erro no arquivo {arquivo.name}!')
    
    desvios = {}
    for categoria, valores in vendas_por_categoria.items():
        desvios[categoria] = statistics.stdev(valores)
    
    return desvios, arquivos_lidos

# Programa principal
if __name__ == '__main__':
    inicio = time.time()

    desvios, total_arquivos = calcular_desvio_padrao_por_categoria()
    
    print("\n" + "-" * 50)
    print(f"Arquivos processados: {total_arquivos}")
    print("-" * 50)
    print("DESVIO PADRÃO DE VENDAS POR CATEGORIA:")
    
    for categoria, desvio in sorted(desvios.items()):
        print(f"{categoria}: {formatar_dinheiro(desvio)}")
    
    fim = time.time()
    tempo = fim - inicio
    print(f"\nTempo de execução: {tempo:.4f} segundos")