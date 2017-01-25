/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pegaruta;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.io.IOException;
import java.io.File; 
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.CopyOption;
import java.nio.file.StandardCopyOption;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
 

import javax.swing.JOptionPane;
/**
 *
 * @author mint
 */
public class Pegaruta {

    /**
     * @param args the command line arguments
            public void actionPerformed(ActionEvent arg0) {
     */
    public static void main(String[] args) {
       
        // Metemos "hola" en el Clipboard
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection texto = new StringSelection("hola");
       // cb.setContents(texto, texto);

        // Construimos una ventana con un boton para leer el Clipboard
        JFrame v = new JFrame("Pega la ruta del archivo fichero...");
          v.setLocationRelativeTo(null);//centrado en pantalla
 
        v.getContentPane().setLayout(new FlowLayout());
        JButton boton = new JButton("pulse para leer ruta");

        // Añadimos al boton la accion de leer el Clipboard y sacarlo por
        // la estandar out si es un String.
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Se obtiene el Clipboard y su contenido
                    Clipboard cb = Toolkit.getDefaultToolkit()
                            .getSystemClipboard();
                    Transferable t = cb.getContents(this);

                    // Construimos el DataFlavor correspondiente a String.
                    DataFlavor dataFlavorStringJava = new DataFlavor(
                            "application/x-java-serialized-object; class=java.lang.String");

                    // Si el dato se puede obtener como String, lo obtenemos y
                    // lo
                    // sacamos por la estándar out.
                    if (t.isDataFlavorSupported(dataFlavorStringJava)) {
                        String texto = (String) t
                                .getTransferData(dataFlavorStringJava);
                        System.out.println(texto);
                        
                        File FicheroOld=new File(texto);
                        
                        // copiamos el archivo a ruta temporal
                        // cambiando el nombre
                        boton.setText(texto);
                        System.out.println("nombre fichero: " + FicheroOld.getName());
                        
                        String separador = System.getProperty("file.separator");
                        
                        Path rutafin = Paths.get(separador + "tmp"+ separador + FicheroOld.getName());
                        
                           System.out.println("Ruta Fin: " + rutafin.toString());
                        
                        
                       Files.copy(Paths.get(FicheroOld.getPath()),rutafin);
                        
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    	
                    JOptionPane.showMessageDialog(null, "Problema al copiar fichero", "Problemas", JOptionPane.WARNING_MESSAGE);
                    boton.setText("pulse para leer ruta");
                }
            }

        });

        // Construcción y visualización del panel.
        v.getContentPane().add(boton);
        v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        v.pack();
        v.setVisible(true);
    }
    }
    

