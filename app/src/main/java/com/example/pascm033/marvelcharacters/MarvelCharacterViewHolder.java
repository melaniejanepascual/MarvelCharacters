package com.example.pascm033.marvelcharacters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

/**
 * View Holder binds data to view
 */
class MarvelCharacterViewHolder extends RecyclerView.ViewHolder {
    private ImageView marvelPic;
    private View.OnClickListener characterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SingleCharacterInformationActivity.start(v.getContext(), mCharacterInfo.getName(), mCharacterInfo.getDescription(), mCharacterInfo.getThumbnail() );
        }
    };

    private CharacterInfo mCharacterInfo;

    /**
     * constructor
     *
     * @param itemView
     */
    public MarvelCharacterViewHolder(View itemView) {
        super(itemView);
        marvelPic = itemView.findViewById(R.id.characterImg);
        itemView.setOnClickListener(characterClickListener);
    }

    public void bind(CharacterInfo characterInfo) {
        if(!(characterInfo.getThumbnail().getPath().equals("http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available"))){
            Picasso.get()
                    .load(Uri.parse(characterInfo.getThumbnail().getPath() + "." + characterInfo.getThumbnail().getExtension()))
                    .resize(500, 500)
                    .into(marvelPic);
        }
        mCharacterInfo = characterInfo;
    }
}

