import java.util.Random;
public class Ponto {
    int x;
    int y;

    public Ponto(){
        x = 0;
        y = 0;
    }
    public void mover(){
    Random random = new Random();
    x = random.nextInt(100);
    y = random.nextInt(100);
    }
    public void incrementarx(){
        x ++;
    }
    public void decrementarx(){
        x --;
    }
    public void incrementary(){
        y ++;
    }
    public void decrementary(){
        y++;
    }
    public void exibirCoordenadas(){
        System.out.println("Coordenadas de X:" + x);
        System.out.println("Coordenadas de Y:" + y);

    }
}
