/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Clases.Usuario;
import Facade.Fachada;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;


public class Main {
    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) throws ParseException{
        Fachada f = new Fachada();
        int Opcion, Opcion1, cupos, tarifa, id, probar;
        String correo, contraseña, tipoUsuario, nombreBuscado, nombreCalle, nombre, mostrar;
        float[] origenCalle = new float[2];
        float[] destinoCalle = new float[2];
        do{
            Opcion1 = Integer.parseInt(JOptionPane.showInputDialog("1. Crear Usuario \n"
                    + "2. Iniciar Sesión\n"
                    + "0. Salir"));
            switch(Opcion1){
                
                //Crear Usuario
                case 1:
                    correo = JOptionPane.showInputDialog("Ingrese su correo institucional");
                    contraseña = JOptionPane.showInputDialog("Ingrese una contraseña");
                    tipoUsuario = JOptionPane.showInputDialog("1. Conductor\n2. Pasajero \n3. Administrador");
                    f.crearUsuario(tipoUsuario, correo, contraseña);
                    break;
                    
                //Iniciar Sesión
                case 2:
                    correo = JOptionPane.showInputDialog("Ingrese su correo institucional");
                    contraseña = JOptionPane.showInputDialog("Ingrese su contraseña");
                    String resultado = f.iniciarSesion(correo, contraseña);
                    if(resultado.equals("Fallido")){
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
                                    
                                    //Crear Ruta
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
                                        f.crearRuta(hora, origen, destino, date, cupos, tarifa, (Usuario)f.buscarUsuario(correo, contraseña));
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
                                        
                                    //Consultar Ruta
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
                                        
                                    //Modificar Ruta
                                    case 3:
                                        mostrar = f.mostrarRutas();
                                        if("Rutas \n".equals(mostrar)){
                                            JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                                            break;
                                        }
                                        else{
                                            id = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de la ruta a modificar: \n"
                                                                   + mostrar));
                                            probar = f.verificarRuta(id);
                                            if(probar == 0){
                                                JOptionPane.showMessageDialog(null, "No existe dicha ruta");
                                                break;
                                            }
                                            else{
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
                                        }
                                        
                                    //Eliminar Ruta
                                    case 4:
                                        mostrar = f.mostrarRutas();
                                        if("Rutas \n".equals(mostrar)){
                                            JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                                            break;
                                        }
                                        else{
                                            id = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de la ruta a eliminar\n"
                                                                   +mostrar));
                                            probar = f.verificarRuta(id);
                                            if(probar == 0){
                                                JOptionPane.showMessageDialog(null, "No existe dicha ruta");
                                                break;
                                            }
                                            else{
                                                f.eliminarRuta(id);
                                                JOptionPane.showMessageDialog(null, "Ruta Eliminada");
                                                break;
                                            } 
                                        }
                                        
                                    //Crear Calle
                                    case 5:
                                        mostrar = f.mostrarRutas();
                                        if("Rutas \n".equals(mostrar)){
                                            JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                                            break;
                                        }
                                        else{
                                            id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de la ruta a la que desea añadir la calle: \n\n"
                                                                    + mostrar ));
                                            probar = f.verificarRuta(id);
                                            if(probar == 0){
                                                JOptionPane.showMessageDialog(null, "No existe dicha ruta");
                                                break;
                                            }
                                            else{
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
                                        }
                                        
                                    //Consultar Calle
                                    case 6:
                                        mostrar = f.mostrarRutas();
                                        if("Rutas \n".equals(mostrar)){
                                            JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                                            break;
                                        }
                                        else{
                                            id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de la ruta de la que desea mostrar las calles: \n \n"
                                                                                          + mostrar));
                                            probar = f.verificarRuta(id);
                                            if(probar == 0){
                                                JOptionPane.showMessageDialog(null, "No existe dicha ruta");
                                                break;
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(null, f.mostrarCalle(f.buscarRuta1(id)));
                                                break;
                                            }  
                                        }
                                        
                                    //Modificar Calle
                                    case 7:
                                        mostrar = f.mostrarRutas();
                                        if("Rutas \n".equals(mostrar)){
                                            JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                                            break;
                                        }
                                        else{
                                            id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de la ruta que contiene la calle que desea modificar:\n \n"
                                                                                          + mostrar));
                                            probar = f.verificarRuta(id);
                                            if(probar == 0){
                                                JOptionPane.showMessageDialog(null, "No existe dicha ruta");
                                                break;
                                            }
                                            else{
                                                mostrar = "";
                                                mostrar = f.mostrarCalle(f.buscarRuta1(id));
                                                nombreBuscado = JOptionPane.showInputDialog("Ingrese el nombre de la calle a modificar: \n\n" + mostrar);
                                                probar = f.verificarCalle(id, nombreBuscado);
                                                if(probar == 0){
                                                    JOptionPane.showMessageDialog(null, "No existe dicha calle");
                                                }
                                                else{
                                                    nombre = JOptionPane.showInputDialog("Ingrese el nombre nuevo de la calle ");
                                                    origenCalle[0] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el nuevo origen de la calle en x"));
                                                    origenCalle[1] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el nuevo origen de la calle en y"));
                                                    destinoCalle[0] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el nuevo destino de la calle en x"));
                                                    destinoCalle[1] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el nuevo destino de la calle en y"));
                                                    f.modificarCalle(f.buscarRuta1(id), nombreBuscado, nombre, origenCalle, destinoCalle);
                                                    JOptionPane.showMessageDialog(null, "Calle modificada");
                                                    break;
                                                }   
                                            }
                                        }
                                        
                                    //Eliminar Calle    
                                    case 8:
                                        mostrar = f.mostrarRutas();
                                        if("Rutas \n".equals(mostrar)){
                                            JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                                            break;
                                        }
                                        else{
                                            id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de la ruta que contiene la calle que desea modificar: \n\n"
                                                                                          + mostrar));
                                            probar = f.verificarRuta(id);
                                            if(probar == 0){
                                                JOptionPane.showMessageDialog(null, "No existe dicha ruta");
                                                break;
                                            }
                                            else{
                                                mostrar = "";
                                                mostrar = f.mostrarCalle(f.buscarRuta1(id));
                                                nombreBuscado = JOptionPane.showInputDialog("Ingrese el nombre de la calle a eliminar: \n\n" + mostrar);
                                                probar = f.verificarCalle(id, nombreBuscado);
                                                if(probar == 0){
                                                    JOptionPane.showMessageDialog(null, "No existe dicha calle");
                                                }
                                                else{
                                                    f.eliminarCalle(f.buscarRuta1(id), nombreBuscado);
                                                    JOptionPane.showMessageDialog(null, "Calle eliminada");  
                                                    break;
                                                } 
                                            } 
                                        }
                                        
                                    case 0: 
                                        JOptionPane.showMessageDialog(null, "Hasta luego conductor.");
                                        
                                    default:
                                        JOptionPane.showMessageDialog(null, "Ingrese una opción valida");
                                }
                            }while(Opcion!= 0);
                        }
                        if(resultado.equals("2")){
                            do{
                                Opcion = Integer.parseInt(JOptionPane.showInputDialog("--- Ingresar Número Correspondiente ---\n"
                                            + "1. Crear Reserva\n"
                                            + "2. Consultar Reserva\n"
                                            + "3. Modificar Reserva\n"
                                            + "4. Eliminar Reserva\n"
                                            + "0. Salir"));
                                switch(Opcion){
                                    
                                    //Crear Reserva
                                    case 1: 
                                        mostrar = f.mostrarRutas();
                                            if("Rutas \n".equals(mostrar)){
                                                JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                                                break;
                                            }
                                            else{
                                                id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de la ruta que desea reservar: \n\n"
                                                                                              + mostrar));
                                                probar = f.verificarRuta(id);
                                                if(probar == 0){
                                                    JOptionPane.showMessageDialog(null, "No existe dicha ruta");
                                                    break;
                                                }
                                                else{
                                                    cupos = Integer.parseInt(JOptionPane.showInputDialog("Ingrese los cupos que desea reservar"));
                                                    f.crearReserva(cupos, f.buscarRuta1(id), (Usuario)f.buscarUsuario(correo, contraseña));
                                                    JOptionPane.showMessageDialog(null, "Reserva creada");
                                                    break;
                                                }
                                            }
                                            
                                    //Mostrar Reserva
                                    case 2:
                                        mostrar = f.mostrarReserva();
                                        if("Reserva \n".equals(mostrar)){
                                               JOptionPane.showMessageDialog(null, "No hay reservas creadas");
                                               break;
                                           }
                                        else{
                                            JOptionPane.showMessageDialog(null, mostrar);
                                            break;
                                        }
                                        
                                    //Modificar Reserva
                                    case 3:
                                        mostrar = f.mostrarReserva();
                                        if("Reserva \n".equals(mostrar)){
                                               JOptionPane.showMessageDialog(null, "No hay reservas creadas");
                                               break;
                                        }
                                        else{
                                            id = Integer.parseInt(JOptionPane.showInputDialog("Selecciones la reserva que desea modificar: \n"
                                                                                          + mostrar));
                                            probar = f.verificarReserva(id);
                                            if(probar == 0){
                                                JOptionPane.showMessageDialog(null, "No existe dicha reserva");
                                                break;
                                            }
                                            else{
                                                cupos = Integer.parseInt(JOptionPane.showInputDialog("Ingrese los cupos que desea reservar nuevamente"));                                            
                                                f.modificarReserva(id,cupos);
                                                JOptionPane.showMessageDialog(null, "Reserva modificada");
                                                break;
                                            }
                                        }
                                        
                                    //Eliminar Reserva
                                    case 4: 
                                        mostrar = f.mostrarReserva();
                                        if("Reserva \n".equals(mostrar)){
                                               JOptionPane.showMessageDialog(null, "No hay reservas creadas");
                                               break;
                                        }
                                        else{
                                            id = Integer.parseInt(JOptionPane.showInputDialog("Seleccione la reserva que desea eliminar: \n\n"
                                                                                          + mostrar));
                                            probar = f.verificarReserva(id);
                                            if(probar == 0){
                                                JOptionPane.showMessageDialog(null, "No existe dicha reserva");
                                                break;
                                            }
                                            else{
                                                f.eliminarReserva(id);
                                                JOptionPane.showMessageDialog(null, "Reserva eliminada");
                                                break;
                                            }
                                        }
                                        
                                    case 0:
                                        JOptionPane.showMessageDialog(null, "Hasta luego pasajero");
                                        
                                    default:
                                        JOptionPane.showMessageDialog(null, "Ingrese una opción valida");
                                }
                            }while(Opcion!= 0);
                        }
                        if(resultado.equals("3")){
                            
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
