package UTN.redes.serverClient.models;

import java.io.*;

import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class Server extends Connect {

    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

    private Map<String, String> respuestas;

    public Server() throws IOException {
        super("servidor");

        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();

        respuestas = new HashMap<String, String>();
        respuestas.put("hola",ANSI_CYAN+"chau"+ANSI_RESET);
        respuestas.put("hora", ANSI_CYAN+ dateFormat.format(date)+ ANSI_RESET);
        respuestas.put("ping", ANSI_CYAN+"pong"+ANSI_RESET);

        }



    public void startServer()
    {
        try
        {
            System.out.println("Esperando conexion...");

            cs = ss.accept();

            System.out.println("Cliente conectado desde "+ cs.getInetAddress());

            salidaCliente = new DataOutputStream(cs.getOutputStream());

            salidaCliente.writeUTF("Envie un mensaje...");

            DataInputStream in = new DataInputStream(cs.getInputStream());

            do//Mientras haya mensajes desde el cliente
            {
                mensajeServidor = in.readUTF();

                //Se muestra por pantalla el mensaje recibido
                System.out.println("Cliente [" + cs.getLocalAddress() + " " + cs.getPort() + "]: "+ mensajeServidor);

                if(respuestas.containsKey(mensajeServidor.toLowerCase(Locale.ROOT)))
                    salidaCliente.writeUTF(respuestas.get(mensajeServidor)+"\n");
                else{
                    salidaCliente.writeUTF("nada que decir");

                }

            }while(!mensajeServidor.equals("x"));

            System.out.println("Conexion finalizada");

            ss.close(); //Se finaliza la conexión con el cliente
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

}
