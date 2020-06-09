package SistemaDistribuido.modelo;

import SistemaDistribuido.Controlador;
import java.rmi.server.UnicastRemoteObject;
import ClienteInterfaces.MaquinaInterface;
import ServidorInterface.ServidorInterface;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Maquina extends UnicastRemoteObject implements MaquinaInterface{
    private String ip;
    private String nombre;
    private String respaldoEn;
    private String respaldoDe;
    private int id;
    private int puertoComunicacion;
    private SistemaArchivos sistemaArchivo;
    private GestorProcesos gestorProcesos;
    private GestorServicios gestorServicios;
    private Controlador controlador;
    private ServidorInterface servidor;
    
    public Maquina(Controlador controlador, ServidorInterface servidor,String nombre, String ip) throws RemoteException{
        super();
        this.nombre  = nombre;
        this.ip = ip;
        this.puertoComunicacion = 0;
        this.controlador = controlador;
        this.servidor = servidor;
        this.gestorServicios =  null;
        this.gestorProcesos = null;
        
    }
    
    @Override
    public void broadcast(String mensaje) throws RemoteException {
        
    }
    
    @Override
    public String getNombre() {
        return nombre;
    }

    public String getIp() {
        return ip;
    }

    @Override
    public String getRespaldoEn() {
        return respaldoEn;
    }

    @Override
    public String getRespaldoDe() {
        return respaldoDe;
    }

    @Override
    public int getId() {
        return id;
    }
    
    @Override
    public void setId(int id) {
        this.id = id;
    }  

    @Override
    public void setRespaldoEn(String nombre) throws RemoteException {
        this.respaldoEn = nombre;
    }

    @Override
    public void setRespaldoDe(String nombre) throws RemoteException {
        this.respaldoDe =  nombre;
    }

    @Override
    public void asignarMaquina(String nombre, String ip, int puerto) throws RemoteException {
        if(gestorProcesos != null)
            if(gestorProcesos.getProcesoMonitoreo() != null){
                gestorProcesos.setMonitoreo(false);
        }
        this.gestorProcesos = new GestorProcesos(this.controlador,nombre,ip,puerto);
        System.out.println("monitoreando a : "+nombre);
    }

    @Override
    public int getPuertoComunicacion() throws RemoteException {
        return puertoComunicacion;
    }

    @Override
    public void setPuertoComunicacion(int puerto) throws RemoteException {
        this.puertoComunicacion = puerto;
    }
    
    @Override
    public void iniciarComunicacion() {
        System.out.println(this.puertoComunicacion);
        try {
            this.gestorServicios = new GestorServicios(this.controlador,nombre,puertoComunicacion);
        } catch (RemoteException ex) {
            Logger.getLogger(Maquina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void reportarFallo(String reporteDe,String maquinaCaida){
        try{
            servidor.reportarFallo(reporteDe, maquinaCaida);
        }catch(RemoteException ex){
            System.out.println(ex.getMessage());
        }
    }
}
