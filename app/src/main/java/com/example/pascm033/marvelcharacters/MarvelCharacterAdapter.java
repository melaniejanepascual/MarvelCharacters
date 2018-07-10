package com.example.pascm033.marvelcharacters;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    //constructor
    public MarvelCharacterAdapter(List<CharacterInfo> characterInfoList) {
        this.characterInfoList = characterInfoList;
    }

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


    @NonNull
    @Override
    public MarvelCharacterAdapter.MarvelCharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.marvelcharacter_item, parent, false);
        MarvelCharacterViewHolder vh = new MarvelCharacterViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MarvelCharacterAdapter.MarvelCharacterViewHolder holder, int position) {
        CharacterInfo info = characterInfoList.get(position);
        holder.bind(info);

    }

    @Override
    public int getItemCount() {
        //total number of items
        return characterInfoList.size();
    }

    public CharacterInfo getItem(int position) {
        return characterInfoList.get(position);
    }

}
