package practica2.ipc.pkg2s;


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jpsam
 */
public class PanelJuego extends JPanel{
    Color colorFondo= Color.gray;
    static final int tamax = 400; 
    static final int cant = 10; 
    static final int tam = (int)tamax/cant;
    
    /*public PanelJuego(int tamax, int cant){
        this.tamax=tamax;
        this.cant=cant;
        this.tam = tamax/cant;
    }*/
    
    public void paint(Graphics pintor){
        super.paint(pintor);
        pintor.setColor(colorFondo);
        for(int i= 0; i<cant; i++){
            for(int j= 0; j<cant; j++){
                pintor.fillRect(i*tam, j*tam, tam-1, tam-1);
            }
        }
    }
    
}
