
// Brick.java -------------------------------------------------------

package mario;


import java.applet.*;
import java.awt.*;
import mario.loaders.Map;

/**
 *
 */
public class Brick extends WorldObject {

	/* Permite que todos los objetos de esta misma clase
	 * puedan pasar de una imagen a otra todos sincronizados.
	 */
	protected static int indexClass = 0;

	/* Indica que es necesario actualizar el indexClass. */
	protected static boolean changeImg = false;

	/* Número de imágenes que representan al sprite. */
	public static int LENGHT_IMAGES = 4;

	/* Permite actualizar las imágenes para todos los objetos
	 * de esa clase. */
	public static void actClass() {
		if (changeImg) {
			indexClass = (indexClass+1)%LENGHT_IMAGES;
			changeImg = false;
		}
	}

	public static AudioClip[] audio;
	public static int indexAudio;
	public static boolean first = true;

	protected String imgNormal = "brick*_0";

	// Indica si el ladrillo está ya roto, hará
	// el efecto que le va a hacer desaparecer.
	protected boolean effect = false;

	// Indica si el ladrillo ha sido golpeado cuando
	// el jugador no tiene la capacidad de romperlos,
	// por lo tanto indica que el ladrillo se está moviendo.
	protected boolean moving = false;
	
	// Velocidad del movimiento.
	protected float movingSpeed = 1.5F;

	// Representa la posición inicial en la que se encuentra
	// y el la cual se quedará quieto el ladrillo cuando
	// realize se movimiento al ser golpeado
	protected float initY = 0;

	public Brick(Stage s) {
		super(s);
		supportsPlayer = true;
		setPreferredSize(Map.TILE_X_SIZE, Map.TILE_Y_SIZE);
		setImages(imgNormal, 0, 4);
		// Rectángulo para las colisiones
		