package SistemaDistribuido.modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author melquia
 */
public class SistemaArchivos {
    private String path;
    private String propietario;
    
    SistemaArchivos(){
        this.path = "";
        this.propietario = "";
    }
    
    SistemaArchivos(String path, String propiertario){
        this.path = path;
        this.propietario = propietario;
    }
    private void mostrar(String path,String padre){
        File dir = new File(path);
        for(String file : dir.list()){
            ///vista.pintarArbol(String [] archivos,padre);
            File temp = new File(path+"\\"+file);
            
            if(temp.isDirectory())
                mostrar(path+"\\"+file,file);
        }
    }
    
    public void listarDirectorio(String path, int nivel,String pathDestino) throws IOException{
        File dir = new File(path);
        for(String file : dir.list()){
            for(int i =0; i<nivel;i++)
                System.out.print("\t");
            System.out.println(file);
            
            File temp = new File(path+"\\"+file);
            
            if(temp.isDirectory()){
                crearDirectorio(pathDestino+"\\"+file);
                listarDirectorio(path+"\\"+file,nivel+1,pathDestino+"\\"+file);
            }
            //else
                //copiarArchivo(path,file,pathDestino);  
        }
    }
    
    public void copiarArchivo(String path,String nombreArchivo,String pathDestino) throws FileNotFoundException, IOException{
        FileInputStream origen = new FileInputStream(path+"\\"+nombreArchivo);
        FileOutputStream destino=new FileOutputStream(pathDestino+"\\"+nombreArchivo);
        
        byte[] buffer = new byte[819200];
        int len;
        while((len=origen.read(buffer))>0) {
            destino.write(buffer,0,len);
        }
        origen.close();
        destino.close();
    }
    
    public void crearDirectorio(String path){
      File file = new File(path);
      //Creating the directory
      boolean bool = file.mkdir();
      if(bool){
         System.out.println("Directory created successfully");
      }else{
         System.out.println("Sorry couldn’t create specified directory");
      }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }
    
    
}
