
public class Room1 {
	
	 int friskx;
	 int frisky;
	 int room1x;
	 int room1y;

	public Room1(int friskx, int frisky, int room1x, int room1y) {
		this.frisky = frisky;
		this.room1x = room1x;
		this.friskx = friskx;
		this.room1y = room1y;
	}
	
	public int ycollis(int nfriskx, int nfrisky,int nroom1x, int nroom1y) {
			
			
			
			
			/*if(nfrisky <= 150 && nroom1y >= 160) {
				nfrisky = 150;
			}
			
			if(nfriskx >= 445 && nroom1x == -695) {
				nfriskx = 445;
			}*/
		return nfrisky;
	}
	public int xcollis(int nfriskx, int nfrisky,int nroom1x, int nroom1y) {
		if(nfriskx < 182 && ( 232<nfrisky && nfrisky<344))
			nfriskx=182;
		System.out.println("Coords are: "+nfriskx+","+nfrisky);
		return nfriskx;
	}
	
}
