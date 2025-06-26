import json
import os
from avaliador import avaliar_respostas, formatar_saida

"""
Script para testar a integração entre o Avaliador e o Tutor
Demonstra o fluxo completo de avaliação de exercícios e recomendação de métodos de aprendizagem.

Matrícula: 2122130042
"""

def main():
    """Função principal para testar a integração."""
    print("\n" + "*" * 70)
    print("*" + " " * 68 + "*")
    print("*  TESTE DE INTEGRAÇÃO: AVALIADOR + TUTOR - MATRÍCULA: 2122130042" + " " * 6 + "*")
    print("*" + " " * 68 + "*")
    print("*" * 70)
    
    # Carregar dados de exemplo
    arquivo_exercicios = "exemplo_exercicios.json"
    
    if not os.path.exists(arquivo_exercicios):
        print(f"\nERRO: Arquivo {arquivo_exercicios} não encontrado!")
        return
    
    with open(arquivo_exercicios, "r", encoding="utf-8") as f:
        dados_exercicios = f.read()
    
    # Processar com o avaliador
    print("\n" + "=" * 70)
    print("AVALIANDO EXERCÍCIOS DO ALUNO")
    print("=" * 70)
    
    resultado_avaliacao = avaliar_respostas(dados_exercicios)
    saida_formatada = formatar_saida(resultado_avaliacao)
    
    # Exibir informações do aluno e da aula
    dados_json = json.loads(dados_exercicios)
    aluno = dados_json.get("aluno", {})
    aula = dados_json.get("aula", {})
    
    print(f"\nALUNO: {aluno.get('nome', 'N/A')} (ID: {aluno.get('id', 'N/A')})")
    print(f"AULA: {aula.get('titulo', 'N/A')} (Código: {aula.get('codigo', 'N/A')})")
    
    # Exibir resultados da avaliação
    print("\nRESULTADOS DA AVALIAÇÃO:")
    print("-" * 50)
    print(f"Total de questões: {resultado_avaliacao['total_questoes']['total']}")
    print(f"Total de acertos: {resultado_avaliacao['acertos']['total']}")
    print(f"Taxa de acerto geral: {resultado_avaliacao['taxa_acerto_geral']:.1f}%")
    print(f"Status: {'APROVADO' if saida_formatada['aprovado'] else 'REPROVADO'}")
    
    # Exibir detalhes por tipo de questão
    print("\nDETALHES POR TIPO DE QUESTÃO:")
    print("-" * 50)
    for tipo in ["texto", "imagem", "video"]:
        total = resultado_avaliacao['total_questoes'][tipo]
        acertos = resultado_avaliacao['acertos'][tipo]
        taxa = (acertos / total * 100) if total > 0 else 0
        print(f"{tipo.upper()}: {acertos}/{total} ({taxa:.1f}%)")
    
    # Verificar se precisa de recomendação do tutor
    if not saida_formatada['aprovado']:
        print("\nALUNO REPROVADO - RECOMENDAÇÃO DO TUTOR:")
        print("-" * 50)
        
        if "partes" in saida_formatada:
            partes = saida_formatada["partes"]
            if "erro" in partes:
                print(f"Erro ao obter recomendação: {partes['erro']}")
            else:
                print("Recomendação de métodos de aprendizagem para refazer a aula:")
                for parte, metodo in partes.items():
                    print(f"- {parte}: {metodo.upper()}")
        else:
            print("Nenhuma recomendação disponível.")
    
    # Salvar resultado em arquivo JSON
    nome_arquivo = "resultado_integracao.json"
    with open(nome_arquivo, "w", encoding="utf-8") as f:
        json.dump(saida_formatada, f, indent=2)
    
    print(f"\nResultado completo salvo em: {nome_arquivo}")
    
    # Exibir formato de saída JSON
    print("\nFORMATO DE SAÍDA JSON:")
    print("-" * 50)
    print(json.dumps(saida_formatada, indent=2))
    
    print("\n" + "*" * 70)
    print("*" + " " * 68 + "*")
    print("*  TESTE DE INTEGRAÇÃO CONCLUÍDO!" + " " * 37 + "*")
    print("*" + " " * 68 + "*")
    print("*" * 70)

if __name__ == "__main__":
    main()
