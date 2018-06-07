package UI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class MissileDestructorView extends BorderPane {
	public static String MISSILE_DESTRUCTOR_IMAGE = "MissileDestructor_burned.png";
	private String id;

	public MissileDestructorView(String id) {
		this.id = id;
		Text txtName = new Text(id);
		Image temp = new Image(MissileLauncherView.class.getResourceAsStream(MISSILE_DESTRUCTOR_IMAGE));
		ImageView image = new ImageView(temp);
		image.setFitHeight(38);
		image.setFitWidth(50);
		this.setTop(image);
		this.setBottom(txtName);

	}
}
