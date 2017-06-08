import java.util.Arrays;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;

public class tiempo {


    public static int[] FZBRUTA(int[] arry){
        int maxSum = 0;
        int d=0, u=0;
        int n = arry.length;
        for(int i=0; i<n; i++)
        {
            int thisSum = 0;
            for (int j=i; j<=n-1; j++)
            {
                thisSum += arry[j];
                if (thisSum > maxSum){
                    maxSum = thisSum;
                    d=i;
                    u=j;}
                if (thisSum == maxSum){
                    if(j-i < u-d) {
                        d=i;
                        u=j;
                    }
                }
            }
        }
        int sec[] = new int[2];
        sec[0] = d;
        sec[1] = u;
        return sec;
    }

    public static int[] submax(int[] sec){
        int L = sec.length;
        if(L==0)
            return new int[] {0,0,0};
        if(L==1)
            return new int[] {0,0,sec[0]};
        if(L==2)
            if(sec[0]>0 && sec[1]>0)
                return new int[] {0,1,sec[0]+sec[1]};
            else if(sec[0]>=sec[1])
                return new int[] {0,0,sec[0]};
            else
                return new int[] {1,1,sec[1]};

        int[] izq = Arrays.copyOfRange(sec, 0, L/2); //dividimos la secuencia
        int[] der = Arrays.copyOfRange(sec, L/2, L);
        int[] maxizq = submax(izq); //aplicamos la recursion para cada mitad
        int[] maxder = submax(der);

        int[] secmax = new int[] {0,0,-Integer.MAX_VALUE};
        int[] thissec = new int[] {maxizq[0],maxizq[0],0};

        for(int i = maxizq[0];i<maxder[1]+L/2+1;++i)
        {   thissec[1]=i;
            thissec[2]+=sec[i];
            if(thissec[2]>secmax[2] || (thissec[2]==secmax[2] && thissec[1]-thissec[0]<secmax[1]-secmax[0]))
                secmax = new int[] {thissec[0],thissec[1],thissec[2]}; //actualizamos secmax

            if(thissec[2]<=0)
                thissec = new int[] {i+1,i,0}; //thissec la mandamos a 0
        }

        if(secmax[2]>maxizq[2] && secmax[2]> maxder[2])
            return secmax;

        else if(maxizq[2]>maxder[2])
            return maxizq;

        else if(maxder[2]>maxizq[2])
            return new int[] {maxder[0]+L/2,maxder[1]+L/2,maxder[2]};

        else if(maxder[2]==maxizq[2]) //debemos comparar el largo de las secuencias
        {   if(maxizq[1]-maxizq[0] > maxder[1]-maxder[0]) //secuencia derecha es mas corta
            return new int[] {maxder[0]+L/2,maxder[1]+L/2,maxder[2]};

        else //secuencia izq es mas corta
            return maxizq;
        }
        return maxizq;
    }

    public static int[] ef(int[] arry){
        int maxSum = 0, thisSum = 0;
        int n = arry.length;
        int j, d=0, u=0, dg = 0, ug = 0;
        for( j=0; j<n; j++)
        {
            u=j;
            thisSum += arry[j];
            if (thisSum > maxSum || thisSum==maxSum && u-d<ug-dg) {
                maxSum = thisSum;
                dg= d;
                ug= u;
            }

            else if (thisSum <= 0){
                thisSum = 0;
                d=j+1;
                u=j;
            }
        }
        int r[] = new int [2];
        r[0] = dg;
        r[1] = ug;
        return r;
    }





    public static long prom(long[] arr){
        long sum = 0;
        long media;
        for (int i = 0; i<10;++i){
            sum += arr[i];
        }
        media = sum/10;
        return media;
    }

    public static void main(String[] args) {
        Random rand = new Random();
        long[] tiempos1 = new long[10];
        long[] tiempos2 = new long[10];
        long[] tiempos3 = new long[10];

        for (int i = 10000;i<=100000; i+=10000) {
            int[] sec1 = new int[i];
            int[] sec2 = new int[i];
            int[] sec3 = new int[i];
            int[] sec4 = new int[i];
            int[] sec5 = new int[i];
            int[] sec6 = new int[i];
            int[] sec7 = new int[i];
            int[] sec8 = new int[i];
            int[] sec9 = new int[i];
            int[] sec10 = new int[i];

            for (int j = 0; j < i; ++j) {
                sec1[j] = rand.nextInt(300) - 150;
                sec2[j] = rand.nextInt(300) - 150;
                sec3[j] = rand.nextInt(300) - 150;
                sec4[j] = rand.nextInt(300) - 150;
                sec5[j] = rand.nextInt(300) - 150;
                sec6[j] = rand.nextInt(300) - 150;
                sec7[j] = rand.nextInt(300) - 150;
                sec8[j] = rand.nextInt(300) - 150;
                sec9[j] = rand.nextInt(300) - 150;
                sec10[j] = rand.nextInt(300) - 150;
            }
            //tenemos 10 secuencias de i elementos aleatorios creadas


            long[] tiempofza = new long[10];

            long start = System.currentTimeMillis();
            FZBRUTA(sec1);
            long end = System.currentTimeMillis();
            tiempofza[0]= end-start;

            start = System.currentTimeMillis();
            FZBRUTA(sec2);
            end = System.currentTimeMillis();
            tiempofza[1]= end-start;

            start = System.currentTimeMillis();
            FZBRUTA(sec3);
            end = System.currentTimeMillis();
            tiempofza[2]= end-start;

            start = System.currentTimeMillis();
            FZBRUTA(sec4);
            end = System.currentTimeMillis();
            tiempofza[3]= end-start;

            start = System.currentTimeMillis();
            FZBRUTA(sec5);
            end = System.currentTimeMillis();
            tiempofza[4]= end-start;

            start = System.currentTimeMillis();
            FZBRUTA(sec6);
            end = System.currentTimeMillis();
            tiempofza[5]= end-start;

            start = System.currentTimeMillis();
            FZBRUTA(sec7);
            end = System.currentTimeMillis();
            tiempofza[6]= end-start;

            start = System.currentTimeMillis();
            FZBRUTA(sec8);
            end = System.currentTimeMillis();
            tiempofza[7]= end-start;

            start = System.currentTimeMillis();
            FZBRUTA(sec9);
            end = System.currentTimeMillis();
            tiempofza[8]= end-start;

            start = System.currentTimeMillis();
            FZBRUTA(sec10);
            end = System.currentTimeMillis();
            tiempofza[9]= end-start;

            tiempos1[(i/10000)-1] = prom(tiempofza);


            long[] tiempodpr = new long[10];

            start = System.currentTimeMillis();
            submax(sec1);
            end = System.currentTimeMillis();
            tiempofza[0]= end-start;

            start = System.currentTimeMillis();
            submax(sec2);
            end = System.currentTimeMillis();
            tiempofza[1]= end-start;

            start = System.currentTimeMillis();
            submax(sec3);
            end = System.currentTimeMillis();
            tiempofza[2]= end-start;

            start = System.currentTimeMillis();
            submax(sec4);
            end = System.currentTimeMillis();
            tiempofza[3]= end-start;

            start = System.currentTimeMillis();
            submax(sec5);
            end = System.currentTimeMillis();
            tiempofza[4]= end-start;

            start = System.currentTimeMillis();
            submax(sec6);
            end = System.currentTimeMillis();
            tiempofza[5]= end-start;

            start = System.currentTimeMillis();
            submax(sec7);
            end = System.currentTimeMillis();
            tiempofza[6]= end-start;

            start = System.currentTimeMillis();
            submax(sec8);
            end = System.currentTimeMillis();
            tiempofza[7]= end-start;

            start = System.currentTimeMillis();
            submax(sec9);
            end = System.currentTimeMillis();
            tiempofza[8]= end-start;

            start = System.currentTimeMillis();
            submax(sec10);
            end = System.currentTimeMillis();
            tiempofza[9]= end-start;

            tiempos2[(i/10000)-1] = prom(tiempodpr);


            long[] tiempoef = new long[10];

            start = System.currentTimeMillis();
            ef(sec1);
            end = System.currentTimeMillis();
            tiempofza[0]= end-start;

            start = System.currentTimeMillis();
            ef(sec2);
            end = System.currentTimeMillis();
            tiempofza[1]= end-start;

            start = System.currentTimeMillis();
            ef(sec3);
            end = System.currentTimeMillis();
            tiempofza[2]= end-start;

            start = System.currentTimeMillis();
            ef(sec4);
            end = System.currentTimeMillis();
            tiempofza[3]= end-start;

            start = System.currentTimeMillis();
            ef(sec5);
            end = System.currentTimeMillis();
            tiempofza[4]= end-start;

            start = System.currentTimeMillis();
            ef(sec6);
            end = System.currentTimeMillis();
            tiempofza[5]= end-start;

            start = System.currentTimeMillis();
            ef(sec7);
            end = System.currentTimeMillis();
            tiempofza[6]= end-start;

            start = System.currentTimeMillis();
            ef(sec8);
            end = System.currentTimeMillis();
            tiempofza[7]= end-start;

            start = System.currentTimeMillis();
            ef(sec9);
            end = System.currentTimeMillis();
            tiempofza[8]= end-start;

            start = System.currentTimeMillis();
            ef(sec10);
            end = System.currentTimeMillis();
            tiempofza[9]= end-start;

            tiempos3[(i/10000)-1] = prom(tiempoef);

        }
        //ahora tenemos un vector con promedios de tiempo de 10 secuencias de distinto largo para cada uno de los algoritmos
        // (tres vectores en total)
        // tiempos1 = algoritmo1 [10000, 20000, 30000, ...]
        // tiempos2 = algoritmo1 [10000, 20000, 30000, ...]
        // tiempos3 = algoritmo1 [10000, 20000, 30000, ...]


        //debemos escribir el fichero




    }
}
