package com.example.inventario;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.MyViewHolder> {


        ArrayList<Post>posts = new ArrayList<>();
        Context context;


    public myAdapter(ArrayList<Post>posts,Context context){

        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Post post = posts.get(holder.getAdapterPosition());
       holder.title.setText(post.getTitle());
        holder.body.setText(post.getBody());


        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent =new Intent(context,DetailActivity.class);
                intent.putExtra("postDetail",posts.get(holder.getAdapterPosition()));

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

           private TextView title,body;

          ConstraintLayout mainLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.names_txt);
            body = (TextView) itemView.findViewById(R.id.description_txt);

            mainLayout = itemView.findViewById(R.id.mainLayout);
        }

        public TextView getTitle(){
            return  title;
        }

        public void setTitle(TextView title){
            this.title=title;

        }

        public TextView getBody() {
            return body;
        }

        public void setBody(TextView completed) {
            this.body = completed;
        }
    }
}
