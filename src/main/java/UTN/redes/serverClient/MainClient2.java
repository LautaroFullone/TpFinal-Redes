package UTN.redes.serverClient;

import UTN.redes.serverClient.models.MyClient2;

public class MainClient2 {
    public static void main(String[] args) {

        Thread client = new MyClient2();
        client.start();
    }
}
