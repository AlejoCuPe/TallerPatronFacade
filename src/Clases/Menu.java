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
public class Menu {
    
    String contenido;
    
    public Menu(){
        
    }
    
    public Menu(String cont){
        this.contenido = cont;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    @Override
    public String toString(){
        return this.getContenido();
    }
    
}
