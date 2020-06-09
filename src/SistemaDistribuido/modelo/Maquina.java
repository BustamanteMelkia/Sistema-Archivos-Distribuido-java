package SistemaDistribuido.modelo;

import SistemaDistribuido.Controlador;
import java.rmi.server.UnicastRemoteObject;
import ClienteInterfaces.MaquinaInterface;
import ServidorInterface.ServidorInterface;
import SistemaDistribuido.modelo.Servidor.ServidorImplement;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Maquina extends UnicastRemoteObject implements MaquinaInterface{
    private String ip,nombre,respaldoEn,respaldoDe;
    private int puertoMonitoreo;
    private SistemaArchivos sistemaArchivo;
    private GestorProcesos gestorProcesos;
    private GestorServicios gestorServicios;
    private Controlador controlador;
    private ServidorInterface servidor;
    private ArrayList<MaquinaInterface> maquinasActivas;
    private ArrayList<String> maquinasCaidas;
    private int puertoComunicacionActual;
    
    public Maquina(Controlador controlador, ServidorInterface servidor,String nombre, String ip) throws RemoteException{
        super();
        System.out.println("<<<"+nombre+">>>");
        this.nombre  = nombre;
        this.ip = ip;
        this.puertoMonitoreo = 0;
        this.controlador = controlador;
        this.servidor = servidor;
        this.gestorServicios =  null;
        this.gestorProcesos = null;
        this.maquinasActivas =  new ArrayList<MaquinaInterface>(); 
        this.maquinasCaidas = new ArrayList<String>();
        this.puertoComunicacionActual = 0;
        
    }
    
    
    /**Método que permite al servidor mandar la lista de máquinas activas cada vez que hay un inicio de sesion**/
    @Override
    public void broadcast(ArrayList<MaquinaInterface> maquinasActivas) throws RemoteException {
        this.maquinasActivas = maquinasActivas;
        //for(MaquinaInterface maquina : maquinasActivas)
        //   System.out.println("\t\tbroadcast\t\t"+maquina.getNombre()+"\t"+maquina.getIp());
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
    public void setRespaldoEn(String nombre) throws RemoteException {
        this.respaldoEn = nombre;
    }

    @Override
    public void setRespaldoDe(String nombre) throws RemoteException {
        this.respaldoDe =  nombre;
    }

    /**Crea un proceso que monitorea a una maquina remota para determinar si aún está activa o no 
     * @param nombre: nombre de la máquina a monitorear
     * @param ip: dirección ip
     * @param puerto: puerto en la maquina remota donde se realizará el monitoreo
    */
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
    public int getPuertoMonitoreo() throws RemoteException {
        return puertoMonitoreo;
    }

    @Override
    public void setPuertoMonitoreo(int puertoMonitoreo) throws RemoteException {
        this.puertoMonitoreo = puertoMonitoreo;
    }
    
    @Override
    public void iniciarPuertoMonitoreo() {
        //System.out.println(this.puertoMonitoreo);
        try {
            this.gestorServicios = new GestorServicios(this.controlador,nombre,puertoMonitoreo);
        }catch (RemoteException ex) {
            Logger.getLogger(Maquina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**Método que reporta al servidor cuando la maquina que se está monitoreando ha fallado
     * Sí al reportar el fallo el servidor no responde entonces, el servidor ha caido y la máquina que detectó el fallo
     * toma el rol de servidor.
     */
    public void reportarFallo(String reporteDe,String maquinaCaida){
        try{
            servidor.reportarFallo(reporteDe, maquinaCaida);
        }catch(RemoteException ex){
            try {
                //System.out.println(ex.getMessage()+"Error ServidorCaido\t\t totalMaquinas  "+this.maquinasActivas.size());
                System.out.println("Servidor listo. \t\tEscuchando en: "+ip+"   puerto: "+5556);
                
                Registry registro = LocateRegistry.createRegistry(5556); /*Crear el registro en determinado puerto*/
                registro.bind("servidor", new ServidorImplement());/*Guardar el registro: llave-valor*/
              
                Registry registro1 = LocateRegistry.getRegistry("192.168.0.104",5556);
                ServidorInterface servidor = (ServidorInterface)(registro1.lookup("servidor"));
                servidor.reestablecerServidor(maquinasActivas, maquinaCaida,ip,maquinasCaidas,puertoComunicacionActual);
                
            } catch (java.rmi.AlreadyBoundException | RemoteException ex1) {
                System.out.println("Errorr catch 1   "+ ex1.getMessage());
            } catch (NotBoundException ex1) {
                System.out.println("Errorr catch 2");
            }
        }
    }

    @Override
    public void reasignarServidor(String ip) throws RemoteException {
        try {
            Registry registro = LocateRegistry.getRegistry(ip,5556);
            servidor = (ServidorInterface)(registro.lookup("servidor"));
            System.out.println("\n\n reconexion  "+servidor.servidorActivo());
        } catch (NotBoundException | AccessException ex) {
            System.out.println("Error  "+ex.getMessage());
        }
    }

    @Override
    public void setMaquinasCaidas(ArrayList<String> maquinasCaidas) throws RemoteException {
        this.maquinasCaidas = maquinasCaidas;
    }

    @Override
    public void setPuertoComActual(int puerto) throws RemoteException {
        this.puertoComunicacionActual = puerto;
    }
}
