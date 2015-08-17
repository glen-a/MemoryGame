package uow.edu.au.memorygame;

/**
 * Created by glen on 15/08/15.
 */
public interface GameModelInterface {

    Boolean gameDidComplete(GameModel g);

    Boolean didMatchTile(GameModel g, int tileIndex, int previousTileIndex);

    Boolean didFailToMatchTile(GameModel g, int tileIndex, int previousTileIndex);

    Boolean scoreDidUpdate( GameModel g, int newScore);
}
