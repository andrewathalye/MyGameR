import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
 final int WIDTH;
 final int HEIGHT;
 final int MAX_FPS;
 
 private BufferStrategy strategy;
 
 
 private boolean isRunning = true;
 private long rest = 0;
 
 
 private float dt;
 private long lastFrame;
 private long startFrame;
 private int fps;
 
 public Main(int width, int height, int fps) {
	 super();
	 
	 this.WIDTH = width;
	 this.HEIGHT = height;
	 this.MAX_FPS = fps;
	 
	 
 }
 
 void init() {
	 setPreferredSize(new Dimension(WIDTH, HEIGHT));
	 setResizable(false);
	 setDefaultCloseOperation(EXIT_ON_CLOSE);
	 
	 setBounds(0, 0, WIDTH, HEIGHT);
	 setIgnoreRepaint(true);
	 
	 setVisible(true);
	 
	 createBufferStrategy(2);
	 strategy = getBufferStrategy();
	 
	 lastFrame = System.currentTimeMillis();
 }
 
 public void run() throws IOException {
	 init();
	 while(isRunning) {
		 putImage("\frisk.png", 0, 0);
		 isRunning = false;
	 }
 }
 
 private void putImage(String filepath, int loc1, int loc2) throws IOException {
	 try {
		 File file = new File(getClass().getResource(filepath).toURI());
		 BufferedImage image = ImageIO.read(file);
		 JLabel label = new JLabel(new ImageIcon(image));
		 JFrame f = new JFrame();
		 f.getContentPane().add(label);
		 f.pack();
		 f.setLocation(loc1, loc2);
		 f.setVisible(true); 
	 	} catch(Exception e) {
	 		System.out.println(e.toString());
	 		System.out.println("Input was " +filepath);
	 	}
 }
 
 public static void main(String args[]) throws IOException {
	 Main my_main = new Main(800, 600, 60);
	 my_main.run();
 }

}
