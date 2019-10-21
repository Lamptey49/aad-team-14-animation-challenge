package com.example.movieapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Context mContext;
    private ArrayList<ListItem> mList;

    public MovieAdapter(Context context, ArrayList<ListItem> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public MovieViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_movie_item, viewGroup, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int i) {
        ListItem currentItem = mList.get(i);

        String imageUrl = currentItem.getImageUrl();
        String movieTitle = currentItem.getmMovieTitle();
        int votes = currentItem.getmVotes();

        holder.mTextViewCreator.setText(movieTitle);
        holder.mTextViewVotes.setText(votes);
        Picasso.get()
                .load(imageUrl)
                .fit()
                .centerInside()
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public class MovieViewHolder extends ViewHolder {
        public ImageView mImageView;
        public TextView mTextViewCreator;
        public TextView mTextViewVotes;

        public MovieViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view1);
            mTextViewCreator = itemView.findViewById(R.id.textView2);
            mTextViewVotes = itemView.findViewById(R.id.textView3);
        }

    }
}
