package SistemaDistribuido.modelo.Servidor;

import ClienteInterfaces.MaquinaInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import ServidorInterface.ServidorInterface;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorImplement extends UnicastRemoteObject implements ServidorInterface{
    private ArrayList<MaquinaInterface> bitacora;
    private ArrayList<String> maquinasCaidas;
    private int identificador;
    private int puertoComunicacion;
    
    public ServidorImplement() throws RemoteException {
        super();
        this.identificador = 0;
        this.puertoComunicacion = 5678;
        this.bitacora =  new ArrayList<MaquinaInterface>();
        this.maquinasCaidas = new ArrayList<String>();
    }
    
    private void asignarMonitoreo() throws RemoteException{
        MaquinaInterface respaldoEn;
        /**realizar el monitoreo siempre que el numero de máquinas registradas sea mayor a uno**/
        if(bitacora.size()>1){
            for (int i = 0 ; i<bitacora.size();i++ ){
                MaquinaInterface aux = bitacora.get(i);
                if(i==bitacora.size()-1)
                    /**Ultima máquina de la lista, por lo tanto su respaldo será en la maquina 1**/
                    respaldoEn = bitacora.get(0);
                else
                    /**No es la última máquina de la lista, por lo tanto su respaldo será en la siguiente maquina**/
                    respaldoEn = bitacora.get(i+1);
                
                /*asignar el nombre de la maquina donde estará el respaldo de aux*/
                aux.setRespaldoEn(respaldoEn.getNombre());
                /*Asignar en la maquina de respaldo el nombre de la maquina que estamos respaldando*/
                respaldoEn.setRespaldoDe(aux.getNombre());
                /**iniciar monitoreo**/
                aux.asignarMaquina(respaldoEn.getNombre(), respaldoEn.getIp(), respaldoEn.getPuertoComunicacion());
            }
        }
    }
   
    @Override
    public boolean iniciarSesion(MaquinaInterface nodo) throws RemoteException {
        boolean reestablecer = maquinasCaidas.contains(nodo.getNombre());
        if(!reestablecer)
            for(MaquinaInterface maquina : bitacora)
                if(maquina.getNombre().equals(nodo.getNombre()))
                   return false;
        
        if(reestablecer)
            maquinasCaidas.remove(nodo.getNombre());
        
        nodo.setId(++identificador);
        nodo.setPuertoComunicacion(puertoComunicacion++);
        nodo.iniciarComunicacion();
        this.bitacora.add(nodo);
        asignarMonitoreo();
        return true;
    }

    @Override
    public boolean servidorActivo() throws RemoteException {
        return true;
    }

    @Override
    public synchronized void reportarFallo(String reporteDe, String maquinaCaida) throws RemoteException {
       
        eliminarMaquinaCaida(maquinaCaida);
        this.maquinasCaidas.add(maquinaCaida);
        
        if((bitacora.size())>=2){
            
            String respaldoMaquinaCaida = this.getRespaldoDeMaquinaCaida(maquinaCaida);
            MaquinaInterface nuevaMaquina = getMaquina(respaldoMaquinaCaida);
            
            MaquinaInterface maquina = getMaquina(reporteDe);
            
            maquina.setRespaldoEn(nuevaMaquina.getNombre());
            maquina.asignarMaquina(nuevaMaquina.getNombre(),nuevaMaquina.getIp(), nuevaMaquina.getPuertoComunicacion());
            nuevaMaquina.setRespaldoDe(maquina.getNombre());
            
            System.out.println("\t\tmaquina: "+reporteDe+"     monitorea a:  "+nuevaMaquina.getNombre());
        }
    }
    
    private String getRespaldoDeMaquinaCaida(String nombreMaquina) throws RemoteException{
        for(MaquinaInterface maquina: bitacora)
            if( maquina.getRespaldoDe().equals(nombreMaquina))
                return maquina.getNombre();
        return null;
    } 
    
    private MaquinaInterface getMaquina(String nombre) throws RemoteException{
        for(MaquinaInterface maquina: bitacora)
            if( maquina.getNombre().equals(nombre))
                return maquina;
        return null;
    }
    
    /**Eliminar del arrayList la maquina que ha fallado
     * @param nombreMaquina : maquina que ha fallado 
     *      busca la 'posicion' de nombreMaquina y el servidor al no poder acceder a nombreMaquina,
     *      lanza la excepción y elimina el elemento del indice actual
     * @return void**/
    
    private void eliminarMaquinaCaida(String nombreMaquina){
        for(int i=0; i<bitacora.size();i++)
            try {
                if( bitacora.get(i).getNombre().equals(nombreMaquina))
                    System.out.println("Enconrado");
            } catch (RemoteException ex) {
                bitacora.remove(i);
                System.out.println("\tEliminado   "+i);
                return;
            }
    }
}
