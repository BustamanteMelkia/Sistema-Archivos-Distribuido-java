package SistemaDistribuido.modelo.servicios;

import java.rmi.server.UnicastRemoteObject;
import ClienteInterfaces.ComunicacionInterface;
import SistemaDistribuido.Controlador;
import SistemaDistribuido.modelo.GestorServicios;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ComunicacionImplement extends UnicastRemoteObject implements ComunicacionInterface{
    
    public ComunicacionImplement() throws RemoteException{
        super();
    }
    public void registrarServicio(){
        try{
            String dirIp=(InetAddress.getLocalHost()).toString();
            System.out.println("Escuchando en: "+ dirIp +" puerto: "+GestorServicios.puertoComunicacion);
            Registry registro = LocateRegistry.createRegistry(GestorServicios.puertoComunicacion); /*Crear el registro en determinado puerto*/
            registro.bind("comunicacion.melkia",(ComunicacionInterface) this);/*Guardar el registro: llave-valor*/
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public String mandarEco() throws RemoteException {
        System.out.println("Servicio corriendo");
        return "Sigo vivo";
    }
    
}
