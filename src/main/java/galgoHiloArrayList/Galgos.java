package galgoHiloArrayList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Galgos extends Thread {
    private String nombre;
    private int tiempo;
    private static final List<Galgos> orden = new ArrayList<>();

    public String getNombre() {
        return nombre;
    }


    public Galgos(String nombre, int tiempo) {
        this.nombre = nombre;
        this.tiempo = tiempo;
    }

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

    public static List<Galgos> getOrden() {
        return orden;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Cuantos galgos?");
        int numGalgo = sc.nextInt();
        List<Galgos> galgosList = new ArrayList<>(numGalgo);

        for (int i = 0; i < numGalgo; i++) {
            System.out.println("¿Nombre para " + (i + 1) + "º galgo?");
            String nombre = sc.next();
            System.out.println("¿Tiempo para " + (i + 1) + "º galgo?");
            int tiempo = sc.nextInt();
            galgosList.add(new Galgos(nombre, tiempo));
        }

        for (Galgos g : galgosList) {
            g.start();
        }

        for (Galgos g : galgosList) {
            try {
                g.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        int index = 1;
        System.out.println("Resultado");
        for (Galgos g : Galgos.getOrden()) {
            System.out.println("- " + index + " " + g.getNombre());
            index++;
        }
        sc.close();
    }
}
