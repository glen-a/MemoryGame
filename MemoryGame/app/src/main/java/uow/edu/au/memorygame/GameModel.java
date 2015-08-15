package uow.edu.au.memorygame;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by glen on 13/08/15.
 */
public class GameModel {
    private int lastTapped;
    private int sLastTapped;
    private List<TileData> tiles;
    private Boolean needToCheck;
    private int tilesMatched;
    //listener Reference
    private int score;

    GameModel(){
        lastTapped = -1;
        sLastTapped = -1;
        needToCheck = false;
        tilesMatched = 0;
        score = 0;
    }

    GameModel(int numTiles, Integer[] images){
        if(numTiles%2 == 0){
            lastTapped = -1;
            sLastTapped = -1;
            needToCheck = false;
            tilesMatched = 0;
            score = 0;
            tiles = new ArrayList<>();
            reset( numTiles, images);
        }
        else{
            Log.v("error", "num tiles is not even");
        }


    }

    void reset(int numTiles, Integer[] images){
        lastTapped = -1;
        sLastTapped = -1;
        needToCheck = false;
        tilesMatched = 0;
        score = 0;
        tiles = new ArrayList<>();


        int numImages = 0;
        for(int i=0; i< numTiles; i++){
            tiles.add(new TileData(numImages, images[numImages]));
//set i++ here and numImages, to i for unique
            tiles.add(new TileData(numImages, images[numImages]));
            i++;
            numImages++;
            if(numImages == images.length){
                numImages= 0;
            }
        }

        //shuffle tiles
        long seed = System.nanoTime();
        Collections.shuffle(tiles, new Random(seed));

        for (int i = 0; i < numTiles; i++) {

            Log.v("tiles", tiles.get(i).getID() + " " + tiles.get(i).getIMG().toString());
        }





        //    ArrayList<Integer> myImageList = new ArrayList<Integer>();
       // myImageList.add(R.drawable.thingOne);
// later...
        //myImageView.setImageResouce(myImageList.get(i));
    }

    @Override public String toString() {
        return getClass().getName() + "[" +
                "lastTapped= " + lastTapped + ", " +
                "sLastTapped= " + sLastTapped + ", \n" +
                "check tiles= " + needToCheck + ", " +
                "check score= " + score + ", " +
                "tilesMatched= " + tilesMatched + ", \n"+
                "img tiles=" + TextUtils.join("\n", tiles) + "]";
    }
}
