import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
public class TextureSource {
	BufferedImage friskimg[] = new BufferedImage[4];
	BufferedImage goldenflowerspawn;
	BufferedImage room1floor;
	BufferedImage Undertaletitle;
	BufferedImage heart;
	
	// boolean title;

	//public TextureSource(boolean title) {
	//	this.title = title;
	//}
	
	
	
	Font smallFont = new Font ("Courier New", 1, 18);
	Font medFont = new Font ("Courier New", 1, 30);
	Font bigFont = new Font ("Courier New", 1, 60);
	Font hugeFont = new Font ("Courier New", 1, 90);
	String prefix="resources/";
	public TextureSource(){
		//Load complex images
		
		//Load simple images
		
		//loading=makeImage(prefix+"loading.png");
		//friskimg=makeImage("friskimg.png");
			Undertaletitle=makeImage("Undertaletitle.jpg");
		friskimg[0]=makeImage("friskUP.png");
		friskimg[1]=makeImage("frisk.png");
		friskimg[2]=makeImage("friskR.png");
		friskimg[3]=makeImage("friskL.png");
		goldenflowerspawn=makeImage("goldenflowerspawn.png");
		room1floor=makeImage("room1floor.png");
		heart=makeImage("heart.png");
	}
	private BufferedImage makeImage(String path){
		try{
			return ImageIO.read(new File(getClass().getResource(path).toURI()));
		} catch(Exception e){
			System.err.println(path);
			e.printStackTrace();
			return null;
		}
	}
	private boolean boolFromInt(int i){
		return i == 1;
	}
}