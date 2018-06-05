package UI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class MissileLauncherView extends BorderPane {
	public static String MISSILE_LAUNCHER_IMAGE = "MissileLauncher.png";
	private String id;


	public MissileLauncherView(String id) {
		Text txtName = new Text(id);
		Image temp = new Image(
				MissileLauncherView.class
						.getResourceAsStream(MISSILE_LAUNCHER_IMAGE));
		ImageView image = new ImageView(temp);
		image.setFitHeight(38);
		image.setFitWidth(50);
		this.setTop(image);
		//this.setBottom(txtName);
		
	}	

}
