package org.example;

public class bob {
    int Q,a;
    int Ya; // Alice Public key
    int Yb; //Bob Public key
    int Xb; // Bob Private key
    int Kab; // Shared key


    // Bob Recive Q and a
    public void setQandA(int Q,int a)
    {
        this.Q=Q;
        this.a=a;
    }

    // Bob recive Alice public key
    public void setAlicePK(int Ya)
    {
        this.Ya = Ya;
    }

    public void generateKEys()
    {
        // genrate the private key randomly

        Xb = (int) ((5 + Math.random() )* 3);

        // gernate the publick key
        Yb =(( a^Xb)%Q);


        System.out.println("Bob Xb= "+ Xb+ " Yb= "+ Yb);

    }


    public void generateSharedKey()
    {
        // genrate the shared key with Alice
        Kab = ((Ya^Xb) % Q );

        System.out.println("Bob Kab="+ Kab);
        if (Kab >25)
        {
            Kab= Kab%26;
            System.out.println("new Kab= "+ Kab);

        }

    }


    //return Bob Public key
    public int getPublicKey()
    {
        return Yb;
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


