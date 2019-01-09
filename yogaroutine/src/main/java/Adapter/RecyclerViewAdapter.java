package Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fitness.yogyam.v2.simple.R;
import com.fitness.yogyam.v2.simple.ViewExerciseActivity;


import java.util.List;

import Interface.ItemClickListener;
import Model.Exercise;

/**
 * Created by James Sarkar.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<Exercise> exerciseList;

    private Context context;

    public RecyclerViewAdapter(List<Exercise> exerciseList, Context context) {
        this.exerciseList = exerciseList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View itemView = inflater.inflate(R.layout.item_exercise, parent, false);

        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
       // holder.image.setImageBitmap(exerciseList.get(position).getImageId());
        Glide.with(context).load(exerciseList.get(position).getImageId())
                .apply(new RequestOptions().placeholder(R.drawable.loading))
                .into(holder.image);
        holder.name.setText(exerciseList.get(position).getName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                // Call new activity
                Intent intent = new Intent(context, ViewExerciseActivity.class);
                intent.putExtra("imageId", exerciseList.get(position).getImageId());
                intent.putExtra("name", exerciseList.get(position).getName());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
}

class RecyclerViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{

    ImageView image;

    TextView name;

    private ItemClickListener itemClickListener;

    public RecyclerViewHolder(View itemView) {
        super(itemView);

        image = (ImageView) itemView.findViewById(R.id.exercise_image);
        name = (TextView) itemView.findViewById(R.id.exercise_name);

        itemView.setOnClickListener(this);
    }

    void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition());
    }
}
