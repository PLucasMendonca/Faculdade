public class Contador {
// estados da classe
    int contador;

    public Contador(){
      contador = 0;
    }
    public void incrementar() {
        contador ++;
    }
    public void zerar() {
        contador = 0;
    }
    public void exibir() {
        System.out.println("contador:" + contador);
    }

}
