package UTN.redes.serverClient;

import UTN.redes.serverClient.models.MyClient;

import java.io.IOException;

public class MainClient {
    public static void main(String[] args) throws IOException {
        MyClient c = new MyClient(); //Se crea el cliente

        System.out.println("Iniciando cliente\n");
        c.startClient(); //Se inicia el cliente
    }
}
