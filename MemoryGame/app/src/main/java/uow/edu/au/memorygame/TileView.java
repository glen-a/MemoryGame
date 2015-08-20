package uow.edu.au.memorygame;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by glen on 15/08/15.
 */
public class TileView extends LinearLayout{
    private Integer image;
    private ImageView imageView;

    private Integer defaultImage;

    //listener variable?
    public TileViewListener reference;

    private int tileIndex;


    public TileView(Context context, AttributeSet attrs) {
        super(context, attrs);

        imageView = new ImageView(context);
        imageView.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        defaultImage = R.drawable.question;

        //here to change image
        image = R.drawable.baldhill;

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.didSelectTile(TileView.this);
            }
        });

        this.addView(imageView);


    }

    public void setImage(Integer img){
        this.image = img;
    }
    public void setTileIndex(int i){
        this.tileIndex = i;
    }
    public void setReference(TileViewListener t){
        this.reference = t;
    }


    public void revealImage(){
        imageView.setImageResource(image);
    }

    public void coverImage(){
        imageView.setImageResource(defaultImage);

    }

    public void hideTile(){
        this.setVisibility(INVISIBLE);
    }


    public android.widget.ImageView getImageView() {
        return imageView;
    }
}

