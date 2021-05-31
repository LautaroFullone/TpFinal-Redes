package edu.utn.tpfinal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerMain {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket;
        IServer server = null;
        System.out.println("Starting Server...");

        try {
            serverSocket = new ServerSocket(3000);//levanto el server
            System.out.println("Setting Up Server...");
            System.out.println(serverSocket.toString());
            System.out.println("Waiting for Client...");

            while (true) {
                Socket client = serverSocket.accept();//acepto al cliente
                server = setServerType();//elijo si el cliente se conectara por consola de java o por telnet
                System.out.println("Client " + client.getLocalAddress() + " " + client.getPort() + " accepted");

                server.setSocket(client);
                server.start();//ejecuto el run del server, ya sea java o telnet
            }

        } catch (IOException e) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private static IServer setServerType() {
        Scanner sc = new Scanner(System.in);
        int opt = 0;
        int flag = 0;

        IServer server = null;

        while (flag == 0) {
            System.out.println("Que tipo de server Desea?");
            System.out.println("1. Server Java");
            System.out.println("2. Server Telnet");
            System.out.println("Opcion: ");

            if (sc.hasNextInt()) {
                opt = sc.nextInt();
                if (opt == 1) {
                    server = new JavaServer();
                    flag = 1;
                } else if (opt == 2) {
                    server = new TelnetServer();
                    flag = 1;
                } else {
                    System.out.println("Opcion erronea");
                }
            } else {
                System.out.println("Incorrecta Opcion");
            }
        }
        return server;
    }
}
