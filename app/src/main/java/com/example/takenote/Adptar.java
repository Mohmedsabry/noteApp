package com.example.takenote;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Adptar extends RecyclerView .Adapter<Adptar.Holder> implements Serializable, Parcelable {
    ArrayList<Note>arrayList;
    Listener listener;
    public Adptar(ArrayList<Note> arrayList,Listener listener)
    {
        this.arrayList = arrayList;
        this.listener=listener;
    }

    protected Adptar(Parcel in) {
    }

    public static final Creator<Adptar> CREATOR = new Creator<Adptar>() {
        @Override
        public Adptar createFromParcel(Parcel in) {
            return new Adptar(in);
        }

        @Override
        public Adptar[] newArray(int size) {
            return new Adptar[size];
        }
    };

    public ArrayList<Note> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Note> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_layout,parent,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        if(arrayList.get(position).getTitle()!=null){
            holder.title.setText(arrayList.get(position).getTitle());
        }
        if(arrayList.get(position).getDecscrption()!=null){
            holder.dec.setText(arrayList.get(position).getDecscrption());
        }
        if(arrayList.get(position).getType()!=null){
            if(arrayList.get(position).getType().equalsIgnoreCase("work")){
                holder.imageView.setImageResource(R.drawable.baseline_noise_control_off_24_blue);
            } else if (arrayList.get(position).getType().equalsIgnoreCase("life")) {
                holder.imageView.setImageResource(R.drawable.baseline_noise_control_off_24_green);
            }else if(arrayList.get(position).getType().equalsIgnoreCase("family")){
                holder.imageView.setImageResource(R.drawable.baseline_noise_control_off_24_red);
            }else{
                holder.imageView.setImageResource(R.drawable.baseline_noise_control_off_24_yellow);
            }
            if (!arrayList.get(position).getHistory().equals("")){
                DateFormat dateFormat=new SimpleDateFormat("MMMM d YYYY");
                Date date=new Date(arrayList.get(position).getHistory());
                System.out.println(date.toString()+" hi feom adpter");
                holder.his.setText(dateFormat.format(date).toString());
            }else{
                DateFormat dateFormat=new SimpleDateFormat("MMMM d YYYY");
                holder.his.setText(dateFormat.format(new Date())+"");
            }
            holder.itemView.setOnClickListener(view -> listener.OnObjectClick(arrayList.get(holder.getAdapterPosition()).getId()));
            System.out.println(arrayList.get(position).toString());
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
    }


    class Holder extends RecyclerView.ViewHolder{
        TextView title,dec,his;
        ImageView imageView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.rv_title);
            dec = itemView.findViewById(R.id.rv_dec);
            his = itemView.findViewById(R.id.rv_history);
            imageView = itemView.findViewById(R.id.rv_img);

        }
    }
    interface Listener{
        public void OnObjectClick(int id);
    }
}
