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
import java.util.Iterator;
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
    public void crearRuta(String hora, String origen, String destino, Date fecha, int cupos, int tarifa, Usuario conductor){
        int id = rutas.size();
        Ruta ruta = new Ruta(id, origen, destino, hora, fecha, cupos, tarifa, conductor);
        rutas.add(ruta);
    }
    
    public String mostrarRuta(String nombreDestino, Date fecha, String hora){
        Ruta buscada = null;
        for(Ruta r : rutas){
            if(r.getDestino().equals(nombreDestino)){
                if(r.getFecha() == fecha && r.getHora().equals(hora)){
                    buscada = r;
                }
            }
        }
        
        if(buscada == null){
            return "No se ha encontrado la ruta";
        }
        else{
            return buscada.toString();
        }
        
    }
    
    public String mostrarRutas(){
        String texto = "Rutas \n";
        for(Ruta r : rutas){                
            texto = texto + r.toString() + "\n ";
        }
        return texto;
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
        Iterator<Ruta> iter = rutas.iterator();
        while (iter.hasNext()){
            Ruta r = iter.next();
            if(r.getId() == id){
                iter.remove();
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

    public String mostrarCalle(Ruta r) {
        ArrayList<Calle> componentes = r.getComponentes();
        String mostrar = "";
        for (Calle c : componentes){
            mostrar = mostrar + c.toString() +"\n";
        }
        return mostrar;
    }
    
    public void modificarCalle(Ruta r, String nombrebuscado, String nombre, float[] origen, float[] destino){
        ArrayList<Calle> componentes = r.getComponentes();
        for (Calle c : componentes){
            if(c.getNombre().equals(nombrebuscado)){
                if(nombre != null) c.setNombre(nombre);
                if(origen != null) c.setOrigen(origen);
                if(destino != null) c.setDestino(destino);
                if(origen != null && destino != null){
                c.setDistancia();
                c.setTiempo();
                }
            }
        }       
    }
    
    public void eliminarCalle(Ruta r, String nombrebuscado){
        for(int i=0; i<r.getComponentes().size(); i++){
            if(r.getComponentes().get(i).getNombre().equals(nombrebuscado)){
                r.getComponentes().remove(i);
            }
        }
    }
    
    //Metodos Usuario    
    public void crearUsuario(String tipoUsuario, String correo, String password){
        switch(tipoUsuario){
            case "2":
                Usuario pasajero = new Pasajero();
                pasajero.adicionar(usuarios.size()+1,correo, password);
                usuarios.add(pasajero);
                JOptionPane.showMessageDialog(null, "Usuario Creado Correctamente");
                break;
            case "1":
                Usuario conductor = new Conductor();
                conductor.adicionar(usuarios.size()+1, correo, password);
                JOptionPane.showMessageDialog(null, "Usuario Creado Correctamente");
                usuarios.add(conductor);
                break;
            case "3":
                Administrador a = new Administrador();
                Usuario u = new AdministradorAdapter(a);
                JOptionPane.showMessageDialog(null, "Usuario Creado Correctamente");
                u.adicionar(usuarios.size()+1, correo, password);
                usuarios.add(u);
                break;
            default:
                break;
            
        }
    }
    
    public String iniciarSesion(String correo, String password){
        int detectar = 0;
        String retornar = null;
        int j = 0;
        System.out.println(usuarios.size());
        for(int i=0; i<usuarios.size(); i++){
            if(usuarios.get(i).getCorreo().equals(correo)){
                if(usuarios.get(i).getPassword().equals(password)){
                    detectar = 1;
                    JOptionPane.showMessageDialog(null, "Sesión Iniciada");
                    j = i;
                }
            }
        }
        if(detectar==0){
            return "Fallido";
        }
        else{
            if("class Clases.Conductor".equals(usuarios.get(j).getClass().toString())){
                retornar = "1";
            }
            if("class Clases.Pasajero".equals(usuarios.get(j).getClass().toString())){
                retornar = "2";
            }
            if("class Clases.AdministradorAdapter".equals(usuarios.get(j).getClass().toString())){
                retornar = "3";
            }
            return retornar;
        }
    }
    
    public Usuario buscarUsuario(String correo, String contraseña){
        Usuario encontrado = null;
        for(Usuario u: usuarios){
            if(u.getCorreo().equals(correo) && u.getPassword().equals(contraseña)){
                encontrado = u;
                break;
            }
        }
        return encontrado;
    }
    
    public Ruta buscarRuta(String hora, Date fecha, int cupos, int tarifa){
        Ruta encontrado = null;
        for(Ruta r: rutas){
            if(r.getHora().equals(hora) && r.getFecha() == fecha && r.getCupos() == cupos && r.getTarifa() == tarifa){
                encontrado = r;
                break;
            }
        }
        return encontrado;
    }
    
    public Ruta buscarRuta1(int id){
        Ruta encontrado = null;
        for(Ruta r: rutas){
            if(r.getId() == id){
                encontrado = r;
                break;
            }
        }
        return encontrado;
    }
}
