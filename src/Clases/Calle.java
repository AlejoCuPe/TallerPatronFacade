/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaces.Componente;

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

    public void setDistancia() {
        this.distancia = (this.destino[1]-this.origen[1])/(this.destino[0]-this.origen[0]);
    }

    public float getTiempo() {
        return tiempo;
    }

    public void setTiempo() {
        this.tiempo = 60/this.distancia;
    }

    @Override
    public void mostrar() {
        System.out.println("\tNombre de la calle: "+nombre+"\n"+
                            "\tCoordenadas origen: X="+origen[0]+"m Y="+origen[1]+"m\n"+
                            "\tCoordenadas destino: X="+destino[0]+"m Y="+destino[1]+"m\n"+
                            "\tDistancia: "+distancia+"m\n"+
                            "\tTiempo: "+tiempo+"s");
    }
   
    @Override
    public String toString() {
        return "\nNombre de la calle: "+nombre+"\n"+
                            "\tCoordenadas origen: X="+origen[0]+"m Y="+origen[1]+"m\n"+
                            "\tCoordenadas destino: X="+destino[0]+"m Y="+destino[1]+"m\n"+
                            "\tDistancia: "+distancia+"m\n"+
                            "\tTiempo: "+tiempo+"s";
    }
    
    @Override
    public Calle devolver(){
        return this;
    }
    
}
