package UI;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MissileLauncherPane extends GridPane {

	public static String MISSILE_LAUNCHER_IMAGE = "MissileLauncher.png";

	public MissileLauncherPane() {
		// this.setHgap(10);
		// this.setVgap(10);
		this.setGridLinesVisible(true);
		this.setPadding(new Insets(0, 10, 0, 10));

		initMissileLauncherIcon(1, 1, 1);
		initMissileLauncherIcon(1, 1, 5);
	}

	private void initMissileLauncherIcon(int missileLauncherId, int row, int col) {

		Image temp = new Image(MissileLauncherPane.class.getResourceAsStream(MISSILE_LAUNCHER_IMAGE));
		ImageView image = new ImageView(temp);
		image.setFitHeight(50);
		image.setFitWidth(50);
		this.add(image, col, row);

	}

}
