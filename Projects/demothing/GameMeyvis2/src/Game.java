import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Game extends JFrame implements KeyListener{

    //window vars
    private final int MAX_FPS;
    private final int WIDTH;
    private final int HEIGHT;

    //double buffer
    private BufferStrategy strategy;

    //loop variables
    private boolean isRunning = true;
    private long rest = 0;
    boolean title = true;

    //timing variables
    private float dt;
    private long lastFrame;
    private long startFrame;
    private int fps;
    

    //sprite1 variables
    private float x = 50;
    private float v = 10;

    //sprite2 variables
    private float x2 = 50.0f;
    private float v2 = 100.0f;
    
    public int friskx = 300;
    public int frisky = 300;
    
    public int hearty;
    public int heartx;
    
    int friskspd = 2;
    int heartspd = 3;
    
    int room1x;
    int room1y;
    boolean battle = false;
    
    String room = "room1";
    
    public int player_to_worldx;
    public int player_to_worldy;
    
    Room1 room1 = new Room1(friskx, frisky, room1x, room1y,player_to_worldx,player_to_worldy); //For Class room1
    AudioSource audio = new AudioSource();
    
    TextureSource textures = new TextureSource();
    
    
    
    ArrayList<Integer> keys = new ArrayList<>(); //Smoothkeys
    
    
    private boolean isKeyPressed = false;
    
    //private String friskdir = "forward";
    boolean friskdirL = false;
    boolean friskdirR = false;
    boolean friskdirUP = false;
    boolean friskdirDOWN = false;
    
    //public String friskimg = "frisk.png";
    int friskindex = 0;


    public Game(int width, int height, int fps){
        super("JFrame Demo");
        this.MAX_FPS = fps;
        this.WIDTH = width;
        this.HEIGHT = height;
    }

    void init(){
        //initialize JFrame
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(0, 0, WIDTH, HEIGHT);

        setIgnoreRepaint(true);

        setResizable(false);
        setVisible(true);

        //create double buffer strategy
        createBufferStrategy(2);
        strategy = getBufferStrategy();

        lastFrame = System.currentTimeMillis();
        
        addKeyListener(this);
        setFocusable(true);
        
        audio.play(audio.music0);
    }
    
    public void keyTyped(KeyEvent keyEvent) {
    	
    }
    
    public void keyPressed(KeyEvent keyEvent) {
    	if(!keys.contains(keyEvent.getKeyCode())) { //Smoothkeys
    		keys.add(keyEvent.getKeyCode());
    	}
    	/*switch(keyEvent.getKeyCode()) {
    	case KeyEvent.VK_SPACE: 
    		
    		break;
    	case KeyEvent.VK_UP:
    		
    		frisky-=10;
    		break;
    	case KeyEvent.VK_DOWN:
    		frisky+=10;
    		break;
    	case KeyEvent.VK_RIGHT:
    		friskx+=10;
        	friskimg = "friskR.png";
    		break;
    	case KeyEvent.VK_LEFT:
    		//friskdir = "left";
    		friskx-=10;
    		friskimg = "friskL.png";
    		break;
    	
    	}
    	if(keyEvent.getKeyCode() == KeyEvent.VK_UP) {
    		frisky-=10;
    		friskimg = "friskUP.png";
    	}
    	if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
    		frisky+=10;
    		friskimg = "frisk.png";
    	}
    	if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
    		friskx+=10;
    		friskimg = "friskR.png";
    	}
    	if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
    		friskx-=10;
    		friskimg = "friskL.png";
    	}*/
    	
    	if(title == true) {
    		if(keyEvent.getKeyCode() == KeyEvent.VK_Z) {
        		title = false;
        	}
    	}
    	
    }
    
    	

    
    public void keyReleased(KeyEvent keyEvent) {
    	switch(keyEvent.getKeyCode()) { //Stops player from moving after key being released
    	case KeyEvent.VK_UP:
    		friskdirUP = false;
			break;
    	case KeyEvent.VK_DOWN:
    		friskdirDOWN = false;
		break;
    	case KeyEvent.VK_RIGHT:
			friskdirR = false;
			break;
    	case KeyEvent.VK_LEFT:
    			friskdirL = false;
    		break;
    	}
    	
    	for(int i=keys.size()-1; i>=0; i--) { //Smoothkeys
    		if(keys.get(i) == keyEvent.getKeyCode()) {
    			keys.remove(i);
    		}
    	}
    }
    
    private void HandleKeys() {
    	for(int i = 0; i<keys.size(); i++) { //Movement
  
    		switch(keys.get(i)) {
    		case KeyEvent.VK_UP:
    			if(battle == false) {
        			friskdirUP = true;
            		frisky-=friskspd;
            		//friskimg = "friskUP.png";
            		friskindex=0;
    			}
    			if(battle == true) {
    				hearty-=heartspd;
    			}
        		break;
        	case KeyEvent.VK_DOWN:
        		if(battle == false) {
            		friskdirDOWN = true;
            		frisky+=friskspd;
            		//friskimg = "frisk.png";
            		friskindex=1;
        		}
        		
        		if(battle == true) {
        			hearty+=heartspd;
        		}
        		break;
        	case KeyEvent.VK_RIGHT:
        		if(battle == false) {
            		friskdirR = true;
            		friskx+=friskspd;
                	//friskimg = "friskR.png";
            		friskindex=2;
        		}
        		
        		break;
        	case KeyEvent.VK_LEFT:
        		if(battle == false) {
            		friskdirL = true;
            		friskx-=friskspd;
            		//friskimg = "friskL.png";
            		friskindex=3;
        		}
        		
        		break;
        	case KeyEvent.VK_F:
        		battle = true;
        		break;
    		}
    	}
    }
    
    private void update(){
        //update current fps
        fps = (int)(1f/dt);
        HandleKeys();
        //Movement on the background, creates fake movement
        if(friskx > 600 && friskdirR == true) {
        	room1x-=friskspd;
        	friskx-=friskspd;
        }
        if(friskx < 100 && friskdirL == true) {
        	room1x+=friskspd;
        	friskx+=friskspd;
        }
        if(frisky < 100 && friskdirUP == true) {
        	room1y+=friskspd;
        	frisky+=friskspd;
        }
        if(frisky > 600 && friskdirDOWN == true) {
        	room1y-=friskspd;
        	frisky-=friskspd;
        }
        
        
        player_to_worldx = friskx + room1x;
        player_to_worldy = frisky + room1y;
        //System.out.println("roomy" + room1y);
       // System.out.println("roomx" + room1x);
        //System.out.println("friskx" + friskx);
        System.out.println(player_to_worldx + ", " + player_to_worldy);
        //System.err.println("errorrrrrr");
        frisky=room1.ycollis(friskx, frisky, room1x, room1y); //For class room1
        friskx=room1.xcollis(friskx, frisky, room1x, room1y);
        
    }

    public void keypr(KeyEvent e) {
        isKeyPressed = true;
    }
    
    //public static void vidchar(String[] args) throws MalformedURLException {
//
     //   URL url = new URL("frisk_WalkL.gif");
     //   Icon icon = new ImageIcon(url);
     //   JLabel label = new JLabel(icon);
//
     //   JFrame f = new JFrame("Animation");
     //   f.getContentPane().add(label);
     //   f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     //   f.pack();
     //   f.setLocationRelativeTo(null);
     //   f.setVisible(true);
    //}
    
    private void draw(){
        //get canvas
        Graphics2D g = (Graphics2D) strategy.getDrawGraphics();

        //clear screen
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH, HEIGHT);

        //draw fps
        g.setColor(Color.GREEN);
        g.drawString(Long.toString(fps), 10, 40);
        
        g.drawImage(textures.room1floor, null, room1x + -300, room1y + -300);
        g.drawImage(textures.goldenflowerspawn, null, room1x + 300, room1y + 300);
        g.drawImage(textures.friskimg[friskindex], null, friskx, frisky);
        
        if(battle == true) {
        	g.drawImage(textures.heart, null, heartx, hearty);
        }
        
        
        
        if(title == true) {
        	g.drawImage(textures.Undertaletitle, null, 0, 0);
        	
        }
        

        
        //g.drawImage();
        
        
        
        

        //release resources, show the buffer
        g.dispose();
        strategy.show();
    }
    BufferedImage makeImage(String path){
    	try{
    		return ImageIO.read(new File(getClass().getResource(path).toURI()));
    		} catch(Exception e){
    			e.printStackTrace();
    			return null;
    			}
    	}


    public void run(){
        init();

        while(isRunning){
            //new loop, clock the start
            startFrame = System.currentTimeMillis();
            //calculate delta time
            dt = (float)(startFrame - lastFrame)/1000;
            //log the current time
            lastFrame = startFrame;
            /*while(isKeyPressed || friskdir != "forward") {
            	friskdir = "forward";
            }*/
            
            //if(friskdir == "forward") {
            //	friskimg = "frisk.png";
            //}
            //call update and draw methods
            update();
            draw();

            //dynamic thread sleep, only sleep the time we need to cap the framerate
            rest = (1000/MAX_FPS) - (System.currentTimeMillis() - startFrame);
            if(rest >0){
                try{ Thread.sleep(rest); }
                catch (InterruptedException e){ e.printStackTrace(); }
            }
        }

    }


    public static void main(String[] args){
        Game game = new Game(1000, 1000, 120); //Game Window propertys
        game.run();
    }
    
    
    

}
