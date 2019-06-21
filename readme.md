<h3> Alumnos</h3>
Davidovich Marcos,
Yañez victor

<h3>Descargar</h3>

Para clonar el repositorio. 
abrir una terminal y ejecutar: 


    git clone https://github.com/davidovichmarcos/NetworkSockets.git
En ese repositorio existen 2 modulos. Uno para el cliente y otro para el servidor.

Ambos proyectos corren independientemente.

Los archivos a ejecutar son ServerApplication.java y ClientApplication.java 
(o utilizar app mobile).

El cliente al no exitir un servidor finalizara.

Al iniciar el mismo consulta direccion y puerto los cuales deben ser 127.0.0.1 y 3000
respectivamente, de lo contrario informara un error.

Al conectarse al server este solicitara un nickname de identificacion.


El servidor envia mensajes mediante el comando "sm", este solicita un destinatario o "*" (general)
y el mensaje.

Para finalizar la conexion tanto del server como el client se debe ingresar "x" y luego enter.

<h3>Teoria</h3>
1) ¿Qué es un Puerto?
Definición de puerto: 	En el ámbito de Internet, un puerto es el valor que se usa, en el modelo de la capa de transporte, para distinguir entre las múltiples aplicaciones que se pueden conectar al mismo host, o puesto de trabajo.

2) ¿Cómo están formados los endpoints?
Diferencia entre endpoint y entrypoint:
Si pudiéramos clasificarlos brevemente sería:
Entrypoint - URL de entrada a una página de un sitio web.
Endpoint - URL de un servicio que utiliza un sitio web para cargar o consumir información.
Ejemplos de uso:
Una página Home de un sitio web es un entrypoint.
Una API Web se accede a través de endpoints.
Los endpoints están formados por una dirección ip (puede ser el mismo localhost), el carácter “:”(dos puntos) el cual indica que termina la dirección ip, y comienza el puerto.	Ejemplo: 192.168.0.1:8080.

3) ¿Qué es un Socket?
Socket designa un concepto abstracto por el cual dos programas (posiblemente situados en computadoras distintas) pueden intercambiar cualquier flujo de datos, generalmente de manera fiable y ordenada.
El término socket es también usado como el nombre de una interfaz de programación de aplicaciones (API) para la familia de protocolos de Internet TCP/IP, provista usualmente por el sistema operativo.
Los sockets de Internet constituyen el mecanismo para la entrega de paquetes de datos provenientes de la tarjeta de red a los procesos o hilos apropiados. Un socket queda definido por un par de direcciones IP local y remota, un protocolo de transporte y un par de números de puerto local y remoto.
Un socket en síntesis es una conexión entre dos puertos de dos máquinas distintas(o la misma máquina) que comunica dos aplicaciones de manera continua y segura, abstrayendo la manera de la que se comunican en las capas inferiores.

4) ¿A qué capa del modelo TPC/IP pertenecen los sockets? ¿Porque?
Los sockets pertenecen a la capa de transporte. Porque, funcionan los mismos crean una conexión entre un puerto e ip de un nodo con otro puerto e ip de otro nodo. Los mismos utilizan protocolos de la capa de transporte para funcionar, como udp o tcp.

5) ¿Cómo funciona el modelo cliente-servidor con TCP/IP Sockets?
Un servidor es una aplicación que ofrece un servicio a usuarios; un cliente es el que pide ese servicio. Una aplicación consta de una parte de servidor y una de cliente, que se pueden ejecutar en el mismo o en diferentes sistemas.
Pero un cliente no puede funcionar sin un servidor, por lo tanto el primero en instanciarse es el servidor. Al iniciarlo es necesario indicar un numero de puerto (comunmente el puerto 3000)  a través del cual este escuchará indefinidamente las peticiones de servicio de los posibles clientes. El modelo TCP está orientado a la conección, por lo tanto al recibir la solicitud de un cliente el servidor generará un socket que mantendrá una coneccion estable abierta con el nuevo cliente, hasta que el decida cerrarlo. El objetivo de este socket es el intercambio de información entre el cliente-servidor.
En el caso del cliente es un poco mas simple, solo necesita una dirección ip y un puerto de entrada, con esta información intenta conectarse a un servidor ya funcionando. De ser efectiva la conección, el cliente y el servidor generan un socket por medio del cual intercambian información. Lo importante a destacar es que el cliente es el que da “el primer paso” en la coneccion, ya que es él quien le solicita servicio al servidor, y no al revés.

6) ¿Cuales son las causas comunes por la que la conexión entre cliente/servidor falle?
La causa principal podría ser falta de vías de comunicación entre ambos. 
Si cliente y servidor están en una misma máquina, el problema podría ser que el cliente esté intentando conectarse a un puerto equivocado. 
Si cliente y servidor están en máquinas diferentes, el problema podría estar referido a la capa de red, es decir, a una falta de conección entre ambas máquinas (sea por ethernet o internet), o a que el cliente tenga una dirección ip/puerto equivocada del servidor.

7) Diferencias entre sockets UDP y TCP
Sockets TCP (socket de Stream): TCP es un servicio orientado a conexión donde los datos se transfieren sin encuadrarlos en registros o bloques. Si se rompe la conexión entre los procesos, éstos serán informados.
El protocolo de comunicaciones con streams es un protocolo orientado a conexión, ya que para establecer una comunicación utilizando el protocolo TCP, hay que establecer en primer lugar una conexión entre un par de sockets. Mientras uno de los sockets atiende peticiones de conexión (servidor), el otro solicita una conexión (cliente). Una vez que los dos sockets estén conectados, se pueden utilizar para transmitir datos en ambas direcciones.
Sockets UDP (socket de Datagrama): UDP Es un servicio de transporte sin conexión. Se transmiten paquetes individuales de información y no garantiza que los paquetes llegarán en alguna forma en particular. De hecho los paquetes pueden perderse, duplicarse e incluso llegar en desorden. 
El protocolo de comunicaciones con datagramas es un protocolo sin conexión, es decir, cada vez que se envíen datagramas es necesario enviar el descriptor del socket local y la dirección del socket que debe recibir el datagrama.                                                                                                                                                                             

8) Diferencia entre sync & async sockets?
La diferencia principal es que un socket sincronico cada vez que envía algo, se queda esperando una respuesta, bloqueando cualquier otro proceso; en cambio un socket asincrónico no bloquea la ejecución de otros procesos. 
Pero para un escenario el cual necesite una cantidad significante de conexiones concurrentes, las async APIs son las únicas que proveen una performance adecuada, la principal desventaja de los sockets async es que requieren más esfuerzo de desarrollo para hacer funcionar la app al mismo nivel de confiabilidad.
 



