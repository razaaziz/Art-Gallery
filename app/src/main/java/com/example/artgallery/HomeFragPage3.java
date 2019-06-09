package com.example.artgallery;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class HomeFragPage3 extends Fragment {
    private static final String TAG = "HomeFragPage3";
    private EditText post;
    private Button picBtn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_tabe_3, container,false);
        post = view.findViewById(R.id.post);
        picBtn = view.findViewById(R.id.picBtn);

        picBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "inClick: opening Dialog");

                UploadImageFrag dialog = new UploadImageFrag();

                dialog.show(getFragmentManager(), "Uploadpicture");
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {    Log.d(TAG, "inClick: opening Dialog");

                UploadImageFrag dialog = new UploadImageFrag();

                dialog.show(getFragmentManager(), "Uploadpicture");
            }
        });


        return view;
    }
}
