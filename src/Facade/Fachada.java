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
import Clases.UsuarioFactory;
import Interfaces.Componente;
import Interfaces.IUsuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author alejo
 */
public class Fachada {
    
    private ArrayList<Ruta> rutas = new ArrayList<>();
    private ArrayList<Reserva> reservas = new ArrayList<>();
    private UsuarioFactory u = new UsuarioFactory();
    
    public Fachada(){
        
    }
    
    //CRUD Ruta   
    public void crearRuta(String hora, String origen, String destino, Date fecha, int cupos, int tarifa, Usuario conductor){
        int id = rutas.size();
        Ruta ruta = new Ruta(id, origen, destino, hora, fecha, cupos, tarifa, conductor);
        rutas.add(ruta);
    }
    
    public String mostrarRuta(String nombreDestino, Date fecha, String hora){
        Componente buscada = null;
        for(Ruta r : rutas){
            if(r.getDestino().equals(nombreDestino)){
                if(r.getFecha().toString().equals(fecha.toString()) && r.getHora().equals(hora)){
                    buscada = r;
                }
            }
        }
        
        if(buscada == null){
            return "No se ha encontrado la ruta";
        }
        else{
            return buscada.mostrar();
        }
        
    }
    
    public String mostrarRutas(){
        String texto = "Rutas \n\n";
        for(Ruta r : rutas){   
            texto = texto + r.mostrarRuta() + "\n \n";
        }
        return texto;
    }
    
    public void modificarRuta(int id, String hora, Date fecha, int cupos, int tarifa){
        for(Ruta c : rutas){
            if(c.getId() == id){
                c.setHora(hora);
                c.setFecha(fecha);
                c.setCupos(cupos);
                c.setTarifa(tarifa);
            }
        }
    }
    
    public int verificarRuta(int id){
        int probar = 0;
        for(Ruta c : rutas){
            if(c.getId() == id){
                probar = 1;
            }
        }
        return probar;
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
    
    //CRUD Calle 
    public void crearCalle(Ruta r, String nombre, float[] origen, float[] destino) {
        Componente c = new Calle(nombre, origen, destino);
        for (Ruta co : rutas) {
            if (co.equals(r)) {
                co.add(c);
                break;
            }
        }
    }

    public String mostrarCalle(Ruta r) {
        ArrayList<Componente> componentes = r.getComponentes();
        String mostrar = "";
        for (Componente c : componentes){
            mostrar = mostrar + c.mostrar() +"\n";
        }
        return mostrar;
    }
    
    public void modificarCalle(Ruta r, String nombrebuscado, String nombre, float[] origen, float[] destino){
        ArrayList<Componente> componentes = r.getComponentes();
        for (Componente c : componentes){
            String cosa = c.mostrar();
            String[] m = cosa.split("°");
            if(m[1].equals(nombrebuscado)){
               c.modificar(nombre, origen, destino);
            }
        }       
    }
    
    public void eliminarCalle(Ruta r, String nombrebuscado){
        for(int i=0; i<r.getComponentes().size(); i++){
            String cosa = r.getComponentes().get(i).mostrar();
            String[] m = cosa.split("°");
            if(m[1].equals(nombrebuscado)){
                r.getComponentes().remove(i);
            }
        }
    }
    
    
    //CRUD Reserva  
    public void crearReserva(int cupos, Ruta ruta, Usuario pasajero){
        int id = reservas.size();
        Reserva reserva = new Reserva(id, ruta, cupos, pasajero);
        for (Ruta t : rutas) {
            if (t.equals(reserva.getRuta())) {
                ruta.setCupos(ruta.getCupos() - cupos);
            }
        }
        reservas.add(reserva);
    } 
    
    public String mostrarReserva(){
       String texto = "Reservas \n\n";
        for(Reserva r : reservas){                
            texto = texto + r.toString() + "\n ";
        }
        return texto;
    }  
    
    public void modificarReserva(int id, int cupos){
        for(Reserva r : reservas){
            if(r.getId() == id){
                int cuposI = r.getCuposReservados();
                r.setCuposReservados(cupos);
                for(Ruta t : rutas){
                    String datos = t.mostrar();
                    if(t.equals(r.getRuta())){
                        t.setCupos(-cupos + t.getCupos() + cuposI);
                        break;
                    }
                }        
            }
        }      
    }
    
    public void eliminarReserva(int id){
        Iterator<Reserva> iter = reservas.iterator();
        while (iter.hasNext()){
            Reserva r = iter.next();
            if(r.getId() == id){
                iter.remove();
            }
        }
    }
    
    public int verificarReserva(int id){
        int probar = 0;
        for(Reserva r : reservas){
            if(r.getId() == id){
                probar = 1;
            }
        }
        return probar;
    }
    
    //Metodos Usuario    
    public void crearUsuario(String tipoUsuario, String correo, String password){
        switch(tipoUsuario){
            case "2":
                Usuario pasajero = new Pasajero();
                pasajero.adicionar(correo, password);
                u.crearUsuario(correo, pasajero);
                JOptionPane.showMessageDialog(null, "Usuario Creado Correctamente");
                break;
            case "1":
                Usuario conductor = new Conductor();
                conductor.adicionar(correo, password);
                JOptionPane.showMessageDialog(null, "Usuario Creado Correctamente");
                u.crearUsuario(correo, conductor);
                break;
            case "3":
                Administrador a = new Administrador();
                a.crear(correo, password);
                Usuario admin = new AdministradorAdapter(a);
                JOptionPane.showMessageDialog(null, "Usuario Creado Correctamente");
                u.crearUsuario(correo, admin);
                break;
            default:
                break;
            
        }
    }
    
    public int verificarCalle(int id1, String nombre){
        int probar = 0;
        Ruta r = buscarRuta1(id1);
        for(Componente c : r.getComponentes()){
            String cosa = c.mostrar();
            String[] m = cosa.split("°");
            if(m[1].equals(nombre)){
                probar = 1;
            }
        }
        return probar;
    }
    
    public String iniciarSesion(String correo, String password){
        String fallido = "Fallido";
        IUsuario x = u.mostrarUsuario(correo);
        if(x.getCorreo() == null){
            JOptionPane.showMessageDialog(null, "No existe ese usuario");
        }
        else{
            if(x.getPassword().equals(password)){
                JOptionPane.showMessageDialog(null, "Sesión Iniciada");
                if("class Clases.Conductor".equals(x.getClass().toString())){
                    fallido = "1";
                }
                if("class Clases.Pasajero".equals(x.getClass().toString())){
                    fallido = "2";
                }
                if("class Clases.AdministradorAdapter".equals(x.getClass().toString())){
                    fallido = "3";
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Contraseña Incorrecta");
            }
        }
        return fallido;
    }
    
    public IUsuario buscarUsuario(String correo, String contraseña){
        IUsuario encontrado = null;
        encontrado = u.mostrarUsuario(correo);
        return encontrado;
    }
    
    public Ruta buscarRuta(String hora, Date fecha, int cupos, int tarifa){
        Ruta encontrado = null;
        for(Componente r: rutas){
            Ruta k = (Ruta) r;
            k.getId();
            if(k.getHora().equals(hora) && k.getFecha().toString().equals(fecha.toString()) && k.getCupos() == cupos && k.getTarifa() == tarifa){
                encontrado = (Ruta) r;
                break;
            }
        }
        return encontrado;
    }
    
    public Ruta buscarRuta1(int id){
        Ruta encontrado = null;
        for(Componente r: rutas){
            Ruta x = (Ruta) r;
            if(x.getId() == id){
                encontrado = (Ruta) r;
                break;
            }
        }
        return encontrado;
    }
}
