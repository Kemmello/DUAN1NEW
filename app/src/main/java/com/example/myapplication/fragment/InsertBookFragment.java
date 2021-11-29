package com.example.myapplication.fragment;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
//import com.example.myapplication.activities.TestActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InsertBookFragment extends Fragment {
    EditText edtAuthor, edtIntroduction, edtPage, edtPrice, edtTitle, edtTypename;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    Button btnTest;
    private StorageReference storageRef, fileRefernce;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;
    private DatabaseReference databaseRef;
    String email, imageEmail , fileTail = "jpg";
    private ImageView profilePicBook;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_test, container, false);
        return root;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        edtAuthor = view.findViewById(R.id.edtAuthor);
        edtIntroduction = view.findViewById(R.id.edtIntroduction);
        edtPage = view.findViewById(R.id.edtPage);
        edtPrice = view.findViewById(R.id.edtPrice);
        edtTitle = view.findViewById(R.id.edtTitle);
        edtTypename = view.findViewById(R.id.edtTypeName);
        firestore= FirebaseFirestore.getInstance();
        btnTest = view.findViewById(R.id.test);
        profilePicBook = view.findViewById(R.id.imgBook);

        profilePicBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePic();
            }
        });
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertData();
            }
        });
    }
    public void InsertData(){
        Map<String, String> items = new HashMap<>();
        items.put("AUTHOR",edtAuthor.getText().toString().trim());
        items.put("INTRODUCTION",edtIntroduction.getText().toString().trim());
        items.put("PAGE",edtPage.getText().toString().trim());
        items.put("PRICE",edtPrice.getText().toString().trim());
        items.put("TITLE",edtTitle.getText().toString().trim());
        items.put("TYPENAME",edtTypename.getText().toString().trim());
        firestore.collection("BOOK").add(items)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        edtAuthor.setText("");
                        edtIntroduction.setText("");
                        edtPage.setText("");
                        edtPrice.setText("");
                        edtTitle.setText("");
                        edtTypename.setText("");
                        Toast.makeText(getActivity(), "Thành công", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void choosePic(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == -1 && data.getData() != null) {
            mImageUri = data.getData();
            Picasso.with(getActivity()).load(mImageUri).into(profilePicBook);
        }
    }

    public static String getMimeType(Context context, Uri uri) {
        String extension;

        //Check uri format to avoid null
        if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            //If scheme is a content
            final MimeTypeMap mime = MimeTypeMap.getSingleton();
            extension = mime.getExtensionFromMimeType(context.getContentResolver().getType(uri));
        } else {
            extension = MimeTypeMap.getFileExtensionFromUrl(String.valueOf(Uri.fromFile(new File(uri.getPath().toString()))));
        }
        return extension;
    }

    private void uploadFile() {
        storageRef = FirebaseStorage.getInstance().getReference();
        databaseRef = FirebaseDatabase.getInstance().getReference("uploads");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        email = user.getEmail();
        imageEmail = email.replace(".com", "");
        fileRefernce = storageRef.child(imageEmail + "." + getMimeType(getContext(), mImageUri));
        auth = FirebaseAuth.getInstance();
        if (mImageUri != null) {

            fileRefernce.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getContext(), "image updated !", Toast.LENGTH_LONG).show();
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), "User image not updated", Toast.LENGTH_LONG).show();
                }
            });

        } else {
            Toast.makeText(getActivity(), "No file selected", Toast.LENGTH_LONG).show();
        }

        try {
            getUserImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getUserImage() throws IOException {
        storageRef = FirebaseStorage.getInstance().getReference().child(imageEmail + "." + fileTail);

        File localFile = File.createTempFile(imageEmail, "jpg");

        storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getContext(), "image retrieved", Toast.LENGTH_LONG).show();
                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                profilePicBook.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "you have no User's image!", Toast.LENGTH_LONG).show();
            }
        });
    }
}