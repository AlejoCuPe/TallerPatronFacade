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
public class AdministradorAdapter extends Usuario{
    private Administrador admin;
    
    public AdministradorAdapter(Administrador admin){
        super();
        this.admin = admin;
    }

    @Override
    public void adicionar(String correo, String password) {
        this.admin.crear(correo, password);
    }

    @Override
    public String consultar(String correo) {
        return this.admin.leer(correo);
    }

    @Override
    public void modificar(String correo, String password) {
        this.admin.actualizar(correo, password);
    }

    @Override
    public String toString() {
        return "Correo: " + getCorreo() + " - Password: " + getPassword() + " - Tipo de Usuario: Administrador\n";
    }
}
