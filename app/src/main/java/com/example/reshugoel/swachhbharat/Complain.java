package com.example.reshugoel.swachhbharat;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.media.MediaRecorder.VideoSource.CAMERA;

public class Complain extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST_CAMERA=1,PICK_IMAGE_REQUEST_GALLERY=2;
    private ImageButton gallery,camera;
    private Button submit;
    private EditText location;
    private ImageView preview;
    private Uri mimage;
    private ProgressBar progressBar;
    private Bitmap mImageBitmap;
    private String mCurrentPhotoPath;

    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private StorageTask storageTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);
        gallery=(ImageButton)findViewById(R.id.gallery);
        camera=(ImageButton)findViewById(R.id.camera);
        submit=(Button)findViewById(R.id.submitcomplain);
        preview=(ImageView)findViewById(R.id.preview);
        progressBar=(ProgressBar)findViewById(R.id.progress);
        location=(EditText)findViewById(R.id.location);

        storageReference=FirebaseStorage.getInstance().getReference("Complaints");
        databaseReference=FirebaseDatabase.getInstance().getReference("Complaints");
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,PICK_IMAGE_REQUEST_GALLERY);
            }
        });
//        camera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
//                    // Create the File where the photo should go
//                    File photoFile = null;
//                    try {
//                        photoFile = createImageFile();
//                    } catch (IOException ex) {
//                        // Error occurred while creating the File
//                        Log.i("TAG", "IOException");
//                    }
//                    // Continue only if the File was successfully created
//                    if (photoFile != null) {
//                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
//                        startActivityForResult(cameraIntent, PICK_IMAGE_REQUEST_CAMERA);
//                    }
//                }
//            }
//        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (storageTask != null && storageTask.isInProgress()) {
                    Toast.makeText(Complain.this,"Submission is in progress",Toast.LENGTH_SHORT).show();

                } else {
                    uploadFile();
                }
            }
        });
    }
//    private File createImageFile() throws IOException {
//        // Create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName,  // prefix
//                ".jpg",         // suffix
//                storageDir      // directory
//        );
//
//        // Save a file: path for use with ACTION_VIEW intents
//        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
//        return image;
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST_GALLERY && resultCode==RESULT_OK && data!=null && data.getData()!= null){
            mimage=data.getData();
            Picasso.get().load(mimage).into(preview);
        }
//        else if(requestCode==PICK_IMAGE_REQUEST_CAMERA && resultCode==RESULT_OK ){
//                try {
//                    mImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(mCurrentPhotoPath));
//                    preview.setImageBitmap(mImageBitmap);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
           //mimage=data.getData();
          // Picasso.get().load(mimage).into(preview);

    }
//Just gets extension of the file
    private String getFileExtension(Uri uri){
        ContentResolver contentResolver= getContentResolver();
        MimeTypeMap mimeTypeMap= MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }
    private void uploadFile(){
        if(mimage!=null){
            StorageReference fileReference= storageReference.child(System.currentTimeMillis()+"."+getFileExtension(mimage));
            storageTask= fileReference.putFile(mimage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(0);
                        }
                    },2500);
                    Toast.makeText(Complain.this,"Upload successful",Toast.LENGTH_SHORT).show();
                    ComplaintSubmit complaintSubmit=new ComplaintSubmit(location.getText().toString().trim(),taskSnapshot.getDownloadUrl().toString());
                    String uploadId=databaseReference.push().getKey();
                    databaseReference.child(uploadId).setValue(complaintSubmit);
//                    Intent intent1= new Intent(Complain.this,HomePage.class);
//                    startActivity(intent1);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                        Toast.makeText(Complain.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    progressBar.setMax(100);
                    progressBar.incrementProgressBy(5);
                }
            });

        }else{
            Toast.makeText(this,"No image selected",Toast.LENGTH_SHORT).show();
        }
    }
}
