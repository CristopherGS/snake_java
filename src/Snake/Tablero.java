/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Usuario
 */
public class Tablero extends JPanel implements ActionListener {
     private final int B_WIDTH = 600;//tamaño del tablero
    private final int B_HEIGHT = 600;//tamaño del tablero
    private final int DOT_SIZE = 10;//tamaño de la manzana en pixeles
    private final int ALL_DOTS = 900;//define cuantas posiciones posibles
    private final int RAND_POS = 29;//calula la posicion de la manzana 
    private final int DELAY = 140;//velocidad del juego

    private final int x[] = new int[ALL_DOTS];//almazenan la posicion de la serpiente
    private final int y[] = new int[ALL_DOTS];
     
    private int puntuacion=0, aumento, comida_x, comida_y; 
    private boolean direccion_izquierda = false, direccion_derecha = true, direccion_arriba = false, direccion_abajo = false, inicioJuego = true;
    private Timer timer;
    private Image cabeza, comida, cuerpo;
  
    public Tablero() {
              addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        ImageIcon comida1 = new ImageIcon("2.png");
        comida = comida1.getImage();
        ImageIcon cuerpo1 = new ImageIcon("3.png");
        cuerpo = cuerpo1.getImage();
        ImageIcon derecha = new ImageIcon("derecha.png");
        cabeza = derecha.getImage();
        inicioJuego();
       // inicioTableto();
    }
    /*
    private void inicioTableto() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        inicioJuego();
    }
    */
/*
    private void cargaImagenes() {
     
        ImageIcon comida1 = new ImageIcon("2.png");
        comida = comida1.getImage();

        ImageIcon cuerpo1 = new ImageIcon("3.png");
        cuerpo = cuerpo1.getImage();
        
        ImageIcon derecha = new ImageIcon("derecha.png");
         cabeza = derecha.getImage();
    }
    */

    private void inicioJuego() {

        aumento = 3;

        for (int i = 0; i < aumento; i++) {
            x[i] = 50 - i * 10;
            y[i] = 50;
        }
        
        locateApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {
        
        if (inicioJuego) {

            g.drawImage(comida, comida_x, comida_y, this);

            for (int z = 0; z < aumento; z++) {
                if (z == 0) {
                    g.drawImage(cabeza, x[z], y[z], this);
                } else {
                    g.drawImage(cuerpo, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
            score(g);
           // timeGame(g);
        }        
    }

    private void gameOver(Graphics g) {
        
        String texto = "Fin del Juego";
        Font small = new Font("..", Font.BOLD, 20);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(texto, (600 - metr.stringWidth(texto)) / 2, 200 / 2);
    }
    private void score(Graphics g) {
        
        String msg = "Puntuacion: "+ puntuacion;
        Font small = new Font("..", Font.BOLD, 15);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.WHITE);
        g.setFont(small);
        g.drawString(msg, (600 - metr.stringWidth(msg)) / 2, 250 / 2);
    }
    /*
    
        private void timeGame(Graphics g) {
        
        String msg = "You time: "+ timer;
        Font small = new Font("..", Font.BOLD, 15);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.green);
        g.setFont(small);
        g.drawString(msg, (600 - metr.stringWidth(msg)) / 2, 300 / 2);
    }
*/

    private void checkApple() {

        if ((x[0] == comida_x) && (y[0] == comida_y)) {

            aumento++;
            puntuacion++;
            locateApple();
        }
    }

    private void move() {

        for (int z = aumento; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (direccion_izquierda) {
            x[0] -= DOT_SIZE;
            
        }

        if (direccion_derecha) {
            x[0] += DOT_SIZE;
        }

        if (direccion_arriba) {
            y[0] -= DOT_SIZE;
        }

        if (direccion_abajo) {
            y[0] += DOT_SIZE;
        }
    }

    private void choque() {

        for (int z = aumento; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inicioJuego = false;
            }
        }

        if (y[0] >= B_HEIGHT) {
            inicioJuego = false;
        }

        if (y[0] < 0) {
            inicioJuego = false;
        }

        if (x[0] >= B_WIDTH) {
            inicioJuego = false;
        }

        if (x[0] < 0) {
            inicioJuego = false;
        }
        
        if (!inicioJuego) {
            timer.stop();
        }
    }

    private void locateApple() {

        int r = (int) (Math.random() * RAND_POS);
        comida_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        comida_y = ((r * DOT_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inicioJuego) {

            checkApple();
            choque();
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!direccion_derecha)) {
                direccion_izquierda = true;
                direccion_arriba = false;
                direccion_abajo = false;
                ImageIcon arriba = new ImageIcon("izquierda.png");
                cabeza = arriba.getImage();
                
            }

            if ((key == KeyEvent.VK_RIGHT) && (!direccion_izquierda)) {
                direccion_derecha = true;
                direccion_arriba = false;
                direccion_abajo = false;
                ImageIcon derecha = new ImageIcon("derecha.png");
                cabeza = derecha.getImage();
            }

            if ((key == KeyEvent.VK_UP) && (!direccion_abajo)) {
                direccion_arriba = true;
                direccion_derecha = false;
                direccion_izquierda = false;
                ImageIcon arriba = new ImageIcon("arriba.png");
                cabeza = arriba.getImage();
                
            }

            if ((key == KeyEvent.VK_DOWN) && (!direccion_arriba)) {
                direccion_abajo = true;
                direccion_derecha = false;
                direccion_izquierda = false;
                ImageIcon abajo = new ImageIcon("abajo.png");
                cabeza = abajo.getImage();
            }
        }
    }
    
}
