package model;

/**
 * MagicP4
 * IUT Lyon 1 - 2017
 * @author Antonin-Christian - IUT Lyon 1 - 2017
 * 
 */

public class DisappearLineEffect extends Effect{

	/**
	 * 
	 * @param line
     * @param column
     * @param game 
	 */
	@Override
	public void playEffect(int line, int column, Game game) {
		
		int tile_id;
		int player1_id = game.getPlayer1().getId();
        int player2_id = game.getPlayer2().getId();
		
        //On fait disparaitre tous les cube de la ligne
		for(int i=0;i<10;i++)
		{
			game.getBoard().getTileIJ(line, i).setStatus(-1);
		}
		//puis on replace tous les cubes correctement en les faisant aller vers le bas s'il n'y a plus rien en dessous d'eux
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
