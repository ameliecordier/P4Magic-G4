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
		int tile_id = game.getBoard().getTileIJ(line, column).getStatus();
		tile_id=-1;
    	game.getBoard().getTileIJ(line, column).setStatus(tile_id);
    	if(line!=game.getBoard().getHeight()-1){
    		game.getBoard().getTileIJ(line+1, column).setStatus(tile_id);
    	}
    	if(line!=0){
    		game.getBoard().getTileIJ(line-1, column).setStatus(tile_id);
    	}
    	if(column!=game.getBoard().getWidth()-1){
    		game.getBoard().getTileIJ(line+1, column+1).setStatus(tile_id);
    	}
    	if(column!=0){
    		game.getBoard().getTileIJ(line, column-1).setStatus(tile_id);
    	}
    	
    	int tempi=-1;
    	int tempj=-1;
    	for(int i=column-1;i<column+1;i++){
    		for(int j=line-1;i<line+1;j--){
    			if(game.getBoard().getTileIJ(j, i).getStatus()!=-1){
    				
    			}
    		}
    	}
	}
}
