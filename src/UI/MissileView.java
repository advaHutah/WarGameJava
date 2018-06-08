package UI;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

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
	
	public void MissileAnimation(int duration,MissileLauncherView from){
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(duration));
		transition.setFromX(from.getScaleX());
		transition.setToX(100);
		transition.setNode(this);
		transition.play();
	}
}

