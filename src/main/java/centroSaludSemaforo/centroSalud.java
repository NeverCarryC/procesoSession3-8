package centroSaludSemaforo;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class centroSalud extends Thread{
    private static Semaphore capacidad = new Semaphore(5);
    protected int idPaciente = 0;
    private static Scanner sc = new Scanner(System.in);

    public centroSalud(int id){
        this.idPaciente = id;
    }

    public void run(){
        try {
            capacidad.acquire();
            System.out.println("El paciente " + this.idPaciente + " entra en el centro salud");
            Thread.sleep((long) (Math.random()*2000 +100));
            System.out.println("El paciente " + this.idPaciente +  "sale del centro salud");
            capacidad.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        int pacientes = 0;
        System.out.println("¿Cuantos pacientes tienen cita en el dia de hoy?");
        pacientes = sc.nextInt();
        for (int i=1; i<=pacientes;i++){
            new centroSalud(i).start();
        }
    }
}
