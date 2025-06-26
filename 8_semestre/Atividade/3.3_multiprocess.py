import json
import time
from pathlib import Path
import multiprocessing as mp
from statistics import stdev

def formata_reais(num):
    return f"R$ {num:.2f}".replace(".", ",")

def le_arquivo(arquivo):
    try:
        arquivo_aberto = open(arquivo, 'r')
        vendas = json.load(arquivo_aberto)
        arquivo_aberto.close()
        
        valores = {}
        
        for v in vendas:
            cat = v.get('categoria', 'Sem Categoria')
            val = float(v.get('valor', 0))
            
            # Guarda valor na categoria
            if cat in valores:
                valores[cat].append(val)
            else:
                valores[cat] = [val]
        
        print(f'Li o arquivo {arquivo.name}!')
        return valores
            
    except:
        print(f'Deu erro no arquivo {arquivo.name}!')
        return {}

def junta_valores(lista):
    valores = {}
    
    for res in lista:
        for cat in res:
            if cat in valores:
                # Junta as listas de valores
                valores[cat] = valores[cat] + res[cat]
            else:
                valores[cat] = res[cat]
    
    return valores

def calcula_desvio(num_processos=4):
    pasta = Path(__file__).parent / 'vendas' / 'vendas'
    arquivos = list(pasta.glob('*.json'))
    
    print(f"\nVou ler {len(arquivos)} arquivos...")
    print("-" * 30)
    
    # Processa em paralelo
    processos = mp.Pool(processes=num_processos)
    resultados = processos.map(le_arquivo, arquivos)
    processos.close()
    processos.join()
    
    valores = junta_valores(resultados)
    
    # Calcula desvio padrao
    desvios = {}
    for cat in valores:
        nums = valores[cat]
        desvios[cat] = stdev(nums)
    
    return desvios, len(arquivos)

if __name__ == '__main__':
    inicio = time.time()
    
    desvios, total = calcula_desvio(8) # 4 processos
    
    print("\n" + "-" * 50)
    print(f"Li {total} arquivos!")
    print("-" * 50)
    
    for cat in sorted(desvios.keys()):
        dp = desvios[cat]
        print(f"Desvio padrão {cat}: {formata_reais(dp)}")
    
    print("-" * 50)
    
    fim = time.time()
    tempo = fim - inicio
    print(f"\nDemorou {tempo:.4f} segundos!")
