/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Clases.Administrador;
import Clases.AdministradorAdapter;
import Clases.Calle;
import Clases.Conductor;
import Clases.Pasajero;
import Clases.Reserva;
import Clases.Ruta;
import Clases.Usuario;
import Clases.UsuarioFactory;
import Interfaces.Componente;
import Interfaces.IUsuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author alejo
 */
public class Fachada {

    private static Fachada instance;
    private final ArrayList<Ruta> rutas = new ArrayList<>();
    private final ArrayList<Reserva> reservas = new ArrayList<>();
    private UsuarioFactory u = new UsuarioFactory();
    private int sesion;

    public Fachada() {
        crearUsuario("1","A","B");
    }

    public static Fachada getInstance() {
        if (instance == null) {
            instance = new Fachada();
        }
        return instance;
    }

    //CRUD Ruta   
    public void crearRuta(String hora, String origen, String destino, Date fecha, int cupos, int tarifa, Usuario conductor) {
        int id = rutas.size();
        Ruta ruta = new Ruta(id, origen, destino, hora, fecha, cupos, tarifa, conductor);
        rutas.add(ruta);
    }

    public String mostrarRuta(String nombreDestino, Date fecha, String hora) {
        Componente buscada = null;
        for (Ruta r : rutas) {
            if (r.getDestino().equals(nombreDestino)) {
                if (r.getFecha().toString().equals(fecha.toString()) && r.getHora().equals(hora)) {
                    buscada = r;
                }
            }
        }

        if (buscada == null) {
            return "No se ha encontrado la ruta";
        } else {
            return buscada.mostrar();
        }

    }

    public String mostrarRutas() {
        String texto = "Rutas \n\n";
        for (Ruta r : rutas) {
            texto = texto + r.mostrarRuta() + "\n \n";
        }
        return texto;
    }

    public void modificarRuta(int id, String hora, Date fecha, int cupos, int tarifa) {
        for (Ruta c : rutas) {
            if (c.getId() == id) {
                c.setHora(hora);
                c.setFecha(fecha);
                c.setCupos(cupos);
                c.setTarifa(tarifa);
            }
        }
    }

    public int verificarRuta(int id) {
        int probar = 0;
        for (Ruta c : rutas) {
            if (c.getId() == id) {
                probar = 1;
            }
        }
        return probar;
    }

    public void eliminarRuta(int id) {
        Iterator<Ruta> iter = rutas.iterator();
        while (iter.hasNext()) {
            Ruta r = iter.next();
            if (r.getId() == id) {
                iter.remove();
            }
        }
    }

    //CRUD Calle 
    public void crearCalle(Ruta r, String nombre, float[] origen, float[] destino) {
        Componente c = new Calle(nombre, origen, destino);
        for (Ruta co : rutas) {
            if (co.equals(r)) {
                co.add(c);
                break;
            }
        }
    }

    public String mostrarCalle(Ruta r) {
        ArrayList<Componente> componentes = r.getComponentes();
        String mostrar = "";
        for (Componente c : componentes) {
            mostrar = mostrar + c.mostrar() + "\n";
        }
        return mostrar;
    }

    public void modificarCalle(Ruta r, String nombrebuscado, String nombre, float[] origen, float[] destino) {
        ArrayList<Componente> componentes = r.getComponentes();
        for (Componente c : componentes) {
            String cosa = c.mostrar();
            String[] m = cosa.split("°");
            if (m[1].equals(nombrebuscado)) {
                c.modificar(nombre, origen, destino);
            }
        }
    }

    public void eliminarCalle(Ruta r, String nombrebuscado) {
        for (int i = 0; i < r.getComponentes().size(); i++) {
            String cosa = r.getComponentes().get(i).mostrar();
            String[] m = cosa.split("°");
            if (m[1].equals(nombrebuscado)) {
                r.getComponentes().remove(i);
            }
        }
    }

    //CRUD Reserva  
    public void crearReserva(int cupos, Ruta ruta, Usuario pasajero) {
        int id = reservas.size();
        Reserva reserva = new Reserva(id, ruta, cupos, pasajero);
        for (Ruta t : rutas) {
            if (t.equals(reserva.getRuta())) {
                ruta.setCupos(ruta.getCupos() - cupos);
            }
        }
        reservas.add(reserva);
    }

    public String mostrarReserva() {
        String texto = "Reservas \n\n";
        for (Reserva r : reservas) {
            texto = texto + r.toString() + "\n ";
        }
        return texto;
    }

    public void modificarReserva(int id, int cupos) {
        for (Reserva r : reservas) {
            if (r.getId() == id) {
                int cuposI = r.getCuposReservados();
                r.setCuposReservados(cupos);
                for (Ruta t : rutas) {
                    String datos = t.mostrar();
                    if (t.equals(r.getRuta())) {
                        t.setCupos(-cupos + t.getCupos() + cuposI);
                        break;
                    }
                }
            }
        }
    }

    public void eliminarReserva(int id) {
        Iterator<Reserva> iter = reservas.iterator();
        while (iter.hasNext()) {
            Reserva r = iter.next();
            if (r.getId() == id) {
                iter.remove();
            }
        }
    }

    public int verificarReserva(int id) {
        int probar = 0;
        for (Reserva r : reservas) {
            if (r.getId() == id) {
                probar = 1;
            }
        }
        return probar;
    }

    //Metodos Usuario    
    public void crearUsuario(String tipoUsuario, String correo, String password) {
        switch (tipoUsuario) {
            case "2":
                Usuario pasajero = new Pasajero();
                pasajero.adicionar(correo, password);
                u.crearUsuario(correo, pasajero);
                JOptionPane.showMessageDialog(null, "Usuario Creado Correctamente");
                break;
            case "1":
                Usuario conductor = new Conductor();
                conductor.adicionar(correo, password);
                JOptionPane.showMessageDialog(null, "Usuario Creado Correctamente");
                u.crearUsuario(correo, conductor);
                break;
            case "3":
                Administrador a = new Administrador();
                Usuario admin = new AdministradorAdapter(a);
                JOptionPane.showMessageDialog(null, "Usuario Creado Correctamente");
                admin.adicionar(correo, password);
                u.crearUsuario(correo, admin);
                break;
            default:
                break;

        }
    }

    public int verificarCalle(int id1, String nombre) {
        int probar = 0;
        Ruta r = buscarRuta1(id1);
        for (Componente c : r.getComponentes()) {
            String cosa = c.mostrar();
            String[] m = cosa.split("°");
            if (m[1].equals(nombre)) {
                probar = 1;
            }
        }
        return probar;
    }

    public boolean iniciarSesion(String correo, String password, int sesion) {
        IUsuario x = u.mostrarUsuario(correo);
        if (x == null) {
            JOptionPane.showMessageDialog(null, "No existe ese usuario");
            return false;
        } else {
            if (x.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(null, "Sesión Iniciada");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Contraseña Incorrecta");
                return false;
            }
        }
    }

    public IUsuario buscarUsuario(String correo, String contraseña) {
        IUsuario encontrado = null;
        encontrado = u.mostrarUsuario(correo);
        return encontrado;
    }

    public Ruta buscarRuta(String hora, Date fecha, int cupos, int tarifa) {
        Ruta encontrado = null;
        for (Componente r : rutas) {
            Ruta k = (Ruta) r;
            k.getId();
            if (k.getHora().equals(hora) && k.getFecha().toString().equals(fecha.toString()) && k.getCupos() == cupos && k.getTarifa() == tarifa) {
                encontrado = (Ruta) r;
                break;
            }
        }
        return encontrado;
    }

    public Ruta buscarRuta1(int id) {
        Ruta encontrado = null;
        for (Componente r : rutas) {
            Ruta x = (Ruta) r;
            if (x.getId() == id) {
                encontrado = (Ruta) r;
                break;
            }
        }
        return encontrado;
    }

    public String menu(String tipoUsuario) {
        
        String menu = "";
        if (tipoUsuario.equals("Conductor")) {
            menu = "--- Ingresar Número Correspondiente ---\n"
                    + "1. Crear Ruta\n"
                    + "2. Consultar Ruta\n"
                    + "3. Modificar Ruta\n"
                    + "4. Eliminar Ruta\n"
                    + "5. Crear Calle\n"
                    + "6. Consultar Calle\n"
                    + "7. Modificar Calle\n"
                    + "8. Eliminar Calle\n"
                    + "0. Salir";
        }
        if (tipoUsuario.equals("Pasajero")) {
            menu = "--- Ingresar Número Correspondiente ---\n"
                    + "1. Crear Reserva\n"
                    + "2. Consultar Reserva\n"
                    + "3. Modificar Reserva\n"
                    + "4. Eliminar Reserva\n"
                    + "5. Pagar Servicio"
                    + "0. Salir";
        }
        if (tipoUsuario.equals("Administrador")) {
            menu = "--- Ingresar Número Correspondiente ---\n"
                    + "1. Buscar Usuario\n"
                    + "2. Modificar Usuario\n"
                    + "3. Eliminar Usuario\n"
                    + "0. Salir";
        }
        return menu;
    }

    public void menuConductor(int opcion) {
        
        int Opcion, Opcion1, cupos, tarifa, id, probar;
        String correo = "", contraseña = "", tipoUsuario, nombreBuscado, nombreCalle, nombre, mostrar;
        float[] origenCalle = new float[2];
        float[] destinoCalle = new float[2];
        switch (opcion) {
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

                crearRuta(hora, origen, destino, date, cupos, tarifa, (Usuario) buscarUsuario(correo, contraseña));
                int elegir = 0;
                do {
                    elegir = Integer.parseInt(JOptionPane.showInputDialog("1. Añadir una calle a la ruta.\n"
                            + "0. Terminar la creación."));
                    switch (elegir) {
                        case 1:
                            nombreCalle = JOptionPane.showInputDialog("Ingrese el nombre de la calle");
                            origenCalle = new float[2];
                            destinoCalle = new float[2];
                            origenCalle[0] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el origen de la calle en x"));
                            origenCalle[1] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el origen de la calle en y"));
                            destinoCalle[0] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el destino de la calle en x"));
                            destinoCalle[1] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el destino de la calle en y"));
                            crearCalle(buscarRuta(hora, date, cupos, tarifa), nombreCalle, origenCalle, destinoCalle);
                            JOptionPane.showMessageDialog(null, "Calle creada");
                            break;
                        case 0:
                            JOptionPane.showMessageDialog(null, "Creación de Calles Terminada");
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Ingrese un número valido");
                            break;
                    }
                } while (elegir != 0);
                JOptionPane.showMessageDialog(null, "Ruta Creada");
                break;

            //Consultar Ruta
            case 2:
                mostrar = mostrarRutas();
                if ("Rutas \n".equals(mostrar)) {
                    JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                    break;
                } else {
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
                    mostrar = mostrarRuta(destino, date, hora);
                    if (mostrar.equals("No se ha encontrado la ruta")) {
                        JOptionPane.showMessageDialog(null, "No se ha encontrado la ruta");
                    } else {
                        JOptionPane.showMessageDialog(null, mostrar);
                    }
                    break;
                }

            //Modificar Ruta
            case 3:
                mostrar = mostrarRutas();
                if ("Rutas \n".equals(mostrar)) {
                    JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                    break;
                } else {
                    id = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de la ruta a modificar: \n"
                            + mostrar));
                    probar = verificarRuta(id);
                    if (probar == 0) {
                        JOptionPane.showMessageDialog(null, "No existe dicha ruta");
                        break;
                    } else {
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
                        modificarRuta(id, hora, date, cupos, tarifa);
                        JOptionPane.showMessageDialog(null, "Ruta Modificada");
                        break;
                    }
                }

            //Eliminar Ruta
            case 4:
                mostrar = mostrarRutas();
                if ("Rutas \n".equals(mostrar)) {
                    JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                    break;
                } else {
                    id = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de la ruta a eliminar\n"
                            + mostrar));
                    probar = verificarRuta(id);
                    if (probar == 0) {
                        JOptionPane.showMessageDialog(null, "No existe dicha ruta");
                        break;
                    } else {
                        eliminarRuta(id);
                        JOptionPane.showMessageDialog(null, "Ruta Eliminada");
                        break;
                    }
                }

            //Crear Calle
            case 5:
                mostrar = mostrarRutas();
                if ("Rutas \n".equals(mostrar)) {
                    JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                    break;
                } else {
                    id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de la ruta a la que desea añadir la calle: \n\n"
                            + mostrar));
                    probar = verificarRuta(id);
                    if (probar == 0) {
                        JOptionPane.showMessageDialog(null, "No existe dicha ruta");
                        break;
                    } else {
                        nombreCalle = JOptionPane.showInputDialog("Ingrese el nombre de la calle");
                        origenCalle[1] = 1;
                        origenCalle[0] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el origen de la calle en x"));
                        origenCalle[1] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el origen de la calle en y"));
                        destinoCalle[0] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el destino de la calle en x"));
                        destinoCalle[1] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el destino de la calle en y"));
                        crearCalle(buscarRuta1(id), nombreCalle, origenCalle, destinoCalle);
                        JOptionPane.showMessageDialog(null, "Calle creada y añadida");
                        break;
                    }
                }

            //Consultar Calle
            case 6:
                mostrar = mostrarRutas();
                if ("Rutas \n".equals(mostrar)) {
                    JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                    break;
                } else {
                    id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de la ruta de la que desea mostrar las calles: \n \n"
                            + mostrar));
                    probar = verificarRuta(id);
                    if (probar == 0) {
                        JOptionPane.showMessageDialog(null, "No existe dicha ruta");
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, mostrarCalle(buscarRuta1(id)));
                        break;
                    }
                }

            //Modificar Calle
            case 7:
                mostrar = mostrarRutas();
                if ("Rutas \n".equals(mostrar)) {
                    JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                    break;
                } else {
                    id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de la ruta que contiene la calle que desea modificar:\n \n"
                            + mostrar));
                    probar = verificarRuta(id);
                    if (probar == 0) {
                        JOptionPane.showMessageDialog(null, "No existe dicha ruta");
                        break;
                    } else {
                        mostrar = "";
                        mostrar = mostrarCalle(buscarRuta1(id));
                        nombreBuscado = JOptionPane.showInputDialog("Ingrese el nombre de la calle a modificar: \n\n" + mostrar);
                        probar = verificarCalle(id, nombreBuscado);
                        if (probar == 0) {
                            JOptionPane.showMessageDialog(null, "No existe dicha calle");
                        } else {
                            nombre = JOptionPane.showInputDialog("Ingrese el nombre nuevo de la calle ");
                            origenCalle[0] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el nuevo origen de la calle en x"));
                            origenCalle[1] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el nuevo origen de la calle en y"));
                            destinoCalle[0] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el nuevo destino de la calle en x"));
                            destinoCalle[1] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el nuevo destino de la calle en y"));
                            modificarCalle(buscarRuta1(id), nombreBuscado, nombre, origenCalle, destinoCalle);
                            JOptionPane.showMessageDialog(null, "Calle modificada");
                            break;
                        }
                    }
                }

            //Eliminar Calle    
            case 8:
                mostrar = mostrarRutas();
                if ("Rutas \n".equals(mostrar)) {
                    JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                    break;
                } else {
                    id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de la ruta que contiene la calle que desea modificar: \n\n"
                            + mostrar));
                    probar = verificarRuta(id);
                    if (probar == 0) {
                        JOptionPane.showMessageDialog(null, "No existe dicha ruta");
                        break;
                    } else {
                        mostrar = "";
                        mostrar = mostrarCalle(buscarRuta1(id));
                        nombreBuscado = JOptionPane.showInputDialog("Ingrese el nombre de la calle a eliminar: \n\n" + mostrar);
                        probar = verificarCalle(id, nombreBuscado);
                        if (probar == 0) {
                            JOptionPane.showMessageDialog(null, "No existe dicha calle");
                        } else {
                            eliminarCalle(buscarRuta1(id), nombreBuscado);
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
    }
}
