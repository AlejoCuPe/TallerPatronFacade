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
    private String hora;
    private ArrayList<Componente> componentes;
    private Date fecha;
    private int cupos;
    private int tarifa;
    private Conductor creador;

    public Ruta(int id, String hora, Date fecha, int cupos, int tarifa, Conductor creador) {
        this.id = id;
        this.hora = hora;
        this.fecha = fecha;
        this.cupos = cupos;
        this.tarifa = tarifa;
        this.creador = creador;
    }

    public void setHora(String hora) {
        this.hora = hora;
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

    public Conductor getCreador() {
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
        getComponentes().add(c);
    }
    
    public ArrayList<Componente> getComponentes(){
        return componentes;
    }
    
    public void setComponentes(ArrayList<Componente> componentes){
        this.componentes = componentes;
    }
    
    @Override
    public String toString() {
        String texto = "Ruta" + "hora=" + hora + ", fecha=" + fecha + ", cupos=" + cupos + ", tarifa=" + tarifa;
        String texto2 = "\n";
        String texto3 = "";
        for(Componente c : getComponentes()){
            texto3 += c.toString() + "\n";
        }
        return texto + texto2 + texto3;
    }

    @Override
    public void mostrar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Calle devolver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      
}
