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
public class Pasajero extends Usuario{
    
    private String correoPasajero;
    private String passwordPasajero;
    
    public Pasajero(){
        super();
    }

    public String getCorreo() {
        return this.correoPasajero;
    }

    public void setCorreo(String correo) {
        this.correoPasajero = correo;
    }

    public String getPassword() {
        return this.passwordPasajero;
    }

    public void setPassword(String password) {
        this.passwordPasajero = password;
    }

   
    @Override
    public void adicionar(String correo, String password) {
        this.setCorreo(correo);
        this.setPassword(password);
    }

    @Override
    public String consultar(String correo) {
        return this.getPassword();
    }

    @Override
    public void modificar(String correo, String password) {
        this.setPassword(password);
    }
    
}
