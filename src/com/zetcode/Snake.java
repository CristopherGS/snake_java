package com.zetcode;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Snake extends JFrame {

    public Snake() {
        
        snake();
    }
    
    private void snake() {
        
        add(new tablero());
               
        setResizable(false);
        pack();
        
        setTitle("GAME SNAKE");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            JFrame ex = new Snake();
            ex.setVisible(true);
        });
    }
}
