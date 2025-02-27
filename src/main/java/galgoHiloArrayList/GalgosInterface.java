package galgoHiloArrayList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GalgosInterface implements Runnable{
    private String nombre;
    private int tiempo;
    private static final List<GalgosInterface> orden = new ArrayList<>();

    public String getNombre() {
        return nombre;
    }

    public GalgosInterface(String nombre, int tiempo) {
        this.nombre = nombre;
        this.tiempo = tiempo;
    }

    @Override
    public void run() {
        try {
            System.out.println("El galgo " + this.nombre + " empieza a correr");
            Thread.sleep(tiempo);
            System.out.println("El galgo " + this.nombre + " ha terminado");
            synchronized (orden) {
                orden.add(this);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<GalgosInterface> getOrden() {
        return orden;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Cuantos galgos?");
        int numGalgo = sc.nextInt();
        List<Thread> hilos = new ArrayList<>();

        for (int i = 0; i < numGalgo; i++) {
            System.out.println("¿Nombre para " + (i + 1) + "º galgo?");
            String nombre = sc.next();
            System.out.println("¿Tiempo para " + (i + 1) + "º galgo?");
            int tiempo = sc.nextInt();
            Thread hilo = new Thread(new GalgosInterface(nombre, tiempo));
            hilos.add(hilo);
        }

        for (Thread t : hilos) {
            t.start();
        }

        for (Thread t: hilos){
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        int index = 0;
        System.out.println("Resultado");
        for (GalgosInterface g : GalgosInterface.getOrden()){
            System.out.println("- " + index + " " + g.getNombre());
            index++;
        }
        sc.close();




    }
}
