
public class Room1 {
	

	 int frisky;
	 int room1x;

	public Room1(int frisky, int room1x) {
		this.frisky = frisky;
		this.room1x = room1x;
	}
	
	public int room1(int nfrisky,int nroom1y) {
			if(nfrisky <= 150 && nroom1y >= 160) {
				nfrisky = 150;
		}
		return nfrisky;
	}
	
}
