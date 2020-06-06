
package SistemaDistribuido.modelo;

import ClienteInterfaces.ComunicacionInterface;
import SistemaDistribuido.modelo.procesos.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class GestorProcesos {
    private ProcesoMonitoreo pMonitoreo;
    private ProcesoReplicador pReplicador;
    private ProcesoCoordinador pCoordinador;

    public GestorProcesos(String nombreMaq, String ip){
        try{
            Registry registro = LocateRegistry.getRegistry(ip,5555);
            ComunicacionInterface comunicacion = (ComunicacionInterface)(registro.lookup("comunicacion."+nombreMaq));
            this.pMonitoreo = new ProcesoMonitoreo(comunicacion);
            this.pMonitoreo.start();
        }catch(Exception e){
            System.out.println("GEstorPRocesos  "+e.getMessage());
        }

    }   

}
