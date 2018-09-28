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
public class CreditoDecorator extends PagoDecorator {

    private String numeroT;
    private String CVV;

    public CreditoDecorator(Component pago) {
        super(pago);
    }

    public void setNumeroT(String numeroT) {
        this.numeroT = numeroT;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    @Override
    public String pagar() {
        return super.pagar() + " \n\t Usando Tarjeta de Crédito No. " + numeroT + " con CVV: " + CVV;
    }


    @Override
    public void asignar(ArrayList<String> valores) {
        super.asignar(valores);
        this.numeroT = valores.get(4);
        this.CVV = valores.get(5);
    }


}
