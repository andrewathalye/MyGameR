
public class Room1 {
	
	 int friskx;
	 int frisky;
	 int room1x;
	 int room1y;
	 int player_to_worldx;
	 int player_to_worldy;

	public Room1(int friskx, int frisky, int room1x, int room1y, int player_to_worldx, int player_to_worldy) {
		this.frisky = frisky;
		this.room1x = room1x;
		this.friskx = friskx;
		this.room1y = room1y;
		this.player_to_worldx = player_to_worldx;
		this.player_to_worldy = player_to_worldy;
		
	}
	
	public void WallX(int player_to_worldx, int player_to_worldx2, int player_to_worldy) {
		
		for(int ptw = player_to_worldx; ptw < player_to_worldx2; ptw++) {
			if(ptw == friskx && player_to_worldy == frisky) {
				frisky = player_to_worldy;
			}
		//if(player_to_worldx == friskx && player_to_worldy == frisky) {
		//	frisky = player_to_worldy;
		//}
		}
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
		//System.out.println("Coords are: "+nfriskx+","+nfrisky);
		return nfriskx;
	}
	
}
