/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaDistribuido.modelo;

import ClienteInterfaces.ComunicacionInterface;
import ServidorInterface.GestorInterface;
import SistemaDistribuido.Controlador;
import SistemaDistribuido.modelo.Servidor.ServidorImplement;
import SistemaDistribuido.modelo.procesos.ProcesoMonitoreo;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            GestorInterface servidor = (GestorInterface)(registro.lookup("servidor"));
            
            InfoMaquinaImplement nuevaMaquina = new InfoMaquinaImplement(this.controlador,nombre, ip);
            
            
            if(servidor.iniciarSesion(nuevaMaquina)){
                this.maquina = new Maquina(this.controlador,nombre,ip);
                registrado =  true;
            }else registrado = false;
            
        }catch(Exception e){
            System.out.println("Inicio de sesion  "+e.getMessage());
        }
        return registrado;
    }
    public void servidorActivo(String ip){
        try{
            
            Registry registro = LocateRegistry.getRegistry(ip,5556);
            GestorInterface servidor = (GestorInterface)(registro.lookup("servidor"));
            boolean activo = servidor.servidorActivo();
            System.out.println(activo);
            
        }catch(Exception ex){
            
            System.out.println("Servidor no disponible");
            System.out.println("Escuchando en: 192.168.0.104 puerto: "+5556);
                
            try {
                Registry registro = LocateRegistry.createRegistry(5556); /*Crear el registro en determinado puerto*/
                registro.bind("servidor", new ServidorImplement());/*Guardar el registro: llave-valor*/
                
            } catch (AlreadyBoundException | RemoteException ex1) {
                    Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }
    }
    
    public String getNombre(){
        return maquina.getNombre();
    }
    public String getIp(){
        return maquina.getIp();
    }
}
