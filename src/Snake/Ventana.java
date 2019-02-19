/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Usuario
 */
public class Ventana extends JFrame { 
    
    public Ventana(){
    add(new Tablero());    
    setTitle("Snake");
    setResizable(false);
    pack();
    setSize(600,600);
    setLocationRelativeTo(null);
    setLayout(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
      
    }

    
}
