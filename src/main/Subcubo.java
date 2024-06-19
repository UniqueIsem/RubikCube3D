package main;

import java.awt.Color;
import java.util.Arrays;

public class Subcubo {

    public int x, y, z, size;
    private final double[][] vertices;
    private final int[][] aristas;
    private final Color[] colores;
    private final int[][] caras;

    public Subcubo(int x, int y, int z, int size) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.size = size;

        vertices = new double[][]{
            {-size / 2.0, -size / 2.0, -size / 2.0},
            {size / 2.0, -size / 2.0, -size / 2.0},
            {size / 2.0, size / 2.0, -size / 2.0},
            {-size / 2.0, size / 2.0, -size / 2.0},
            {-size / 2.0, -size / 2.0, size / 2.0},
            {size / 2.0, -size / 2.0, size / 2.0},
            {size / 2.0, size / 2.0, size / 2.0},
            {-size / 2.0, size / 2.0, size / 2.0}
        };

        aristas = new int[][]{
            {0, 1}, {1, 2}, {2, 3}, {3, 0},
            {4, 5}, {5, 6}, {6, 7}, {7, 4},
            {0, 4}, {1, 5}, {2, 6}, {3, 7}
        };

        caras = new int[][]{
            {0, 1, 2, 3}, // back
            {4, 5, 6, 7}, // front
            {0, 1, 5, 4}, // bottom
            {2, 3, 7, 6}, // top
            {0, 3, 7, 4}, // left
            {1, 2, 6, 5} // right
        };

        colores = new Color[]{Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.WHITE};
    }

    public void dibujar(Graficos g, double escala, double anguloX, double anguloY, double anguloZ, int trasX, int trasY, int trasZ, boolean lines) {
        double[][] rotadas = new double[8][3];
        for (int i = 0; i < 8; i++) {
            rotadas[i] = rotar(vertices[i], anguloX, anguloY, anguloZ);
        }

        // Aplicar traslación a los vértices rotados
        double[][] trasladadas = new double[8][3];
        for (int i = 0; i < 8; i++) {
            trasladadas[i][0] = rotadas[i][0] * escala + this.x + trasX;
            trasladadas[i][1] = rotadas[i][1] * escala + this.y + trasY;
            trasladadas[i][2] = rotadas[i][2] * escala + this.z + trasZ;
        }

        // Algoritmo del pintor
        double[] profundidades = new double[6];
        for (int i = 0; i < 6; i++) {
            profundidades[i] = (trasladadas[caras[i][0]][2] + trasladadas[caras[i][1]][2] + trasladadas[caras[i][2]][2] + trasladadas[caras[i][3]][2]) / 4.0;
        }

        Integer[] indices = {0, 1, 2, 3, 4, 5};
        Arrays.sort(indices, (a, b) -> Double.compare(profundidades[b], profundidades[a]));

        for (int i : indices) {
            int[] xPoints = new int[4];
            int[] yPoints = new int[4];
            for (int j = 0; j < 4; j++) {
                xPoints[j] = (int) trasladadas[caras[i][j]][0];
                yPoints[j] = (int) trasladadas[caras[i][j]][1];
            }
            g.fillPolygon(xPoints, yPoints, 4, colores[i]); // Pintar caraas
            if (lines) {
                for (int j = 0; j < 4; j++) {
                    int next = (j + 1) % 4;
                    g.drawLine(xPoints[j], yPoints[j], xPoints[next], yPoints[next], Color.BLACK);
                }
            }
        }
    }

    public double[] rotar(double[] punto, double anguloX, double anguloY, double anguloZ) {
        double[] resultado = Arrays.copyOf(punto, 3);
        double radX = Math.toRadians(anguloX);
        double radY = Math.toRadians(anguloY);
        double radZ = Math.toRadians(anguloZ);

        double temp = resultado[1] * Math.cos(radX) - resultado[2] * Math.sin(radX);
        resultado[2] = resultado[1] * Math.sin(radX) + resultado[2] * Math.cos(radX);
        resultado[1] = temp;

        temp = resultado[0] * Math.cos(radY) + resultado[2] * Math.sin(radY);
        resultado[2] = -resultado[0] * Math.sin(radY) + resultado[2] * Math.cos(radY);
        resultado[0] = temp;

        temp = resultado[0] * Math.cos(radZ) - resultado[1] * Math.sin(radZ);
        resultado[1] = resultado[0] * Math.sin(radZ) + resultado[1] * Math.cos(radZ);
        resultado[0] = temp;

        return resultado;
    }
}
