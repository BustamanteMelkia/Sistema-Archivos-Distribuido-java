
package SistemaDistribuido.modelo;

import SistemaDistribuido.Controlador;
import SistemaDistribuido.modelo.servicios.ComunicacionImplement;
import SistemaDistribuido.modelo.servicios.CrudImplement;
import java.rmi.RemoteException;

public class GestorServicios {
    public static int puertoComunicacion = 5555;
    public static int puertoServicioCRUD= 5556;
    private CrudImplement crud;
    private ComunicacionImplement comunicacion;
    private Controlador controlador;
    
    public GestorServicios(Controlador controlador){
        this.controlador =  controlador;
        this.crud = new CrudImplement();
        
        try{
            this.comunicacion = new ComunicacionImplement();
            this.comunicacion.registrarServicio();            
        }catch(Exception e){
            System.out.println("Error GServicios "+e.getMessage());
        }
    }

}
