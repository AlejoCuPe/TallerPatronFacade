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
public abstract class PagoDecorator implements Component {

    protected Component pago;
    protected String fecha;

    public PagoDecorator(Component pago) {
        this.pago = pago;
    }

    @Override
    public String pagar() {
        return pago.pagar() + fecha;
    }
    
    @Override
    public void asignar(ArrayList<String> valores){
        fecha = valores.get(0);
    }

}
