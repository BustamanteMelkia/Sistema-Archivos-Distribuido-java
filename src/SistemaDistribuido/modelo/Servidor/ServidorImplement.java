package SistemaDistribuido.modelo.Servidor;

import ClienteInterfaces.InformacionMaquina;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import ServidorInterface.GestorInterface;
import java.util.ArrayList;

public class ServidorImplement extends UnicastRemoteObject implements GestorInterface{
    private ArrayList<Object> bitacora;

    public ServidorImplement() throws RemoteException {
        super();
        this.bitacora =  new ArrayList();
    }
    
    @Override
    public boolean iniciarSesion(InformacionMaquina nodo) throws RemoteException {
        if(!bitacora.contains(nodo)){
            this.bitacora.add(nodo);
            System.out.println(nodo.getNombre());
            nodo.asignarMaquina();
            return true;
        }else return false;
        
    }

    @Override
    public boolean servidorActivo() throws RemoteException {
        return true;
    }
    
}
