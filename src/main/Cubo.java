package main;

import main.Subcubo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class Cubo extends JFrame {

    private Graficos graficos;
    private Subcubo[][][] cuboRubik;
    private double anguloX = 0, anguloY = 0, anguloZ = 0;

    public Cubo() {
        initComponents();
        setSubcube();
        moveCube();
    }

    private void setSubcube() {
        cuboRubik = new Subcubo[3][3][3];
        int size = 50;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    cuboRubik[x][y][z] = new Subcubo(x * size, y * size, z * size, size);
                }
            }
        }
    }

    private void moveCube() {
        graficos.clear();
        // Ajuste de traslación del cubo
        int trasX = 400; // Coordenada X del centro de la ventana
        int trasY = 300; // Coordenada Y del centro de la ventana
        int trasZ = -75; // Ajusta según sea necesario para la perspectiva

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    cuboRubik[x][y][z].dibujar(graficos, 1, anguloX, anguloY, anguloZ, trasX - 75, trasY - 75, trasZ);
                }
            }
        }
    }

    public static void main(String[] args) {
        new Cubo();
    }

    private void initComponents() {
        setTitle("Cubo de Rubik en 3D");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        graficos = new Graficos(800, 600);
        add(graficos);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        anguloX -= 5;
                        break;
                    case KeyEvent.VK_S:
                        anguloX += 5;
                        break;
                    case KeyEvent.VK_D:
                        anguloY -= 5;
                        break;
                    case KeyEvent.VK_A:
                        anguloY += 5;
                        break;
                    case KeyEvent.VK_J:
                        anguloZ -= 5;
                        break;
                    case KeyEvent.VK_L:
                        anguloZ += 5;
                        break;
                }
                moveCube();
            }
        });

        setVisible(true);
    }
}
