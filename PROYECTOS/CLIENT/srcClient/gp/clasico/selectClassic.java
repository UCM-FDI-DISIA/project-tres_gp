package gp.clasico;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
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

public class selectClassic {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private boolean esMiTurno = false;
    @FXML
    private MenuItem btnConectar;

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
    private Button btnFicha51;

    @FXML
    private MenuItem btnRestart;

    @FXML
    private MenuItem btnVolver;

    @FXML
    private MenuButton menuButton;
    
    @FXML
    private GridPane gridPane;
    
    private Socket socket;
    private DataInputStream fromServer;
    private DataOutputStream toServer;
    private boolean ganado = false;
    private boolean conectado = true;
    @FXML
    void switchToSceneInicial(ActionEvent event) throws IOException {
        MenuItem menuItem = (MenuItem) event.getSource(); // Obtener el MenuItem
        Parent parent = (Parent) menuItem.getParentPopup().getOwnerNode(); // Obtener el nodo padre del menú emergente
        Scene scene = parent.getScene(); // Obtener la escena
        Stage stage = (Stage) scene.getWindow(); // Obtener el Stage
        root = FXMLLoader.load(getClass().getResource("/gp/PORTADA INICIAL.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
        desconectar();
    }

    @FXML
    void switchToSceneInicialExit(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/gp/PORTADA INICIAL.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        desconectar();
    }
    @FXML
    void switchToTableroNormal(ActionEvent event) throws IOException {
        MenuItem menuItem = (MenuItem) event.getSource(); // Obtener el MenuItem
        Parent parent = (Parent) menuItem.getParentPopup().getOwnerNode(); // Obtener el nodo padre del menú emergente
        Scene scene = parent.getScene(); // Obtener la escena
        Stage stage = (Stage) scene.getWindow(); // Obtener el Stage
        root = FXMLLoader.load(getClass().getResource("TABLERO CLASSIC ONLINE.fxml"));
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
    private void conectar(ActionEvent event) {
        try {
            socket = new Socket("localhost", 12345);
            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());
            // Iniciar un hilo para recibir y procesar las jugadas del servidor
            Thread receiverThread = new Thread(() -> {
					receiveMovesFromServer();
			});
            receiverThread.start(); // Iniciar el hilo para recibir jugadas del servidor
            System.out.println("Conectado al servidor.");
            
        } catch (IOException e) {
            System.out.println("Error al conectar con el servidor: " + e.getMessage());
        }
    }

    @FXML
    private void colocarFicha(MouseEvent event) {
    	if(toServer == null)
    		System.out.println("No hay conexión con el servidor");
    	else {
	    	if(!ganado) {
		        Node source = (Node) event.getSource();
		        Node parent = source.getParent();
		        Integer columnaInteger = GridPane.getColumnIndex(parent);
		        int columna = (columnaInteger != null) ? columnaInteger : 0;
		
		        try {
		            toServer.writeInt(columna); // Enviamos la columna al servidor
		            toServer.flush(); // Aseguramos que los datos se envíen inmediatamente
		
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
	    	}
	    	else {
	    		try {
					endMessage();
					desconectar();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
    	}
    }
    
    protected void endMessage() throws IOException {
    	Parent alertRoot = FXMLLoader.load(getClass().getResource("VOLVER A INICIAL.fxml"));
		gridPane.add(alertRoot, 0, 0, gridPane.getColumnCount(), gridPane.getRowCount());
        GridPane.setHalignment(alertRoot, HPos.CENTER);
        GridPane.setValignment(alertRoot, VPos.CENTER);
    }
    
    private void receiveMovesFromServer() {
        try {
            while (conectado) {
            	ganado = fromServer.readBoolean();
            	if(!ganado) {
	                int filaServidor = fromServer.readInt();    // Leer la fila de la jugada
	                int columnaServidor = fromServer.readInt(); // Leer la columna de la jugada
	                int turnoServidor = fromServer.readInt();
	                esMiTurno = (turnoServidor == 1);
	                if(esMiTurno) {
			                // Agenda la actualización de la interfaz de usuario en el hilo de JavaFX
			            Platform.runLater(() -> {
			                try {
			                    Parent fichaServidor = FXMLLoader.load(getClass().getResource("/gp/FICHA JUGADOR %s.fxml".formatted(turnoServidor)));
			                    gridPane.add(fichaServidor, columnaServidor, filaServidor);
			                } catch (IOException e) {
			                        e.printStackTrace();
			                }
			            });
	                }
            	}
            	else {
            		int completo = fromServer.readInt();
            		if(completo == 0) {
            			receiveWinnersFromServer();
            		}
            		
            	}
            }
        } catch (IOException e) {
        	desconectar();
            // Se ha cerrado la conexión del socket, probablemente porque el cliente se desconectó
        	e.printStackTrace();
        }
    }
        
    private void receiveWinnersFromServer() {
            try {
                int numSets = fromServer.readInt(); // Lee el número de conjuntos de ganadores
                for (int i = 0; i < numSets; i++) {
                    int setSize = fromServer.readInt(); // Lee el tamaño de este conjunto de ganadores
                    for (int j = 0; j < setSize; j++) {
                        int fila = fromServer.readInt(); // Lee la fila de la posición ganadora
                        int columna = fromServer.readInt(); // Lee la columna de la posición ganadora
                        Platform.runLater(() -> {
                            try {
                                Parent fichaServidor = FXMLLoader.load(getClass().getResource("/gp/FICHA GANADORA.fxml"));
                                gridPane.add(fichaServidor, columna, fila);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                }
            } catch (IOException e) {
                System.out.println("Error al recibir la lista de ganadores del servidor: " + e.getMessage());
                e.printStackTrace();
            }
    }


    @FXML
    private void desconectar() {
        try {
            if (socket != null && !socket.isClosed()) {
            	conectado = false;
                socket.close();
            }
            System.out.println("Desconectado del servidor.");
        } catch (IOException e) {
            System.out.println("Error al desconectar: " + e.getMessage());
        }
    }

}

