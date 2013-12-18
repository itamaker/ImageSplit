import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Main {

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File imageFile = new File("2.png");
		File out = new File("E:/out");
		splitImage(imageFile, out, 256, 256);
	}
	
	public static void splitImage(File imageFile,File outDir,int split_width,int split_height){
		try {
			BufferedImage image = ImageIO.read(imageFile);
			//save to file "startx,starty,sizex,sizey,level.png"
			final int width = image.getWidth();
			final int height = image.getHeight();
			int sizex = split_width;
			int sizey = split_height;
			int level = 0;
			for(int startx = 0;startx < width;startx+=split_width){
				for(int starty =0;starty < height;starty+=split_height){
					if(width - startx < split_width){
						sizex = width - startx;
					} else {
						sizex = split_width;
					}
					if(height - starty < split_height){
						sizey = height - starty;
					} else {
						sizey = split_height;
					}
					BufferedImage sub = image.getSubimage(startx, starty, sizex, sizey);
					String outName = startx + "," + starty+","+sizex+","+sizey+","+level+".png";
					File out = new File(outDir, outName);
					ImageIO.write(sub, "png", out);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
