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

    @Override
    public String getCorreo() {
        return this.correoPasajero;
    }

    @Override
    public void setCorreo(String correo) {
        this.correoPasajero = correo;
    }

    @Override
    public String getPassword() {
        return this.passwordPasajero;
    }

    @Override
    public void setPassword(String password) {
        this.passwordPasajero = password;
    }
    
    @Override
    public void adicionar(int id, String correo, String password) {
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
