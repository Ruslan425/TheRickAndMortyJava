package ru.romazanov.screens.character;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;
import ru.romazanov.data.model.character.Character;
import ru.romazanov.therickandmortyjava.databinding.CharacterItemBinding;


public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private ArrayList<Character> dataList = new ArrayList<>();

    public void setDataList(ArrayList<Character> list) {
        dataList = list;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
       CharacterItemBinding binding = CharacterItemBinding.inflate(layoutInflater, parent, false);
       return new CharacterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        holder.binding.idView.setText(String.valueOf(dataList.get(position).id));
        holder.binding.nameView.setText(dataList.get(position).name);
        holder.binding.specialView.setText(dataList.get(position).species);
        Glide.with(holder.binding.imageView)
                .load(dataList.get(position).image)
                .apply(RequestOptions.centerCropTransform())
                .into(holder.binding.imageView);
    }

    @Override
    public int getItemCount() {
        if (dataList == null)
            return 0;
        else
            return dataList.size();
    }

    public static class CharacterViewHolder extends RecyclerView.ViewHolder {
        CharacterItemBinding binding;
        public CharacterViewHolder(@NonNull CharacterItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
