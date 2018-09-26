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
public class Conductor extends Usuario{    
    public Conductor(){
        super();
    }

    @Override
    public void adicionar(String correo, String password) {
        super.setCorreo(correo);
        super.setPassword(password);
    }

    @Override
    public String consultar(String correo) {
        return super.getPassword();
    }

    @Override
    public void modificar(String correo, String password) {
        super.setPassword(password);
    }
    
    @Override
    public String toString() {
        return "Correo: "+super.getCorreo()+" - Password: "+super.getPassword()+" - Tipo de Usuario: Conductor";
    }

  
        
}
