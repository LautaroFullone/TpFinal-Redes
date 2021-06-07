package UTN.redes.serverClient;

import UTN.redes.serverClient.models.Server;


import java.io.IOException;

/**
 * Hello world!
 *
 */
public class MainServer
{
    public static void main(String[] args) throws IOException
    {
        Server serv = new Server();
        System.out.println("Iniciando servidor\n");

        serv.startServer();
    }
}
