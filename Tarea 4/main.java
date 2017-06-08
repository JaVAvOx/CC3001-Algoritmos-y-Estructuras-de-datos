import java.util.Scanner;


//PRIMERO IMPLEMENTAMOS ARBOLES AVL

class NodoAVL{
    Comparable valor; //valor del nodo
    NodoAVL izq;
    NodoAVL der;
    int altura;

    //constructor
    public NodoAVL(Comparable valor)
    {
        this(valor , null, null);
    }

    public NodoAVL(Comparable valor, NodoAVL izq, NodoAVL der){
        this.valor = valor;
        this.izq = izq;
        this.der = der;
        altura = 0; //por defecto
    }

    //creamos una metodo que nos de como string el sub-arbol
    //a partir de un nodo (para despues utilizarla con el nodo 'raiz' del arbol
    public String astr (){
        String aux = "";
        aux+=this.valor+" ";
        if(this.izq == null){
            aux+=". ";
        }
        else {
            aux+=this.izq.astr();
        }
        if(this.der == null){
            aux+=". ";
        }
        else {
            aux+=this.der.astr();

        }
        return aux;
    }
}




class ArbolAVL {
    NodoAVL raiz;

    public void insertar(Comparable x) {
        raiz = insertar(x, raiz);
    }


    private NodoAVL insertar(Comparable x, NodoAVL q) {
        if (q == null)
            q = new NodoAVL(x, null, null);
        else if (x.compareTo(q.valor) < 0) {
            q.izq = insertar(x, q.izq);
            if (alturaH(q.izq) - alturaH(q.der) == 2)
                if (x.compareTo(q.izq.valor) < 0)
                    q = rotarconizq(q);
                else
                    q = doblarizq(q);
        } 
        else if (x.compareTo(q.valor) > 0) {
            q.der = insertar(x, q.der);
            if (alturaH(q.der) - alturaH(q.izq) == 2)
                if (x.compareTo(q.der.valor) > 0)
                    q = rotarconder(q);
                else
                    q = doblarder(q);
        } else
            ;
        q.altura = max(alturaH(q.izq), alturaH(q.der)) + 1;
        return q;
    }


    private static int max(int a, int b) {
        return a > b ? a : b;
    }


    //definimos metodos para poder realizar las rotaciones para el balanceo


    private static NodoAVL rotarconizq(NodoAVL aux2) {
        NodoAVL aux1 = aux2.izq;
        aux2.izq = aux1.der;
        aux1.der = aux2;
        aux2.altura = max(alturaH(aux2.izq), alturaH(aux2.der)) + 1;
        aux1.altura = max(alturaH(aux1.izq), aux2.altura) + 1;
        return aux1;
    }


    private static NodoAVL rotarconder(NodoAVL aux1) {
        NodoAVL aux2 = aux1.der;
        aux1.der = aux2.izq;
        aux2.izq = aux1;
        aux1.altura = max(alturaH(aux1.izq), alturaH(aux1.der)) + 1;
        aux2.altura = max(alturaH(aux2.der), aux1.altura) + 1;
        return aux2;
    }


    private static NodoAVL doblarizq(NodoAVL aux3) {
        aux3.izq = rotarconder(aux3.izq);
        return rotarconizq(aux3);
    }


    private static NodoAVL doblarder(NodoAVL aux1) {
        aux1.der = rotarconizq(aux1.der);
        return rotarconder(aux1);
    }

    //para actualizar altura
    private static int alturaH(NodoAVL q){
        return q == null ? -1 : q.altura;}

}





//AHORA IMPLEMENTAMOS ARBOLES 2-3

class ArbolDT {
    //puede tener 2 valores, 3 descendientes
    int valor1,valor2;
    ArbolDT izq,mid,der;
    
    
    ArbolDT(int val, ArbolDT izq, ArbolDT der)
    {
        this.izq = izq;
        this.der = der;
        this.mid = null;
        this.valor1 = val;
        this.valor2 = val;
        
    }

    //constructor (un valor)
    ArbolDT(int val)
    {this(val,null,null);}

    


    ArbolDT insertar(int val) {
        if(this.izq == null)
        {
            if(this.valor1==this.valor2) //tiene 1 valor
            {
                if(this.valor1<val)
                {
                    this.valor2=val;
                    return null;}
                else
                {
                    this.valor1=val;
                    return null;}
            }
            else //tiene 2 valores
            {
                if(this.valor2<val) //val es mayor a los valores
                {
                    //dos arboles aux (denuevo)
                    ArbolDT izq = new ArbolDT(this.valor1);
                    ArbolDT der = new ArbolDT(val);
                    this.izq = izq;
                    this.der = der;
                    this.valor1=this.valor2;
                    return new ArbolDT(this.valor2,izq,der); //nuevo arbol
                    }
                else if(this.valor1>val){ //val es menor a los valores
                    //creamos dos arboles auxiliares
                    ArbolDT izq=new ArbolDT(val);
                    ArbolDT der=new ArbolDT(this.valor2);
                    this.izq=izq;
                    this.der=der;
                    this.valor2=this.valor1;
                    return new ArbolDT(this.valor1,izq,der); //creamos nuevo arbol
                }

                else //val es valor intermedio
                {
                    //la misma estructura que antes
                    ArbolDT izq = new ArbolDT(this.valor1);
                    ArbolDT der  = new ArbolDT(this.valor2);
                    this.izq=izq;
                    this.der=der;
                    this.valor1=val;
                    this.valor2=this.valor1;
                    return new ArbolDT(val,izq,der);
                }
            }
        }

        else
        {
            if(this.valor1>val) 
            {
                ArbolDT desc = this.izq.insertar(val);
                if(desc!=null) //descenciente no es vacio
                {
                    if(this.valor1==this.valor2)
                    {
                        this.mid=desc.der;
                        this.izq=desc.izq;
                        this.valor1=desc.valor1;
                        return null;}
                    else
                    {
                        //seguimos la misma estructura de crear dos arboles auxiliares para operar
                        //y luego crear otro para retornar
                        ArbolDT izq = new ArbolDT(desc.valor1,desc.izq,desc.der);
                        ArbolDT der = new ArbolDT(this.valor2,this.mid,this.der);
                        this.izq = izq;
                        this.der = der;
                        this.mid = null;
                        this.valor2 = this.valor1;
                        return new ArbolDT(this.valor1,izq,der);}
                }
                else //descendiente es vacio
                {
                    return null;}
            }
            else if(this.valor2<val)
            {
                ArbolDT desc = this.der.insertar(val);
                if(desc!=null)
                {
                    if(this.valor1==this.valor2)
                    {
                        this.mid=desc.izq;
                        this.der=desc.der;
                        this.valor2=desc.valor1; 
                        
                        return null;
                    }
                    else
                    {
                        ArbolDT izq = new ArbolDT(this.valor1,this.izq,this.mid);
                        ArbolDT der = new ArbolDT(desc.valor1,desc.izq,desc.der);
                        this.izq = izq;
                        this.der = der;
                        this.mid = null;
                        this.valor1 = this.valor2; 
                        
                        return new ArbolDT(this.valor2,izq,der);
                    }
                }
                else
                {
                    return null;}
            }
            else
            {
                ArbolDT desc = this.mid.insertar(val);
                if(desc!=null)
                {
                    ArbolDT izq = new ArbolDT(this.valor1,this.izq,desc.izq); 
                    ArbolDT der = new ArbolDT(this.valor2,desc.der,this.der);
                    this.mid = null;
                    this.izq = izq;
                    this.der = der;
                    this.valor1 = desc.valor1;
                    this.valor2 = this.valor1;
                    
                    return new ArbolDT(desc.valor1,izq,der);
                }
                else
                {
                    return null;
                }

            }
        }
    }

    //convierte el arbol a string
    //(ej. a = arbol.astr; y luego System.out.println(a); imprime el arbol)
    String astr() {
        //debemos ir recorriendo el arbol
        String aux = ""; //crea un string aux
        if(this.valor1!=this.valor2) //2 valores distintos
        {
            aux+=this.valor1+","+this.valor2+" "; //agrega los 2 valores a aux
        }
        else //1 solo valor
            aux+=this.valor1+" "; //agrega el valor a aux
        if(this.izq!=null)
            aux+=this.izq.astr();
        else //llega al final
            aux+=". ";
        if(this.mid!=null)
            aux+=this.mid.astr();
        else if(this.valor1!=this.valor2)
            aux+=". ";
        if(this.der!=null)
            aux+=this.der.astr();
        else
            aux+=". ";

        return aux;
    }
    
}



public class main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()) {
            String sec = in.nextLine();

            String[] secuencia = sec.split(" ");
            int n = secuencia.length;
            String op = secuencia[0];

            if (op.equals("AVL")){
                //crear avl
                ArbolAVL arbol = new ArbolAVL();
                //insertamos los elementos
                for (int i = 1; i<n; ++i){
                    arbol.insertar(Integer.parseInt(secuencia[i]));
                }
                
                //outeamos el string
                String arbols;
                arbols = arbol.raiz.astr();
                System.out.println(arbols);

            }

            //es 2-3
            else{
                //crear 2-3
                ArbolDT arbol = new ArbolDT(Integer.parseInt(secuencia[1]));
                //insertamos los elementos
                for (int i = 2; i<n; ++i){
                    arbol.insertar(Integer.parseInt(secuencia[i]));
                }
                //outeamos el arbol
                String arbols;
                arbols = arbol.astr();
                System.out.println(arbols);
            }


        }

    }
}
