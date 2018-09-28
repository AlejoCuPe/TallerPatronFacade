/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaces.Component;
import java.util.ArrayList;

/**
 *
 * @author alejo
 */
public class Pago implements Component {

    String valor;
    String IDPasajero;
    String IDConductor;

    @Override
    public String pagar() {
        return "Se ha realizado el pago de valor: " + this.valor +" Informacion IDs" +IDPasajero+ " "+ IDConductor;
    }

    @Override
    public void asignar(ArrayList<String> valores) {
        if (valores.size() != 3) {
            this.valor = valores.get(0);
        } else {
            this.valor = valores.get(0);
            this.IDPasajero = valores.get(1);
            this.IDConductor = valores.get(2);
        }
    }

}
