package SistemaDistribuido.modelo;

import ServidorInterface.ServidorInterface;
import SistemaDistribuido.Controlador;
import SistemaDistribuido.modelo.Servidor.ServidorImplement;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Logica {
    private Maquina maquina;
    private Controlador controlador;
    
    public Logica(Controlador controlador){
        this.controlador =  controlador;
    }
    
    public boolean iniciarSesion(String nombre,String ip){
        boolean registrado = false;
        servidorActivo(ip);
        try{
            Registry registro = LocateRegistry.getRegistry(ip,5556);
            ServidorInterface servidor = (ServidorInterface)(registro.lookup("servidor"));
            
            this.maquina= new Maquina(this.controlador,servidor,nombre, ip);
            
            if(servidor.iniciarSesion(maquina)){
                registrado =  true;
            }else registrado = false;
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return registrado;
    }
    public void servidorActivo(String ip){
        try{
            Registry registro = LocateRegistry.getRegistry(ip,5556);
            ServidorInterface servidor = (ServidorInterface)(registro.lookup("servidor"));
            boolean activo = servidor.servidorActivo();
            //System.out.println(activo);
            
        }catch(Exception ex){
            
            System.out.println("Servidor listo. \nEscuchando en: "+ip+"   puerto: "+5556);
                
            try {
                Registry registro = LocateRegistry.createRegistry(5556); /*Crear el registro en determinado puerto*/
                registro.bind("servidor", new ServidorImplement());/*Guardar el registro: llave-valor*/
                
            } catch (AlreadyBoundException | RemoteException ex1) {
                    System.out.println("Error   servidorActivo "+ex1.getMessage());
            }
        }
    }
    
    public String getNombre(){
        return maquina.getNombre();
    }
    public String getIp(){
        return maquina.getIp();
    }
    public Maquina getMaquina(){
        return this.maquina;
    }
}
