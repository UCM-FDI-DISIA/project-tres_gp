package gp.clasico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import gp.GameObjects.Piece;
import gp.logic.Game;
import gp.logic.Position;
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
    private List<GameMemento> savedStates;
    @FXML
    private MenuItem btnVolver;
    
    @FXML
    private MenuItem btnRestart;
    
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
    
    private ServerSocket servidorSocket;
    private Socket clienteSocket;
    private PrintWriter outputToServer;
    private BufferedReader inputFromServer;
    private static final int PUERTO = 12345;
    public selectClassic() {
        this.game = new Game();
        this.savedStates = new ArrayList<>();
    }
    
    private static class GameMemento{
    	private final Game game;
    	
    	public GameMemento(Game game) {
    		this.game = game;
    	}
    	
    	public Game getSavedGame() {
    		return game;
    	}
    }

    @FXML
    private void colocarFicha(MouseEvent event) {
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

    @FXML
    void switchToScene2(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/gp/SEGUNDA PORTADA.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
        cerrarConexion();
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
    
   

    @FXML
    private void conectarAlServidor(ActionEvent event) {
        new Thread(() -> {
            try {
                servidorSocket = new ServerSocket(PUERTO);
                System.out.println("Servidor escuchando en la dirección: " + servidorSocket.getInetAddress().getHostAddress());
                System.out.println("Puerto: " + servidorSocket.getLocalPort());
                System.out.println(servidorSocket);
                clienteSocket = servidorSocket.accept();
                System.out.println("Conexión entrante desde: " + clienteSocket.getInetAddress().getHostAddress());

                // Inicializar outputToServer e inputFromServer
                outputToServer = new PrintWriter(clienteSocket.getOutputStream(), true);
                inputFromServer = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @FXML
    private void colocarFichaOnline(MouseEvent event) {
        Node source = (Node) event.getSource();
        Node parent = source;
        parent = parent.getParent();
        GridPane gridPane = (GridPane) parent.getParent();
        Integer columnaInteger = GridPane.getColumnIndex(parent);

        // Verificar si la columna es null y asignar 0 como valor predeterminado
        int columna = (columnaInteger != null) ? columnaInteger : 0;

        try {
            // Verificar si el socket está conectado
            if (servidorSocket != null && !servidorSocket.isClosed()) {
                // Envía la columna donde se colocó la ficha al servidor

                // Cargamos la ficha localmente
                Parent ficha = FXMLLoader.load(getClass().getResource("/gp/FICHA JUGADOR %s.fxml".formatted(game.getTurn())));

                // Obtener la fila donde se coloca la ficha
                int fila = game.place(columna);
                outputToServer.println(columna + " " + fila);
                outputToServer.flush();
                // Agregar la ficha al GridPane localmente
                gridPane.add(ficha, columna, fila);

                // Actualizar el estado del juego local
                Position pos = new Position(columna, fila);
                game.addObject(new Piece(game, pos));

                // Recibe el estado actualizado del juego desde el servidor
                String gameState = inputFromServer.readLine();
                String[] gameStateParts = gameState.split(" ");

                int filaServidor = Integer.parseInt(gameStateParts[0]);
                int columnaServidor = Integer.parseInt(gameStateParts[1]);

                // Cargar la ficha en el GridPane basado en el estado del servidor
                Parent fichaServidor = FXMLLoader.load(getClass().getResource("/gp/FICHA JUGADOR %s.fxml".formatted(game.getTurn())));
                gridPane.add(fichaServidor, columnaServidor, filaServidor);

                // Actualizar el estado del juego basado en el estado del servidor
                Position posServidor = new Position(columnaServidor, filaServidor);
                game.addObject(new Piece(game, posServidor));

                // Si alguien gana después de colocar la ficha
                if (game.someoneWin()) {
                    System.out.println("Gana el Jugador%s".formatted(game.getTurn()));
                    // Mostrar una alerta o pantalla de victoria
                    Parent alertRoot = FXMLLoader.load(getClass().getResource("/gp/clasico/VOLVER A INICIAL.fxml"));
                    gridPane.add(alertRoot, 0, 0, gridPane.getColumnCount(), gridPane.getRowCount());
                    GridPane.setHalignment(alertRoot, HPos.CENTER);
                    GridPane.setValignment(alertRoot, VPos.CENTER);
                    cerrarConexion();
                } else {
                    game.update(); // Actualiza el estado del juego
                }
            } else {
                // Mostrar un mensaje de error si no se puede conectar al servidor
                System.err.println("Pulsa el botón Conectar del Menú");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cerrarConexion() {
        try {
            if (inputFromServer != null) {
                inputFromServer.close();
            }
            if (outputToServer != null) {
                outputToServer.close();
            }
            if (clienteSocket != null) {
                clienteSocket.close();
            }
            if (servidorSocket != null) {
                servidorSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    //Guardar el estado actual del juego
	private void guardarPartida() {
    	savedStates.add(new GameMemento(game));
    }
    
    @FXML
    //Restaurar el estado de la partida
	private void restaurarPartida() {
    	if(!savedStates.isEmpty()) {
    		GameMemento memento = savedStates.remove(savedStates.size()-1);
    		game = memento.getSavedGame();
    	}else {
    		System.out.println("No hay partidas que continuar");
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
}

