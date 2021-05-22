# TpFinal-Redes
Modelo Cliente - Servidor - TCP/IP

## 1. ¿Que es un puerto?
> Un puerto de red es una interfaz para comunicarse con un programa a través de una red.
Los números de puerto se indican mediante una palabra de un procesador de 16 bits, o sea, de 2 bytes (16 bits) y su valor varía entre 0 y 65535. 

## 2. ¿Como estan formados los endpoints?
> Están formados por diversos elementos como sistemas operativos, licencias anti-virus o cliente VPN, todos ellos actualizados

## 3. ¿Que es un socket?
> 3)Un socket es un punto de comunicacion por el cual pueden intercambiar cualquier flujo de datos, generalmente de manera fiable y ordenada.

## 4. ¿A qué capa del modelo TPC/IP pertenecen los sockets? ¿Porque?
>4)Los sockets pertenecen a la capa de transporte. Esto se debe a que las aplicaciones que emplean sockets son diseñadas para utilizar tanto del modelo UDP o TCP su capa de transporte, siempre y cuando que los protocolos especificados en su capa satisfagan el tipo de conexión que requiere la aplicación.

## 5. ¿Cómo funciona el modelo cliente-servidor con TCP/IP Sockets?
> Los usuarios invocan la parte cliente de la aplicación, que construye una solicitud para ese servicio y se la envía al servidor de la aplicación que usa TCP/IP como transporte.
    El servidor recibe una solicitud, realiza el servicio requerido y devuelve los resultados en forma de una respuesta

## 6. ¿Cuales son las causas comunes por la que la conexión entre cliente/servidor falle?
> 6)Las causas comunes por la que la conexión entre cliente/servidor falle son:
Error 2200: El cliente no recibió una respuesta del servidor en un tiempo determinado (timeout); esto sucede solo si un límite de tiempo fue especificado.
Error 2300: El servidor cerró la conexión.
Error 2310: El servidor se ha caído mientras intentaba procesar el Handshake Request (conexión con el cliente). La conexión se cerró.
Error 2315: El servidor recibió el Handshake Request (conexión con el cliente) y devolvió una respuesta del tipo non-IIOP que el cliente no puede procesar (El cliente recibe una respuesta en un lenguage que no entinede).

## 7. Diferencias entre sockets UDP y TCP
> La principal diferencia entre ambos es que el UDP necesita que le entregemos paquetes de datos que el usuario debe construir, mientras el TCP admite bloques de datos  que serán empaquetados de forma transparente antes de ser transmitidos.
Además otra diferencia importante. Tanto los paquetes de datos UDP como los segmentos TCP pueden perderse . Si un paquete se pierde el UDP no hace nada. Por el contrario, si un segmento se pierde el TCP lo retransmitirá, y este proceso durará hasta que el segmento ha sido correctamente entregado al host receptor, o se produzca un número máximo de retransmisiones.
En el UDP controlamos qué datos viajan en cada paquete. En el TCP esto no es posible porque el empaquetamiento es automático.

## 8. Diferencia entre sync & async sockets?
>Cuando hablamos de algo sincrónico, nos referimos a algo que sigue un orden. Es decir, que al terminar de ejecutar el paso uno, luego ira el dos, el tres y así sucesivamente. Sin embargo, el paso tres nunca puede ocurrir antes que el paso dos; debe esperar a que este concluya para terminar.
Asincrónico es lo opuesto a esto ya que los pasos pueden ocurrir al mismo tiempo, aleatoriamente o mientras uno de los pasos se está ejecutando.
Volviendo a sockets, decimos que es sincrónico cuando los clientes interactúan con el servidor en orden. Esto significa que cuando el servidor se encuentre interactuando con un cliente y otro cliente desee hacer lo mismo, quedara en espera hasta que el servidor se desocupe.
Pero un socket asincrónico permite que varios clientes interactúen a la vez con el servidor sin la necesidad de esperar a que este se encuentre desocupado.

