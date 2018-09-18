/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author alejo
 */
public class Reserva {

    int id;
    Ruta ruta;
    Usuario pasajero;
    int cuposReservados;

    public Reserva(int id, Ruta ruta, int cuposReservados, Usuario pasajero) {
        this.id = id;
        this.ruta = ruta;
        this.cuposReservados = cuposReservados;
        this.pasajero = pasajero;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public void setCuposReservados(int cuposReservados) {
        this.cuposReservados = cuposReservados;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public int getCuposReservados() {
        return cuposReservados;
    }

    public Usuario getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }   

    @Override
    public String toString() {
        return "Reserva #"+(id)+": " + "\nRuta con destino a: " + ruta.getDestino() +"\nFecha: "+ruta.getFecha().toString() +"\nCupos Reservados: " + cuposReservados;
    }

    
}
