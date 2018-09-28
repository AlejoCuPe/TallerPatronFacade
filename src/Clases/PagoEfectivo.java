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
public class PagoEfectivo extends PagoDecorator{

    private String moneda;
    
    public PagoEfectivo(Component pago) {
        super(pago);
    }
    
    @Override
    public String pagar() {
        return super.pagar()+" mediante pago en efectivo usando "+moneda;
    }


    @Override
    public void asignar(ArrayList<String> valores) {
        super.asignar(valores);
        this.fecha = valores.get(1);
    }
    
}
