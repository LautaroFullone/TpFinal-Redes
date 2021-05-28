package UTN.redes.serverClient.models;

import java.io.DataOutputStream;
import java.io.IOException;

public class MyClient extends Connect {

    public MyClient() throws IOException {super("cliente");}

    public void startClient()
    {
        try
        {
            salidaServidor = new DataOutputStream(cs.getOutputStream());

            for (int i = 0; i < 2; i++){
                salidaServidor.writeUTF("Este es el mensaje número " + (i+1) + "\n");
            }
            cs.close();//Fin de la conexión
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
