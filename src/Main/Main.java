/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Clases.Proxy;
import Facade.Fachada;
import java.text.ParseException;
import java.util.Random;
import javax.swing.JOptionPane;

public class Main {

    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) throws ParseException {

        Proxy proxy = Proxy.getInstance();

        int opcion, sesion;
        String usuario, password, tipoU;

        do {
            usuario = JOptionPane.showInputDialog("Ingrese el usuario");
            password = JOptionPane.showInputDialog("Ingrese la contraseña");
            sesion = generarNum();
            tipoU = proxy.buscar(usuario, password);
            Fachada f = Fachada.getInstance();
            if (!f.iniciarSesion(usuario, password, sesion)) {
                JOptionPane.showMessageDialog(null, "No existe ese usuario");
            } else {
                do {
                    opcion = Integer.parseInt(JOptionPane.showInputDialog(f.menu(tipoU)));
                    f.llamarMenu(opcion, tipoU, usuario, password);
                } while (opcion != 0);
            }
        } while (1==1);
    }

    private static boolean esPrimo(int inputNum) {
        if (inputNum <= 3 || inputNum % 2 == 0) {
            return inputNum == 2 || inputNum == 3; //this returns false if number is <=1 & true if number = 2 or 3
        }
        int divisor = 3;
        while ((divisor <= Math.sqrt(inputNum)) && (inputNum % divisor != 0)) {
            divisor += 2; //iterates through all possible divisors
        }
        return inputNum % divisor != 0; //returns true/false
    }

    private static int generarNum() {
        int sesion;
        Random rand = new Random(); // generate a random number
        sesion = rand.nextInt(9999) + 1;
        while (!esPrimo(sesion)) {
            sesion = rand.nextInt(9999) + 1;
        }
        return sesion;
    }

}
