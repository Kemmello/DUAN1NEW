package com.example.myapplication.fragment;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class UserFragment extends Fragment {
    private ImageView profilePic;
    private Uri mImageUri;
    private static final int PICK_IMAGE_REQUEST = 1;
    private StorageReference storageRef, fileRefernce;
    private DatabaseReference databaseRef;
    private FirebaseStorage storage;
    private FirebaseFirestore database;
    FirebaseAuth auth;

    String email, name, phone, address, birthday, avatarName, imgUrl, imageEmail, fileTail="jpg";
    Long Role;
    EditText edtUserName, edtDate, edtPhone, edtEmail, edtAddress;
    Button btnSave, btnCancel;
    String id;
    int imageChecked=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        edtUserName = view.findViewById(R.id.edtUserName);
        edtDate = view.findViewById(R.id.edtDate);
        edtPhone = view.findViewById(R.id.edtPhone);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtAddress = view.findViewById(R.id.edtAddress);
        btnSave = view.findViewById(R.id.btnSave);
        btnCancel = view.findViewById(R.id.btnCancelUpdate);
	    profilePic = view.findViewById(R.id.profilePic);

//        fileRefernce = storageRef.child(imageEmail+"."+getMimeType(getContext(),mImageUri));


        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePic();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser();
                if(imageChecked==1){
                    uploadFile();
                }

//                if (imageUploadTask != null && imageUploadTask.isInProgress()){
//                    Toast.makeText(getContext(),"can't upload image right now", Toast.LENGTH_LONG).show();
//                }else{
//                    uploadFile();
//                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUser();
                try {
                    getUserImage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        database = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
//        id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        id = auth.getCurrentUser().getUid();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        email = user.getEmail();
        imageEmail = email.replace(".com","");

//        storageRef = FirebaseStorage.getInstance().getReference();
//        databaseRef = FirebaseDatabase.getInstance().getReference("uploads");


        getUser();
        Role = getUser();
        try {
            getUserImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Long getUser() {
        DocumentReference reference = database.collection("USER").document(id);
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.getResult().exists()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    name = task.getResult().getString("NAME");
                    email = user.getEmail();
                    phone = task.getResult().getString("PHONE");
                    address = task.getResult().getString("ADDRESS");
                    birthday = task.getResult().getString("BIRTHDAY");
                    Role = task.getResult().getLong("ROLE");
                    String userImageLink = "";
                    edtUserName.setText(name);
                    edtDate.setText(birthday);
                    edtPhone.setText(phone);
                    edtEmail.setText(email);
                    edtAddress.setText(address);
                } else {
                    Toast.makeText(getActivity(), "vui long dang nhap lai!:", Toast.LENGTH_LONG).show();
                }
            }
        });
        return Role;
    }

    public void updateUser() { //String imageUrl

        name = edtUserName.getText().toString();
        email = edtEmail.getText().toString();
        birthday = edtDate.getText().toString();
        phone = edtPhone.getText().toString();
        address = edtAddress.getText().toString();


        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getActivity(), "Tên không hợp lệ !", Toast.LENGTH_LONG).show();
            edtUserName.requestFocus();
            edtDate.setFocusable(false);
            edtPhone.setFocusable(false);
            edtEmail.setFocusable(false);
            edtAddress.setFocusable(false);
            return;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getActivity(), "Email không hợp lệ !", Toast.LENGTH_LONG).show();
            edtUserName.setFocusable(false);
            edtDate.setFocusable(false);
            edtPhone.setFocusable(false);
            edtEmail.requestFocus();
            edtAddress.setFocusable(false);
            return;
        }
        if (TextUtils.isEmpty(birthday)) {
            Toast.makeText(getActivity(), "Ngày sinh không hợp lệ !", Toast.LENGTH_LONG).show();
            edtUserName.setFocusable(false);
            edtDate.requestFocus();
            edtPhone.setFocusable(false);
            edtEmail.setFocusable(false);
            edtAddress.setFocusable(false);
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(getActivity(), "Sđt không hợp lệ !", Toast.LENGTH_LONG).show();
            edtUserName.setFocusable(false);
            edtDate.setFocusable(false);
            edtPhone.requestFocus();
            edtEmail.setFocusable(false);
            edtAddress.setFocusable(false);
            return;
        }
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(getActivity(), "địa chỉ không hợp lệ !", Toast.LENGTH_LONG).show();
            edtUserName.setFocusable(false);
            edtDate.setFocusable(false);
            edtPhone.setFocusable(false);
            edtEmail.setFocusable(false);
            edtAddress.requestFocus();
            return;
        }
//        if (task.isSuccessful()){
//                            User user = new User(email,password,name,",",",",0,2);
        DocumentReference documentReference = database.collection("USER").document(id);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.updateEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Map<String, Object> user = new HashMap<>();
//            user.put("EMAIL",email);
                            user.put("NAME", name);
                            user.put("ADDRESS", address);
//                            user.put("AVATARLINK", imageUrl); //uploadFile());
                            user.put("BIRTHDAY", birthday);
                            user.put("PHONE", phone);
                            user.put("ROLE", Role);
                            documentReference.set(user)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(getActivity(), "Cập nhật thành công", Toast.LENGTH_LONG).show();
                                        }
                                    });
                        } else {
                            Toast.makeText(getActivity(), "Cập nhật thất bại!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

	public void choosePic(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        imageChecked =1;
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == -1 &&  data.getData() != null){
            mImageUri = data.getData();
            Picasso.with(getContext()).load(mImageUri).into(profilePic);
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
            //If scheme is a File
            //This will replace white spaces with %20 and also other special characters. This will avoid returning null values on file name with spaces and special characters.
            extension = MimeTypeMap.getFileExtensionFromUrl(String.valueOf(Uri.fromFile(new File(uri.getPath().toString()))));
        }
        return extension;
    }



//     filePath.substring(filePath.lastIndexOf(".") + 1);


    private void uploadFile(){
        storageRef = FirebaseStorage.getInstance().getReference();
        databaseRef = FirebaseDatabase.getInstance().getReference("uploads");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        email = user.getEmail();
        imageEmail = email.replace(".com","");
        fileRefernce = storageRef.child(imageEmail+"."+getMimeType(getContext(),mImageUri));
        auth = FirebaseAuth.getInstance();
        if(mImageUri !=  null){

                    fileRefernce.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getContext(),"image updated !", Toast.LENGTH_LONG).show();
//                    Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() { imageProgressBar.setProgress(0); }
//                    },500);
//                    Uri downloadUrl = taskSnapshot.getDownloadUrl();

//                    Task<Uri> imageUrl = taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                        @Override
//                        public void onSuccess(Uri uri) {
//                            // Got the uri
//                            User userImage = new User(imageEmail, uri.toString());
//                            // Wrap with Uri.parse() when retrieving
//
//                            String uploadId = databaseRef.push().getKey();
//                            databaseRef.child(uploadId).setValue(userImage);
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception exception) {
//                            // Handle any errors
//                        }
//                    });
//                    User userImage = new User(email.trim(), imageUrl.toString());
//                    String upLoadId = databaseRef.push().getKey();
//                    databaseRef.child(upLoadId).setValue(userImage);
//
//                    updateUser(imageUrl.toString());

//                    Task<Uri> imageUrl = storageRef.child(imageEmail+"."+getMimeType(getContext(),mImageUri)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                        @Override
//                        public void onSuccess(Uri uri) {
//                            // Got the uri
//                            User userImage = new User(imageEmail, uri.toString());
//                            // Wrap with Uri.parse() when retrieving
//
//                            String uploadId = databaseRef.push().getKey();
//                            databaseRef.child(uploadId).setValue(userImage);
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                        }
//                    });
//                    updateUser(imageUrl.toString());
                }

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), "User image not updated", Toast.LENGTH_LONG).show();
                }
            });
//                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
//                    double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
//                    imageProgressBar.setProgress((int) progress);
//               }
//            })

        }else{Toast.makeText(getContext(),"No file selected", Toast.LENGTH_LONG).show();}

        try {
            getUserImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getUserImage() throws IOException {
        storageRef = FirebaseStorage.getInstance().getReference().child(imageEmail+"."+fileTail);


        File localFile = File.createTempFile(imageEmail,"jpg");

        storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getContext(),"image retrieved",Toast.LENGTH_LONG).show();
                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                profilePic.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(),"you have no User's image!",Toast.LENGTH_LONG).show();
            }
        });

//        try{
//            final File localTempFile = File.createTempFile("user","jpg");
//            storageRef.getFile(localTempFile)
//                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                            Toast.makeText(getContext(),"image retrieved",Toast.LENGTH_LONG).show();
//                            Bitmap bitmap = BitmapFactory.decodeFile(localTempFile.getAbsolutePath());
//                            profilePic.setImageBitmap(bitmap);
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(getContext(),"Error retrieved",Toast.LENGTH_LONG).show();
//                }
//            });
//        }catch(IOException e){
//            e.printStackTrace();
//        }
    }


}



