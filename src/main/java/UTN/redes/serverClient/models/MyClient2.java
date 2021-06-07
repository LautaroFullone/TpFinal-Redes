package UTN.redes.serverClient.models;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MyClient2 extends Thread{

    public MyClient2() {
    }

    @Override
    public void run() {

        DataInputStream in;
        DataOutputStream out;

        Scanner teclado = new Scanner(System.in);
        String msg = "";

        int flag = 0;

        String HOST;
        Integer PUERTO;

        Socket client = null;
        try {

            while (flag == 0){
                System.out.println("Ingrese el host -> ");
                HOST = teclado.nextLine();

                System.out.println("Ingrese el puerto -> ");
                PUERTO = Integer.parseInt(teclado.nextLine());

                try{
                    client = new Socket(HOST,PUERTO);
                    flag++;//si se logra conectar salgo del while, sino atrapo la exception, con lo cual flag seguira siendo 0
                }catch (IOException i){
                    System.out.println("Ocurrio un error al intentar conectarse, vuelva a intentarlo");
                }
            }

            System.out.println("Aguarde hasta recibir el mensaje de bienvenida...");

            in = new DataInputStream(client.getInputStream());

            String mensaje = in.readUTF();

            System.out.println(mensaje);

            while (true) {

                in = new DataInputStream(client.getInputStream());//con esto recibire los mensajes del servidor

                out = new DataOutputStream(client.getOutputStream());//con esto mandare mensajes al Servidor

                //en este punto el servidor esta esperando un mensaje del cliente
                System.out.println("Que quiere decirle al servidor?: ");

                msg = teclado.nextLine();

                out.writeUTF(msg); //envio mi mensaje

                String respuesta = in.readUTF(); //leo lo que me responde el servidor

                System.out.println("Respuesta del server -> " + respuesta);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

