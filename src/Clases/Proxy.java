/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Facade.Fachada;
import Interfaces.IMenu;
import java.util.ArrayList;

/**
 *
 * @author alejo
 */
public class Proxy implements IMenu{

    private static Proxy instance;
    private ArrayList<String[]> listaUsuarios = new ArrayList<>();
    
    public Proxy(){
        llenarUsuarios();
    }
      
    public static Proxy getInstance(){
        if(instance == null){
            instance = new Proxy();
        }
        return instance;
    }
    
    @Override
    public String buscar(String usuario, String password){

        String tipoUsuario = null;
        for(String[] s : this.listaUsuarios){
            if(s[0].equalsIgnoreCase(usuario)){
                if(s[1].equalsIgnoreCase(password)){
                    tipoUsuario = s[2];
                }
            }
        }
        return tipoUsuario;
    }
    
    private void llenarUsuarios(){
        String[] uno = {"A","B","Conductor"};
        String[] dos = {"C","D","Pasajero"};
        String[] tres = {"E","F","Administrador"};
        
        listaUsuarios.add(uno);
        listaUsuarios.add(dos);
        listaUsuarios.add(tres);
    }
    
}
