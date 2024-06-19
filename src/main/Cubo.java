package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class Cubo extends JFrame {

    private Graficos graficos;
    private Subcubo[][][] cuboRubik;
    private double anguloX = 0, anguloY = 0, anguloZ = 0;
    private int trasX = 400, trasY = 300, trasZ = 0;
    private int size = 50;

    public Cubo() {
        initComponents();
        setSubcube();
        moverCubo();
    }

    private void setSubcube() {
        cuboRubik = new Subcubo[3][3][3];  // Cambiar a 3x3x3 para tener 27 subcubos

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    // Ajustar las posiciones para los 27 subcubos
                    int offsetX = -size;
                    int offsetY = -size;
                    int offsetZ = -size;

                    int posX = (x - 1) * size + offsetX;
                    int posY = (y - 1) * size + offsetY;
                    int posZ = (z - 1) * size + offsetZ;

                    cuboRubik[x][y][z] = new Subcubo(posX, posY, posZ, size);
                }
            }
        }
    }

    private void moverCubo() {
        graficos.clear();

        // Encontrar el centro del cubo
        int centroX = 1;
        int centroY = 1;
        int centroZ = -1; // Coordenadas del subcubo 14

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    // Calcular las nuevas coordenadas rotadas alrededor del subcubo 14
                    int newX = centroX + (x - centroX);
                    int newY = centroY + (y - centroY);
                    int newZ = centroZ + (z - centroZ);

                    double posX = (newX - 1) * size;
                    double posY = (newY - 1) * size;
                    double posZ = (newZ - 1) * size;

                    // Aplicar las rotaciones alrededor del subcubo 14
                    double[] rotatedPos = cuboRubik[x][y][z].rotar(new double[]{posX, posY, posZ}, anguloX, anguloY, anguloZ);

                    // Traslación con respecto al movimiento general del cubo
                    int finalX = (int) (rotatedPos[0] + trasX);
                    int finalY = (int) (rotatedPos[1] + trasY);
                    int finalZ = (int) (rotatedPos[2] + trasZ);

                    cuboRubik[x][y][z].dibujar(graficos, 1, anguloX, anguloY, anguloZ, finalX, finalY, finalZ);
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
                    case KeyEvent.VK_A:
                        trasX -= 5;
                        break;
                    case KeyEvent.VK_D:
                        trasX += 5;
                        break;
                    case KeyEvent.VK_W:
                        trasY -= 5;
                        break;
                    case KeyEvent.VK_S:
                        trasY += 5;
                        break;
                    case KeyEvent.VK_I:
                        anguloX -= 5;
                        break;
                    case KeyEvent.VK_K:
                        anguloX += 5;
                        break;
                    case KeyEvent.VK_L:
                        anguloY -= 5;
                        break;
                    case KeyEvent.VK_J:
                        anguloY += 5;
                        break;
                    case KeyEvent.VK_U:
                        anguloZ -= 5;
                        break;
                    case KeyEvent.VK_O:
                        anguloZ += 5;
                        break;
                    case KeyEvent.VK_Z:
                        size -= 5;
                        break;
                    case KeyEvent.VK_X:
                        size += 5;
                        break;
                }
                moverCubo();
            }
        });

        setVisible(true);
    }
}
