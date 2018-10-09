/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaces.IUsuario;

/**
 *
 * @author alejo
 */
abstract public class Usuario implements IUsuario{
    protected String correo;
    protected String password;

    public Usuario(){
        
    }
    
    public Usuario(String correo, String password) {
        this.correo = correo;
        this.password = password;
    }
    
    @Override
    public abstract void adicionar(String correo, String password);
    @Override
    public abstract String consultar(String correo);
    @Override
    public abstract void modificar(String correo, String password);
    @Override
    public abstract String toString();

    @Override
    public String getCorreo() {
        return this.correo;
    }

    @Override
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }      
}
