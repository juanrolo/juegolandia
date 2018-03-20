
// Background.java --------------------------------------------------

package mario;


import java.awt.*;
import java.awt.image.*;
import mario.loaders.Map;

/**
 *
 */
public class Background {

	public static final int IMG_DIMENSIONS = -1;

	protected Stage stage;
	protected Map map;

	protected double x, y, speedFactor, speedX, speedY;

	protected float alpha;

	protected int width, height;

	protected BufferedImage img;

	protected double defaultSpeed = 0;

	/**
	 *
	 */
	public Background(Stage s, String imgName) {
		this(s, imgName, 1.0, 0, IMG_DIMENSIONS, IMG_DIMENSIONS, 1.0F);
	}

	public Background(Stage s, String imgName, double speedFactor,
					double defaultSpeed, int width, int height,
					float alpha) {
		this.stage = s;
		this.map = ((Main)s).getCurrentMap();
		this.width = (width != IMG_DIMENSIONS) ?
			width : IMG_DIMENSIONS;
		this.height = (height != IMG_DIMENSIONS) ?
			height : IMG_DIMENSIONS;
		setImage(imgName);
		this.x = 0;
		this.y = 0;
		this.alpha = alpha;
		this.defaultSpeed = defaultSpeed;
		this.speedFactor = speedFactor;
	}

	// SET methods --------------------------------------------------
	public void setX(double x) {
		this.x = x%width;
	}

	public void setY(double y) {
		this.y = y%height;
	}

	private void setImage(String imgName) {
		setImage(stage.getImagesLoader().getImage(imgName));
	}

	