// PlatformGame.java -------------------------------------------------

package mario;


import java.awt.*;
import java.awt.event.*;

import mario.loaders.ImagesLoader;
import mario.loaders.Map;
import mario.loaders.SoundsLoader;


/**
 *
 */
public class Main extends Stage {

	// For load images, sounds and maps.
	private ImagesLoader loader;
	private SoundsLoader sounds;
	private Map map;

	// Gravedad del escenario. Defecto 0.2
	private float gravity = 0.2F;

	Point pointCursor = new Point(-1,-1);

	public Main(boolean applet) {
		super(CANVAS);
		setFPS(80);
		setSize(960, 640);
		// Creamos el mapa en el mundo=1 nivel=1.
		map = new Map(this, 1, 1);
		// Creamos los cargadores pero de momento
		// no cargamos nada.
		loader = new ImagesLoader("res/img", "loader");
		sounds = new SoundsLoader("res/sounds", "loader");
		// Añadimos los cargadores de sonido y de
		// imagen a el objeto Stage (superclase).
		setImagesLoader(loader);
		setSoundsLoader(sounds);
	}

	public Main() {
		super(JFRAME);
		setFPS(80);
		setSize(960-6, 640-6);
		window.setResizable(false);
		// Creamos el mapa en el mundo=1 nivel=1.
		map = new Map(this, 1, 1);
		// Creamos los cargadores pero de momento
		// no cargamos nada.
		loader = new ImagesLoader("res/img", "loader");
		sounds = new SoundsLoader("res/sounds", "loader");
		// Añadimos los cargadores de sonido y de
		// imagen a el objeto Stage (superclase).
		setImagesLoader(loader);
		setSoundsLoader(sounds);
	}
	
	public synchronized void initStage() {
		// Cargamos las imágenes y los sonidos
		// que están indicados en el archivo externo.
		loader.startLoader();
		sounds.startLoader();
		
		// Iniciamos el mapa.
		