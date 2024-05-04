package gp.clasico;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import gp.GameObjects.GameObject;
import gp.GameObjects.Piece;
import gp.logic.Game;
import gp.logic.Position;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class selectClassic {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Game game;

    @FXML
    private MenuItem btnVolver;
    
    @FXML
    private MenuItem btnRestart;
    
    @FXML
    private MenuItem btnRestaurar;
    
    @FXML
    private MenuItem btnConectar;
    
    @FXML
    private Button btnSalirGana;
    
    @FXML
    private Button btnFicha;

    @FXML
    private Button btnFicha1;

    @FXML
    private Button btnFicha2;

    @FXML
    private Button btnFicha3;

    @FXML
    private Button btnFicha4;

    @FXML
    private Button btnFicha5;

    @FXML
    private Button btnFicha6;

    @FXML
	private GridPane gridPane;

    @FXML
    private MenuButton menuButton;
    
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private DataOutputStream toClient;
    private DataInputStream fromClient;
    private static final int PUERTO = 12345;
    protected boolean isFinished = false;

    @FXML
    private void colocarFicha(MouseEvent event) throws IOException {
    	if (!isFinished) {
    		int columna = getColumn(event);
            try {
                // Place piece
            	updateGridPane(columna);
                
            	// Check if there is a winner
                if (game.someoneWin()) { 
                	isFinished = true;
                	showWinners(gridPane);
                } 
                else {game.flip();} // Turn change
            } 
            catch (IOException e) { e.printStackTrace();}
    	}
    	else {endMessage();}
    }
    
    protected void updateGridPane(int columna) throws IOException {
    	Parent ficha = FXMLLoader.load(getClass().getResource("/gp/FICHA JUGADOR %s.fxml".formatted(game.getTurn())));
        int fila = game.place(columna); // Suponemos que esto coloca la ficha lógicamente y devuelve la fila donde se colocó
        gridPane.add(ficha, columna, fila); // Añadimos la ficha físicamente al GridPane
    }
    
    protected void showWinners(GridPane gridPane) throws IOException {
    	List<List<Position>> winners = game.getWinners();
    	for (List<Position> winner : winners) {
    	    for (Position pos : winner) {
            	Parent fichaGanadora = FXMLLoader.load(getClass().getResource("/gp/FICHA GANADORA.fxml"));
    	        int fila = pos.getRow();
    	        int columna = pos.getCol();
    	        gridPane.add(fichaGanadora, columna, fila);
    	    }
    	}
    }
    
    private void sendWinnersToClient(List<List<Position>> winners, boolean sendWinners) {
        try {
            toClient.writeBoolean(sendWinners); // Envía el booleano indicando si se deben procesar los ganadores
            if (sendWinners) {
                toClient.writeInt(winners.size()); // Envía el número de conjuntos de ganadores
                for (List<Position> winner : winners) {
                    toClient.writeInt(winner.size()); // Envía el número de posiciones en este conjunto
                    for (Position pos : winner) {
                        toClient.writeInt(pos.getRow()); // Envía la fila
                        toClient.writeInt(pos.getCol()); // Envía la columna
                    }
                }
            }
            toClient.flush(); // Asegura que los datos se envíen inmediatamente
        } catch (IOException e) {
            System.out.println("Error al enviar los ganadores al cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    
    protected int getColumn(MouseEvent event) {
    	Node source = (Node) event.getSource();
        Node parent = source;
        parent = parent.getParent();
        GridPane gridPane = (GridPane) parent.getParent();
        Integer columnaInteger = GridPane.getColumnIndex(parent);

        // Comprueba si la columna es null y asigna 0 como valor predeterminado    	
        return (columnaInteger != null) ? columnaInteger : 0;
    }
    
    protected void endMessage() throws IOException {
    	Parent alertRoot = FXMLLoader.load(getClass().getResource("/gp/clasico/VOLVER A INICIAL.fxml"));
		gridPane.add(alertRoot, 0, 0, gridPane.getColumnCount(), gridPane.getRowCount());
        GridPane.setHalignment(alertRoot, HPos.CENTER);
        GridPane.setValignment(alertRoot, VPos.CENTER);
    }

    @FXML
    void switchToScene2(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/gp/SEGUNDA PORTADA.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        desconectarServidor();
    }
    @FXML
    private void desconectarServidor() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close(); // Cierra el ServerSocket si está abierto
                System.out.println("Servidor desconectado.");
            }
        } catch (IOException e) {
            System.out.println("Error al desconectar el servidor: " + e.getMessage());
        }
    }

    @FXML
    void switchToScene2Menu(ActionEvent event) throws IOException {
        MenuItem menuItem = (MenuItem) event.getSource(); // Obtener el MenuItem
        Parent parent = (Parent) menuItem.getParentPopup().getOwnerNode(); // Obtener el nodo padre del menú emergente
        Scene scene = parent.getScene(); // Obtener la escena
        Stage stage = (Stage) scene.getWindow(); // Obtener el Stage
        root = FXMLLoader.load(getClass().getResource("/gp/SEGUNDA PORTADA.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
        desconectarServidor();
    }

    @FXML
    void switchToTableroNormal(ActionEvent event) throws IOException {
        MenuItem menuItem = (MenuItem) event.getSource(); // Obtener el MenuItem
        Parent parent = (Parent) menuItem.getParentPopup().getOwnerNode(); // Obtener el nodo padre del menú emergente
        Scene scene = parent.getScene(); // Obtener la escena
        Stage stage = (Stage) scene.getWindow(); // Obtener el Stage
        root = FXMLLoader.load(getClass().getResource("TABLERO CLASSIC.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void onMouseEntered(MouseEvent event) {
        // Código para el efecto al entrar con el mouse
        Button button = (Button) event.getSource();
        button.setOpacity(0.35); // Cambiar la opacidad para simular una luz encendida
    }

    @FXML
    void onMouseExited(MouseEvent event) {
        // Código para revertir el efecto al salir con el mouse
        Button button = (Button) event.getSource();
        button.setOpacity(0.0); // Restaurar la opacidad original para apagar la "luz"
    }

    public selectClassic() {
        this.game = new Game();
    }
    
    public void conectar(ActionEvent event) {
        try {
            serverSocket = new ServerSocket(12345);
            System.out.println("Servidor iniciado, esperando al cliente...");
            clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado: " + clientSocket.getInetAddress().getHostAddress());
            fromClient = new DataInputStream(clientSocket.getInputStream());
            toClient = new DataOutputStream(clientSocket.getOutputStream());
            // Nuevo hilo para escuchar al cliente
            Thread serverThread = new Thread(() -> {
                try {
                    recibirJugada(); // Llama a recibirJugada cuando el cliente coloca una ficha
                } catch (Exception e) {
                    System.out.println("Error al aceptar conexión del cliente: " + e.getMessage());
                }
            });
            System.out.println("Empieza el cliente.");
            serverThread.start();
        } catch (IOException e) {
            System.out.println("No se pudo iniciar el servidor: " + e.getMessage());
        }
    }


    @FXML
    private void colocarFichaOnline(MouseEvent event) {
    	if(game.getTurn() == 2) {
	        Node source = (Node) event.getSource();
	        Node parent = source.getParent();
	        GridPane gridPane = (GridPane) parent.getParent();
	        Integer columnaInteger = GridPane.getColumnIndex(parent);
	        int columna = (columnaInteger != null) ? columnaInteger : 0;
	
	        try {
	            Parent ficha = FXMLLoader.load(getClass().getResource("/gp/FICHA JUGADOR %s.fxml".formatted(game.getTurn())));
	            int fila = game.placeOnline(columna, false);  // Colocamos la ficha y obtenemos la fila donde se colocó
	            gridPane.add(ficha, columna, fila); // Añadimos la ficha físicamente al GridPane
	
	            // Enviamos la fila y la columna al cliente para que coloque su ficha
	            enviarJugada(fila, columna, 2);
	
	            if (game.someoneWin()) {
	            	List<List<Position>> winners = game.getWinners();
	            	sendWinnersToClient(winners, true); 
	            	isFinished = true;
	            	showWinners(gridPane);
	
	            } else {
	                game.flip(); // Actualizamos el estado del juego
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
    	}
    	else {
    		System.out.println("NO ES TU TURNO");
    	}
    }

    private void enviarJugada(int fila, int columna, int turno) {
        try {
        	toClient.writeBoolean(false);
            toClient.writeInt(fila);
            toClient.writeInt(columna);
            toClient.writeInt(turno);
            toClient.flush(); // Aseguramos que los datos se envíen inmediatamente
        } catch (IOException e) {
            System.out.println("Error al enviar la jugada al cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void recibirJugada() {
    	if(game.getTurn() == 1) {
	    	while(true) {
		        try {
		            // Recibimos la columna enviada por el cliente
		            int columnaCliente = fromClient.readInt();
		
		            // Agenda la actualización de la interfaz de usuario en el hilo de JavaFX
		            Platform.runLater(() -> {
		                try {
		                    Parent ficha = FXMLLoader.load(getClass().getResource("/gp/FICHA JUGADOR %s.fxml".formatted(game.getTurn())));
		                    // Realizamos la operación de game.place(columna) para obtener la fila correspondiente
		                    int fila = game.placeOnline(columnaCliente, true);
		                    gridPane.add(ficha, columnaCliente, fila);
		                    // Enviamos la fila de vuelta al cliente
		                    enviarJugada(fila, columnaCliente, game.getTurn());
		                    if (game.someoneWin()) {
		    	            	List<List<Position>> winners = game.getWinners();
		    	            	sendWinnersToClient(winners, true); 
		    	            	isFinished = true;
		    	            	showWinners(gridPane);
		    	
		    	            } else {
		    	                game.flip(); // Actualizamos el estado del juego
		    	            }
		                } catch (IOException e) {
		                    System.out.println("Error al recibir la jugada del cliente: " + e.getMessage());
		                    e.printStackTrace();
		                }
		            });
		        } catch (IOException e) {
		            System.out.println("Error al recibir la jugada del cliente: " + e.getMessage());
		            e.printStackTrace();
		        }
	    	}
    	}
    	else {
    		System.out.println("No es el turno del cliente");
    	}
    }

	    @FXML
	 // Guardar el estado actual del juego
	 private void guardarPartida(ActionEvent event) {
	     try {
	         File file = new File("partidas.txt");
	         if (file.exists()) {
	             file.delete(); // Eliminar el archivo si ya existe
	         }
	         
	         FileWriter writer = new FileWriter(file);
	         List<GameObject> pieces = game.getGameObjectContainer(); // Asumimos que esto retorna una lista de Pieces
	         for (GameObject piece : pieces) {
	             writer.write(piece.serialize() + "\n"); // Guarda cada Piece como una línea en el archivo
	         }
	         writer.close();
	     } catch (IOException e) {
	         e.printStackTrace();
	     }
	 }
	
	 @FXML
	 private void restaurarPartida(ActionEvent event) {
	     try {
	         BufferedReader reader = new BufferedReader(new FileReader("partidas.txt"));
	         List<GameObject> pieces = new ArrayList<>();
	         String line;
	         while ((line = reader.readLine()) != null) {
	             pieces.add(GameObject.deserialize(game, line));
	             Position p = GameObject.deserializePos(game, line);
	             int turn = GameObject.deserializeTurn(game, line);
	             Parent ficha = FXMLLoader.load(getClass().getResource("/gp/FICHA JUGADOR %s.fxml".formatted(turn)));
	             gridPane.add(ficha, p.getCol(), p.getRow());
	         }
	         reader.close();
	         game.setGameObjectContainer(pieces);
	     } catch (IOException e) {
	         e.printStackTrace();
	     }
	 }  
    
    @FXML
    private void colocarFichaFacil(MouseEvent event) {
        Node source = (Node) event.getSource();
        Node parent = source;
        parent = parent.getParent();
        GridPane gridPane = (GridPane) parent.getParent();
        Integer columnaInteger = GridPane.getColumnIndex(parent);

        // Verificar si la columna es null y asignar 0 como valor predeterminado
        int columna = (columnaInteger != null) ? columnaInteger : 0;

        try {
            // Cargamos la ficha
            Parent ficha = FXMLLoader.load(getClass().getResource("/gp/FICHA JUGADOR %s.fxml".formatted(game.getTurn())));
            int fila = game.place(columna); // Suponemos que esto coloca la ficha lógicamente y devuelve la fila donde se colocó
            gridPane.add(ficha, columna, fila); // Añadimos la ficha físicamente al GridPane
            if (game.someoneWin()) { // Si alguien gana después de colocar la ficha
                System.out.println("Gana el Jugador%s".formatted(game.getTurn()));
                // Mostrar una alerta o pantalla de victoria
                Parent alertRoot = FXMLLoader.load(getClass().getResource("/gp/clasico/VOLVER A INICIAL.fxml"));
                gridPane.add(alertRoot, 0, 0, gridPane.getColumnCount(), gridPane.getRowCount());
                GridPane.setHalignment(alertRoot, HPos.CENTER);
                GridPane.setValignment(alertRoot, VPos.CENTER);
            } else {
                game.updateBot(); // Actualiza el estado del juego
                columna = game.botEasyMove();
                ficha = FXMLLoader.load(getClass().getResource("/gp/FICHA JUGADOR %s.fxml".formatted(game.getTurn())));
                fila = game.place(columna); // Suponemos que esto coloca la ficha lógicamente y devuelve la fila donde se colocó
                gridPane.add(ficha, columna, fila); // Añadimos la ficha físicamente al GridPane
                if (game.someoneWin()) { // Si alguien gana después de colocar la ficha
                    System.out.println("Gana el Jugador%s".formatted(game.getTurn()));
                    // Mostrar una alerta o pantalla de victoria
                    Parent alertRoot = FXMLLoader.load(getClass().getResource("/gp/clasico/VOLVER A INICIAL.fxml"));
                    gridPane.add(alertRoot, 0, 0, gridPane.getColumnCount(), gridPane.getRowCount());
                    GridPane.setHalignment(alertRoot, HPos.CENTER);
                    GridPane.setValignment(alertRoot, VPos.CENTER);
                }
                else {
                   game.updateBot();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    @FXML
    private void colocarFichaDificil(MouseEvent event) {
        Node source = (Node) event.getSource();
        Node parent = source;
        parent = parent.getParent();
        GridPane gridPane = (GridPane) parent.getParent();
        Integer columnaInteger = GridPane.getColumnIndex(parent);

        // Verificar si la columna es null y asignar 0 como valor predeterminado
        int columna = (columnaInteger != null) ? columnaInteger : 0;

        try {
            // Cargamos la ficha
            Parent ficha = FXMLLoader.load(getClass().getResource("/gp/FICHA JUGADOR %s.fxml".formatted(game.getTurn())));
            int fila = game.place(columna); // Suponemos que esto coloca la ficha lógicamente y devuelve la fila donde se colocó
            gridPane.add(ficha, columna, fila); // Añadimos la ficha físicamente al GridPane
            if (game.someoneWin()) { // Si alguien gana después de colocar la ficha
                System.out.println("Gana el Jugador%s".formatted(game.getTurn()));
                // Mostrar una alerta o pantalla de victoria
                Parent alertRoot = FXMLLoader.load(getClass().getResource("/gp/clasico/VOLVER A INICIAL.fxml"));
                gridPane.add(alertRoot, 0, 0, gridPane.getColumnCount(), gridPane.getRowCount());
                GridPane.setHalignment(alertRoot, HPos.CENTER);
                GridPane.setValignment(alertRoot, VPos.CENTER);
            } else {
                game.updateBot(); // Actualiza el estado del juego
                columna = game.getBestColumn();
                ficha = FXMLLoader.load(getClass().getResource("/gp/FICHA JUGADOR %s.fxml".formatted(game.getTurn())));
                fila = game.place(columna); // Suponemos que esto coloca la ficha lógicamente y devuelve la fila donde se colocó
                gridPane.add(ficha, columna, fila); // Añadimos la ficha físicamente al GridPane
                if (game.someoneWin()) { // Si alguien gana después de colocar la ficha
                    System.out.println("Gana el Jugador%s".formatted(game.getTurn()));
                    // Mostrar una alerta o pantalla de victoria
                    Parent alertRoot = FXMLLoader.load(getClass().getResource("/gp/clasico/VOLVER A INICIAL.fxml"));
                    gridPane.add(alertRoot, 0, 0, gridPane.getColumnCount(), gridPane.getRowCount());
                    GridPane.setHalignment(alertRoot, HPos.CENTER);
                    GridPane.setValignment(alertRoot, VPos.CENTER);
                }
                else {
                   game.updateBot();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void colocarFichaMedio(MouseEvent event) {
        Node source = (Node) event.getSource();
        Node parent = source;
        parent = parent.getParent();
        GridPane gridPane = (GridPane) parent.getParent();
        Integer columnaInteger = GridPane.getColumnIndex(parent);

        // Verificar si la columna es null y asignar 0 como valor predeterminado
        int columna = (columnaInteger != null) ? columnaInteger : 0;

        try {
            // Cargamos la ficha
            Parent ficha = FXMLLoader.load(getClass().getResource("/gp/FICHA JUGADOR %s.fxml".formatted(game.getTurn())));
            int fila = game.place(columna); // Suponemos que esto coloca la ficha lógicamente y devuelve la fila donde se colocó
            gridPane.add(ficha, columna, fila); // Añadimos la ficha físicamente al GridPane
            if (game.someoneWin()) { // Si alguien gana después de colocar la ficha
                System.out.println("Gana el Jugador%s".formatted(game.getTurn()));
                // Mostrar una alerta o pantalla de victoria
                Parent alertRoot = FXMLLoader.load(getClass().getResource("/gp/clasico/VOLVER A INICIAL.fxml"));
                gridPane.add(alertRoot, 0, 0, gridPane.getColumnCount(), gridPane.getRowCount());
                GridPane.setHalignment(alertRoot, HPos.CENTER);
                GridPane.setValignment(alertRoot, VPos.CENTER);
            } else {
                game.updateBot(); // Actualiza el estado del juego
                columna = game.botMediumMove();
                ficha = FXMLLoader.load(getClass().getResource("/gp/FICHA JUGADOR %s.fxml".formatted(game.getTurn())));
                fila = game.place(columna); // Suponemos que esto coloca la ficha lógicamente y devuelve la fila donde se colocó
                gridPane.add(ficha, columna, fila); // Añadimos la ficha físicamente al GridPane
                if (game.someoneWin()) { // Si alguien gana después de colocar la ficha
                    System.out.println("Gana el Jugador%s".formatted(game.getTurn()));
                    // Mostrar una alerta o pantalla de victoria
                    Parent alertRoot = FXMLLoader.load(getClass().getResource("/gp/clasico/VOLVER A INICIAL.fxml"));
                    gridPane.add(alertRoot, 0, 0, gridPane.getColumnCount(), gridPane.getRowCount());
                    GridPane.setHalignment(alertRoot, HPos.CENTER);
                    GridPane.setValignment(alertRoot, VPos.CENTER);
                }
                else {
                   game.updateBot();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void colocarFichaContinuar(MouseEvent event) {
        Node source = (Node) event.getSource();
        Node parent = source;
        parent = parent.getParent();
        GridPane gridPane = (GridPane) parent.getParent();
        Integer columnaInteger = GridPane.getColumnIndex(parent);

        // Verificar si la columna es null y asignar 0 como valor predeterminado
        int columna = (columnaInteger != null) ? columnaInteger : 0;

        try {
            // Cargamos la ficha
            Parent ficha = FXMLLoader.load(getClass().getResource("/gp/FICHA JUGADOR %s.fxml".formatted(game.getTurn())));
            int fila = game.place(columna); // Suponemos que esto coloca la ficha lógicamente y devuelve la fila donde se colocó
            gridPane.add(ficha, columna, fila); // Añadimos la ficha físicamente al GridPane
            Position pos = new Position(columna, fila);
            game.addObject(new Piece(game, pos));
            if (game.someoneWin()) { // Si alguien gana después de colocar la ficha
                System.out.println("Gana el Jugador%s".formatted(game.getTurn()));
                // Mostrar una alerta o pantalla de victoria
                Parent alertRoot = FXMLLoader.load(getClass().getResource("/gp/clasico/VOLVER A INICIAL.fxml"));
                gridPane.add(alertRoot, 0, 0, gridPane.getColumnCount(), gridPane.getRowCount());
                GridPane.setHalignment(alertRoot, HPos.CENTER);
                GridPane.setValignment(alertRoot, VPos.CENTER);
            } else {
                game.update(); // Actualiza el estado del juego
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

