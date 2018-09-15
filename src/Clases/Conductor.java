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
    
    private String correoConductor;
    private String passwordConductor;
    
    public Conductor(){
        super();
    }

    public String getCorreo() {
        return this.correoConductor;
    }

    public void setCorreo(String correoConductor) {
        this.correoConductor = correoConductor;
    }

    public String getPassword() {
        return this.passwordConductor;
    }

    public void setPassword(String passwordConductor) {
        this.passwordConductor = passwordConductor;
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
