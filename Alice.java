package org.example;
import javax.crypto.KeyGenerator;
import java.util.Random;
import java.util.Scanner;
import static org.example.PrimitiveRoot.findPrimitive;

public class Alice {

    int Q,a;
    int Ya; // Alice Public key
    int Yb; //Bob Public key
    int Xa; // Alice Private key
    int Kab; // Shared key

    // Alice recieves Bobs public key
    public void setBobPK(int Yb)
    {
        this.Yb = Yb;
    }

    public static boolean isPrime(long num){
        long temp;
        boolean isPrime=true;

        if (num <2)

        {
            isPrime=false;

        }
        for(int i=2;i<=num/2;i++)
        {
            temp=num%i;
            if(temp==0)
            {
                isPrime=false;
                break;
            }
        }
        return isPrime;
    }





    //Generate Q and A
    public int [] gernateQnadA()
    {
        // create empty int array of size 2
          int[] arr = new int[2];

        // generate a random number less than 100
         Q =(int) (100+Math.random());


        // use isPrime method+ while loop,
        //if random number is not prime generate another random until you find a prime
        while(! isPrime(Q))
            Q= (int)(100*Math.random());


        System.out.println("Q= " +Q);

        a=findPrimitive(Q);
        System.out.println("a="+ a);

        // store q and a in array
        arr[0] = Q;
        arr[1] = a;

        // return array
        return arr;

    }




    public void generateKEys()
    {
        // genrate the private key randomly
        Xa  = (int) ((5 + Math.random() )* 2);

        // gernate the publick key
        Ya= ((a^Xa) % Q);


        System.out.println("Alice Xa= "+ Xa+ " Ya= "+ Ya);
    }



    public void generateSharedKey()
    {
        // genrate the shared key with Bob
        //q
        Kab = ((Yb^Xa)% Q);


        System.out.println("Alice Kab="+ Kab);

        if (Kab >25)
        {
            Kab= Kab%26;
            System.out.println("new Kab= "+ Kab);

        }
    }
    //return Alice public key
    public int getPublicKey()
    {
        return Ya;
    }

    /* Recive a message and decrypt it using  ceaser Cipher with the
     shared key gernated from Deffie helmen*/
    public String reciveMessage(String Ciphertext)
    {
        String OriginalText = "";
        for(int i=0; i<Ciphertext.length();i++)
            OriginalText += (char)((((Ciphertext.charAt(i)-97)-Kab+26)%26)+97);

        System.out.println(OriginalText);
        return OriginalText;

    }
    /*  Encrypt a message using  ceaser Cipher with the
   shared key gernated from Deffie helmen  */
    public String senMessage(String plaintext )
    {

        String cyphertext = "";
        for(int i=0; i<plaintext.length();i++)
            cyphertext += (char)((((plaintext.charAt(i)-97)+Kab)%26)+97);

        System.out.println(cyphertext);
        return cyphertext;
    }

    // return (x^n) mod p
    public static int powerMod(int x, int n, int p) {
        if (n == 0) {
            return 1;
        }
        int tmp = powerMod((x * x) % p, n / 2, p);
        if (n % 2 != 0) {
            tmp = (tmp * x) % p;
        }
        return tmp;
    }
}


