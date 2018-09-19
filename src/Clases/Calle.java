/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaces.Componente;
import java.util.Date;

/**
 *
 * @author alejo
 */
public class Calle implements Componente{
    
    private String nombre;
    private float[] origen;
    private float[] destino;
    private float distancia;
    private float tiempo;
    
    public Calle(){

    }

    public Calle(String nombre, float[] origen, float[] destino) {
        this.nombre = nombre;
        this.origen = origen;
        this.destino = destino;
        this.setDistancia();
        this.setTiempo();
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float[] getOrigen() {
        return origen;
    }

    public void setOrigen(float[] origen) {
        this.origen = origen;
    }

    public float[] getDestino() {
        return destino;
    }

    public void setDestino(float[] destino) {
        this.destino = destino;
    }

    public float getDistancia() {
        return distancia;
    }

    private void setDistancia() {
        this.distancia = (this.destino[1]-this.origen[1])/(this.destino[0]-this.origen[0]);
    }

    public float getTiempo() {
        return tiempo;
    }

    private void setTiempo() {
        this.tiempo = 60/this.distancia;
    }

    @Override
    public String mostrar() {
        String mostrar = "\tNombre de la calle: "+nombre+"\n"+
                            "\tCoordenadas origen: X="+origen[0]+"m Y="+origen[1]+"m\n"+
                            "\tCoordenadas destino: X="+destino[0]+"m Y="+destino[1]+"m\n"+
                            "\tDistancia: "+distancia+"m\n"+
                            "\tTiempo: "+tiempo+"s";
        return mostrar;
    }
   

    @Override
    public String devolverNombre() {
        return nombre;
    }

    @Override
    public String[] mostrarDatos() {
        String[] datos = new String[5];
        datos[0] = nombre;
        datos[1] = Float.toString(origen[0]);
        datos[2] = Float.toString(origen[1]);
        datos[3] = Float.toString(destino[0]);
        datos[4] = Float.toString(destino[1]);
        datos[5] = Float.toString(distancia);
        datos[4] = Float.toString(tiempo);
        return datos;
    }

    @Override
    public void modificarInformacion(String hora, Date fecha, int cupos, int tarifa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void añadirComponente(Componente c) {
        System.out.println("No se requiere");
    }
    
}
