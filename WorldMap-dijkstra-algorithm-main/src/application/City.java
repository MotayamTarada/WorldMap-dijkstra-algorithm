//package application;
//
//import javafx.geometry.Insets;
//import javafx.scene.control.Label;
//import javafx.scene.control.RadioButton;
//import javafx.scene.control.ToggleGroup;
//import javafx.scene.control.Tooltip;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//
//public class City {
//	private double WIDTH = 1000;  
//	private double HEIGHT = 500;
//	private String name;
//	private double x;
//	private double y;
//	private double longitude;
//	private double latitude;
//	private RadioButton radioButton = new RadioButton();
//	private ToggleGroup group;
//	private Pane container ;
//	
//	public City() {
//		// TODO Auto-generated constructor stub
//	}
//
//	public City(String line) {
//		String[] arr = line.split(":");
//
//		this.name = arr[0];
//
//		this.latitude = Double.parseDouble(arr[1]);
//		this.longitude = Double.parseDouble(arr[2]);
//
//		radioButton.setToggleGroup(group);
//		radioButton.setPadding(new Insets(-7.5));
//		ImageView vi = new ImageView(new Image("location-pin (1).png"));
//		vi.setFitHeight(16);
//		vi.setFitWidth(16);
//		radioButton.setGraphic(vi);
//		Tooltip tip = new Tooltip(this.getName());
//		tip.setFont(new Font(16));
//		tip.setStyle("-fx-background-color:grey;");
//		generateLabel(this.getName());
//		radioButton.setTooltip(tip);
//		 Font fontCitiess = Font.font("Courier New", FontWeight.BOLD, 14);
//
//	        Label label = new Label(this.getName()); // Use the original city name
//	        
//	        label.setLayoutX(radioButton.getLayoutX() + 15); // Adjust the x-coordinate offset as needed
//	        label.setLayoutY(radioButton.getLayoutY() - 15); // Adjust the y-coordinate offset as needed
//	        label.setFont(fontCitiess);
//	        label.setTextFill(Color.BLACK);
//	        
//		radioButton.setOnAction(o -> {
//			if (Main.numOfPointChoice == 0) {
//				ImageView vi0 = new ImageView(new Image("location-pin.png"));
//				vi0.setFitHeight(16);
//				vi0.setFitWidth(16);
//				radioButton.setGraphic(vi0);
//			}
//			
//			if (Main.numOfPointChoice == 1) {
//				ImageView vi0 = new ImageView(new Image("location-pin (2).png"));
//				vi0.setFitHeight(16);
//				vi0.setFitWidth(16);
//				radioButton.setGraphic(vi0);
//			}
//			radioButton.setSelected(true);
//			Main.numOfPointChoice += 1;
//			if (Main.numOfPointChoice == 2) {
//				Main.lock();
//			}
//			
//			if (Main.click.isSelected()) {
//				if (Main.numOfPointChoice == 2) {
//					Main.targetText.getSelectionModel().select(getName());
//				}
//				if (Main.numOfPointChoice == 1) {
//					Main.scourseText.getSelectionModel().select(getName());
//				}
//			}
//
//		});
//	}
//	
//	 private Label generateLabel(String name) {
//
//	       
//	        radioButton.setGraphic(label);
//	        return label;
//	    }
//	 
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public double getLongitude() {
//		return longitude;
//	}
//
//	public void setLongitude(double longitude) {
//		this.longitude = longitude;
//	}
//
//	public double getLatitude() {
//		return latitude;
//	}
//
//	public void setLatitude(double latitude) {
//		this.latitude = latitude;
//	}
//
//	public RadioButton getRadioButton() {
//		return radioButton;
//	}
//
//	public void setRadioButton(RadioButton radioButton) {
//		this.radioButton = radioButton;
//	}
//
//	public ToggleGroup getGroup() {
//		return group;
//	} 
//
//	public void setGroup(ToggleGroup group) {
//		this.group = group;
//	}
//
//	public double getX() {
//		return  ((longitude + 180) / 360 * WIDTH);
//	} 
//
//	public void setX(double x) {
//		this.x = x;
//	}
//
//	public double getY() { 
//		return (HEIGHT - (latitude + 90) / 180 * HEIGHT);
//	}
//
//	public void setY(double y) {
//		this.y = y;
//	}
//
//}

package application;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class City {
	private double WIDTH = 1000;
	private double HEIGHT = 500;
	private String name;
	private double x;
	private double y;
	private double longitude;
	private double latitude;
	private RadioButton radioButton = new RadioButton();
	private ToggleGroup group;
	private Pane pane;

	public City() {
		// TODO Auto-generated constructor stub
	}

	public City(String line) {
		String[] arr = line.split(":");

		this.name = arr[0];

		this.latitude = Double.parseDouble(arr[1]);
		this.longitude = Double.parseDouble(arr[2]);

		radioButton.setToggleGroup(group);
		radioButton.setPadding(new Insets(-7.5));

		ImageView vi = new ImageView(new Image("location-pin (1).png"));
		vi.setFitHeight(12);
		vi.setFitWidth(12);

		pane = new Pane();
		pane.getChildren().add(vi);

		genrateLabel(this.getName());
		radioButton.setGraphic(pane);

		Tooltip tip = new Tooltip(this.getName());
		tip.setFont(new Font(16));
		tip.setStyle("-fx-background-color:grey;");
		radioButton.setTooltip(tip);

		radioButton.setOnAction(o -> {
			if (Main.numOfPointChoice == 0) {
				ImageView vi0 = new ImageView(new Image("location-pin.png"));
				vi0.setFitHeight(12);
				vi0.setFitWidth(12);
				pane.getChildren().set(0, vi0);
			}

			if (Main.numOfPointChoice == 1) {
				ImageView vi0 = new ImageView(new Image("location-pin (2).png"));
				vi0.setFitHeight(12);
				vi0.setFitWidth(12);
				pane.getChildren().set(0, vi0);
			}
			radioButton.setSelected(true);
			Main.numOfPointChoice += 1;
			
			if (Main.numOfPointChoice == 2) {
				Main.lock();
			}
			
				if (Main.numOfPointChoice == 2) {
					Main.targetText.getSelectionModel().select(getName());
				}
				if (Main.numOfPointChoice == 1) {
					Main.scourseText.getSelectionModel().select(getName());
				}
			
			
			
			
			
			
		});
	}

	public Label genrateLabel(String name) {

		Font fontCities = Font.font("Courier New", FontWeight.BOLD, 14);

		Label label = new Label(name);
		label.setFont(fontCities);
		label.setTextFill(Color.HONEYDEW);
		label.setLayoutX(20); // Adjust as necessary for proper position
		label.setLayoutY(-5); // Adjust as necessary for proper position
		pane.getChildren().add(label);

		return label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public RadioButton getRadioButton() {
		return radioButton;
	}

	public void setRadioButton(RadioButton radioButton) {
		this.radioButton = radioButton;
	}

	public ToggleGroup getGroup() {
		return group;
	}

	public void setGroup(ToggleGroup group) {
		this.group = group;
	}

	public double getX() {
		return ((longitude + 180) / 360 * WIDTH);
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return (HEIGHT - (latitude + 90) / 180 * HEIGHT);
	}

	public void setY(double y) {
		this.y = y;
	}


}
