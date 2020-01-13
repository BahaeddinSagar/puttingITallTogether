package ly.bsagar.gsonpicasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieArrayAdapter extends ArrayAdapter<MovieClass> {
    public MovieArrayAdapter(@NonNull Context context, int resource, @NonNull List<MovieClass> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // this is recycling logic, if converview exists use it, else make a new convertview
        if (convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_layout,parent,false);
        }
        // get views from convert view
        TextView titleText = convertView.findViewById(R.id.title);
        TextView descText = convertView.findViewById(R.id.des);
        ImageView posterView = convertView.findViewById(R.id.imageView);
        //get the Movie from ArrayLost at current position
        MovieClass currentMovie = getItem(position);
        //populate convertview with values
        titleText.setText(currentMovie.name);
        descText.setText(currentMovie.description.substring(0,10) + "...");
        //confirm that imageURL starts with https to avoid any errors
        if (currentMovie.ImageURL.startsWith("https")){
            Picasso.get().load(currentMovie.ImageURL).into(posterView);
        }
        //retrun the edited view to show in screen
        return convertView;
    }
}
