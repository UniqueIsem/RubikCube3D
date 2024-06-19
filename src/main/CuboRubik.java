package main;

public class CuboRubik {

    private int dimension;
    private Subcubo[][][] subcubos;

    public CuboRubik(int dimension, int size) {
        this.dimension = dimension;
        subcubos = new Subcubo[dimension][dimension][dimension];
        int offset = (dimension - 1) * size / 2;
        for (int x = 0; x < dimension; x++) {
            for (int y = 0; y < dimension; y++) {
                for (int z = 0; z < dimension; z++) {
                    subcubos[x][y][z] = new Subcubo(x * size - offset, y * size - offset, z * size - offset, size);
                }
            }
        }
    }

    public void dibujar(Graficos graficos, double factorEscala, double anguloX, double anguloY, double anguloZ, int traslacionX, int traslacionY, int traslacionZ) {
        for (int x = 0; x < dimension; x++) {
            for (int y = 0; y < dimension; y++) {
                for (int z = 0; z < dimension; z++) {
                    subcubos[x][y][z].dibujar(graficos, factorEscala, anguloX, anguloY, anguloZ, traslacionX, traslacionY, traslacionZ);
                }
            }
        }
    }

    public void rotarCaraFrontal(double angulo) {
        // Implementar la rotación de la cara frontal del cubo Rubik
        // Aquí se puede agregar la lógica para rotar los subcubos de la cara frontal
        // Actualizar las posiciones de los subcubos en la cara frontal según sea necesario
    }
}
