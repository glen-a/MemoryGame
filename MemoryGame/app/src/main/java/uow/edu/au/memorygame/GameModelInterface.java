package uow.edu.au.memorygame;

/**
 * Created by glen on 15/08/15.
 */
public interface GameModelInterface {

    Boolean gameDidComplete(GameModel g);

    Boolean didMatchTile(GameModel g, int tileIndex, int previousTileIndex);

    Boolean didFaileToMatchTile(GameModel g, int tileIndex, int previousTileIndex);

    Boolean scoredidUpdate( GameModel g, int newScore);
}
