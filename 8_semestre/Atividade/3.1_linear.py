import json
from pathlib import Path
from collections import defaultdict
import time

# Função para deixar o valor em reais
def formatar_dinheiro(valor):
    return f"R$ {valor:.2f}".replace(".", ",")

# Função principal que lê os arquivos e calcula as vendas
def calcular_vendas_por_categoria():
    pasta = Path(__file__).parent / 'vendas' / 'vendas'
    vendas = defaultdict(float)
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
                    vendas[cat] += val
                arquivos_lidos += 1
                print(f'Arquivo {arquivo.name} OK!')
        except Exception as e:
            print(f'Erro no arquivo {arquivo.name}!')
    
    return dict(vendas), arquivos_lidos

# Programa principal
if __name__ == '__main__':
    inicio = time.time()

    vendas, total_arquivos = calcular_vendas_por_categoria()
    total = sum(vendas.values())
    
    print("\n" + "-" * 50)
    print(f"Arquivos processados: {total_arquivos}")
    print("-" * 50)
    
    for categoria, valor in sorted(vendas.items()):
        print(f"{categoria}: {formatar_dinheiro(valor)}")
    
    print("-" * 50)
    print(f"Total: {formatar_dinheiro(total)}")
    print("-" * 50)
    
    tempo = time.time() - inicio
    print(f"\nTempo de execução: {tempo:.4f} segundos")
