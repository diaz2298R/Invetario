package com.example.inventario;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {



    TextView title ,description;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Post post  = (Post) getIntent().getSerializableExtra("postDetail");
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);

        title.setText(post.getTitle());
        description.setText(post.getBody());

    }


}