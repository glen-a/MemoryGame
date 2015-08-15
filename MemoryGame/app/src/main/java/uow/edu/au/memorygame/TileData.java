package uow.edu.au.memorygame;

import android.graphics.drawable.Drawable;

/**
 * Created by glen on 13/08/15.
 */
public class TileData {

    private int id;
    private Integer img;

    TileData(){

    }
    TileData(int id, Integer img){
        this.id = id;
        this.img = img;
    }

    int getID(){
        return id;
    }


    void setID(int id){
        this.id = id;
    }

    Integer getIMG(){
        return this.img;
    }

    @Override public String toString() {
        return getClass().getName() + "[" +
                "id= " + id + ", " +
                "img= " + img + ", " +"]";
    }



}
