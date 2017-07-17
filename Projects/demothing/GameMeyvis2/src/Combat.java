
public class Combat {
	
	private int heartx;
	private int hearty;

	public Combat(int heartx, int hearty) {
		this.heartx = heartx;
		this.hearty = hearty;
	}
	
	public void Wall() {
		if(heartx <= 326) {
			heartx = 326;
		}
		
		if(hearty <= 420) {
			hearty = 420;
		}
		
		if(heartx >= 680) {
			heartx = 680;
		}
		//return heartx;
	}

	
}
