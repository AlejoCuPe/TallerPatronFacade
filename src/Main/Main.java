/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Facade.Fachada;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) throws ParseException{
        Fachada f = new Fachada();
        int Opcion, Opcion1, cupos, tarifa, id;
        String correo, contraseña, tipoUsuario, nombreBuscado, nombreCalle, nombre, mostrar;
        float[] origenCalle = new float[2];
        float[] destinoCalle = new float[2];
        do{
            Opcion1 = Integer.parseInt(JOptionPane.showInputDialog("1. Crear Usuario \n"
                    + "2. Iniciar Sesión\n"
                    + "0. Salir"));
            switch(Opcion1){
                case 1:
                    correo = JOptionPane.showInputDialog("Ingrese su correo institucional");
                    contraseña = JOptionPane.showInputDialog("Ingrese una contraseña");
                    tipoUsuario = JOptionPane.showInputDialog("1. Conductor\n2. Pasajero \n3. Administrador");
                    f.crearUsuario(tipoUsuario, correo, contraseña);
                    break;
                case 2:
                    correo = JOptionPane.showInputDialog("Ingrese su correo institucional");
                    contraseña = JOptionPane.showInputDialog("Ingrese su contraseña");
                    String resultado = f.iniciarSesion(correo, contraseña);
                    if(resultado.equals("Fallido")){
                        JOptionPane.showMessageDialog(null, "Credenciales Incorrectas");
                        break;
                    }
                    else{
                        if(resultado.equals("1")){
                            do{
                                Opcion = Integer.parseInt(JOptionPane.showInputDialog("--- Ingresar Número Correspondiente ---\n"
                                        + "1. Crear Ruta\n"
                                        + "2. Consultar Ruta\n"
                                        + "3. Modificar Ruta\n"
                                        + "4. Eliminar Ruta\n"
                                        + "5. Crear Calle\n"
                                        + "6. Consultar Calle\n"
                                        + "7. Modificar Calle\n"
                                        + "8. Eliminar Calle\n"
                                        + "0. Salir"));
                                switch(Opcion){
                                    case 1:
                                        String hora = JOptionPane.showInputDialog("Ingrese la hora como hh:mm");
                                        String origen = JOptionPane.showInputDialog("Ingrese el origen"); 
                                        String destino = JOptionPane.showInputDialog("Ingrese el destino"); 
                                        Date date = null;
                                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                        String dateInString = JOptionPane.showInputDialog("Ingrese la fecha como dd/MM/yyyy");
                                        try {
                                            date = formatter.parse(dateInString);
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        cupos = Integer.parseInt(JOptionPane.showInputDialog("Ingrese los cupos de la ruta"));
                                        tarifa = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la tarifa de la ruta"));
                                        f.crearRuta(hora, origen, destino, date, cupos, tarifa, f.buscarUsuario(correo, contraseña));
                                        int elegir = 0;
                                        do{
                                            elegir = Integer.parseInt(JOptionPane.showInputDialog("1. Añadir una calle a la ruta.\n"
                                                                                                + "0. Terminar la creación."));
                                            switch(elegir){
                                                case 1:
                                                    nombreCalle = JOptionPane.showInputDialog("Ingrese el nombre de la calle");
                                                    origenCalle = new float[2];
                                                    destinoCalle = new float[2];
                                                    origenCalle[0] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el origen de la calle en x"));
                                                    origenCalle[1] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el origen de la calle en y"));
                                                    destinoCalle[0] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el destino de la calle en x"));
                                                    destinoCalle[1] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el destino de la calle en y"));
                                                    f.crearCalle(f.buscarRuta(hora, date, cupos, tarifa), nombreCalle, origenCalle, destinoCalle);
                                                    JOptionPane.showMessageDialog(null, "Calle creada");
                                                    break;
                                                case 0:
                                                    JOptionPane.showMessageDialog(null, "Creación de Calles Terminada");
                                                    break;
                                                default:
                                                    JOptionPane.showMessageDialog(null, "Ingrese un número valido");
                                                    break;
                                            }
                                        }while(elegir != 0);
                                        JOptionPane.showMessageDialog(null, "Ruta Creada");
                                        break;
                                    case 2:
                                        mostrar = f.mostrarRutas();
                                        if("Rutas \n".equals(mostrar)){
                                            JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                                            break;
                                        }
                                        else{
                                            destino = JOptionPane.showInputDialog("Ingrese el destino");
                                            date = null;
                                            formatter = new SimpleDateFormat("dd/MM/yyyy");
                                            dateInString = JOptionPane.showInputDialog("Ingrese la fecha como dd/MM/yyyy");
                                            try {
                                                date = formatter.parse(dateInString);
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }
                                            hora = JOptionPane.showInputDialog("Ingrese la fecha como hh:mm");
                                            mostrar = f.mostrarRuta(destino, date, hora);
                                            if(mostrar.equals("No se ha encontrado la ruta")){
                                                JOptionPane.showMessageDialog(null, "No se ha encontrado la ruta");
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(null, mostrar);
                                            }
                                            break;
                                        }
                                    case 3:
                                        mostrar = f.mostrarRutas();
                                        if("Rutas \n".equals(mostrar)){
                                            JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                                            break;
                                        }
                                        else{
                                            id = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de la ruta a modificar: \n"
                                                                   + mostrar));
                                            hora = JOptionPane.showInputDialog("Ingrese la fecha como hh:mm");
                                            date = null;
                                            formatter = new SimpleDateFormat("dd/MM/yyyy");
                                            dateInString = JOptionPane.showInputDialog("Ingrese la fecha como dd/MM/yyyy");
                                            try {
                                                date = formatter.parse(dateInString);
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            };
                                            cupos = Integer.parseInt(JOptionPane.showInputDialog("Ingrese los cupos de la ruta"));
                                            tarifa = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la tarifa de la ruta"));
                                            f.modificarRuta(id, hora, date, cupos, tarifa);
                                            JOptionPane.showMessageDialog(null, "Ruta Modificada");
                                            break;
                                        }
                                    case 4:
                                        mostrar = f.mostrarRutas();
                                        if("Rutas \n".equals(mostrar)){
                                            JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                                            break;
                                        }
                                        else{
                                            id = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de la ruta a eliminar\n"
                                                                   +mostrar));
                                            f.eliminarRuta(id);
                                            JOptionPane.showMessageDialog(null, "Ruta Eliminada");
                                            break;
                                        }
                                    case 5:
                                        mostrar = f.mostrarRutas();
                                        if("Rutas \n".equals(mostrar)){
                                            JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                                            break;
                                        }
                                        else{
                                            id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de la ruta a la que desea añadir la calle: \n"
                                                                    + mostrar ));
                                            nombreCalle = JOptionPane.showInputDialog("Ingrese el nombre de la calle");


                                            origenCalle[1] = 1;
                                            origenCalle[0] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el origen de la calle en x"));
                                            origenCalle[1] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el origen de la calle en y"));
                                            destinoCalle[0] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el destino de la calle en x"));
                                            destinoCalle[1] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el destino de la calle en y"));
                                            f.crearCalle(f.buscarRuta1(id), nombreCalle, origenCalle, destinoCalle);
                                            JOptionPane.showMessageDialog(null, "Calle creada y añadida");
                                            break;
                                        }
                                    case 6:
                                        mostrar = f.mostrarRutas();
                                        if("Rutas \n".equals(mostrar)){
                                            JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                                            break;
                                        }
                                        else{
                                            id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de la ruta de la que desea mostrar las calles: \n"
                                                                                          + mostrar));
                                            JOptionPane.showMessageDialog(null, f.mostrarCalle(f.buscarRuta1(id)));
                                            break;
                                        }
                                    case 7:
                                        mostrar = f.mostrarRutas();
                                        if("Rutas \n".equals(mostrar)){
                                            JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                                            break;
                                        }
                                        else{
                                            id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de la ruta que contiene la calle que desea modificar: \n"
                                                                                          + mostrar));
                                            mostrar = f.mostrarCalle(f.buscarRuta1(id));
                                            nombreBuscado = JOptionPane.showInputDialog("Ingrese el nombre de la ruta a modificar: " + mostrar);
                                            nombre = JOptionPane.showInputDialog("Ingrese el nombre nuevo de la calle ");
                                            origenCalle[0] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el nuevo origen de la calle en x"));
                                            origenCalle[1] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el nuevo origen de la calle en y"));
                                            destinoCalle[0] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el nuevo destino de la calle en x"));
                                            destinoCalle[1] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el nuevo destino de la calle en y"));
                                            f.modificarCalle(f.buscarRuta1(id), nombreBuscado, nombre, origenCalle, destinoCalle);
                                            JOptionPane.showMessageDialog(null, "Calle modificada");
                                            break;
                                        }
                                        
                                    case 8:
                                        mostrar = f.mostrarRutas();
                                        if("Rutas \n".equals(mostrar)){
                                            JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                                            break;
                                        }
                                        else{
                                            id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de la ruta que contiene la calle que desea modificar: \n"
                                                                                          + mostrar));
                                            mostrar = f.mostrarCalle(f.buscarRuta1(id));
                                            nombreBuscado = JOptionPane.showInputDialog("Ingrese el nombre de la ruta a modificar: " + mostrar);
                                            f.eliminarCalle(f.buscarRuta1(id), nombreBuscado);
                                            JOptionPane.showMessageDialog(null, "Calle eliminada");  
                                            break;
                                        }
                                }
                            }while(Opcion!= 0);
                        }
                        if(resultado.equals("2")){
                            JOptionPane.showMessageDialog(null, "Si llego a Pasajero");
                        }
                        if(resultado.equals("3")){
                            JOptionPane.showMessageDialog(null, "Todavía no hay para admin, sorry");
                        }
                    }

                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "Profe pongános 50 :'v");
                default:
                    JOptionPane.showMessageDialog(null, "Ingrese una opción valida");
            }
        }while(Opcion1 != 0);
    }
}
