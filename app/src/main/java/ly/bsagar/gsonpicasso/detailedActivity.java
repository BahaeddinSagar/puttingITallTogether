package ly.bsagar.gsonpicasso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class detailedActivity extends AppCompatActivity {
    // declare view
    TextView titleTextView;
    TextView yearTextView;
    TextView descTextView;
    ImageView imageView;
    // variable url must be global to be used in moreDetails function
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        // initilize views
        titleTextView = findViewById(R.id.detailedTitle);
        yearTextView = findViewById(R.id.detailedyear);
        descTextView = findViewById(R.id.detailedDecs);
        imageView = findViewById(R.id.detailedImage);

        // get data from intent
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String desc = intent.getStringExtra("desc");
        int year = intent.getIntExtra("year" , -1);
        String imageURL = intent.getStringExtra("ImageURL");
        url = intent.getStringExtra("URL");
        // display data
        titleTextView.setText(title);
        yearTextView.setText(String.valueOf(year));
        descTextView.setText(desc);
        Picasso.get().load(imageURL).into(imageView);
    }
    // this function redirects the user to the movies' url
    public void moreDetails(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
