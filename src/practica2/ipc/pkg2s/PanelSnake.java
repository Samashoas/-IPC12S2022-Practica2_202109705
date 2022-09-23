package practica2.ipc.pkg2s;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import practica2.ipc.pkg2s.Caminante;
import javax.swing.JPanel;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import static practica2.ipc.pkg2s.Inicio.dificultad;
import static practica2.ipc.pkg2s.Inicio.intervalo;
import static practica2.ipc.pkg2s.PanelJuego.cant;
import static practica2.ipc.pkg2s.PanelJuego.tam;
import static practica2.ipc.pkg2s.PanelJuego.tamax;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jpsam
 */
public class PanelSnake extends JPanel implements ActionListener{
    Color colorSnake= Color.green;
    Color colorComida= Color.blue;
    
    static final int CuerpoT_Serpiente = 26;
    int[] serpienteX = new int[CuerpoT_Serpiente];
    int[] serpienteY = new int[CuerpoT_Serpiente];
    public static int cuerpo_snake = 1;
    public static int NomManzanas;
    
    int comidaX;
    int comidaY;
    
    public static char direccion = 'R';
    
    public static boolean estado = true;
    Thread hilo;
    Caminante camino;
    
    Random random = new Random();
    
    
    public PanelSnake(){
        this.setFocusable(true);
        iniciarJuego();
    }
    
    public void paint(Graphics pintor){
        super.paint(pintor);
        pintor.setColor(colorComida);
        pintor.fillRect(comidaX, comidaY, tam-1, tam-1);
        if(estado=true){
        for(int i= 0; i<cuerpo_snake; i++){
            if(i==0){
            pintor.setColor(colorSnake);
            pintor.fillRect(serpienteX[i], serpienteY[i], tam-1, tam-1);
            }else{
                pintor.setColor(new Color(45,180,0));
            pintor.fillRect(serpienteX[i], serpienteY[i], tam-1, tam-1);
            }
        }
        pintor.setColor(Color.red);
	pintor.setFont( new Font("Ink Free",Font.BOLD, 40));
	FontMetrics metrics = getFontMetrics(pintor.getFont());
	pintor.drawString("Score: "+NomManzanas, (416 - metrics.stringWidth("Score: "+NomManzanas))/2, pintor.getFont().getSize());
        }else{
        }
    }
    public void iniciarJuego(){
        generarComida();
        camino = new Caminante(this);
        hilo = new Thread(camino);
        hilo.start();
    }
    
    public void avanzar(){
        for(int i=cuerpo_snake; i>0; i--){
            serpienteX[i] = serpienteX[i-1];
            serpienteY[i] = serpienteY[i-1];
        }
        switch(direccion){
            case 'R':
                serpienteX[0]=serpienteX[0]+tam;
                break;
            case 'L':
                serpienteX[0]=serpienteX[0]-tam;
                break;
            case 'U':
                serpienteY[0]=serpienteY[0]+tam;
                break;
            case 'D':
                serpienteY[0]=serpienteY[0]-tam;
                break;
        }
    }

    public void Colisiones(){
        for(int i =cuerpo_snake; i>0; i--){
            if((serpienteX[0] == serpienteX[i])&&(serpienteY[0] == serpienteY[i])){
                estado = false;
            }
        }
        
        if(serpienteX[0]<0){
            estado = false;
        }
        
        if(serpienteX[0]>396){
            estado = false;
        }
        
        if(serpienteY[0]<0){
            estado = false;
        }
        
        if(serpienteY[0] > 396){
            estado = false;
        }
        
        
        if(!estado){
            hilo = new Thread(camino);
            hilo.stop();
            JOptionPane.showMessageDialog(null, "Game over");
            System.exit(0);
        }
    }
    
    public void ComerManzana(){
        if((serpienteX[0]==comidaX)&&(serpienteY[0]==comidaY)){
            cuerpo_snake++;
            NomManzanas++;
            generarComida();
            if(cuerpo_snake == 25){
                JOptionPane.showMessageDialog(null, "Victoria: "+cuerpo_snake++);
                reporte();
                System.exit(0);
            }
        }
    }
    
    public void gameOver(Graphics pintor){
        pintor.setColor(Color.red);
        pintor.setFont(new Font("Inl Free",Font.BOLD,75));
        FontMetrics metric = getFontMetrics(pintor.getFont());
        pintor.drawString("Game over", 413-metric.stringWidth("Game over"), 396-metric.stringWidth("Game over"));
    }
    
    public void reporte(){
        String nombre = "Juan Pablo Samayoa Ruiz-202109705";
        String Gamemode = dificultad;
        int tiempo =0;
        int inter = intervalo;
        int tam_Snake = cuerpo_snake-1;
        int movimientos = 0;

        File archivo = new File("reportes\\202109705.html");

        FileWriter escribir;

        PrintWriter nuevaLinea;

        if(!archivo.exists()){
            try {
                archivo.createNewFile();
                escribir = new FileWriter(archivo,true);
                nuevaLinea = new PrintWriter(escribir);
                nuevaLinea.println("<!DOCTYPE html>\n" +
                                    "<html lang=\"en\">\n" +
                                    "<head>\n" +
                                    "    <meta charset=\"UTF-8\">\n" +
                                    "    <title>Document</title>\n" +
                                    "</head>\n" +
                                    "<body>");

                nuevaLinea.println("<h1>REPORTE</h1>");
                nuevaLinea.println("<table>");

                nuevaLinea.println("<tr>");
                nuevaLinea.println("<th> Jugador </th>");
                nuevaLinea.println("<th> Dificultad </th>");
                nuevaLinea.println("<th> Tiempo transcurrido </th>");
                nuevaLinea.println("<th> Intervalo </th>");
                nuevaLinea.println("<th> Tamaño serpiente </th>");
                nuevaLinea.println("<th> Historial de moviemientos </th>");
                nuevaLinea.println("</tr>");

                nuevaLinea.println("<tr>");
                nuevaLinea.println("<td>"+nombre+"</td>");
                nuevaLinea.println("<td>"+Gamemode+"</td>");
                nuevaLinea.println("<td>"+tiempo+"</td>");
                nuevaLinea.println("<td>"+inter+"</td>");
                nuevaLinea.println("<td>"+tam_Snake+"</td>");
                nuevaLinea.println("<td>"+movimientos+"</td>");
                nuevaLinea.println("</tr>");

                nuevaLinea.println("</table>");

                nuevaLinea.println("</body>\n" +
                                   "</html>");
                escribir.close();


            } catch (Exception e) {

            }
        }else{
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("reportes\\202109705.html"));
                bw.write("");


                escribir = new FileWriter(archivo,true);
                nuevaLinea = new PrintWriter(escribir);
                nuevaLinea.println("<!DOCTYPE html>\n" +
                                    "<html lang=\"en\">\n" +
                                    "<head>\n" +
                                    "    <meta charset=\"UTF-8\">\n" +
                                    "    <title>Document</title>\n" +
                                    "</head>\n" +
                                    "<body>");

                nuevaLinea.println("<h1>REPORTE</h1>");
                nuevaLinea.println("<table>");

                nuevaLinea.println("<tr>");
                nuevaLinea.println("<th> Jugador </th>");
                nuevaLinea.println("<th> Dificultad </th>");
                nuevaLinea.println("<th> Tiempo transcurrido </th>");
                nuevaLinea.println("<th> Intervalo </th>");
                nuevaLinea.println("<th> Tamaño serpiente </th>");
                nuevaLinea.println("<th> Historial de moviemientos </th>");
                nuevaLinea.println("</tr>");


                nuevaLinea.println("<tr>");
                nuevaLinea.println("<td>"+nombre+"</td>");
                nuevaLinea.println("<td>"+Gamemode+"</td>");
                nuevaLinea.println("<td>"+tiempo+"</td>");
                nuevaLinea.println("<td>"+inter+"</td>");
                nuevaLinea.println("<td>"+tam_Snake+"</td>");
                nuevaLinea.println("<td>"+movimientos+"</td>");
                nuevaLinea.println("</tr>");


                nuevaLinea.println("</table>");

                nuevaLinea.println("</body>\n" +
                                   "</html>");
                escribir.close();
            } catch (Exception e) {
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        hilo.start();
        repaint();
    }
    
    
    
    public void generarComida(){
        comidaX = random.nextInt(cant)*tam;
        comidaY = random.nextInt(cant)*tam;
    }
    
}
