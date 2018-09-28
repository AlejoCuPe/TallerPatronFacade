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
public class PagoPSE extends PagoDecorator {

    private String noCuentaP;
    private String claveCuenta;
    private String noCuentaD;

    public PagoPSE(Component pago) {
        super(pago);
    }

    @Override
    public String pagar() {
        return super.pagar() + " mediante PSE\nNo. Cuenta Pasajero: " + noCuentaP
                + "\nNo. Cuenta Conductor: " + noCuentaD;
    }

    @Override
    public void asignar(ArrayList<String> valores) {
        if (valores.size() == 4) {
            super.asignar(valores);
            this.noCuentaP = valores.get(1);
            this.claveCuenta = valores.get(2);
            this.noCuentaD = valores.get(3);
        } else {
            super.asignar(valores);
            this.noCuentaP = valores.get(1);
            this.claveCuenta = valores.get(2);
            this.noCuentaD = valores.get(3);
        }
    }

}
