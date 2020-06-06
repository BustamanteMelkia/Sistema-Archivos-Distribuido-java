package SistemaDistribuido.modelo.procesos;
import ClienteInterfaces.ComunicacionInterface;


public class ProcesoMonitoreo extends Thread{
    private ComunicacionInterface comunicacion;
    private boolean funcionando = true;
    
    public ProcesoMonitoreo(ComunicacionInterface comunicacion){
        this.comunicacion =  comunicacion;
    }
    
    @Override
    public void run(){
        while (funcionando){
            
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
            mandarEco();
        }
    }
    
    public void mandarEco(){
        try{
            System.out.println(comunicacion.mandarEco());
        }catch(Exception e){
            funcionando = false;
            System.out.println("Máquina caida"+e.getMessage());
        }
    }
    
}
