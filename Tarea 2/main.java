import java.io.IOException;
import java.util.Scanner;
import static java.lang.Math.*;

public class main {
    public static void main(String[] args) throws IOException {

        int m;
        int n;
        int grad;
        String kernel;
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {

        String linea = in.nextLine();
        String[] param = linea.split(" ");
        m = Integer.parseInt(param[0]);
        n = Integer.parseInt(param[1]);
        grad = Integer.parseInt(param[2]);
        kernel = param[3];
        int[][] R = new int[m][n];
        int[][] G = new int[m][n];
        int[][] B = new int[m][n];

        for (int j = 0; j < m; ++j) {
            linea = in.nextLine();
            param = linea.split(" ");
            for (int h = 0; h < n; ++h) {
                R[j][h] = Integer.parseInt(param[h]);}}

        for (int j = 0; j < m; ++j) {
            linea = in.nextLine();
            param = linea.split(" ");
            for (int h = 0; h < n; ++h) {
                G[j][h] = Integer.parseInt(param[h]);}}

        for (int j = 0; j < m; ++j) {
            linea = in.nextLine();
            param = linea.split(" ");
            for (int h = 0; h < n; ++h) {
                B[j][h] = Integer.parseInt(param[h]);}}

        long[][] A = new long[m][n];
        for (int i = 0; i < m; ++i) {
            for (int k = 0; k < n; ++k) {
                A[i][k] =  round(0.3 * R[i][k] + 0.59 * G[i][k] + 0.11 * B[i][k]);}}

        if (kernel.equals("robert")){
        int[][] M1 = new int[2][2];
        int[][] M2 = new int[2][2];
        M1[0][0] = 0;
        M1[1][0] = -1;
        M1[0][1] = 1;
        M1[1][1] = 0;

        M2[0][0] = 1;
        M2[1][0] = 0;
        M2[0][1] = 0;
        M2[1][1] = -1;
        long[][] C1 = new long[m-2+1][n-2+1];
        long[][] C2 = new long[m-2+1][n-2+1];
        int p = 2;
        int q = 2;

        for (int i = 0; i < m-p+1;++i){
            for (int j = 0; j < n-q+1;++j){
                C1[i][j] = A[i][j]*M1[0][0]+ A[i][j+1]*M1[0][1] + A[i+1][j]*M1[1][0] + A[i+1][j+1]*M1[1][1];
                C2[i][j] = A[i][j]*M2[0][0]+ A[i][j+1]*M2[0][1] + A[i+1][j]*M2[1][0] + A[i+1][j+1]*M2[1][1];}}
        //ya tenemos creadas C1 y C2
        double[][] Gr = new double[m-2+1][n-2+1];
        for (int i = 0; i < m-p+1;++i) {
            for (int j = 0; j < n - q + 1; ++j) {
                Gr[i][j] = Math.sqrt((C1[i][j]*C1[i][j] + C2[i][j]*C2[i][j]));}}
        float[][] borde = new float[m-2+1][n-2+1];
        for (int i = 0; i < m-p+1;++i) {
            for (int j = 0; j < n - q + 1; ++j) {
                if (Gr[i][j] < grad){
                    borde[i][j] = 0;}
                else { borde[i][j] = 1;}}}
        //RETORNAR AQUI
        for (int i = 0; i < m-p+1;++i) {
            for (int j = 0; j < n - q; ++j) {
                System.out.print(borde[i][j] + " ");}
            System.out.print(borde[i][n - q]);
            //hacemos un salto de linea
            System.out.println();        }}

        if (kernel.equals("prewitt")) {
            int[][] M1 = new int[3][3];
            int[][] M2 = new int[3][3];
            M1[0][0] = -1;
            M1[1][0] = -1;
            M1[0][1] = 0;
            M1[1][1] = 0;
            M1[0][2] = 1;
            M1[1][2] = 1;
            M1[2][0] = -1;
            M1[2][1] = 0;
            M1[2][2] = 1;

            M2[0][0] = -1;
            M2[1][0] = 0;
            M2[0][1] = -1;
            M2[1][1] = 0;
            M2[0][2] = -1;
            M2[1][2] = 0;
            M2[2][0] = 1;
            M2[2][1] = 1;
            M2[2][2] = 1;

            long[][] C1 = new long[m - 3 + 1][n - 3 + 1];
            long[][] C2 = new long[m - 3 + 1][n - 3 + 1];
            int p = 3;
            int q = 3;

            for (int i = 0; i < m - p + 1; ++i) {
                for (int j = 0; j < n - q + 1; ++j) {
                    C1[i][j] = A[i][j] * M1[0][0] + A[i][j + 1] * M1[0][1] + A[i + 1][j] * M1[1][0] + A[i + 1][j + 1] * M1[1][1] + A[i + 2][j] * M1[2][0] + A[i][j + 2] * M1[0][2] + A[i + 1][j + 2] * M1[1][2] + A[i + 2][j + 1] * M1[2][1] + A[i + 2][j + 2] * M1[2][2];
                    C2[i][j] = A[i][j] * M2[0][0] + A[i][j + 1] * M2[0][1] + A[i + 1][j] * M2[1][0] + A[i + 1][j + 1] * M2[1][1] + A[i + 2][j] * M2[2][0] + A[i][j + 2] * M2[0][2] + A[i + 1][j + 2] * M2[1][2] + A[i + 2][j + 1] * M2[2][1] + A[i + 2][j + 2] * M2[2][2];}}
            //ya tenemos creadas C1 y C2
            double[][] Gr = new double[m - 2 + 1][n - 2 + 1];
            for (int i = 0; i < m - p + 1; ++i) {
                for (int j = 0; j < n - q + 1; ++j) {
                    Gr[i][j] = Math.sqrt((C1[i][j] * C1[i][j] + C2[i][j] * C2[i][j]));}}
            float[][] borde = new float[m - 2 + 1][n - 2 + 1];
            for (int i = 0; i < m - p + 1; ++i) {
                for (int j = 0; j < n - q + 1; ++j) {
                    if (Gr[i][j] < grad) {
                        borde[i][j] = 0;
                    } else {
                        borde[i][j] = 1;}}}
            //RETORNAR AQUI
            for (int i = 0; i < m-p+1;++i) {
                for (int j = 0; j < n - q; ++j) {
                    System.out.print(borde[i][j] + " ");
                }
                System.out.print(borde[i][n - q]);
                //hacemos un salto de linea
                System.out.println();            }}

        if (kernel.equals("sobel")){
            int[][] M1 = new int[3][3];
            int[][] M2 = new int[3][3];
            M1[0][0] = -1;
            M1[1][0] = -2;
            M1[0][1] = 0;
            M1[1][1] = 0;
            M1[0][2] = 1;
            M1[1][2] = 2;
            M1[2][0] = -1;
            M1[2][1] = 0;
            M1[2][2] = 1;

            M2[0][0] = -1;
            M2[1][0] = 0;
            M2[0][1] = -2;
            M2[1][1] = 0;
            M2[0][2] = -1;
            M2[1][2] = 0;
            M2[2][0] = 1;
            M2[2][1] = 2;
            M2[2][2] = 1;

            long[][] C1 = new long[m-3+1][n-3+1];
            long[][] C2 = new long[m-3+1][n-3+1];
            int p = 3;
            int q = 3;

            for (int i = 0; i < m-p+1;++i){
                for (int j = 0; j < n-q+1;++j){
                    C1[i][j] = A[i][j]*M1[0][0]+ A[i][j+1]*M1[0][1] + A[i+1][j]*M1[1][0] + A[i+1][j+1]*M1[1][1] + A[i+2][j]*M1[2][0] + A[i][j+2]*M1[0][2] + A[i+1][j+2]*M1[1][2] + A[i+2][j+1]*M1[2][1] + A[i+2][j+2]*M1[2][2];
                    C2[i][j] = A[i][j]*M2[0][0]+ A[i][j+1]*M2[0][1] + A[i+1][j]*M2[1][0] + A[i+1][j+1]*M2[1][1] + A[i+2][j]*M2[2][0] + A[i][j+2]*M2[0][2] + A[i+1][j+2]*M2[1][2] + A[i+2][j+1]*M2[2][1] + A[i+2][j+2]*M2[2][2];}}
            //ya tenemos creadas C1 y C2
            double[][] Gr = new double[m-2+1][n-2+1];
            for (int i = 0; i < m-p+1;++i) {
                for (int j = 0; j < n - q + 1; ++j) {
                    Gr[i][j] = Math.sqrt((C1[i][j]*C1[i][j] + C2[i][j]*C2[i][j]));}}
            float[][] borde = new float[m-2+1][n-2+1];
            for (int i = 0; i < m-p+1;++i) {
                for (int j = 0; j < n - q + 1; ++j) {
                    if (Gr[i][j] < grad){
                        borde[i][j] = 0;
                    }
                    else { borde[i][j] = 1;}}}
            //RETORNAR AQUI AQUI
            for (int i = 0; i < m-p+1;++i) {
                for (int j = 0; j < n - q; ++j) {
                    System.out.print(borde[i][j] + " ");}
                System.out.print(borde[i][n - q]);
                //hacemos un salto de linea
                System.out.println();            }
        }
        }
    }
}