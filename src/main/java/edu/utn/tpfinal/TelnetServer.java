package edu.utn.tpfinal;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class TelnetServer extends Thread implements IServer {

    private Socket client;
    private Scanner sc;
    private DataInputStream in;
    private PrintStream ps; //esto me sirve para que la app de telnet vea los mensajes

    public TelnetServer(){ }

    @Override
    public void setSocket(Socket client) {
        this.client = client;
    }

    @Override
    public void init() {

        try {
            sc = new Scanner(client.getInputStream());
            in = new DataInputStream(System.in);
            ps = new PrintStream(client.getOutputStream());

            String msg = "Welcome...";
            ps.println(msg);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            init();
            String msg = "";

            msg = "Enter you message: ";
            ps.println(msg);

            while (!"x".equals(msg = sc.nextLine())) {//voy a recibir los mensajes del cliente mientras que no mande x

                System.out.println("Client " + client.getLocalAddress() + " " + client.getPort() + ": " + msg);
                System.out.println("Entre you Message: ");

                msg = in.readLine();
                ps.println("Server Response: " + msg);
            }
            disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        try {
            System.out.println("Client: " + client.getLocalAddress() + " " + client.getPort() + " Disconected");
            String msg = "Bye Bye " + client.getLocalAddress() + " " + client.getPort();
            ps.println(msg);
            ps.flush();
            ps.close();
            sc.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
