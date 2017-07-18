//import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.net.MalformedURLException;
//import java.net.URL;
import java.util.ArrayList;

public class Game extends JFrame implements KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7858544797196776326L;
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
	//private float x = 50;
	//private float v = 10;

	//sprite2 variables
	///private float x2 = 50.0f;
	//private float v2 = 100.0f;

	public int friskx = 500;
	public int frisky = 400;

	public int hearty;
	public int heartx;
	public boolean battleturn = true;

	int friskspd = 2;
	int heartspd = 3;

	int room1x;
	int room1y;
	boolean battle = false;

	String room = "room1";

	public int player_to_worldx;
	public int player_to_worldy;

	private boolean quitbool = false;

	Room1 room1 = new Room1(friskx, frisky, room1x, room1y,player_to_worldx,player_to_worldy); //For Class room1
	AudioSource audio = new AudioSource();

	TextureSource textures = new TextureSource();

	int[] Quitting = new int[3];

	ArrayList<Integer> keys = new ArrayList<>(); //Smoothkeys


	//private boolean isKeyPressed = false;

	//private String friskdir = "forward";
	boolean friskdirL = false;
	boolean friskdirR = false;
	boolean friskdirUP = false;
	boolean friskdirDOWN = false;

	//public String friskimg = "frisk.png";
	int friskindex = 0;


	public Game(int width, int height, int fps){
		super("Undertale Recreation");
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
		case KeyEvent.VK_Q:
			quitbool = false;
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
					//frisky-=friskspd;
					room1y+=friskspd;
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
					//frisky+=friskspd;
					room1y-=friskspd;
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
					//friskx+=friskspd;
					room1x-=friskspd;
					//friskimg = "friskR.png";
					friskindex=2;
				}
				if(battle == true) {
					heartx+=heartspd;
				}
				break;
			case KeyEvent.VK_LEFT:
				if(battle == false) {
					friskdirL = true;
					//friskx-=friskspd;
					room1x+=friskspd;
					//friskimg = "friskL.png";
					friskindex=3;
				}
				if(battle == true) {
					heartx-=heartspd;
				}
				break;
			case KeyEvent.VK_F:
				battle = true;
				break;
			case KeyEvent.VK_Q:
				quitbool = true;
				System.out.println("Quiting...");
				break;
			}
		}
	}

	private void update(){
		//update current fps
		fps = (int)(1f/dt);
		HandleKeys();


		if(battle == true) { //Battle

			//Buttons

			if(battleturn == true) {
				//Walls
				if(heartx <= 326) {
					heartx = 326;
				}

				if(hearty <= 420) {
					hearty = 420;
				}

				if(heartx >= 680) {
					heartx = 680;
				}
				if(hearty >= 777) {
					hearty = 777;
				}
			}


		}

		player_to_worldx = friskx + room1x;
		player_to_worldy = frisky + room1y;
		System.out.println("x,y: "+room1x+","+room1y);
		//frisky=room1.ycollis(friskx, frisky, room1x, room1y); //For class room1
		//friskx=room1.xcollis(friskx, frisky, room1x, room1y);

		if(room1y >= 414) {
			room1y = 414;
		}
		if(room1y <= -306) {
			room1y = -306;
		}
		if(room1y<-185 && ( 325>room1x && 210<room1x)){
			if(room1y>-210)
				room1y=-185;
			else
				room1x=210;
		}
		if(room1y<-180 && -670>room1x){
			if(room1y>-185)
				room1y=-180;
			else
				room1x=-670;
		}
		if(room1y >= 308 && room1x >= 172 && room1x <= 180) {
			room1x = 172;
		}
		if(room1y >= 296 && room1x >= 175) {
			room1y = 296;
		}
		if(room1x >= 318) {
			room1x = 318;
		}
		if(room1x <= -768) {
			room1x = -768;
		}
		if(room1x <= -630 && room1y >= 308 && room1x >= -635) {
			room1x = -630;
		}
		if(room1x <= -635 && room1y > 308) {
			room1y = 308;
		}
	}



	public void keypr(KeyEvent e) {
		//isKeyPressed = true;
	}

	private void draw(){
		//get canvas
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();

		//clear screen
		if(quitbool != true){
		g.setColor(Color.black);
		g.fillRect(0,0,WIDTH, HEIGHT);
		}

		//draw fps
		g.setColor(Color.GREEN);
		g.drawString(Long.toString(fps), 10, 40);

		//g.drawImage(textures.room1floor, null, room1x + -300, room1y + -300);
		//g.drawImage(textures.goldenflowerspawn, null, room1x + 300, room1y + 300);
		//g.drawImage(textures.friskimg[friskindex], null, friskx, frisky);
		if(title == true) {
			g.drawImage(textures.Undertaletitle, null, 0, 0);

		} else{

			if(quitbool == true) {
				quit();
			}
			if(title == false) {

				if(battle == false) {
					g.drawImage(textures.room1floor, null, room1x + -300, room1y + -300);
					g.drawImage(textures.goldenflowerspawn, null, room1x + 300, room1y + 300);
					g.drawImage(textures.friskimg[friskindex], null, friskx, frisky);
				}

				if(battle == true) {

					if(battleturn == true) {
						g.drawImage(textures.heart, null, heartx, hearty);
						g.drawImage(textures.combat_box, null, 300, 400);
					}
					g.drawImage(textures.combat_fight, null, 0, 900);
					g.drawImage(textures.combat_act, null, 250, 900);
					g.drawImage(textures.combat_item, null, 500, 900);
					g.drawImage(textures.combat_mercy, null, 750, 900);
				}

			}
		}


		//release resources, show the buffer
		g.dispose();
		strategy.show();
	}

	private void quit() {
		for(int i=0;i<3;i++) {
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.drawImage(textures.Quitting[i], null, 0, 0);
			g.dispose();
			strategy.show();

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		//Thread.sleep(1000);
		System.exit(0);


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
			//call update and draw methods
			update();
			draw();

			//dynamic thread sleep, only sleep the time we need to cap the framerate
			rest = (1000/MAX_FPS) - (System.currentTimeMillis() - startFrame);
			if(rest >0 && rest<1000){
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
