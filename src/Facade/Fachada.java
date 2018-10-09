/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Clases.Administrador;
import Clases.AdministradorAdapter;
import Clases.BalotoDecorator;
import Clases.Calle;
import Clases.Conductor;
import Clases.CreditoDecorator;
import Clases.Pago;
import Clases.PagoEfectivo;
import Clases.PagoPSE;
import Clases.Pasajero;
import Clases.Proxy;
import Clases.Reserva;
import Clases.Ruta;
import Clases.Usuario;
import Clases.UsuarioFactory;
import Interfaces.Component;
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
        Usuario conductor = new Conductor();
        conductor.adicionar("A", "B");
        u.crearUsuario("A", conductor);
        Usuario pasajero = new Pasajero();
        pasajero.adicionar("C", "D");
        u.crearUsuario("C", pasajero);
        Administrador a = new Administrador();
        Usuario admin = new AdministradorAdapter(a);
        admin.adicionar("E", "F");
        u.crearUsuario("E", admin);
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
            case "1":
                Usuario conductor = new Conductor();
                conductor.adicionar(correo, password);
                JOptionPane.showMessageDialog(null, "Conductor Creado Correctamente");
                u.crearUsuario(correo, conductor);
                break;
            case "2":
                Usuario pasajero = new Pasajero();
                pasajero.adicionar(correo, password);
                u.crearUsuario(correo, pasajero);
                JOptionPane.showMessageDialog(null, "Pasajero Creado Correctamente");
                break;
            case "3":
                Administrador a = new Administrador();
                a.crear(correo, password);
                Usuario admin = new AdministradorAdapter(a);
                JOptionPane.showMessageDialog(null, "Usuario Creado Correctamente");
                admin.adicionar(correo, password);
                u.crearUsuario(correo, admin);
                JOptionPane.showMessageDialog(null, "Administrador Creado Correctamente");
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
        boolean booleano = false;
        if (x.getCorreo() == null) {
            if (x == null) {
                JOptionPane.showMessageDialog(null, "No existe ese usuario");
                booleano = false;
            } else {
                if (x.getPassword().equals(password)) {
                    JOptionPane.showMessageDialog(null, "Sesión Iniciada");
                    booleano = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Contraseña Incorrecta");
                    booleano = false;
                }
            }
        }
        return booleano;
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
                    + "5. Pagar Servicio\n"
                    + "0. Salir";
        }
        if (tipoUsuario.equals("Administrador")) {
            menu = "--- Ingresar Número Correspondiente ---\n"
                    + "1. Crear Usuario"
                    + "2. Buscar Usuario\n"
                    + "3. Modificar Usuario\n"
                    + "4. Eliminar Usuario\n"
                    + "0. Salir";
        }
        return menu;
    }

    public void llamarMenu(int opcion, String tipoU, String usuario, String password) {
        if (tipoU.equals("Conductor")) {
            menuConductor(opcion, usuario, password);
        }
        if (tipoU.equals("Pasajero")) {
            menuPasajero(opcion, usuario, password);
        }
        if (tipoU.equals("Administrador")) {
            menuAdministrador(opcion, usuario, password);
        }
    }

    private void menuConductor(int opcion, String usuario, String password) {
        switch (opcion) {
            case 1:
                String hora = JOptionPane.showInputDialog("Ingrese la hora como hh:mm");
                String origen = JOptionPane.showInputDialog("Ingrese el origen");
                String destino = JOptionPane.showInputDialog("Ingrese el destino");
                Date date = null;
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String fecha = JOptionPane.showInputDialog("Ingrese la fecha como dd/MM/yyyy");
                try {
                    date = formatter.parse(fecha);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                int cupos = Integer.parseInt(JOptionPane.showInputDialog("Ingrese los cupos de la ruta"));
                int tarifa = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la tarifa de la ruta"));
                crearRuta(hora, origen, destino, date, cupos, tarifa, (Usuario) buscarUsuario(usuario, password));
                int elegir = 0;
                do {
                    elegir = Integer.parseInt(JOptionPane.showInputDialog("1. Añadir una calle a la ruta.\n"
                            + "0. Terminar la creación."));
                    switch (elegir) {
                        case 1:
                            String nombreCalle = JOptionPane.showInputDialog("Ingrese el nombre de la calle");
                            float[] origenCalle = new float[2];
                            float[] destinoCalle = new float[2];
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
            case 2:
                String mostrar2 = mostrarRutas();
                if ("Rutas \n".equals(mostrar2)) {
                    JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                    break;
                } else {
                    String destino2 = JOptionPane.showInputDialog("Ingrese el destino");
                    Date date2 = null;
                    SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
                    String fecha2 = JOptionPane.showInputDialog("Ingrese la fecha como dd/MM/yyyy");
                    try {
                        date2 = formatter2.parse(fecha2);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String hora2 = JOptionPane.showInputDialog("Ingrese la fecha como hh:mm");
                    mostrar2 = mostrarRuta(destino2, date2, hora2);
                    if (mostrar2.equals("No se ha encontrado la ruta")) {
                        JOptionPane.showMessageDialog(null, "No se ha encontrado la ruta");
                    } else {
                        JOptionPane.showMessageDialog(null, mostrar2);
                    }
                    break;
                }
            case 3:
                String mostrar3 = mostrarRutas();
                if ("Rutas \n".equals(mostrar3)) {
                    JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                    break;
                } else {
                    int id3 = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de la ruta a modificar: \n"
                            + mostrar3));
                    int probar = verificarRuta(id3);
                    if (probar == 0) {
                        JOptionPane.showMessageDialog(null, "No existe dicha ruta");
                        break;
                    } else {
                        String hora3 = JOptionPane.showInputDialog("Ingrese la fecha como hh:mm");
                        Date date3 = null;
                        SimpleDateFormat formatter3 = new SimpleDateFormat("dd/MM/yyyy");
                        String dateInString3 = JOptionPane.showInputDialog("Ingrese la fecha como dd/MM/yyyy");
                        try {
                            date3 = formatter3.parse(dateInString3);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        };
                        int cupos3 = Integer.parseInt(JOptionPane.showInputDialog("Ingrese los cupos de la ruta"));
                        int tarifa3 = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la tarifa de la ruta"));
                        modificarRuta(id3, hora3, date3, cupos3, tarifa3);
                        JOptionPane.showMessageDialog(null, "Ruta Modificada");
                        break;
                    }
                }
            case 4:
                String mostrar4 = mostrarRutas();
                if ("Rutas \n".equals(mostrar4)) {
                    JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                    break;
                } else {
                    int id4 = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de la ruta a eliminar\n"
                            + mostrar4));
                    int probar4 = verificarRuta(id4);
                    if (probar4 == 0) {
                        JOptionPane.showMessageDialog(null, "No existe dicha ruta");
                        break;
                    } else {
                        eliminarRuta(id4);
                        JOptionPane.showMessageDialog(null, "Ruta Eliminada");
                        break;
                    }
                }
            case 5:
                String mostrar5 = mostrarRutas();
                if ("Rutas \n".equals(mostrar5)) {
                    JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                    break;
                } else {
                    int id5 = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de la ruta a la que desea añadir la calle: \n\n"
                            + mostrar5));
                    int probar5 = verificarRuta(id5);
                    if (probar5 == 0) {
                        JOptionPane.showMessageDialog(null, "No existe dicha ruta");
                        break;
                    } else {
                        String nombreCalle5 = JOptionPane.showInputDialog("Ingrese el nombre de la calle");
                        float[] origenCalle5 = new float[2];
                        float[] destinoCalle5 = new float[2];
                        origenCalle5[1] = 1;
                        origenCalle5[0] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el origen de la calle en x"));
                        origenCalle5[1] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el origen de la calle en y"));
                        destinoCalle5[0] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el destino de la calle en x"));
                        destinoCalle5[1] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el destino de la calle en y"));
                        crearCalle(buscarRuta1(id5), nombreCalle5, origenCalle5, destinoCalle5);
                        JOptionPane.showMessageDialog(null, "Calle creada y añadida");
                        break;
                    }
                }
            case 6:
                String mostrar6 = mostrarRutas();
                if ("Rutas \n".equals(mostrar6)) {
                    JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                    break;
                } else {
                    int id6 = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de la ruta de la que desea mostrar las calles: \n \n"
                            + mostrar6));
                    int probar6 = verificarRuta(id6);
                    if (probar6 == 0) {
                        JOptionPane.showMessageDialog(null, "No existe dicha ruta");
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, mostrarCalle(buscarRuta1(id6)));
                        break;
                    }
                }
            case 7:
                String mostrar7 = mostrarRutas();
                if ("Rutas \n".equals(mostrar7)) {
                    JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                    break;
                } else {
                    int id7 = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de la ruta que contiene la calle que desea modificar:\n \n"
                            + mostrar7));
                    int probar7 = verificarRuta(id7);
                    if (probar7 == 0) {
                        JOptionPane.showMessageDialog(null, "No existe dicha ruta");
                        break;
                    } else {
                        mostrar7 = "";
                        mostrar7 = mostrarCalle(buscarRuta1(id7));
                        String nombreBuscado7 = JOptionPane.showInputDialog("Ingrese el nombre de la calle a modificar: \n\n" + mostrar7);
                        probar7 = verificarCalle(id7, nombreBuscado7);
                        if (probar7 == 0) {
                            JOptionPane.showMessageDialog(null, "No existe dicha calle");
                        } else {
                            String nombre7 = JOptionPane.showInputDialog("Ingrese el nombre nuevo de la calle ");
                            float[] origenCalle7 = new float[2];
                            float[] destinoCalle7 = new float[2];
                            origenCalle7[0] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el nuevo origen de la calle en x"));
                            origenCalle7[1] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el nuevo origen de la calle en y"));
                            destinoCalle7[0] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el nuevo destino de la calle en x"));
                            destinoCalle7[1] = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el nuevo destino de la calle en y"));
                            modificarCalle(buscarRuta1(id7), nombreBuscado7, nombre7, origenCalle7, destinoCalle7);
                            JOptionPane.showMessageDialog(null, "Calle modificada");
                            break;
                        }
                    }
                }
            case 8:
                String mostrar8 = mostrarRutas();
                if ("Rutas \n".equals(mostrar8)) {
                    JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                    break;
                } else {
                    int id8 = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de la ruta que contiene la calle que desea modificar: \n\n"
                            + mostrar8));
                    int probar8 = verificarRuta(id8);
                    if (probar8 == 0) {
                        JOptionPane.showMessageDialog(null, "No existe dicha ruta");
                        break;
                    } else {
                        mostrar8 = "";
                        mostrar8 = mostrarCalle(buscarRuta1(id8));
                        String nombreBuscado8 = JOptionPane.showInputDialog("Ingrese el nombre de la calle a eliminar: \n\n" + mostrar8);
                        probar8 = verificarCalle(id8, nombreBuscado8);
                        if (probar8 == 0) {
                            JOptionPane.showMessageDialog(null, "No existe dicha calle");
                        } else {
                            eliminarCalle(buscarRuta1(id8), nombreBuscado8);
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

    private void menuPasajero(int opcion, String usuario, String password) {
        switch (opcion) {
            case 1:
                String mostrar = mostrarRutas();
                if ("Rutas \n".equals(mostrar)) {
                    JOptionPane.showMessageDialog(null, "No hay rutas creadas");
                    break;
                } else {
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de la ruta que desea reservar: \n\n"
                            + mostrar));
                    int probar = verificarRuta(id);
                    if (probar == 0) {
                        JOptionPane.showMessageDialog(null, "No existe dicha ruta");
                        break;
                    } else {
                        int cupos = Integer.parseInt(JOptionPane.showInputDialog("Ingrese los cupos que desea reservar"));
                        crearReserva(cupos, buscarRuta1(id), (Usuario) buscarUsuario(usuario, password));
                        JOptionPane.showMessageDialog(null, "Reserva creada");
                        break;
                    }
                }
            case 2:
                String mostrar2 = mostrarReserva();
                if ("Reserva \n".equals(mostrar2)) {
                    JOptionPane.showMessageDialog(null, "No hay reservas creadas");
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, mostrar2);
                    break;
                }
            case 3:
                String mostrar3 = mostrarReserva();
                if ("Reserva \n".equals(mostrar3)) {
                    JOptionPane.showMessageDialog(null, "No hay reservas creadas");
                    break;
                } else {
                    int id3 = Integer.parseInt(JOptionPane.showInputDialog("Selecciones la reserva que desea modificar: \n"
                            + mostrar3));
                    int probar3 = verificarReserva(id3);
                    if (probar3 == 0) {
                        JOptionPane.showMessageDialog(null, "No existe dicha reserva");
                        break;
                    } else {
                        int cupos3 = Integer.parseInt(JOptionPane.showInputDialog("Ingrese los cupos que desea reservar nuevamente"));
                        modificarReserva(id3, cupos3);
                        JOptionPane.showMessageDialog(null, "Reserva modificada");
                        break;
                    }
                }
            case 4:
                String mostrar4 = mostrarReserva();
                if ("Reserva \n".equals(mostrar4)) {
                    JOptionPane.showMessageDialog(null, "No hay reservas creadas");
                    break;
                } else {
                    int id4 = Integer.parseInt(JOptionPane.showInputDialog("Seleccione la reserva que desea eliminar: \n\n"
                            + mostrar4));
                    int probar4 = verificarReserva(id4);
                    if (probar4 == 0) {
                        JOptionPane.showMessageDialog(null, "No existe dicha reserva");
                        break;
                    } else {
                        eliminarReserva(id4);
                        JOptionPane.showMessageDialog(null, "Reserva eliminada");
                        break;
                    }
                }
            case 5:
                ArrayList<String> pagos = new ArrayList<>();
                Component pago;
                String valor = JOptionPane.showInputDialog("Ingrese el valor a pagar");
                String id1 = JOptionPane.showInputDialog("Ingrese el ID del Pasajero");
                String id2 = JOptionPane.showInputDialog("Ingrese el ID del Conductor");
                pagos.add(valor);
                pagos.add(id1);
                pagos.add(id2);
                Component p = new Pago();
                p.asignar(pagos);

                String medioPago = JOptionPane.showInputDialog("Ingrese el medio de pago"
                        + "\n 1. Efectivo"
                        + "\n 2. PSE");
                if (medioPago.equals("1")) {
                    ArrayList<String> efectivo = new ArrayList<>();
                    pago = new PagoEfectivo(p);
                    efectivo.add(valor);
                    efectivo.add("Pesos colombianos");
                    p.asignar(efectivo);
                    JOptionPane.showMessageDialog(null, "PAGO REALIZADO CON EXITO\n" + p.pagar());
                } else if (medioPago.equals("2")) {
                    pago = new PagoPSE(new Pago());
                    ArrayList<String> pse = new ArrayList<>();
                    String noCuentaP = JOptionPane.showInputDialog("Ingrese el numero de su cuenta");
                    String claveCuenta = JOptionPane.showInputDialog("Ingrese la clave de la cuenta");
                    String noCuentaD = JOptionPane.showInputDialog("Ingrese el numero de la cuenta del conductor");
                    pse.add(valor);
                    pse.add(noCuentaP);
                    pse.add(claveCuenta);
                    pse.add(noCuentaD);
                    System.out.print(pse.size());
                    pago.asignar(pse);
                    String alternate = "1";
                    String medPagAlt = "PSE";
                    do {
                        alternate = JOptionPane.showInputDialog("¿Desea pagar con " + medPagAlt + " con un medio de pago alternativo?"
                                + "\n1. Si"
                                + "\n2. No");
                        if (alternate.equals("2")) {
                            break;
                        }
                        String medioAltPSE = JOptionPane.showInputDialog("Elija el medio de pago alternativo"
                                + "\n1. Baloto"
                                + "\n2. Tarjeta Credito");
                        switch (medioAltPSE) {
                            case "1":
                                medPagAlt = "Baloto";
                                pago = new BalotoDecorator(pago);
                                ArrayList<String> baloto = new ArrayList<>();
                                String noConf = JOptionPane.showInputDialog("Ingrese el numero de confirmacion");
                                baloto.add(valor);
                                baloto.add(noCuentaP);
                                baloto.add(claveCuenta);
                                baloto.add(noCuentaD);
                                baloto.add(noConf);
                                System.out.println(baloto.size());
                                pago.asignar(baloto);
                                break;
                            case "2":
                                medPagAlt = "Tarjeta de Credito";
                                pago = new CreditoDecorator(pago);
                                ArrayList<String> credito = new ArrayList<>();
                                String noTarj = JOptionPane.showInputDialog("Ingrese el numero de la tarjeta");
                                String cvv = JOptionPane.showInputDialog("Ingrese el CVV");
                                credito.add(valor);
                                credito.add(noCuentaP);
                                credito.add(claveCuenta);
                                credito.add(noCuentaD);
                                credito.add(noTarj);
                                credito.add(cvv);

                                pago.asignar(credito);
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Ingrese una opcion valida");
                                break;
                        }
                        break;
                    } while (!alternate.equals("2"));
                    JOptionPane.showMessageDialog(null, "PAGO REALIZADO CON EXITO\n" + pago.pagar());
                }
            case 0:
                JOptionPane.showMessageDialog(null, "Hasta luego pasajero");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Ingrese una opción valida");
                break;

        }
    }

    private void menuAdministrador(int opcion, String usuario, String password) {
        switch (opcion) {
            case 1:
                String registro = "";
                usuario = JOptionPane.showInputDialog("Ingrese el correo del usuario");
                registro += usuario;
                password = JOptionPane.showInputDialog("Ingrese la contraseña del usuario");
                registro += "-" + password;
                String tipoU = JOptionPane.showInputDialog("Ingrese el número correspondiente al tipo de usuario\n"
                        + "1. Conductor\n"
                        + "2. Pasajero\n"
                        + "3. Administrador");
                if (tipoU.equals("1")) {
                    registro += "-Conductor";
                } else if (tipoU.equals("2")) {
                    registro += "-Pasajero";
                } else if (tipoU.equals("3")) {
                    registro += "-Administrador";
                }
                Proxy.getInstance().crearRegistro(registro);
                crearUsuario(tipoU, usuario, password);
                break;
            case 2:
                String correo = JOptionPane.showInputDialog("Ingrese el correo del usuario");
                Usuario user = (Usuario) u.mostrarUsuario(correo);
                if (user == null) {
                    JOptionPane.showMessageDialog(null, "No existe ese usuario");
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Se encontro el usuario\n"
                            + "Correo: " + user.getCorreo() + "\n"
                            + "Contraseña: " + user.getPassword() + "\n"
                            + "Tipo de Usuario: " + Proxy.getInstance().buscar(correo, user.getPassword()));
                }
                break;
            case 3:
                usuario = JOptionPane.showInputDialog("Ingrese el correo del usuario a modificar");
                Usuario user3 = (Usuario) u.mostrarUsuario(usuario);
                if (user3 == null) {
                    JOptionPane.showMessageDialog(null, "No existe ese usuario");
                    break;
                } else {
                    String nemail = JOptionPane.showInputDialog("Ingrese el nuevo correo \n"
                            + "Correo: " + user3.getCorreo() + "\n"
                            + "Contraseña: " + user3.getPassword() + "\n");
                    String npass = JOptionPane.showInputDialog("Ingrese la nueva contraseña \n"
                            + "Correo: " + nemail + "\n"
                            + "Contraseña: " + user3.getPassword() + "\n");
                    u.modificarUsuario(usuario, nemail, npass);
                    JOptionPane.showMessageDialog(null, "Usuario modificado");
                }
                break;
            case 4:
                usuario = JOptionPane.showInputDialog("Ingrese el correo del usuario a eliminar");
                u.eliminarUsuario(usuario);
            case 0:
                JOptionPane.showMessageDialog(null, "Hasta luego administrador");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Ingrese una opción valida");
                break;

        }
    }
}
