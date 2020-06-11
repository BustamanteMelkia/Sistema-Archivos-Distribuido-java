package SistemaDistribuido.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class PanelSistemaArchivos extends JPanel implements ActionListener{
    private final int WIDTH=400;
    private final int HEIGHT = 280;
    private JTree arbol;
    private JScrollPane scrollPaneArbol;
    private String ruta ;
    private JLabel nombre;
    private JPopupMenu popup;
    private JMenuItem elemento;
    private VentanaPrincipal vP;
    
    public PanelSistemaArchivos(VentanaPrincipal vP,String ruta, String nombreMaquina){
        super();
        this.vP =  vP;
        this.ruta =  ruta;
        iniciarComponentes(nombreMaquina);
        iniciarEventos();
        
    }
    private void iniciarComponentes(String nombreMaquina){      
        this.setLayout(new BorderLayout());
        /**Label**/
        this.nombre = new JLabel(nombreMaquina);
        this.nombre.setForeground(new Color(47,58,86));
        this.nombre.setFont(new Font("Helvetica", Font.BOLD, 14));
        this.add(nombre,BorderLayout.NORTH);
        
        /**Árbol de directorios**/
        File raiz = new File(ruta);
        DefaultMutableTreeNode carpetaRaiz = new DefaultMutableTreeNode(raiz.getName()); 
        
        /*Definimos el modelo donde se agregaran los nodos*/
        DefaultTreeModel modelo = new DefaultTreeModel(carpetaRaiz);
        arbol = new JTree(modelo);/*agregamos el modelo al arbol, donde previamente establecimos la raiz*/
        crearArbol(modelo,carpetaRaiz,ruta);
        expandirPrimerNivel(arbol,0,2);
        
        scrollPaneArbol = new JScrollPane();
        scrollPaneArbol.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        scrollPaneArbol.setViewportView(arbol);
        this.add(scrollPaneArbol,BorderLayout.CENTER);
        
        popup = new JPopupMenu();
        
        JMenu menuTransferencia = new JMenu("Transferir a");
        
        elemento = new JMenuItem("Melkia");
        elemento.addActionListener(this);
        elemento.setActionCommand("melkia");
        menuTransferencia.add(elemento);
        
        elemento = new JMenuItem("David");
        elemento.addActionListener(this);
        elemento.setActionCommand("deivid");
        menuTransferencia.add(elemento);
        
        elemento = new JMenuItem("Lalo");
        elemento.addActionListener(this);
        elemento.setActionCommand("lalo");
        menuTransferencia.add(elemento);
        
        popup.add(menuTransferencia);
        
        elemento = new JMenuItem("Eliminar");
        elemento.addActionListener(this);
        elemento.setActionCommand("eliminar");
        popup.add(elemento);
        
        arbol.addMouseListener(new MouseAdapter(){
            public void mouseReleased(MouseEvent e){
                if(e.isPopupTrigger()){
                    popup.show((JComponent) e.getSource(), e.getX(), e.getY());
                }
            }
        });
    }
    
    private void expandirPrimerNivel(JTree arbol, int inicio, int nivel){
       for(int i=inicio;i<nivel;++i){
           arbol.expandRow(i);
       }
    }
    
    private void crearArbol(DefaultTreeModel arbol, DefaultMutableTreeNode padre, String ruta) {
	DefaultMutableTreeNode aux = null;
	File archivo = new File(ruta);
	File[] archivos = archivo.listFiles();

	if (archivos != null)
            for (int i = 0; i < archivos.length; i++) {
		aux = new DefaultMutableTreeNode(archivos[i].getName());
 		arbol.insertNodeInto(aux, padre, i);
                if (archivos[i].isDirectory()) 
                    crearArbol(arbol, aux, archivos[i].getAbsolutePath());
            }
    }
    private void iniciarEventos(){
        arbol.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                //JOptionPane.showMessageDialog(null, obtenerRuta(e.getPath()));
            }
        });
    }
    private String obtenerRuta(TreePath rutaSeleccionada){
        String ruta ="";
        for(Object nodo : rutaSeleccionada.getPath())
            ruta+=nodo.toString() + " / ";
        return ruta;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
