package storiesclasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramloginform.R;

import java.util.List;

public class StoriesAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Story> stories;
    private Context context;
String imageUrl;
    public StoriesAdapter(List<Story> stories, Context context) {
        this.stories = stories;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.story_item, parent, false);
        Log.d("om name", "done");

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (stories.get(position).isSeen()) {
            int color = ContextCompat.getColor(context, R.color.green);
        }
            holder.textView.setText(stories.get(position).getName());
        String imageUrl = stories.get(position).getImageUrl(); // Assuming getImageUrl() returns a String URL
        Picasso.get().load(imageUrl).into(holder.imageView);    }




    @Override
    public int getItemCount() {
        return stories.size();
    }

//    public class StoriesViewHolder extends RecyclerView.ViewHolder {
//        CardView storyOutline;
//
//        public StoriesViewHolder(@NonNull View itemView) {
//            super(itemView);
//            storyOutline = itemView.findViewById(R.id.storyOutline);
//             ImageView imageView = itemView.findViewById(R.id.Storyimage);
//            if (imageView != null) {
//                // Load the image from the URL using Picasso
////                String imageUrl = "";
//                try {
//                    Picasso.get().load(imageUrl).into(imageView);
//                    Log.d("error image", "successfully");
//
//                } catch (Exception e) {
//                    Log.d("error image", e.toString());
//                    e.printStackTrace();
//                }
//
//            }
//            else{
//                Log.d("error image","not load");
//            }
//
//        }
//    }
}
