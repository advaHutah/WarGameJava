package UI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class MissileView  extends BorderPane{
	public static String MISSILE_IMAGE = "missile.png";
	private String id;

	public MissileView(String id) {
		this.id = id;
		Text txtName = new Text(id);
		Image temp = new Image(MissileLauncherView.class.getResourceAsStream(MISSILE_IMAGE));
		ImageView image = new ImageView(temp);
		image.setFitHeight(38);
		image.setFitWidth(50);
		this.setTop(image);
		this.setBottom(txtName);
	}
}
