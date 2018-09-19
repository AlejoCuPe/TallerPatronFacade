/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.Date;

/**
 *
 * @author alejo
 */
public interface Componente {
    public String mostrar();
    public String[] mostrarDatos();
    public void modificarInformacion(String hora, Date fecha, int cupos, int tarifa);
    public String devolverNombre();
    public void añadirComponente(Componente c);
}
