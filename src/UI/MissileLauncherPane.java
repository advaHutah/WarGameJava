package UI;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class MissileLauncherPane extends BorderPane {

	public static String MISSILE_LAUNCHER_IMAGE = "MissileLauncher.png";
	
	private int missileLauncherId;
	
	private Button btnMoveToOtherTribe;
	


	public MissileLauncherPane(GameApplication theApplication, int missileLauncherId) {
		this.missileLauncherId = missileLauncherId;

		initLabelAndIcon(missileLauncherId);


		this.setBottom(btnMoveToOtherTribe);

		
	}

	

	private void initLabelAndIcon(int missileLauncherId) {
		StackPane stack = new StackPane(); // put data in layers
		 
		VBox vbox = new VBox();
		
		Text txtName = new Text(String.valueOf(missileLauncherId));
	    txtName.setId("missileLauncherId"); 
	   
	    Image temp = new Image(
				MissileLauncherPane.class
						.getResourceAsStream(MISSILE_LAUNCHER_IMAGE));
		ImageView image = new ImageView(temp);
		
		vbox.setAlignment(Pos.CENTER);
	    vbox.getChildren().add(txtName);
	    vbox.getChildren().add(image);
		
		Rectangle background = new Rectangle(100.0, 80.0);
		background.setArcHeight(3.5);
		background.setArcWidth(3.5);
		background.setId("MissileLauncher-background");

	    stack.getChildren().addAll(background, vbox); // put in layers
	    stack.setAlignment(Pos.CENTER);    
	
	    this.setCenter(stack);
	    
	    
	}
	
	
}
