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
abstract public class Usuario {
    
    protected String correo;
    protected String password;
    
    public abstract void adicionar(String correo, String password);
    public abstract String consultar(String correo);
    public abstract void modificar(String correo, String password);

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }  
    
}
