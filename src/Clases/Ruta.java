/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaces.Componente;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author alejo
 */
public class Ruta implements Componente{
    
    private int id;
    private String origen;
    private String destino;
    private String hora;
    private ArrayList<Componente> componentes = new ArrayList<>();
    private Date fecha;
    private int cupos;
    private int tarifa;
    private Usuario creador;

    public Ruta(int id, String origen, String destino, String hora, Date fecha, int cupos, int tarifa, Usuario creador) {
        this.id = id;
        this.destino = destino;
        this.origen = origen;
        this.hora = hora;
        this.fecha = fecha;
        this.cupos = cupos;
        this.tarifa = tarifa;
        this.creador = creador;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setCupos(int cupos) {
        this.cupos = cupos;
    }

    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }

    public String getHora() {
        return hora;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getCupos() {
        return cupos;
    }

    public int getTarifa() {
        return tarifa;
    }

    public Usuario getCreador() {
        return creador;
    }

    public void setCreador(Conductor creador) {
        this.creador = creador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void add(Componente c){
        componentes.add(c);
    }
    
    public ArrayList<Componente> getComponentes(){
        return componentes;
    }

    @Override
    public String mostrar() {
        String texto = "Ruta #" + (id) + "\nHora: " + hora + "\nFecha: N" + fecha.toString() + "\nCupos: " + cupos + "\nTarifa: " + tarifa+"\n\n";
        for(Componente c : getComponentes()){
            texto = texto + c.mostrar();
        }
        return texto;
    }
    
    public String mostrarRuta() {
        String texto = "Ruta #" + (id) + "\nHora: " + hora + "\nFecha: N" + fecha.toString() + "\nCupos: " + cupos + "\nTarifa: " + tarifa+"\n\n";
        return texto;
    }

    @Override
    public void modificar(String nombre, float[] origen, float[] destino) {
        for(Componente c : componentes){
            String cosa = c.mostrar();
            String[] m = cosa.split("°");
            if(m[1].equals(nombre)){
                c.modificar(nombre, origen, destino);
            }
        }
    }

}


