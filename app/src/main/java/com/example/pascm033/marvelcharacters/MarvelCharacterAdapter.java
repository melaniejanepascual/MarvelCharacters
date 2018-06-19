package com.example.pascm033.marvelcharacters;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Adapter: binds all the data to the all the views
 */
public class MarvelCharacterAdapter extends
        RecyclerView.Adapter<MarvelCharacterAdapter.MarvelCharacterViewHolder> {

    //data
    private List<CharacterInfo> characterInfoList;

    public void setCharacterInfoList(List<CharacterInfo> characterInfoList) {
        this.characterInfoList = characterInfoList;
        notifyDataSetChanged(); //refreshing the list
    }

    /**
     * View Holder binds data to view
     */
    class MarvelCharacterViewHolder extends RecyclerView.ViewHolder {
        public ImageView marvelPic;

        /**
         * constructor
         *
         * @param itemView
         */
        public MarvelCharacterViewHolder(View itemView) {
            super(itemView);
            marvelPic = (ImageView) itemView.findViewById(R.id.characterImg);
        }
    }

    @NonNull
    @Override
    public MarvelCharacterAdapter.MarvelCharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // build a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.marvelcharacter_item, parent, false);

        MarvelCharacterViewHolder vh = new MarvelCharacterViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MarvelCharacterAdapter.MarvelCharacterViewHolder holder, int position) {
        // gets an image with a CharacterInfo instance, then converts it from URL to an image
        CharacterInfo info = characterInfoList.get(position);
        Picasso.get()
                .load(Uri.parse(info.getThumbnail().getPath() + "." + info.getThumbnail().getExtension()))
                .into(holder.marvelPic);
    }

    @Override
    public int getItemCount() {
        //total number of items
        return characterInfoList.size();
    }

}
