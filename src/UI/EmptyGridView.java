package UI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EmptyGridView extends ImageView{
	public static String BLANK_IMAGE = "Blank.png";


	public EmptyGridView() {
		Image temp = new Image(EmptyGridView.class.getResourceAsStream(BLANK_IMAGE));
		this.setImage(temp);
		this.setFitHeight(50);
		this.setFitWidth(50);
		
	}

	
}
