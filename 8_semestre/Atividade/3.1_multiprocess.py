import json
from pathlib import Path
from collections import defaultdict
import time
import multiprocessing


def formatar_valor(valor):
    return f"R$ {valor:.2f}".replace(".", ",")

def ler_arquivo(arquivo):
    try:
        arquivo_json = open(arquivo, 'r')
        dados = json.load(arquivo_json)
        arquivo_json.close()
        
        # Dicionário para guardar vendas de cada categoria
        vendas = {}
        
        for venda in dados:
            categoria = venda.get('categoria', 'Sem Categoria')
            valor = float(venda.get('valor', 0))
            
            # Adiciona ou soma valor na categoria
            if categoria in vendas:
                vendas[categoria] += valor
            else:
                vendas[categoria] = valor
        
        print(f'Arquivo {arquivo.name} OK!')
        return vendas
            
    except:
        print(f'Erro ao ler arquivo {arquivo.name}!')
        return {}

def somar_resultados(resultados):
    # Soma total de cada categoria
    soma_categorias = {}
    
    for resultado in resultados:
        for categoria in resultado:
            if categoria in soma_categorias:
                soma_categorias[categoria] += resultado[categoria]
            else:
                soma_categorias[categoria] = resultado[categoria]
            
    return soma_categorias

def processar_vendas(num_processos=4):
    pasta = Path(__file__).parent / 'vendas' / 'vendas'
    
    arquivos = []
    for arquivo in pasta.glob('*.json'):
        arquivos.append(arquivo)
    
    print(f"\nProcessando {len(arquivos)} arquivos...")
    print("-" * 30)
    
    pool = multiprocessing.Pool(processes=num_processos)
    
    resultados = pool.map(ler_arquivo, arquivos)
    
    pool.close()
    pool.join()
    
    vendas_total = somar_resultados(resultados)
    return vendas_total, len(arquivos)

# Programa principal
if __name__ == '__main__':
    inicio = time.time()
    
    vendas, total_arquivos = processar_vendas(8) # 4 processos
    
    print("\n" + "-" * 50)
    print(f"Total de arquivos: {total_arquivos}")
    print("-" * 50)
    
    total = 0
    for categoria in sorted(vendas.keys()):
        valor = vendas[categoria]
        print(f"{categoria}: {formatar_valor(valor)}")
        total += valor
    
    print("-" * 50)
    print(f"Total geral: {formatar_valor(total)}")
    print("-" * 50)
    
    # Calcula tempo gasto
    fim = time.time()
    tempo = fim - inicio
    print(f"\nTempo total: {tempo:.4f} segundos")
    
