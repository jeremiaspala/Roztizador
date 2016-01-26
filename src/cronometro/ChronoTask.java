/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cronometro;

import com.sun.javafx.util.TempState;
import static cronometro.IDE.lblDisplay;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.TimerTask;
import javax.swing.JLabel;


public class ChronoTask extends TimerTask { 
 
    private static final long ONE_SECOND_MILLIS = 1000; 
    public JLabel etiqueta;
    public int ya;
    public String tiempoanterior;
    private SimpleDateFormat chronoFormat; 
    private long chronoTime; 
    private long anterior; 
    private int h,m,s,hh,mm,t;
    
    public ChronoTask(JLabel eti, int yaa, String tiempoant) { 
        this.tiempoanterior = tiempoant;
        this.etiqueta = eti;
        this.ya = yaa;
        chronoFormat = new SimpleDateFormat("HH:mm:ss");
        chronoFormat.setTimeZone(TimeZone.getTimeZone("UTC")); 
        String temp[] = tiempoant.split(":");
        this.h = Integer.parseInt(temp[0]);
        this.m = Integer.parseInt(temp[1]);
        this.s = Integer.parseInt(temp[2]);
        hh= h*3600;  
        mm= m*60;  
        this.chronoTime=(hh+mm+s)*1000;
    } 
 
    @Override 
    public void run() { 
        etiqueta.setText(chronoFormat.format(chronoTime).toString());
        System.out.println(chronoFormat.format(chronoTime)); 
        chronoTime += ONE_SECOND_MILLIS; 
        Conector con = new Conector();
        con.connect();
        con.setTiempo(ya, lblDisplay.getText().toString());
        con.getJuegos(IDE.jtblJuegos);
        con.close();
    } 
 
} 