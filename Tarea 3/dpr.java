import java.util.Arrays;
import java.util.Scanner;
public class dpr {

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



            int[] prueba;
            //prueba = maxsum(arr,0,n-1);
            prueba = submax(arr);


            System.out.println(prueba[0]+","+prueba[1]);


        }

    }
}
