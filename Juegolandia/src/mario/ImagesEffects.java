
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
			