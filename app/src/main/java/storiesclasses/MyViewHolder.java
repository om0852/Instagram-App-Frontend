package storiesclasses;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramloginform.HomePage;
import com.example.instagramloginform.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
ImageView imageView;
TextView textView;

    public MyViewHolder(@NonNull View itemView) {

        super(itemView);
        imageView = itemView.findViewById((R.id.Storyimage));
        textView = itemView.findViewById((R.id.storyName));
        Log.d("textView",textView.toString());
    }
}
