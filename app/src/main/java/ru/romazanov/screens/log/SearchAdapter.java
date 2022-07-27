package ru.romazanov.screens.log;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import ru.romazanov.data.model.SearchItem;
import ru.romazanov.therickandmortyjava.R;
import ru.romazanov.therickandmortyjava.databinding.SearchItemBinding;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>{



    private ArrayList<SearchItem> dataList = new ArrayList<>();

    public void setDataList(ArrayList<SearchItem> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        SearchItemBinding binding = SearchItemBinding.inflate(layoutInflater, parent, false);
        return new SearchViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.binding.section.setText(dataList.get(position).section);
        holder.binding.id.setText(String.valueOf(dataList.get(position).id));
        holder.binding.name.setText(dataList.get(position).name);
            Glide.with(holder.binding.imageView)
                    .load(dataList.get(position).image)
                    .placeholder(R.drawable.ic_one)
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

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        SearchItemBinding binding;
        public SearchViewHolder(@NonNull SearchItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
