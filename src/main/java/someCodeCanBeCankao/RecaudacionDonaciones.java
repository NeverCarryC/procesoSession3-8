package someCodeCanBeCankao;

import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.ArrayList;
import java.util.List;

public class RecaudacionDonaciones {
    private static List<Donacion> donaciones = new ArrayList<>();
    private static Semaphore semaforo = new Semaphore(1);  // Para garantizar la sincronización
    private static double totalRecaudado = 0;
    private static int personasQueDonaron = 0;

    // Clase interna para representar una donación
    static class Donacion extends Thread {
        private String nombre;
        private double cantidad;

        public Donacion(String nombre, double cantidad) {
            this.nombre = nombre;
            this.cantidad = cantidad;
        }

        @Override
        public void run() {
            try {
                semaforo.acquire();  // Adquirir el semáforo para asegurar acceso exclusivo
                donaciones.add(this);
                totalRecaudado += cantidad;
                personasQueDonaron++;
                semaforo.release();  // Liberar el semáforo después de la operación
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public String getNombre() {
            return nombre;
        }

        public double getCantidad() {
            return cantidad;
        }
    }

    // Función principal que gestiona las donaciones
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido a la aplicación de donaciones voluntarias.");
        System.out.println("¿Cuántas personas van a donar?");
        int numDonantes = sc.nextInt();
        sc.nextLine();  // Limpiar el buffer del scanner

        // Crear un hilo por cada donante
        for (int i = 0; i < numDonantes; i++) {
            System.out.println("Ingrese el nombre del donante " + (i + 1) + ":");
            String nombre = sc.nextLine();
            System.out.println("Ingrese la cantidad que quiere donar (en euros):");
            double cantidad = sc.nextDouble();
            sc.nextLine();  // Limpiar el buffer

            // Crear un hilo para cada donante
            Donacion donante = new Donacion(nombre, cantidad);
            donante.start();
        }

        // Esperar un momento antes de mostrar el resumen
        try {
            Thread.sleep(5000);  // Dormir el hilo principal para esperar las donaciones
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Mostrar los resultados
        System.out.println("\nResumen de las donaciones:");
        System.out.println("Número de personas que han donado: " + personasQueDonaron);
        System.out.println("Cantidad total recaudada: " + totalRecaudado + " euros");

        // Imprimir los detalles de cada donante
        System.out.println("\nDetalles de cada donación:");
        for (Donacion d : donaciones) {
            System.out.println(d.getNombre() + " ha donado " + d.getCantidad() + " euros.");
        }

        sc.close();
    }
}
