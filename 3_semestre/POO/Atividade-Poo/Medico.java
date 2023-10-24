public class Medico extends Empregado {

  private String crm;

  public String getCrm() {
    return crm;
  }

  public void setCrm(String novoCrm) {
    this.crm = novoCrm;
  }

  @Override // sobrescrita ou polimorfismo
  public void setNome(String novoNome) {
    this.nome = "Dr. " + novoNome;
  }

  // Sobrecarga
  public void setNome(String sufixo, String novoNome) {
    this.nome = sufixo + " " + novoNome; 
  }
  // sobrescrita
  public void setNome(String sufixo, String novoNome) {
    this.nome = sufixo + " - " + novoNome; 
  }

  public void prescreverReceita(String paciente) {
    System.out.println("Aqui implementa a prescrição de receita ao paciente " + paciente);
  }

  // Sobrecarga
  public void prescreverReceita(String paciente, String[] medicamentos) {
    System.out.println("Aqui implementa a prescrição dos medicamentos ("+ medicamentos + ") ao paciente " + paciente);
  }

  @Override // sobrescrita ou polimorfismo
  public String toString() {
    return "nome: " + this.nome + "\n"
          + "Data Nasc: " + this.dataNasc + "\n"
          + "CRM: " + this.crm;
  }
}