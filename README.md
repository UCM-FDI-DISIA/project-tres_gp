[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/5NOJ1vCd)


# **Descripción del Proyecto**

   ### *Objetivo* 
   Queremos diseñar el juego del 4 en raya y modos auxiliares, llevando a cabo un trabajo constante y eficiente organizando las diferentes tareas en nuestro grupo de 5 personas, conformado 
   por: Pablo Alonso Romero, Marina Triviño de las Heras, Óscar Marín Esteban, Rodrigo Jesús-Portanet Martínez, Álvaro Enol Alonso Ortega.

   ![PORTADA 4FURYROW](https://github.com/UCM-FDI-DISIA/project-tres_gp/assets/127221222/11911fc6-6755-4de3-9358-582793c23cb4)
   
   ### *Modos de Juego*
     Nuestro juego ofrece cuatro modos diferentes en los que poder jugar. Estos son: modo Clásico, modo Super Fihcas, modo Pop out y modo 5 en raya.
   
            - Modo Clásico: En este modo dos usuarios se enfrentaran en un tablero de 7 columnas x 6 filas. 
            Con el objetivo de lograr, antes que su adversario, una línea de 4 fichas del mismo color en cualquier dirección.
           
            - Modo Super Fichas: En este modo, basandonos en las reglas del modo Clásico, añadiremos unas __Super Fichas__. 
            Al comienzo del juego, ambos usuarios deberán elegir dos _Super Fichas_ de las cuatro opciones que ofrecemos, ya sean repetidas o diferentes. 
            Estas fichas podrán usarse durante el juego con el objetivo de generar una ventaja para sí mismo o ya sea una desventaja para el adversario. 
            Las __Super Fichas__ que ofrecemos son; Bomba, Yunque y Flecha.
 
            - Modo Pop Out: En este modo, en cada turno el jugador decidirá si tirar una ficha suya que esté en la última fila, o si colocar una ficha nueva.
 
            - Modo 5 en Raya: En este modo, no solo se añade una ficha en línea a la condición de victoria, sino que también se añaden dos columnas, una a cada lado, llenas de fichas de   
            colores alternativos, las cuales puedes usar para ayudarte a formar las 5 en raya, los cuales ya están colocadas al principio del juego.
            
   ![image](https://github.com/UCM-FDI-DISIA/project-tres_gp/assets/127221222/784405cf-6f32-4691-bef2-d2425fc31fe1)

   ### *Modalidades dentro del Clásico*
   Las diferentes modalidades que habrán son, 1vs1 entre jugadores, 1vs1 entre máquina y máquina y 1vs1 entre jugador y máquina.
 
   ### *Niveles en la implementación*
   Se añadirán distintos niveles de dificultad a la máquina, entre ellos fácil, difícil, insano, en cada uno la máquina jugará con el potencial correspondiente a su nivel.
 
   ### *Método de desarrollo del Proyecto*
     Utilizaremos Scrum para gestionar el proyecto poco a poco, este método se basa en los siguientes 3 pilares, que son:  
       1. Transparencia: en el que queremos que el desarrollo del proyecto sea visible por todos los miembros del equipo.  
       2. Inspección: Queremos que se revise el progreso y resultados para identificar mejoras, incluyendo reuniones para ponernos al día de la situación del proyecto.  
       3. Adaptación: Para adaptar  los aspectos que se desvían del proyecto.
   
   ### *Herramientas del Proyecto* 
   El desarrollo del proyecto lo haremos en Java, y para la interfaz gráfica utilizaremos JavaFx. Este software está escrito como una API de Java y que permite a los desarrolladores diseñar, 
   crear y desplegar aplicaciones con contenidos avanzados de audio y video, además de poder incluir animaciones. Con esta herramienta, además de poder jugar en interfaz gráfica, podremos 
   hacer el juego algo más entretenido pudiendo diseñar, por ejemplo, varios tableros, fichas y pantallas para cada nivel. 
   
   ### *Entrega Final* 
   Esperamos terminar el proyecto con una versión del juego 1vs1 entre jugadores, 1vs1 entre máquina y máquina y 1vs1 entre jugador y máquina para el Modo Clásico, y haber implementado el resto de modos correctamente.

# **Descarga del proyecto** 
  El proyecto está separado en distintos tags y releases según las entregas, podrás descargar cada versión yendo a [releases](https://github.com/UCM-FDI-DISIA/project-tres_gp/releases), o 
  para clonar el proyecto actual, deberás utilizar el siguiente [enlace](https://github.com/UCM-FDI-DISIA/project-tres_gp.git).
  
  Para que el proyecto funcione correctamente, lo primero que tienes que saber es que el srcServer es todo el juego, y el srcClient es solo el modo online, que se conecta con el servidor (Este modo en servidor está disponible picando classic, play with a friend, online, y luego pulsar conectar, primero en servidor y luego en cliente), una vez dicho esto, lo que habrá que hacer es en el srcClient, ir a buildPath, dentro de este ir a configure Build Path..., una vez dentro seleccionaremos la ventana de libraries, y clicando en modulepath, le daremos a añadir libreria, selecciona User Library, selecciona la opción de User Libraries..., le daremos a New..., le ponemos el nombre exacto de JavaFX (importante el nombre), le damos a add External JARs..., vamos a la carpeta SERVER, luego a javafx-sdk-21.0.2, lib, y seleccionamos todos dentro de lib, a partir de aquí es apply and close hasta salir, estará configurado el server y el client.
  
  Para hacer run, crearemos una nueva configuración, una para el cliente y otra para el servidor, y en la ventana de Arguments, en VM Arguments, para ambos pondremos lo siguiente:--module-path 'C:\JavaFX\lib' --add-modules javafx.controls,javafx.fxml, ya estaría configurado.

Video Explicativo:

https://github.com/UCM-FDI-DISIA/project-tres_gp/assets/127221222/e8074785-96ef-4710-895c-860fd0a15864


# **Estructura del código**

**En este apartado se explican tanto la estructura del código en el servidor, como en el cliente:**

**Implementamos nuestro *servidor* (interfaz) de la siguiente forma:**

   - Hay un paquete general que gestiona las pantallas de antes de los modos, así como algunas 
   	funciones de esta como la opacidad de los botones al seleccionar con el ratón.

	- Tenemos un paquete por cada modo de juego (Clásico, 5enRaya, Superfichas, PopOut).
	
	- En cada paquete hay un controlador que va seleccionando las portadas una a una, es decir, primero la de iniciar
	juego, luego la de seleccionar el modo de juego y por último ya nuestro tablero para el modo de juego que hayamos 
	elegido.

	- En el modo de juego Superfichas, a cada lado del tablero aparecen las diferentes fichas que los jugadores pueden
	utilizar a lo largo de la partida.

    - Y por último tenemos los paquetes GameObject y Logic, que consisten en los distintos objetos que contiene el juego 
     y en sus lógicas básicas respectivamente, explicado en el siguiente punto.

**Implementamos nuestro *cliente* de la siguiente forma:**

- Hay un paquete general que se encarga de gestionar el movimiento de pantallas en la interfaz, así como algunas 
funciones como cambiar la opacidad de los botones al poner un ratón encima.
	
   	- Tenemos el paquete clásico que funciona de la siguiente manera: el jugador del cliente coloca una ficha y se manda 
    la columna al servidor a través de un thread (se escribe y continuamente se comprueba que hay ahí a través de una función).
    El servidor comprueba la posición de la ficha y calcula en la fila correspondiente, después la manda al cliente de nuevo (thread).
    El cliente la recibe y la coloca visualmente. Si se termina la partida el cliente tiene una función booleana para ello.

**Implementamos nuestro juego (objetos y lógica) de la siguiente forma:**

	-Tenemos el paquete GameObjects, donde tenemos las clases GameObject, las distintas clases de fichas
 	especiales (Anvil, Arrow, Bomb, Ice), la clase Piece y la clase SuperPiece. 
  	En la clase Gameobject devolvemos las posiciones de los objetos del juego,verificamos si algún objeto 
   	esta en una posición determinada posición y cogemos el turno del jugador correspondiente.
    En las clases de las distintas superfichas cogemos estas si el jugador las selecciona.
    En Piece cogemos que tipo de ficha según el jugador que toca. 
    Y en SuperPiece cogemos lo usamos para generar el mecanismo de la superficha en caso de que sea de este tipo.
      

	-Tenemos el paquete logic, que incluye las clases Direction, Game, Game5, GameObjectContainer y Position.
	En la clase Direction, obtenemos las posibles direcciones (Up, Right, Left, Down).
	En la clase Game tenemos las variables principales del juego y las funciones básicas a realizar del juego, 
	como ejecutar una acción, actualizar el juego, contar los ciclos, crear un objeto, versi es correcta la posición 
	de un objeto...
 	En la clase Game5 implementamos la misma clase Game solo que para el modo de 5 en raya.
	En la clase GameObjectContainer tenemos todos los objetos del juego en un array de objetos y además añadimos objetos
	nuevos al juego.
	En la clase Position tenemos la opción de obtener la posición de algo del juego (objeto), si coinciden posiciones
	dos objetos y si está en el tablero un objeto.
 

# **Instrucciones de trabajo** 
  #### Proyectos
  En la sección de [proyectos](https://github.com/orgs/UCM-FDI-DISIA/projects/48), se encontrará el trabajo general que hay que hacer en el sprint actual, las historias de usuario
  #### Issues
  En el apartado de [issues](https://github.com/UCM-FDI-DISIA/project-tres_gp/issues), se publicará un poco más el día a día 
  del proyecto, problemas que se vayan teniendo, o pequeñas tareas que se vayan haciendo para completar un apartado de la sección proyectos.
  #### Tests
  Por motivos de organización, anticipación y depuración, se llevarán a cabo tests para comprobar que funcionan correctamente las funciones programadas, y comprobar que no tienen un error no 
  esperado.
