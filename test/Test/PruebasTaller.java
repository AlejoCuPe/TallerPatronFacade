/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Clases.Calle;
import Clases.Conductor;
import Clases.Pasajero;
import Clases.Reserva;
import Clases.Ruta;
import Clases.Usuario;
import Facade.Fachada;
import Interfaces.Componente;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alejo
 */
public class PruebasTaller {
    
    public PruebasTaller() {
    }

    @Test
    public void testCrearRuta() {
        Fachada f = Fachada.getInstance();
        f.crearRuta("13:30", "Bogota", "Zipa", new Date(), 3, 3500, new Conductor());
        
        Ruta buscada = f.buscarRuta("13:30", new Date(), 3, 3500);
        assertEquals("Zipa", buscada.getDestino());
    }
    
    @Test
    public void testMostrarRuta() {
        Fachada f = Fachada.getInstance();
        Date d = new Date();
        f.crearRuta("13:30", "Bogota", "Zipa", d, 3, 3500, new Conductor());
        
        String ruta = f.mostrarRuta("Zipa", d, "13:30");
        assertEquals(ruta, ruta.toString());
    }
    
    @Test
    public void testVerificarRuta() {
        Fachada f = Fachada.getInstance();
        f.crearRuta("13:30", "Bogota", "Zipa", new Date(), 3, 3500, new Conductor());
              
        assertEquals(f.verificarRuta(0), 1);
    }
    
    @Test
    public void testEliminarRuta() {
        Fachada f = Fachada.getInstance();
        f.crearRuta("13:30", "Bogota", "Zipa", new Date(), 3, 3500, new Conductor());
        f.eliminarRuta(0);
              
        assertEquals(f.buscarRuta1(0), null);
    }
    
    @Test
    public void testCrearCalle() {
        Fachada f = Fachada.getInstance();
        Ruta r = new Ruta(0, "13:30", "Bogota", "Zipa", new Date(), 3, 3500, new Conductor());
        Componente c = new Calle("Caracas", new float[2], new float[2]);
        ArrayList<Componente> comp = new ArrayList<>();
        comp.add(c);
        r.add(c);
              
        assertEquals(r.getComponentes(), comp);
    }
    
    @Test
    public void testMostrarCalle() {
        Fachada f = Fachada.getInstance();
        Ruta r = new Ruta(0, "13:30", "Bogota", "Zipa", new Date(), 3, 3500, new Conductor());
        Componente c = new Calle("Caracas", new float[2], new float[2]);
        r.add(c);
              
        assertEquals(f.mostrarCalle(r), c.mostrar()+"\n");
    }
    
    @Test
    public void testModificarCalle() {
        Fachada f = Fachada.getInstance();
        Ruta r = new Ruta(0, "13:30", "Bogota", "Zipa", new Date(), 3, 3500, new Conductor());
        Componente c = new Calle("Caracas", new float[2], new float[2]);
        r.add(c);
        f.modificarCalle(r, "Caracas", "Boyaca", new float[2], new float[2]);
              
        assertNotSame(f.mostrarCalle(r), c.mostrar()+"\n");
    }
    
    @Test
    public void testEliminarCalle() {
        Fachada f = Fachada.getInstance();
        Ruta r = new Ruta(0, "13:30", "Bogota", "Zipa", new Date(), 3, 3500, new Conductor());
        Componente c = new Calle("Caracas", new float[2], new float[2]);
        r.add(c);
        f.eliminarCalle(r, "Caracas");
              
        assertEquals(f.mostrarCalle(r), "");
    }
    
    @Test
    public void testCrearReserva() {
        Date d = new Date();
        Fachada f = Fachada.getInstance();
        f.crearRuta("13:30", "Bogota", "Zipa", d, 3, 3500, new Conductor());
        Ruta buscada = f.buscarRuta("13:30", d, 3, 3500);
        f.crearReserva(3, buscada, new Pasajero());
              
        assertEquals(f.buscarRuta1(1).getCupos(), 0);
    }
    
    @Test
    public void testModificarReserva() {
        Date d = new Date();
        Fachada f = Fachada.getInstance();
        f.crearRuta("13:30", "Bogota", "Zipa", d, 3, 3500, new Conductor());
        Ruta buscada = f.buscarRuta("13:30", d, 3, 3500);
        f.crearReserva(3, buscada, new Pasajero());
        f.modificarReserva(0, 2);
              
        assertEquals(f.buscarRuta1(0).getCupos(), 1);
    }
    
    @Test
    public void testEliminarReserva() {
        Date d = new Date();
        Fachada f = Fachada.getInstance();
        f.crearRuta("13:30", "Bogota", "Zipa", d, 3, 3500, new Conductor());
        Ruta buscada = f.buscarRuta("13:30", d, 3, 3500);
        f.crearReserva(3, buscada, new Pasajero());
        f.eliminarReserva(0);
              
        assertEquals(f.buscarRuta1(2).getCupos(), 3);
    }
    
    @Test
    public void testVerificarReserva(){
        Date d = new Date();
        Fachada f = Fachada.getInstance();
        f.crearRuta("13:30", "Bogota", "Zipa", d, 3, 3500, new Conductor());
        Ruta buscada = f.buscarRuta("13:30", d, 3, 3500);
        f.crearReserva(3, buscada, new Pasajero());
        
        assertNotSame(f.verificarReserva(0),null);
    }
}
