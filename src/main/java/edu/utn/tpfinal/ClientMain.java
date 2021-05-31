package edu.utn.tpfinal;

public class ClientMain {
    public static void main(String[] args) {
        Thread client = new Client();
        client.start();
    }
}
