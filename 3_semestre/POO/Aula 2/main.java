public class main {

    public static void main(String[] args) {

       System.out.println("Questão 4:");

       Contador c1 = new Contador();
       Contador c2 = new Contador();
       c1.exibir();
       c1.incrementar();
       c1.exibir();
       c2.exibir();
       c2.incrementar();
       c2.incrementar();
       c2.incrementar();
       c2.exibir();
       c1.zerar();
       c1.exibir();
       System.out.println("==============================================");

       System.out.println("Questão 5:");

       Ponto pC = new Ponto();

       pC.mover();
       pC.exibirCoordenadas();
       pC.incrementarx();
       pC.incrementarx();
       pC.incrementarx();
       pC.exibirCoordenadas();
       pC.decrementarx();
       pC.decrementarx();
       pC.decrementary();
       pC.decrementary();
       pC.exibirCoordenadas();

    }
}
