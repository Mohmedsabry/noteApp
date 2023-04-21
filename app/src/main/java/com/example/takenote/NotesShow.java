package com.example.takenote;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class NotesShow extends Fragment {


    private static final String ARG_PARAM1 = "Adptar";
    private Adptar adptar;


    private ArrayList<Note> arrayList;

    public NotesShow() {
        // Required empty public constructor
    }


    public static NotesShow newInstance(Adptar adptar) {
        NotesShow fragment = new NotesShow();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, adptar);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            adptar = (Adptar) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rc=view.findViewById(R.id.frag_rv);
        rc.setAdapter(adptar);
        rc.setLayoutManager(new GridLayoutManager(view.getContext(),2));
        rc.setHasFixedSize(true);
    }
}