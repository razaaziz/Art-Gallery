package com.example.artgallery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class UploadImageFrag extends DialogFragment {
    private static final String TAG = "UploadImageFrag";

    private EditText cap;
    private Button chPicBtn, tkPicBtn;
    private TextView action_ok, action_cancel;
    private ImageView img;

    private Uri filePath;

    private static final int PICK_IMAGE_REQUEST = 1234;
    private static final int CAMERA_IMAGE_REQUEST = 4321;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.upload_picture_dialogue, container, false);
        cap = view.findViewById(R.id.cap);
        chPicBtn = view.findViewById(R.id.chPicBtn);
        tkPicBtn = view.findViewById(R.id.tkPicBtn);
        img = view.findViewById(R.id.img);
        action_ok = view.findViewById(R.id.action_ok);
        action_cancel = view.findViewById(R.id.action_cancel);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        chPicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }

        });
        tkPicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });
        action_cancel.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               getDialog().dismiss();
           }
       });
        action_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String in = cap.getText().toString();
                chooseImage();
                if(!in.equals("")){

                }
            }
        });


        return view;
    }

    private void chooseImage() {
        Log.d(TAG, "onClick: accessing phone's memory");
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);

    }

    private void takePhoto() {
        Log.d(TAG, "onClick: starting camera");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_IMAGE_REQUEST);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && requestCode == Activity.RESULT_OK && data != null && data.getData() != null){

            filePath = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContext().getContentResolver().query(filePath, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            img.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }else if(requestCode == CAMERA_IMAGE_REQUEST && requestCode == Activity.RESULT_OK){
            Bitmap bt;
            bt = (Bitmap) data.getExtras().get("data");
        }
    }
}
