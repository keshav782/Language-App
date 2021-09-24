package com.example.miwok;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word>  {

    private int mcolorid;

    public WordAdapter(Activity context, ArrayList<Word> words,int colorid){
        super(context,0,words);
        mcolorid=colorid;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;
        if (listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        Word currentWord=getItem(position);

        TextView miwokTextView= (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentWord.getmivokTransition());

        TextView defaultTextView= (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getenglishTransition());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        if(currentWord.hasimage()){
            imageView.setImageResource(currentWord.getimageTransition());
            imageView.setVisibility(View.VISIBLE);
        }
        else {
            imageView.setVisibility(View.GONE);
        }
        View textcontainer = listItemView.findViewById(R.id.text_container);

        int color = ContextCompat.getColor(getContext(),mcolorid);
        textcontainer.setBackgroundColor(color );
        return listItemView;
    }
}
