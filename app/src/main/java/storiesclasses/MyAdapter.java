package storiesclasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramloginform.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Storyitem> items;
    public MyAdapter(Context context,List<Storyitem> item){
        this.context =context;
        this.items =item;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.story_bar,parent,false));
    }

    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Storyitem currentItem = items.get(position);
        holder.textView.setText("salunke");
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
