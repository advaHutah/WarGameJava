package UI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class TreeView  extends BorderPane{
	public static String TREE_IMAGE = "deciduous-tree.png";

	public TreeView() {
		Image temp = new Image(MissileLauncherView.class.getResourceAsStream(TREE_IMAGE));
		ImageView image = new ImageView(temp);
		image.setFitHeight(38);
		image.setFitWidth(50);
		this.setTop(image);
	}
}
