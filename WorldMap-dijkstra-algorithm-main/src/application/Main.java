package application;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;

public class Main extends Application {
	public static File file;
	static ComboBox<String> scourseText = new ComboBox<String>();
	static ComboBox<String> targetText = new ComboBox<String>();
	static ToggleButton click = new ToggleButton("Click in map");
	static ToggleButton combo = new ToggleButton("Combo Box");
	static int numOfPointChoice = 0;
	static Pane pane2 = new Pane();
	private Alert error = new Alert(AlertType.ERROR);
	static TextArea textPath = new TextArea();
	Label labels;
	Label labelt;
	FileChooser fileChooser = new FileChooser();

	static ArrayList<vertex> Citys = new ArrayList<>();

	@Override
	public void start(Stage primaryStage) {
		Image m = new Image("World-Map.jpg");
		ImageView image = new ImageView(m);

		image.setFitHeight(519);
		image.setFitWidth(1002);
		pane2.getChildren().add(image);

		Label title = new Label("Flight path system");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
		title.setPadding(new Insets(15));
		file = new File("cities (1).txt");
		
		java.io.File selectedFile = fileChooser.showOpenDialog(primaryStage);
		if (selectedFile != null && selectedFile.exists()) {
		    file = selectedFile.getAbsoluteFile();
		    // Perform the read operation here
		    readFile(file);
		} else {
			 Alert alert = new Alert(AlertType.ERROR);
			    alert.setTitle("Error");
			    alert.setHeaderText(null);
			    if (selectedFile == null) {
			        alert.setContentText("No file selected.");
			    } else {
			        alert.setContentText("The selected file does not exist.");
			    }
			    alert.showAndWait();
		}
		
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(10));
		pane.setTop(title);
		pane.setStyle("-fx-background-image: url('bb.jpg');");

		BorderPane.setAlignment(title, Pos.CENTER);

		Label choose = new Label("Choose City by :");
		choose.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
		choose.setPadding(new Insets(15));
		ToggleGroup tg = new ToggleGroup();

		click.setToggleGroup(tg);
		combo.setToggleGroup(tg);

		// Style To Taggle group ;

		combo.setStyle("-fx-background-color: white; " + "-fx-text-fill: green; " + "-fx-font-size: 14px; "
				+ "-fx-border-color: lightgray; " + "-fx-border-width: 1px; " + "-fx-border-radius: 4px;");

		click.setStyle("-fx-background-color: white; " + "-fx-text-fill: red; " + "-fx-font-size: 14px; "
				+ "-fx-border-color: lightgray; " + "-fx-border-width: 1px; " + "-fx-border-radius: 4px;");

		combo.setOnMouseEntered(e -> {
			combo.setStyle("-fx-background-color: white; " + "-fx-text-fill: green; " + "-fx-font-size: 14px; "
					+ "-fx-border-color: green; " + "-fx-border-width: 2px; " + "-fx-border-radius: 5px; "
					+ "-fx-effect: dropshadow(gaussian, lightgray, 5, 0.5, 0, 0);");
		});

		combo.setOnMouseExited(e -> {
			combo.setStyle("-fx-background-color: white; " + "-fx-text-fill: green; " + "-fx-font-size: 14px; "
					+ "-fx-border-color: lightgray; " + "-fx-border-width: 1px; " + "-fx-border-radius: 4px;");
		});

		click.setOnMouseEntered(e -> {
			click.setStyle("-fx-background-color: white; " + "-fx-text-fill: red; " + "-fx-font-size: 14px; "
					+ "-fx-border-color: red; " + "-fx-border-width: 2px; " + "-fx-border-radius: 5px; "
					+ "-fx-effect: dropshadow(gaussian, lightgray, 5, 0.5, 0, 0);");
		});

		click.setOnMouseExited(e -> {
			click.setStyle("-fx-background-color: white; " + "-fx-text-fill: red; " + "-fx-font-size: 14px; "
					+ "-fx-border-color: lightgray; " + "-fx-border-width: 1px; " + "-fx-border-radius: 4px;");
		});

		scourseText.setOnAction(e -> {
			if (combo.isSelected()) {
				for (int i = 0; i < Citys.size(); i++) {
					if (Citys.get(i).getCity().getName().equals(scourseText.getSelectionModel().getSelectedItem())) {
						ImageView vi0 = new ImageView(new Image("location-pin.png"));
						vi0.setFitHeight(12);
						vi0.setFitWidth(12);

						// Create and configure the label with the city name
						labels = new Label(Citys.get(i).getCity().getName());
						labels.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
						labels.setTextFill(javafx.scene.paint.Color.HONEYDEW);

						// Create a container pane to hold the image and label
						Pane container = new Pane();
						container.getChildren().addAll(vi0, labels);

						// Position the label next to the image
						labels.setLayoutX(vi0.getFitWidth() + 5);
						labels.setLayoutY(0); // Adjust as needed

						Citys.get(i).getCity().getRadioButton().setGraphic(container);
						Citys.get(i).getCity().getRadioButton().setSelected(true);

						numOfPointChoice += 1;
						if (numOfPointChoice == 2) {
							lock();
						}
						break;
					}
				}
			}
		});

		targetText.setOnAction(e -> {
			if (combo.isSelected()) {
				for (int i = 0; i < Citys.size(); i++) {
					if (Citys.get(i).getCity().getName().equals(targetText.getSelectionModel().getSelectedItem())) {
						ImageView vi0 = new ImageView(new Image("location-pin (2).png"));
						vi0.setFitHeight(12);
						vi0.setFitWidth(12);

						// Create and configure the label with the city name
						labelt = new Label(Citys.get(i).getCity().getName());
						labelt.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
						labelt.setTextFill(javafx.scene.paint.Color.HONEYDEW);
						// Create a container pane to hold the image and label
						Pane container = new Pane();
						container.getChildren().addAll(vi0, labelt);

						// Position the label next to the image
						labelt.setLayoutX(vi0.getFitWidth() + 5);
						labelt.setLayoutY(0); // Adjust as needed

						Citys.get(i).getCity().getRadioButton().setGraphic(container);
						Citys.get(i).getCity().getRadioButton().setSelected(true);

						numOfPointChoice += 1;
						if (numOfPointChoice == 2) {
							lock();
						}
						break;
					}
				}
			}
		});

		HBox hx = new HBox(10, combo);
		hx.setAlignment(Pos.CENTER);
		hx.setPadding(new Insets(3));

		HBox h = new HBox(choose, hx);
		h.setAlignment(Pos.CENTER);

		Label scourse = new Label("Sourse :");
		scourse.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");

		scourse.setPadding(new Insets(7));
		scourseText.setMinWidth(150);
		for (int i = 0; i < Citys.size(); i++) {
			scourseText.getItems().add(Citys.get(i).getCity().getName());
		}

		HBox h1 = new HBox(scourse, scourseText);
		h1.setAlignment(Pos.CENTER);

		Label target = new Label("Target :");
		target.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
		target.setPadding(new Insets(7));
		for (int i = 0; i < Citys.size(); i++) {
			targetText.getItems().add(Citys.get(i).getCity().getName());
		}
		targetText.setMinWidth(150);

		HBox h2 = new HBox(target, targetText);
		h2.setAlignment(Pos.CENTER);

		Button run = new Button("Run");
		Button reset = new Button("Reset");

		HBox butBox = new HBox(20, run, reset);
		butBox.setAlignment(Pos.CENTER);

		Label path = new Label("Shortest Path :");
		path.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
		path.setPadding(new Insets(7));
		path.setMinHeight(200);
		path.setPadding(new Insets(7));

		textPath.setMaxHeight(200);
		textPath.setMaxWidth(300);
		textPath.setEditable(false);

		HBox h3 = new HBox(path, textPath);
		h3.setAlignment(Pos.CENTER);

		Label distance = new Label("Distance :");
		distance.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
		distance.setPadding(new Insets(7));
		TextField distanceText = new TextField();

		HBox h4 = new HBox(distance, distanceText);
		h4.setAlignment(Pos.CENTER);

		VBox v = new VBox(30, h, h1, h2, butBox);
		v.setAlignment(Pos.CENTER);
		v.setPadding(new Insets(10));

		VBox v1 = new VBox(30, h3, h4);
		v1.setAlignment(Pos.CENTER);
		v1.setPadding(new Insets(10));

		VBox mix = new VBox(10, v, v1);
		mix.setAlignment(Pos.CENTER);

		VBox Vmap = new VBox(pane2);
		Vmap.setAlignment(Pos.CENTER);

		HBox mainBox = new HBox(20, Vmap, mix);
		mainBox.setAlignment(Pos.CENTER);

		pane.setCenter(mainBox);

		run.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;"
				+ " -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0.5, 0, 0);");
		reset.setStyle("-fx-background-color: #ff0000; -fx-text-fill: white; -fx-font-size: 14px;"
				+ " -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0.5, 0, 0);");

		run.setOnAction(e -> {
			vertex vertx1 = null;
			vertex vertx2 = null;
			String s1 = scourseText.getValue();
			String s2 = targetText.getValue();
			
			for (int i = 0; i < Citys.size(); i++) {
				if (Citys.get(i).getCity().getName().equals(s1)) {
					vertx1 = Citys.get(i);
				}
				if (Citys.get(i).getCity().getName().equals(s2)) {
					vertx2 = Citys.get(i);
				}
			}

			if (vertx1 != null && vertx2 != null) {
				int i = drowLine(Dijekstra(vertx1, vertx2));
				if (i == 0)
					distanceText.setText("0");
				else if (i == 1) {
					distanceText.setText(String.valueOf(vertx2.distance));
				}
			}

		});

		reset.setOnAction(l -> {
			
			pane2.getChildren().clear();
			
		   labels.setText("");
		   labelt.setText("");
		   

			targetText.getSelectionModel().select(null);
			scourseText.getSelectionModel().select(null);
			distanceText.setText("");
			textPath.setText(" ");
			numOfPointChoice = 0;
			new City();
			
			pane2.getChildren().add(image);

			// Reset cities that were visited during the last operation
			for (int i = 0; i < Citys.size(); i++) {

				if (Citys.get(i).visited) {
					Citys.get(i).visited = false;
					Citys.get(i).previous = null;
				}
			}
			free();
			readFile(file);

			addPoint();
		});

		addPoint();

		Scene scene = new Scene(pane, 1535, 800);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private int drowLine(vertex Destination) {
		if (Destination == null) {
			
			textPath.setText("No Path");
			return 0;
		} else {
			List<vertex> p = new ArrayList<>();
			for (vertex v = Destination; v != null; v = v.previous) {
				
				p.add(v);
			}
			System.out.println();
			// V
			Collections.reverse(p);
			printPathToTextArea(p); // Print path in text area

			if (p.size() <= 1) {
				textPath.setText("No Path");
				
			}

			for (int i = 0; i < p.size() - 1; i++) {
				vertex u = p.get(i); // source 
				vertex v = p.get(i + 1); // destination 
				
				if (i != 0 && i != p.size() - 1) {
					ImageView vi0 = new ImageView(new Image("location-pin (4).png"));
					vi0.setFitHeight(12);
					vi0.setFitWidth(12);
					u.getCity().getRadioButton().setGraphic(vi0);
				}

				Line line = new Line(u.city.getX(), u.city.getY(), v.city.getX(), v.city.getY());

				pane2.getChildren().add(line); // add line to pane map 
			}
		}
		return 1;

	}

	private void addPoint() {
		for (int i = 0; i < Citys.size(); i++) {
			RadioButton r = Citys.get(i).getCity().getRadioButton();
			r.setLayoutX(Citys.get(i).getCity().getX());
			r.setLayoutY(Citys.get(i).getCity().getY());
			pane2.getChildren().add(r);
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void lock() {
		try {
			for (int i = 0; i < Citys.size(); i++) {
				Citys.get(i).getCity().getRadioButton().setDisable(true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void free() {
		try {
			for (int i = 0; i < Citys.size(); i++) {
				Citys.get(i).getCity().getRadioButton().setDisable(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public vertex Dijekstra(vertex Source, vertex Destination) {// O(n) = (V(logV+E))
		Source.distance = 0;
		if (Source == Destination) {
			return null;
		}

		MinHeap<vertex> heap = new MinHeap<>();

		heap.add(Source);

		while (!heap.isEmpty()) { // V
			vertex u = heap.poll(); //// Log V

			u.visited = true;
			
			if (u.city.getName().equals(Destination.getCity().getName())) { //  source if goal 
				break;
			}
			
			for (edges e : u.getE()) { // E  
				vertex v = e.desination;
				if (!v.visited) {
					double weight = e.weight; 
					double distanceThroughU = u.distance + weight;
					if (distanceThroughU < v.distance) {
						v.distance = distanceThroughU;
						v.previous = u;
						heap.add(v);
					}
				}
			}
		}

		return Destination;
	}

	public void readFile(File file) {
		try {
			Scanner sc = new Scanner(file);
			String[] l = sc.nextLine().split(":");
			int numCounter = Integer.parseInt(l[0]); // number of city 
			int numEdge = Integer.parseInt(l[1]); // number of edges 
			int count = 0;
			int num = 0;

			while (count < numCounter - 1) {
				String line = sc.nextLine();
				System.out.println(line);
				vertex ver = new vertex(new City(line), num++); // num -> number of city
				Citys.add(ver);
				count++;

			}
			// Fill the Edges ;
			count = 0;
			while (count < numEdge) {
				String tokens[] = sc.nextLine().split(":");
				for (int i = 0; i < Citys.size(); i++) {
					if (Citys.get(i).getCity().getName().compareToIgnoreCase(tokens[0]) == 0) { // compate the data // 
						for (int j = 0; j < Citys.size(); j++) {

							if (Citys.get(j).getCity().getName().compareToIgnoreCase(tokens[1]) == 0) {

								Citys.get(i).e.add(
										new edges(Citys.get(i), Citys.get(j), Distance(Citys.get(i), Citys.get(j))));
								Citys.get(j).e.add(
										new edges( Citys.get(j),Citys.get(i), Distance(Citys.get(j), Citys.get(i))));
							}

						}
					}
				}
				count++;
			}
			sc.close();
		} catch (FileNotFoundException t) {
			System.out.println(t);
		}
	}

	private void printPathToTextArea(List<vertex> path) {
		StringBuilder sb = new StringBuilder();
		for (vertex v : path) {
			sb.append(v.city.getName()).append(" -> ");
		}
		// Remove the last " -> "
		if (sb.length() > 0) {
			sb.setLength(sb.length() - 4);
		}
		textPath.setText(sb.toString());
	}
	
	// Haversine low هافيرسين
	public double Distance(vertex a, vertex b) {

		final int EARTH_RADIUS = 6371;
		double lat1Rad = Math.toRadians(a.getCity().getLatitude());
		double lat2Rad = Math.toRadians(b.getCity().getLatitude());
		double deltaLat = Math.toRadians(b.getCity().getLatitude() - a.getCity().getLatitude());
		double deltaLon = Math.toRadians(b.getCity().getLongitude() - a.getCity().getLongitude());

		double dis = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
				+ Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(dis), Math.sqrt(1 - dis));

		return EARTH_RADIUS * c;

	}
}
