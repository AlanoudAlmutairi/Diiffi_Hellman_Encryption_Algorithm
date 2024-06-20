package org.example;

import java.util.Random;
import java.util.Scanner;

public class Lab6_Tamplet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        Alice alice=new Alice();
        bob bob =new bob();
        //Alice genrate Q and A
        int QandA[]= alice.gernateQnadA();
        // Send Q and A to Bob
        bob.setQandA(QandA[0], QandA[1]);
        // Genrate Alice public and private keys
        alice.generateKEys();
        // Genrate Bob public and private keys
        bob.generateKEys();
        // send Bob public key to Alice
        alice.setBobPK(bob.getPublicKey());
        // send Alice public key to Bob
        bob.setAlicePK(alice.getPublicKey());
        // Alice genrate the shared key
        alice.generateSharedKey();
        // Bob genrate the shared key
        bob.generateSharedKey();
        //Alice encrypt the message and  send  it to Bob
        bob.reciveMessage(alice.senMessage("hello"));


    }
}
