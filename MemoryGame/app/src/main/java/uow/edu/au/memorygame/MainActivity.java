package uow.edu.au.memorygame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity implements GameModelInterface, TileViewListener {

    public ArrayList<TileView> tilesss;
    public Integer[] myImageList = new Integer[]{R.drawable.baldhill, R.drawable.cathedral, R.drawable.lake};
    public final int numTiles = 12;
    public GameModel g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tilesss = new ArrayList<>();

        g = new GameModel(numTiles,myImageList);
        g.reference = this;


        int counter1=1;
        int counter2=1;
        for(int i=0; i< numTiles; i++) {

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

            //4b.7
            t.coverImage();

            //4b.3
            tilesss.add(t);

        }


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

        final int score = g.getScore();

        //bug with dismissing dialog before removal of last 2 tiles
        //add delay to creating dialog to stop this from happening
        new CountDownTimer(1500, 1000) {

            public void onTick(long millisUntilFinished) {
                // blank
            }

            public void onFinish() {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("end of the line")
                        .setMessage("you scored " + score + " points!\n go again!")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                MainActivity.this.g.reset(numTiles, myImageList);
                                for (int i = 0; i < numTiles; i++) {
                                    tilesss.get(i).setImage(MainActivity.this.g.getTile(i).getIMG());
                                    tilesss.get(i).coverImage();
                                    tilesss.get(i).setVisibility(View.VISIBLE);
                                }
                                dialog.dismiss();
                                MainActivity.this.g.reference.scoreDidUpdate(new GameModel(), 0);
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();
            }
        }.start();


        return true;
    }


    @Override
    public Boolean didMatchTile(GameModel g, final int tileIndex, final int tileIndex2){

        ImageView image = tilesss.get(tileIndex).getImageView();

            Animation fadeout = new AlphaAnimation(1.f, 0.f);
            fadeout.setDuration(500);
            fadeout.setStartOffset(1000);
            image.startAnimation(fadeout);
            image.postDelayed(new Runnable() {
                @Override
                public void run() {
                    tilesss.get(tileIndex).hideTile();
                }
            }, 1500);

            Animation fadeout2 = new AlphaAnimation(1.f, 0.f);
            fadeout2.setDuration(500);
            fadeout2.setStartOffset(1000);
            image.startAnimation(fadeout);
            image.postDelayed(new Runnable() {
                @Override
                public void run() {
                    tilesss.get(tileIndex2).hideTile();
                }
            }, 1500);

        return true;
    }

    @Override
    public Boolean didFailToMatchTile(GameModel g, final int tileIndex, final int previousTileIndex){

            new CountDownTimer(1000, 1000) {
            public void onFinish() {
                tilesss.get(tileIndex).coverImage();
                tilesss.get(previousTileIndex).coverImage();
            }
            public void onTick(long millisUntilFinished) {
            }
        }.start();

        return true;
    }

    @Override
    public Boolean scoreDidUpdate( GameModel g, int newScore){

        TextView score = (TextView) findViewById(R.id.textView2);
        score.setText("score: " + newScore);

        return true;
    }
    @Override
    public void didSelectTile(final TileView tileview) {


        AlphaAnimation animation1 = new AlphaAnimation(1.0f, 0.0f);
        animation1.setDuration(500);
        animation1.setFillAfter(true);
        tileview.getImageView().startAnimation(animation1);

        AlphaAnimation animation2 = new AlphaAnimation(0.0f, 1.0f);
        animation2.setDuration(500);
        animation2.setFillAfter(true);
        tileview.getImageView().startAnimation(animation2);
        tileview.revealImage();

        g.pushTileIndex(tileview.getId());

    }

}
