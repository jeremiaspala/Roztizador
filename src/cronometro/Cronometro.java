/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cronometro;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author jpalazzesi
 */
public class Cronometro {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        IDE id = new IDE();
        id.setTitle("Rostizando...");
        id.setLocationRelativeTo(null);
        id.setVisible(true);
    }
    
}
