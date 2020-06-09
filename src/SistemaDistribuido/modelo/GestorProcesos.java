
package SistemaDistribuido.modelo;

import SistemaDistribuido.modelo.procesos.ProcesoMonitoreo;
import ClienteInterfaces.ComunicacionInterface;
import SistemaDistribuido.Controlador;
import SistemaDistribuido.modelo.procesos.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class GestorProcesos {
    private ProcesoMonitoreo pMonitoreo=null;
    private ProcesoReplicador pReplicador;
    private ProcesoCoordinador pCoordinador;
    private Controlador controlador;
    private boolean monitoreoActivo;

    public GestorProcesos(Controlador controlador,String nombreMaq, String ip, int puerto){
        try{
            monitoreoActivo = true;
            this.controlador =  controlador;
            Registry registro = LocateRegistry.getRegistry(ip,puerto);
            ComunicacionInterface comunicacion = (ComunicacionInterface)(registro.lookup("comunicacion."+nombreMaq));

            this.pMonitoreo = new ProcesoMonitoreo(this.controlador,comunicacion,this,nombreMaq);
            this.pMonitoreo.start();
        }catch(Exception e){
            System.out.println("GestorPRocesos  "+e.getMessage());
        }
    }
    public ProcesoMonitoreo getProcesoMonitoreo(){
        return this.pMonitoreo;
    }
    public void setMonitoreo(boolean band){
        this.monitoreoActivo =  band;
    }
    public boolean monitoreoEstaActivo(){
        return this.monitoreoActivo;
    }

}
