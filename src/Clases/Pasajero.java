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
    public Pasajero(){
        super();
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

    @Override
    public String toString() {
        return "Correo: "+getCorreo()+" - Password: "+getPassword()+" - Tipo de Usuario: Pasajero\n";
    }
    
}
