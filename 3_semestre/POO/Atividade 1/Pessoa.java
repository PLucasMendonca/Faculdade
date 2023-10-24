public class Pessoa{
    // atributos
    String nome;
    int idade;
    String genero;

    double posX;
    double posY;

    // contruor da calsse Pessoa
    public Pessoa(String nome, int idade, String genero){
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        posX = 0;
        posY = 0;

    }

    //Metodos
    public void andar (double x, double y){
        posX = x;
        posY = y;
    }

    public void falar(String texto){
        System.out.println(texto);
    }
    public static void main (String[] args){
        Pessoa pessoa1 = new Pessoa("Lucas", 21,"Masculino");
        pessoa1.nome = "Lucas";
        pessoa1.idade = 21;
        pessoa1.genero = "Masculino";
        pessoa1.posX = 0;
        pessoa1.posY = 0;

        pessoa1.falar("Bom dia!");
    }

}
