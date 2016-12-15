package model;

public class DisappearLineEffect extends Effect{

	@Override
	public void playEffect(int line, int column, Game game) {
		
		int tile_id;
		int player1_id = game.getPlayer1().getId();
        int player2_id = game.getPlayer2().getId();
		
		for(int i=0;i<10;i++)
		{
			game.getBoard().getTileIJ(line, i).setStatus(-1);
		}
		for(int i=0;i<10;i++)
		{
			for(int j=line;j>=0;j--)
			{
				tile_id = game.getBoard().getTileIJ(j, i).getStatus();
				
				if(tile_id != -1)
				{
					game.getBoard().getTileIJ(j, i).setStatus(-1);
					game.getBoard().getTileIJ(j+1, i).setStatus(tile_id);
				}
			}
		}
	}

}
