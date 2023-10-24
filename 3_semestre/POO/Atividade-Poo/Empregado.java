import java.util.Date;

public abstract class Empregado {
  protected String nome;
  protected Date dataNasc;

  // getters e setters
  public String getNome() {
    return nome;
  }

  public void setNome(String novoNome) {
    this.nome = novoNome;
  }

  public Date getDate() {
    return dataNasc;
  }

  public void setDataNasc(Date novaDataNasc) {
    this.dataNasc = novaDataNasc;
  }
}