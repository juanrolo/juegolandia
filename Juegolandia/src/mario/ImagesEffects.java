
// ImagesEfects.java ------------------------------------------------

package mario;

import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;

import mario.loaders.ImagesLoader;
/**
 *
 */
public class ImagesEffects {

	protected ImagesLoader imgLoader;

	/**
	 *
	 */
	public ImagesEffects(ImagesLoader loader) {
		this.imgLoader = loader;
	}

	public void paintAlphaImg(Graphics g,
		String name, int x, int y, int w,
		int h, float alpha) {
			this.paintAlphaImg(g,
				imgLoader.getImage(name), x, y,
				w, h, alpha);
	}

	public void paintAlphaImg(Graphics g,
		BufferedImage img, int x, int y,
		int w, int h, float alpha) {
			Graphics2D g2 = (Graphics2D)g;
			Composite c = g2.getComposite();
			g2.setComposite(
				AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, alpha));
			g2.drawImage(img, x, y, w, h, null);
			g2.setComposite(c);
	}

	public void paintAlphaImg(Graphics g,
		BufferedImage img, int dx1, int dy1,
		int dx2, int dy2, int sx1, int sy1,
		int sx2, int sy2, float alpha) {
			Graphics2D g2 = (Graphics2D)g;
			Composite c = g2.getComposite();
			g2.setComposite(
				AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, alpha));
			g2.drawImage(img, dx1, dy1, dx2, dy2,
				sx1, sy1, sx2, sy2, null);
			g2.setComposite(c);
	}

	public BufferedImage returnAlphaImg(BufferedImage img, float alpha) {
		BufferedImage image = new BufferedImage(
			img.getWidth(), img.getHeight(), img.getType());
		Graphics2D g2 = (Graphics2D)image.getGraphics();
		g2.setComposite(
			AlphaComposite.getInstance(
			AlphaComposite.SRC_OVER, alpha));
		g2.drawImage(img, 0, 0, image.getWidth(),
			img.getHeight(), null);
		return image;
	}

	public BufferedImage returnRescaleImg(
		BufferedImage img, int w, int h) {
		System. out . println ( img . getType()) ;
		Bufferedlmage image = new Bufferedlmage (
		w, h , img . getTypeQ ) ;
		Graphics g = image. getGraphics () ;
		g. drawlmage (img , 0, 0, w , h , null ) ;
		return image ;
		}
	
		public Bufferedlmage returnRotatedlmgC
		Bufferedlmage img , int degrees) {
		return returnRotatedImg (img , Math .toRadians(degrees)) ;
		}
		
		public void paintShearlmg (Graphics g ,
		String name , int x , int y , int w, int h ,
		double shx , double shy) {
		Graphics2D g2 = (Graphics2 D) g ;
		g 2 . shear (shx , shy) ;
		g2 . drawImage( imgLoader. getlmage(name) ,
		x , y , w , h , null ) ;
		g 2 . di sposeQ ;
		}
		
		public void paintRotatedlmg(Graphics g ,
		String name , int x , int y , int w,
		int h , int degrees) {
		Graphics2D g2 = (Graphics2 D) g ;
		/*RenderingHints rh = new RenderingHints(
		Rende ringHints. KEY_ANTIALIASING ,
		RenderingHints. VAL U E_ANTIALIAS_ON) ;
		rh . put ( RenderingHints. KEY_ RENDERING ,
		RenderingHints. VALUE_RENDER_QUALITY) ;
		g2 . setRende ringHints ( rh) ;*/
		AffineTransform original = g2. getTransform () ;
		AffineTransform rotate = new AffineTransform () ;
		rotate. rotate (Math. toRadians(degrees) , x+w/2 , y+ h/2) ;
		g2 . transform( rotate) ;
		g2 . drawImage( imgLoader. getlmage(name) , x , y , w , h , null ) ;
		g2 .transform(original ) ;
		g2 . dispose () ;
		}
		
		public Bufferedlmage returnRotatedImg (
		Bufferedlmage img , double radians) {
		Bufferedlmage image = new Bufferedlmage (
		img . getwidth () , img. getHeight () ,
		img . getColorModel () . getTransparencyO ) ;
		Graphics2D g2 = (Graphics2 D) image. getGraphics() ;
		AffineTransform rotation = new AffineTransform() ;
		rotation. rotate( radians ,
		img . getwidth0 / 2 , img . getHei ght0/2) ;
		g2 .transform( rotation) ;
		return image;
		}
		
	}//fin de la clase ImagesEfects

	//fin de ImagesEfects.java
	