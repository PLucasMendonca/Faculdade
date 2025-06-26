import json
import time
from pathlib import Path
import multiprocessing
from statistics import median

def deixar_bonito(numero):
    texto = f"R$ {numero:.2f}"
    return texto.replace(".", ",")

def ler_arquivo_json(caminho_arquivo):
    try:
        f = open(caminho_arquivo, 'r')
        dados = json.load(f)
        f.close()
        
        valores_por_categoria = {}
        
        for venda in dados:
            if 'categoria' in venda:
                categoria = venda['categoria']
            else:
                categoria = 'Sem Categoria'
                
            if 'valor' in venda:
                valor = float(venda['valor'])
            else:
                valor = 0
            
            # Adiciona valor na categoria
            if categoria in valores_por_categoria:
                valores_por_categoria[categoria].append(valor)
            else:
                valores_por_categoria[categoria] = [valor]
        
        print(f'Arquivo {caminho_arquivo.name} lido com sucesso!')
        return valores_por_categoria
            
    except:
        print(f'Deu erro ao ler o arquivo {caminho_arquivo.name}!')
        return {}

def juntar_todos_valores(lista_resultados):
    todos_valores = {}
    
    for resultado in lista_resultados:
        for categoria in resultado:
            if categoria in todos_valores:
                # Junta as listas de valores
                lista1 = todos_valores[categoria]
                lista2 = resultado[categoria]
                todos_valores[categoria] = lista1 + lista2
            else:
                todos_valores[categoria] = resultado[categoria]
    
    return todos_valores

def calcular_medianas(numero_processos=4):
    pasta_atual = Path(__file__).parent
    pasta_vendas = pasta_atual / 'vendas' / 'vendas'
    
    arquivos = []
    for arquivo in pasta_vendas.glob('*.json'):
        arquivos.append(arquivo)
    
    print(f"\nVai processar {len(arquivos)} arquivos...")
    print("-" * 30)
    
    # Processa os arquivos em paralelo
    pool = multiprocessing.Pool(processes=numero_processos)
    resultados = pool.map(ler_arquivo_json, arquivos)
    pool.close()
    pool.join()
    
    # Calcula mediana por categoria
    valores = juntar_todos_valores(resultados)
    medianas = {}
    for categoria in valores:
        lista_valores = valores[categoria]
        medianas[categoria] = median(lista_valores)
    
    return medianas, len(arquivos)

if __name__ == '__main__':
    hora_inicio = time.time()
    
    medianas, numero_arquivos = calcular_medianas(8) # 4 processos
    
    print("\n" + "-" * 50)
    print(f"Arquivos processados: {numero_arquivos}")
    print("-" * 50)
    
    for categoria in sorted(medianas.keys()):
        mediana = medianas[categoria]
        print(f"Mediana da categoria {categoria}: {deixar_bonito(mediana)}")
    
    print("-" * 50)
    
    hora_fim = time.time()
    tempo_total = hora_fim - hora_inicio
    print(f"\nDemorou {tempo_total:.4f} segundos para calcular")
