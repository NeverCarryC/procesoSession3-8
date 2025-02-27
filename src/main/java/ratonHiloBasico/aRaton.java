package ratonHiloBasico;

public class aRaton {
    private String nombre;
    private int tiempoAlimentacion;

    public aRaton(String nombre, int tiempoAlimentacion){
        this.nombre = nombre;
        this.tiempoAlimentacion = tiempoAlimentacion;
    }

    public void comer(){
        try {
            System.out.printf("El raton %S ha comenzado a alimentarse%n",nombre);
            Thread.sleep(tiempoAlimentacion*1000);
            System.out.printf("El raton %s ha terminado de alimentarse%n", nombre);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        aRaton fievel = new aRaton("fievel", 4);
        aRaton jerry = new aRaton("jerry",5);
        aRaton pinky = new aRaton("pinkty", 3);
        aRaton mickey = new aRaton("mickey", 6);
        fievel.comer();
        jerry.comer();
        pinky.comer();
        mickey.comer();
    }
}
