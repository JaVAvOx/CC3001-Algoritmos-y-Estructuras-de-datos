import java.util.Scanner;
public class fzabruta {
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

            System.out.println(FZBRUTA(arr)[0] + "," + FZBRUTA(arr)[1]);


        }

    }
}
