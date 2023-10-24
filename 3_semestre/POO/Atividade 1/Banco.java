public class Banco {
    String nome;
    String cpf;
    double saldo;

    public Banco(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        saldo = 0;

    }
    public double sacar(double valorSaque){
        if(valorSaque > saldo){
            System.out.println("Saldo insuficiente!");
            return 0;
        }
        saldo = saldo - valorSaque;
        return valorSaque;
    }
    public void depoistar(double valor){
        saldo = saldo + valor;
    }
    public void exibirSaldo(){
        System.out.println("Saldo disponivel: " + saldo);
    }

public static void main (String[] args){
    Banco conta1 = new Banco("Lucas","063.228.261-40");

}
}

