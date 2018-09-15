/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Facade.Fachada;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args){
        Fachada f = new Fachada();
        int Opcion;
        
        do{
            Opcion = Integer.parseInt(JOptionPane.showInputDialog("1. Crear Usuario\n"
                                                                  + "2. Iniciar Sesión\n"
                                                                  + "3. Crear Ruta"
                    + "4. Consultar Ruta\n"
                    + "5. Modificar Ruta\n"
                    + "6. Eliminar Ruta\n"
                    + "7. Crear Calle\n"
                    + " "));
    
        }while(Opcion != 0);
    }    
}
