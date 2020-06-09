package SistemaDistribuido.modelo.procesos;

import ClienteInterfaces.ComunicacionInterface;
import SistemaDistribuido.Controlador;
import SistemaDistribuido.modelo.GestorProcesos;
import java.rmi.RemoteException;

public class ProcesoMonitoreo extends Thread{
    
    private ComunicacionInterface comunicacion;
    private Controlador controlador;
    private boolean funcionando;
    private String maquinaMonitoreada;
    private GestorProcesos gestorProcesos;
    
    public ProcesoMonitoreo(Controlador controlador,ComunicacionInterface comunicacion,GestorProcesos gProcesos,String maquinaMonitoreada){
        this.comunicacion =  comunicacion;
        this.controlador =  controlador;
        this.gestorProcesos = gProcesos;
        funcionando = true;
        this.maquinaMonitoreada = maquinaMonitoreada;
    }
    
    @Override
    public void run(){
        while (gestorProcesos.monitoreoEstaActivo() && funcionando){
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
            
            mandarEco();
        }
        System.out.println("hilo muerto");
    }
    
    public void mandarEco() {
        try{
            comunicacion.mandarEco();
        }catch(RemoteException e){
            funcionando = false;
            System.out.println("\t\tMáquina caida en_"+controlador.getNombre());
            this.controlador.reportarCaida(controlador.getNombre(),maquinaMonitoreada);
            
        }
      
    }
}
