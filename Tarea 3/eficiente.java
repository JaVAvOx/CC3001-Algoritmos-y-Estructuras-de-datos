import java.util.Scanner;
public class eficiente {
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

    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()) {
            String sec = in.nextLine();

            String[] secuencia = sec.split(" ");
            int n = secuencia.length;
            int arr[] = new int[n];
            for (int i=0; i<n; ++i){
                arr[i] = Integer.parseInt(secuencia[i]);
            }


            System.out.println(ef(arr)[0] + "," + ef(arr)[1]);


        }

    }
}
