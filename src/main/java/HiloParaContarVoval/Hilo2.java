package HiloParaContarVoval;

import org.w3c.dom.Text;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class Hilo2 extends Thread {
    private char letra;
    private String cadena = "";
    private int contador = 0;

    public char getLetra() {
        return letra;
    }

    public String getCadena() {
        return cadena;
    }

    public int getContador() {
        return contador;
    }

    public Hilo2(char letra, String cadena) {
        this.letra = letra;
        this.cadena = cadena;
    }

    public void run(){
        CharacterIterator comprueba = new StringCharacterIterator(cadena);
        System.out.println("Letra a buscar: " + this.getLetra());

        try {
            Thread.sleep((long)Math.random()*5000 + 100);
            while(comprueba.current() != CharacterIterator.DONE){
                if (comprueba.current() == this.getLetra()){
                    contador++;
                }
                comprueba.next();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("El numero de letra " + this.getLetra() + " es " + this.getContador());
    }

    public static void main(String[] args) {
        int totalVocales = 0;
        String Texto = "asdlkjhasdklabsdkjzxclabsdnlañishdñoqiend lxc.naliñdhañowndñalsdkn añslkdn";
        Hilo2 primero = new Hilo2('a', Texto);
        Hilo2 segundo = new Hilo2('e', Texto);
        Hilo2 tercero = new Hilo2('o', Texto);
        Hilo2 cuarto = new Hilo2('u', Texto);
        Hilo2 quiento = new Hilo2('i', Texto);

        primero.start();
        segundo.start();
        tercero.start();
        cuarto.start();
        quiento.start();


        try {
            primero.join();
            segundo.join();
            tercero.join();
            cuarto.join();
            quiento.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        totalVocales = primero.getContador() + segundo.getContador() + tercero.getContador() + cuarto.getContador() + quiento.getContador();
        System.out.println("numuero total de vocales es: " + totalVocales);

    }
}
