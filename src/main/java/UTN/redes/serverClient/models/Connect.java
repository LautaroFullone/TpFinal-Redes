package UTN.redes.serverClient.models;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connect {

    private final int PUERTO = 3000; //Puerto para la conexión
    private final String HOST = "localhost"; //Host para la conexión
    protected String mensajeServidor; //Mensajes entrantes (recibidos) en el servidor
    protected ServerSocket ss; //Socket del servidor
    protected Socket cs; //Socket del cliente
    protected DataOutputStream salidaServidor;
    protected DataOutputStream salidaCliente;

    public Connect(String tipo) throws IOException //Constructor
    {
        if(tipo.equalsIgnoreCase("servidor")) {
            ss = new ServerSocket(PUERTO);
            cs = new Socket();
        }
        if(tipo.equalsIgnoreCase("cliente"))
            cs = new Socket(HOST, PUERTO);

    }
}
