package UI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class MissileLauncherDestructorView extends BorderPane {
	public static String MISSILE_LAUNCHER_DESTRUCTOR_PLAIN = "airplan.png";
	public static String MISSILE_LAUNCHER_DESTRUCTOR_SHIP = "ship.png";

	private String id;

	public MissileLauncherDestructorView(String id, String imageType) {
		this.id = id;
		Text txtName = new Text(id);
		Image temp = null;
		if (imageType.equals("plane")) {
			temp = new Image(MissileLauncherView.class.getResourceAsStream(MISSILE_LAUNCHER_DESTRUCTOR_PLAIN));
		} else if (imageType.equals("ship"))
			temp = new Image(MissileLauncherView.class.getResourceAsStream(MISSILE_LAUNCHER_DESTRUCTOR_SHIP));
		ImageView image = new ImageView(temp);
		image.setFitHeight(38);
		image.setFitWidth(50);
		this.setTop(image);
		this.setBottom(txtName);
	}
}
