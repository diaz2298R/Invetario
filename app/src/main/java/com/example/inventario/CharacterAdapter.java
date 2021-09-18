package com.example.inventario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.MyViewHolder> {

    ArrayList<Character> characters = new ArrayList<>();
    Context context ;

    public CharacterAdapter(ArrayList<Character> characters,Context context){
        this.context = context;
        this.characters = characters;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.character_row,parent,false);
        return new CharacterAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Character character =characters.get(holder.getAdapterPosition());
        Glide.with(context).load(character.getImage())
                .apply(new RequestOptions().override(300,300))
                .into(holder.photo);
        holder.name.setText(character.getName());
        holder.gender.setText(character.getGender());

    }

    @Override
    public int getItemCount() {
        return characters.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,gender;
        ConstraintLayout characterLayout;
        ImageView photo;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameCharacter);
            gender = itemView.findViewById(R.id.genderCharacter);
            photo = itemView.findViewById(R.id.characterPhoto);
            characterLayout =itemView.findViewById(R.id.characterlayout);
        }

        public TextView getName() {
            return name;
        }

        public void setName(TextView name) {
            this.name = name;
        }

        public TextView getGender() {
            return gender;
        }

        public void setGender(TextView gender) {
            this.gender = gender;
        }

        public ImageView getPhoto() {
            return photo;
        }

        public void setPhoto(ImageView photo) {
            this.photo = photo;
        }

    }

}
