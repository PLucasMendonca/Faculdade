
import requests
import sys
import urllib3
from collections import deque

def obter_labirinto():

  urllib3.disable_warnings()
  response = requests.get('https://gtm.delary.dev/labirintos', verify=False)
  if response.status_code == 200:
      return response.json()
  else:
      print(f"A solicitação falhou com o código de status {response.status_code}")
      return None

labirinto = obter_labirinto()
print(labirinto)

def iniciar_labirinto(nome_labirinto, id_jogador):
  urllib3.disable_warnings()
  url_iniciar = "https://gtm.delary.dev/iniciar"

  dados_requisicao = {
      "id": id_jogador,
      "labirinto": nome_labirinto
  }

  resposta_iniciar = requests.post(url_iniciar, json=dados_requisicao, verify = False)

  if resposta_iniciar.status_code == 200:
      print("Resposta do /iniciar:")
      return resposta_iniciar.json()
  else:
      print(f"A solicitação falhou com o código de status {resposta_iniciar.status_code}")
      return None

def movimentar_labirinto(nome_labirinto,id_jogador, nova_posicao):
  urllib3.disable_warnings()
  url_movimentar = "https://gtm.delary.dev/movimentar"

  dados_requisicao = {
      "id": id_jogador,
      "labirinto": nome_labirinto,
      "nova_posicao": nova_posicao
  }
  resposta_movimentar = requests.post(url_movimentar, json=dados_requisicao, verify = False)

  if resposta_movimentar.status_code == 200:
      print("Resposta do /movimentar:")
      return resposta_movimentar.json()
  else:
      print(f"A solicitação falhou com o código de status {resposta_movimentar.status_code}")
      return None

inicio_lab = iniciar_labirinto('very-large-maze','Lucas')
visitados = set()
p_atual = inicio_lab
pilha = [(inicio_lab['pos_atual'])]
final = None
visitados.add((inicio_lab['pos_atual']))
grafo = {}
while True:
  movs = 0
  for movimento in p_atual['movimentos']:
    if movimento not in visitados:
      print(p_atual)
      p_atual = movimentar_labirinto('very-large-maze', 'Lucas', movimento)
      print(p_atual)
      print(pilha)
      if p_atual['final']:
        final = p_atual['pos_atual']
      pilha.append(p_atual['pos_atual'])
      visitados.add(p_atual['pos_atual'])
      movs = 1
      print(pilha)
      break
  if movs == 0 and len(pilha) > 1:
    pilha.pop()
    novo_ponto = movimentar_labirinto('very-large-maze', 'Lucas', pilha[-1])
    p_atual = novo_ponto
    print(p_atual)
  else:
    if len(pilha) > 1:
      continue
    else:
      break