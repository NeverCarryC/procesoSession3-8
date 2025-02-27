package ratonHiloBasico;

public class bRaton extends Thread{
    private String nombre;
    private int tiempoAlimentacion;

    public bRaton(String nombre, int tiempoAlimentacion){
        this.nombre = nombre;
        this.tiempoAlimentacion = tiempoAlimentacion;
    }

    public void run(){
        try {
            System.out.printf("El raton %S ha comenzado a alimentarse%n",nombre);
            Thread.sleep(tiempoAlimentacion*1000);
            System.out.printf("El raton %s ha terminado de alimentarse%n", nombre);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        bRaton fievel = new bRaton("fievel", 4);
        bRaton jerry = new bRaton("jerry",5);
        bRaton pinky = new bRaton("pinkty", 3);
        bRaton mickey = new bRaton("mickey", 6);
        fievel.start();
        jerry.start();
        pinky.start();
        mickey.start();
    }
}
