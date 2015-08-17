package uow.edu.au.memorygame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GameModelInterface, TileViewListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Integer[] myImageList = new Integer[]{R.drawable.baldhill, R.drawable.cathedral, R.drawable.lake};

        //ArrayList<TileView> tiles = new ArrayList<>();
        final int numTiles = 12;


        GameModel g = new GameModel(numTiles,myImageList);
        g.reference = this;




        /*
        int counter1=1;
        int counter2=1;
        for(int i=0; i< numTiles; i++) {

            //get tileView id's (4b.2)
            if (counter2 == 4){
                counter2 = 1;
                counter1++;
            }
            int resID = getResources().getIdentifier("tile" + counter1 + counter2, "id", getPackageName());
            TileView t = (TileView) findViewById(resID);
            counter2++;




            //4b.4 and 4b.5
            t.setImage(g.getTile(i).getIMG());
            t.setId(i);

            //4b.6
            t.reference = this;


            //4b.3
            tiles.add(t);

            //4b.7
            t.coverImage();

            //4c





        */

       // }
       // gameDidComplete(g);
        //Log.v("debug output", g.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Boolean gameDidComplete(GameModel g){


        new AlertDialog.Builder(this)
                .setTitle("end of the line")
                .setMessage("you scored " + g.getScore() + " points!\n go again!")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();

        return true;
    }

    @Override
    public Boolean didMatchTile(GameModel g, int tileIndex, int previousTileIndex){

        return true;
    }

    @Override
    public Boolean didFailToMatchTile(GameModel g, int tileIndex, int previousTileIndex){

        return true;
    }

    @Override
    public Boolean scoreDidUpdate( GameModel g, int newScore){

        return true;
    }
    @Override
    public void didSelectTile(TileView tileview){
        tileview.revealImage();
        //Log.v("1","here");
    }

}
