package SistemaDistribuido.modelo;

import SistemaDistribuido.Controlador;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Maquina {
    private String ip;
    private String nombre;
    private int identificador;
    private SistemaArchivos sistemaArchivo;
    private GestorProcesos gestorProcesos;
    private GestorServicios gestorServicios;
    private Controlador controlador;
    
    public Maquina(Controlador controlador,String nombre, String ip) throws RemoteException{
        this.nombre  = nombre;
        this.ip = ip;
        this.controlador = controlador;
        //this.gestorServicios = new GestorServicios(this.controlador,nombre);
        //this.gestorProcesos = new GestorProcesos(nombre,ip);
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }
    
}
