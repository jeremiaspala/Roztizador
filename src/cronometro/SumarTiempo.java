/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cronometro;

import java.util.TimerTask;

/**
 *
 * @author jpalazzesi
 */
public class SumarTiempo extends TimerTask{
    public String tiempo;
    public int horafinal;
    public int minutofinal;
    public int segundofinal;
    
    public SumarTiempo(int hora, int minutos, int segundos){
        segundos++;
        if (segundos >= 60){ 
            segundos-= 60; 
            minutos++; 
        } 

        if (minutos >= 60){ 
            minutos-= 60; 
            hora++; 
        } 
        this.tiempo = hora + ":" + minutos + ":" + segundos; 
   } 
   public String getTiempo(){
       return tiempo;
   }

    @Override
    public void run() {
        System.out.println(new SumarTiempo(26, 11, 59));
        try {
            wait(1000);
        } catch(Exception e){}
    }
}
