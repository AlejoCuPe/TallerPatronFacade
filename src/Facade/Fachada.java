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
    private ArrayList<Componente> rutas = new ArrayList<>();
    private ArrayList<Reserva> reservas = new ArrayList<>();
    
    
    public Fachada(){
        
    }
    
    //CRUD Ruta   
    public void crearRuta(String hora, String origen, String destino, Date fecha, int cupos, int tarifa, Usuario conductor){
        int id = rutas.size();
        Ruta ruta = new Ruta(id, origen, destino, hora, fecha, cupos, tarifa, conductor);
        rutas.add(ruta);
        ruta.add(ruta);
    }
    
    public String mostrarRuta(String nombreDestino, Date fecha, String hora){
        Ruta buscada = null;
        for(Componente r : rutas){
            Ruta x = (Ruta) r;
            if(x.getDestino().equals(nombreDestino)){
                if(x.getFecha() == fecha && x.getHora().equals(hora)){
                    buscada = x;
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
        String texto = "Rutas \n\n";
        for(Componente c : rutas){   
            texto = texto + c.mostrar() + "\n \n";
        }
        return texto;
    }
    
    public void modificarRuta(int id, String hora, Date fecha, int cupos, int tarifa){
        for(Componente c : rutas){
            Ruta x = (Ruta) c;
            if(x.getId() == id){
                if(hora != null) x.setHora(hora);
                if(fecha != null) x.setFecha(fecha);
                if(cupos != 0) x.setCupos(cupos);
                if(tarifa != 0) x.setTarifa(tarifa);
                c = x;
            }
        }
    }
    
    public int verificarRuta(int id){
        int probar = 0;
        for(Componente c : rutas){
            Ruta r = (Ruta) c;
            if(r.getId() == id){
                probar = 1;
            }
        }
        return probar;
    }
    
    public void eliminarRuta(int id){
        Iterator<Componente> iter = rutas.iterator();
        while (iter.hasNext()){
            Componente r = iter.next();
            Ruta x = (Ruta) r;
            if(x.getId() == id){
                iter.remove();
            }
        }
    }
    
    
    //CRUD Reserva  
    public void crearReserva(int cupos, Ruta ruta, Usuario pasajero){
        int id = reservas.size();
        Reserva reserva = new Reserva(id, ruta, cupos, pasajero);
        for (Componente t : rutas) {
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
                Ruta l = r.getRuta();
                for(Componente t : rutas){
                    Ruta x = (Ruta) t;
                    if(t.equals(l)){
                        x.setCupos(-cupos + x.getCupos() + cuposI);
                        t = x;
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
    
    //CRUD Calle 
    public void crearCalle(Ruta r, String nombre, float[] origen, float[] destino) {
        Componente c = new Calle(nombre, origen, destino);
        for (Componente co : rutas) {
            Ruta x = (Ruta) co;
            if (x.equals(r)) {
                x.add(c);
                co = x;
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
            if(c.devolverNombre().equals(nombrebuscado)){
               componentes.remove(c);
               Componente x = new Calle(nombre, origen, destino);
               componentes.add(x);
            }
        }       
    }
    
    public void eliminarCalle(Ruta r, String nombrebuscado){
        for(int i=0; i<r.getComponentes().size(); i++){
            if(r.getComponentes().get(i).devolverNombre().equals(nombrebuscado)){
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
    
    public int verificarCalle(int id1, String nombre){
        int probar = 0;
        Ruta r = buscarRuta1(id1);
        ArrayList<Componente> componentes = r.getComponentes();
        for(Componente c : componentes){
            if(c.devolverNombre().equals(nombre)){
                probar = 1;
            }
        }
        return probar;
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
        for(Componente r: rutas){
            Ruta x = (Ruta)r;
            if(x.getHora().equals(hora) && x.getFecha() == fecha && x.getCupos() == cupos && x.getTarifa() == tarifa){
                encontrado = x;
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
                encontrado = x;
                break;
            }
        }
        return encontrado;
    }
    
    public String mostrarUsuarios(){
        String retornar = "Usuarios: \n\n";
        for(Usuario u : usuarios){
            retornar = retornar + u.toString();
        }
        return retornar;
    }
}
