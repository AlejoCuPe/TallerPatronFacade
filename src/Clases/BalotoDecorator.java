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
public class BalotoDecorator extends PagoDecorator {

    private String noConfirmacion;

    public BalotoDecorator(Component pagoPSE) {
        super(pagoPSE);
    }

    @Override
    public String pagar() {
        return super.pagar() + "\n\t Usando Baloto con No. de Confirmación " + noConfirmacion;
    }


    @Override
    public void asignar(ArrayList<String> valores) {
        super.asignar(valores);
        this.noConfirmacion = valores.get(4);
    }

}
