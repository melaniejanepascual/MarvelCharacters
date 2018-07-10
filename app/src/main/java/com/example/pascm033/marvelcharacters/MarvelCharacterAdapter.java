package com.example.pascm033.marvelcharacters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

/**
 * Adapter: binds all the data to the all the views
 */
public class MarvelCharacterAdapter extends
        RecyclerView.Adapter<MarvelCharacterViewHolder> {

    private List<CharacterInfo> characterInfoList;
    public MarvelCharacterAdapter(List<CharacterInfo> characterInfoList) {
        this.characterInfoList = characterInfoList;
    }


    @NonNull
    @Override
    public MarvelCharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.marvelcharacter_item, parent, false);
        MarvelCharacterViewHolder vh = new MarvelCharacterViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MarvelCharacterViewHolder holder, int position) {
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
