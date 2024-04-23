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
  
  Para hacer run, deberás asegurarte de añadir los jars de la carpeta lib al classpath, luego en elarguments VM del run configurations deberás poner lo siguiente: --module-path 'C:\JavaFX\lib' --add-modules javafx.controls,javafx.fxml

# **Estructura del código**

**En este apartado se explican tanto la estructura del código en interfaz, como en terminal:**

**Implementamos nuestra *interfaz* de la siguiente forma:**

   - Hay un paquete general que gestiona las pantallas de antes de los modos.

	- Tenemos un paquete por cada modo de juego (Clásico, 5enRaya, Superfichas, PopOut).
	
	- En cada paquete hay un controlador que va seleccionando las portadas una a una, es decir, primero la de iniciar
	juego, luego la de seleccionar el modo de juego y por último ya nuestro tablero para el modo de juego que hayamos 
	elegido.

	- En el modo de juego Superfichas, a cada lado del tablero aparecen las diferentes fichas que los jugadores pueden
	utilizar a lo largo de la partida.


**Implementamos nuestro juego en *terminal* de la siguiente forma:**

	-Tenemos el paquete GameObjects, donde tenemos as clases GameObject y la clase
	piece. En la clase Gameobject devolvemos las posiciones de los objetos del juego,verificamos si
	algún objeto esta en una posición determinada posición y cogemos el turno del jugador correspondiente.
	Y en Piece cogemos que tipo de ficha según el jugador que toca.

	-Tenemos el paquete commands que es donde vamos a programar todos los comandos del juego, que son:
	La clase ExitCommand, donde programamos la acción de salir del juego.
	La clase HelpCommand, donde programamos una ventana de ayuda que nos diga los posibles comandos e
	instrucciones del juego.
	La clase NoParamsCommand, donde programamos en caso de que no se meta ningún comando o un comando incorrecto. 
	La clase PlaceCommand, donde programamos la opción de poner una ficha en el tablero.
	Y a parte de estos comandos, dentro del mismo paquete tenemos las clases: Command(clase superior), donde 
	ejecutamos todos los comandos, y la clase CommandGenerator, donde creamos los comandos para oder luego ser 
	procesados.

	-Tenemos el paquete control, en el que se encuentra la clase Controller, que es la encargada de crear el juego
	y de procesarlo hasta que acabe o salgamos de él. Es como la clase main de nuestro juego.

	-Tenemos el paquete exceptions, donde se encuentran las siguientes clases:
	CommandExecuteException, es una clase en la que llamamos a mensajes para que salgan por pantalla en caso de 
	una excepción en la ejecución.
	CommandParseException, es una clase en la que llamamos a mensajes para que salgan por pantalla en caso de 
	una excepción a la hora de parsear algo.
	FullColumnException, en esta clase lanzamos un mensaje en caso de que se produzca la excepción de que hay 
	una columna llena de fichas en la que ya no se pueden insertar más.
	OffWorldException, en esta clase lanzamos la excepción en forma de mensaje en caso de que intentemos insertar 
	una ficha fuera de los límites de nuestro tablero.

	-Tenemos el paquete logic, que incluye las clases Direction, Game, GameObjectContainer y Position.
	En la clase Direction, obtenemos las posibles direcciones (Up, Right, Left, Down).
	En la clase Game tenemos las variables principales del juego y las funciones básicas a realizar del juego, 
	como ejecutar una acción, actualizar el juego, contar los ciclos, crear un objeto, versi es correcta la posición 
	de un objeto...
	En la clase GameObjectContainer tenemos todos los objetos del juego en un array de objetos y además añadimos objetos
	nuevos al juego.
	En la clase Position tenemos la opción de obtener la posición de algo del juego (objeto), si coinciden posiciones
	dos objetos y si está en el tablero un objeto.
	
	-Tenemos el paquete util, con la clase StringUtils, que sirve para modificar los strings que 
	utilizamos en el juego.

	-Tenemos el paquete view, que consiste en todo lo que se ve por pantalla del juego. Contiene las siguientes clases:
	BoardPrinter, donde pintamos el tablero del juego.
	GamePrinter (clase superior), donde pintamos todo el juego.
	Messages, donde tenemos todos los mensajes posibles que podemos sacar por pantalla del juego.

	-Por último, tenemos nuestro Main que utiliza todos los paquetes.

# **Instrucciones de trabajo** 
  #### Proyectos
  En la sección de [proyectos](https://github.com/orgs/UCM-FDI-DISIA/projects/48), se encontrará el trabajo general que hay que hacer en el sprint actual, las historias de usuario
  #### Issues
  En el apartado de [issues](https://github.com/UCM-FDI-DISIA/project-tres_gp/issues), se publicará un poco más el día a día 
  del proyecto, problemas que se vayan teniendo, o pequeñas tareas que se vayan haciendo para completar un apartado de la sección proyectos.
  #### Tests
  Por motivos de organización, anticipación y depuración, se llevarán a cabo tests para comprobar que funcionan correctamente las funciones programadas, y comprobar que no tienen un error no 
  esperado.
