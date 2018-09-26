/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
 *
 * @author Andrés
 */
public interface IUsuario {
    public String getCorreo();
    public void setCorreo(String correo);
    public String getPassword();
    public void setPassword(String password);
 
    public void adicionar(String correo, String password);
    public String consultar(String correo);
    public void modificar(String correo, String password);
    public String toString();
}
