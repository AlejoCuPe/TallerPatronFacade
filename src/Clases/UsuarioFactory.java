/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaces.IUsuario;
import java.util.HashMap;

/**
 *
 * @author Andrés
 */
public class UsuarioFactory {
    private HashMap<String,IUsuario> listaUsuarios = new HashMap();
    
    public void eliminarUsuario(String correo){
        listaUsuarios.remove(correo);
    }
    
    public void crearUsuario(String correo, IUsuario u){
        listaUsuarios.put(correo, u);
    }
    
    public IUsuario mostrarUsuario(String correo){
        return (IUsuario)listaUsuarios.get(correo);
    }
    
    public void modificarUsuario(String correoBuscado, String correo, String password){
        listaUsuarios.get(correo).setPassword(password);
        listaUsuarios.get(correo).setCorreo(correo);
    }
}

