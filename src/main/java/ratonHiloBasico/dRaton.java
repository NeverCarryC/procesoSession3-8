package ratonHiloBasico;

import java.util.Random;

public class dRaton extends Thread {
    private String nombre;
    private static int tiempoAlimentacion=0;

    Random random = new Random();
    public dRaton(String nombre){
        this.nombre = nombre;
    }

    public void comer(){
        try {
            System.out.println("EL raton " + nombre + " ha comenzado a alimentarse");
            int randomMilisegundos = 10 + random.nextInt(901);
            Thread.sleep(randomMilisegundos);
            tiempoAlimentacion += randomMilisegundos;
            System.out.println("El raton " + nombre + " ha terminado de alimentarse y ha tardado " + randomMilisegundos);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void run(){
        this.comer();
    }

    public static void main(String[] args) {
        dRaton r1 = new dRaton("1");
        dRaton r2 = new dRaton("2");
        dRaton r3 = new dRaton("3");
        dRaton r4 = new dRaton("4");

        r1.start();
        r2.start();
        r3.start();
        r4.start();

        try {
            r1.join();
            r2.join();
            r3.join();
            r4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("tiempo total: " + tiempoAlimentacion);
    }
}
