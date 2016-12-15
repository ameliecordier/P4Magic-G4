package model;

public class ExplodeSurroundingsEffect extends Effect {
	
	/**
     * This effect erases the tiles next to the tile that has just been played
     *
     * @param line
     * @param column
     * @param game
     */
	@Override
    public void playEffect(int line, int column, Game game) {
		int tile_id=-1;
    	game.getBoard().getTileIJ(line, column).setStatus(tile_id);
    	if(line!=game.getBoard().getHeight()-1){
    		game.getBoard().getTileIJ(line+1, column).setStatus(tile_id);
    	}
    	if(line!=0){
    		game.getBoard().getTileIJ(line-1, column).setStatus(tile_id);
    	}
    	if(column!=game.getBoard().getWidth()-1){
    		game.getBoard().getTileIJ(line, column+1).setStatus(tile_id);
    		gravity(column+1,line,game);
    	}
    	if(column!=0){
    		game.getBoard().getTileIJ(line, column-1).setStatus(tile_id);
    		gravity(column-1,line,game);
    	}
    	
    	
    	
	}
	
	public void gravity(int column,int lign,Game game){
		for(int i=lign;i>0;i--){
			if(game.getBoard().getTileIJ(i,column).getStatus()==1){
				game.getBoard().getTileIJ(lign, column).setStatus(1);
				game.getBoard().getTileIJ(i,column).setStatus(-1);
				lign--;
			}
			else if(game.getBoard().getTileIJ(i,column).getStatus()==2){
				game.getBoard().getTileIJ(lign, column).setStatus(2);
				game.getBoard().getTileIJ(i,column).setStatus(-1);
				lign--;
			}
		}
	}
}
