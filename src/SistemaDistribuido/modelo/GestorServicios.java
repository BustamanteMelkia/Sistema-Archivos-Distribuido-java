
package SistemaDistribuido.modelo;

import SistemaDistribuido.Controlador;
import SistemaDistribuido.modelo.servicios.ComunicacionImplement;
import SistemaDistribuido.modelo.servicios.CrudImplement;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class GestorServicios {
    public static int puertoComunicacion = 5555;
    public static int puertoServicioCRUD= 5556;
    private CrudImplement crud;
    private Controlador controlador;
    private String nombre="";
    
    public GestorServicios(Controlador controlador,String nombre) throws RemoteException{
        this.controlador =  controlador;
        this.crud = new CrudImplement();
        this.nombre =  nombre;
        this.registrarServicioComunicacion();
    }
    public void registrarServicioComunicacion(){
        try{
            System.out.println("Escuchando en: 192.168.0.104 puerto: "+GestorServicios.puertoComunicacion);
            
            Registry registro = LocateRegistry.createRegistry(GestorServicios.puertoComunicacion); /*Crear el registro en determinado puerto*/
            registro.bind("comunicacion."+this.nombre, new ComunicacionImplement());/*Guardar el registro: llave-valor*/     
        }catch(Exception e){
            System.out.println("Error GServicios "+e.getMessage());
        }
    }
}
