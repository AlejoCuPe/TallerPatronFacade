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
public class Administrador {
    
    private String correoAdmin;
    private String passwordAdmin;
    
    public Administrador(){
        
    }
    
    public void crear(String correo, String password){
        this.passwordAdmin = password;
        this.correoAdmin = correo;
    }
    
    public String leer(String correo){
        return this.passwordAdmin;
    }
    
    public void actualizar(String correo, String password){
        this.passwordAdmin = password;
    }
}
