
// Coin.java ---------------------------------------------------------

package mario;


import java.applet.*;


/**
 *
 */
public class Coin extends WorldObject {

	/* Permite que todos los objetos de esta misma clase
	 * puedan pasar de una imagen a otra todos sincronizados.
	 */
	protected static int indexClass = 0;

	/* Indica que es necesario actualizar el indexClass. */
	protected static boolean changeImg = false;

	/* Número de imágenes que representan al sprite. */
	