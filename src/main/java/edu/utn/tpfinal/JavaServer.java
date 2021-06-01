package edu.utn.tpfinal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class JavaServer extends Thread implements IServer {

    private Socket client;
    private DataOutputStream out;
    private DataInputStream in;
    private DataInputStream dis;
    private Scanner sc;

    public JavaServer() {
    }

    @Override
    public void setSocket(Socket client) {
        this.client = client;
    }

    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

    private Map<String, String> respuestas;

    public void init() {
        try {
            //con esto recibire los mensajes del cliente
            in = new DataInputStream(client.getInputStream());
            //con esto mandare mensajes al cliente
            respuestas = new HashMap<String, String>();
            respuestas.put("hola",ANSI_CYAN+"Chau no te quiero hablar"+ANSI_RESET);
            respuestas.put("ping", ANSI_CYAN+"pong"+ANSI_RESET);

           out = new DataOutputStream(client.getOutputStream());
            out.writeUTF("Bienvenido al mejor Servidor de LAS...");
            dis = new DataInputStream(System.in);
            sc = new Scanner(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        init();
        String msg = "";// cuando convierta a hilos tengo que ver bien donde inicializar el msg, para q no empiece como x con los nuevos clientes

        try {
            while (!"x".equals(msg)) {//voy a recibir los mensajes del cliente mientras que no mande x

                //aca recibo el mensaje del cliente
                msg = in.readUTF();
                System.out.println("Cliente " + client.getLocalAddress() + " " + client.getPort() + ": " + msg);

                if(respuestas.containsKey(msg.toLowerCase(Locale.ROOT))){
                    out.writeUTF(respuestas.get(msg)+"\n");
                    out.flush();
                } else if(!respuestas.containsKey(msg.toLowerCase(Locale.ROOT)) && !msg.equals("x")) {
                    out.writeUTF("Ingrese algo valido\n"
                    + respuestas.keySet().toString());
                    out.flush();
                }

                if ("x".equals(msg)) {
                    //si llega aca, quiere decir que puso x, con lo cual se quiere ir
                    out.writeUTF("Chau cerrando servidor, vuelva pronto " + client.getLocalAddress() + " " + client.getPort());
                    System.out.println("Cliente Desconectado");
                    disconnect();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        try {
            out.close();
            in.close();
            dis.close();
            sc.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
