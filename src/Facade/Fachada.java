/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Clases.Administrador;
import Clases.AdministradorAdapter;
import Clases.Calle;
import Clases.Conductor;
import Clases.Pasajero;
import Clases.Reserva;
import Clases.Ruta;
import Clases.Usuario;
import Interfaces.Componente;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author alejo
 */
public class Fachada {
    
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Ruta> rutas = new ArrayList<>();
    private ArrayList<Reserva> reservas = new ArrayList<>();
    
    
    public Fachada(){
        
    }
    
    //CRUD Ruta   
    public void crearRuta(String hora, String origen, String destino, Date fecha, int cupos, int tarifa, Conductor conductor){
        int id = rutas.size();
        Ruta ruta = new Ruta(id, hora, fecha, cupos, tarifa, conductor);
        rutas.add(ruta);
    }
    
    public String mostrarRuta(String nombreDestino, Date fecha, String hora){
        Ruta buscada = null;
        for(Ruta r : rutas){
            for(int i=0; i<r.getComponentes().size(); i++){
                if(r.getComponentes().get(i).devolver().getNombre().equals(nombreDestino)){
                    if(r.getFecha() == fecha && r.getHora().equals(hora)){
                        buscada = r;
                    }
                }
            }
        }
        return buscada.toString();
    }
    
    public void modificarRuta(int id, String hora, Date fecha, int cupos, int tarifa){
        for(Ruta r : rutas){
            if(r.getId() == id){
                if(hora != null) r.setHora(hora);
                if(fecha != null) r.setFecha(fecha);
                if(cupos != 0) r.setCupos(cupos);
                if(tarifa != 0) r.setTarifa(tarifa);
            }
        }
    }
    
    public void eliminarRuta(int id){
        for(Ruta r : rutas){
            if(r.getId() == id){
                rutas.remove(r);
            }
        }
    }
    
    
    //CRUD Reserva  
    public void crearReserva(int cupos, Ruta ruta, Pasajero pasajero){
        int id = reservas.size();
        Reserva reserva = new Reserva(id, ruta, cupos, pasajero);
        for (Ruta t : rutas) {
            if (t.equals(reserva.getRuta())) {
                ruta.setCupos(ruta.getCupos() - cupos);
            }
        }
        reservas.add(reserva);
    } 
    
    public String mostrarReserva(int id){
        Reserva buscada = null;
        for(Reserva r : reservas){
            if(r.getId() == id){
                buscada = r;
            }
        }
        return buscada.toString();
    }  
    
    public void modificarReserva(int id, int cupos){
        for(Reserva r : reservas){
            if(r.getId() == id){
                int cuposI = r.getCuposReservados();
                for(Ruta t : rutas){
                    if(t.equals(r.getRuta())){
                        t.setCupos(-cupos + t.getCupos() + cuposI);
                    }
                }        
            }
        }      
    }
    
    public void eliminarReserva(int id){
        for(Reserva r : reservas){
            if(r.getId() == id){
                reservas.remove(r);
            }
        }
    }
    
    //CRUD Calle 
    public void crearCalle(Ruta r, String nombre, float[] origen, float[] destino) {
        Calle c = new Calle(nombre, origen, destino);
        c.setDistancia();
        c.setTiempo();
        for (Ruta ruta : rutas) {
            if (ruta.equals(r)) {
                r.add(c);
            }
        }
    }

    public String mostrarCalle(Ruta r, int i) {
        ArrayList<Componente> componentes = r.getComponentes();
        String c = componentes.get(i).toString();
        return c;
    }
    
        public void modificarCalle(Ruta r, int i, String nombre, float[] origen, float[] destino){
        ArrayList<Componente> componentes = r.getComponentes();
        Calle c = componentes.get(i).devolver();
        if(nombre != null) c.setNombre(nombre);
        if(origen != null) c.setOrigen(origen);
        if(destino != null) c.setDestino(destino);
        if(origen != null && destino != null){
            c.setDistancia();
            c.setTiempo();
        }       
    }
    
    public void eliminarCalle(Ruta r, int i){
        r.getComponentes().remove(i);
    }
    
    
    
    //Metodos Usuario    
    public void crearUsuario(String tipoUsuario, String correo, String password){
        switch(tipoUsuario){
            case "Pasajero":
                Usuario pasajero = new Pasajero();
                pasajero.adicionar(correo, password);
                usuarios.add(pasajero);
                JOptionPane.showMessageDialog(null, "Usuario Creado Correctamente");
                break;
            case "Conductor":
                Usuario conductor = new Conductor();
                conductor.adicionar(correo, password);
                JOptionPane.showMessageDialog(null, "Usuario Creado Correctamente");
                usuarios.add(conductor);
                break;
            case "Administrador":
                Administrador a = new Administrador();
                Usuario u = new AdministradorAdapter(a);
                JOptionPane.showMessageDialog(null, "Usuario Creado Correctamente");
                u.adicionar(correo, password);
                usuarios.add(u);
                break;
            default:
                break;
            
        }
    }
    
    public void iniciarSesion(String correo, String password){
        Usuario b = null;
        int i=0;
        System.out.println(usuarios.size());
        for(i=0; i<usuarios.size(); i++){
            if(usuarios.get(i).getCorreo().equals(correo)){
                if(usuarios.get(i).getPassword().equals(password)){
                    i=1;
                }
            }
        }
        if(i==0){
            JOptionPane.showMessageDialog(null, "Credenciales Incorrectas");
        }
    }
    
}
