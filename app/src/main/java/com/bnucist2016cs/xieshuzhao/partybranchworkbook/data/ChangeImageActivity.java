package com.bnucist2016cs.xieshuzhao.partybranchworkbook.data;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bnucist2016cs.xieshuzhao.partybranchworkbook.MainActivity;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.R;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.base.BasicActivity;
import com.bumptech.glide.Glide;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.security.auth.login.LoginException;

public class ChangeImageActivity extends BasicActivity {

    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;
    private Button btn_photograph;
    private Button btn_photo_gallery;
    String fileName;
    String time;
    ImageView picture;
    List<String> paths = new ArrayList<>();
    List<Book> books = new ArrayList<>();
    Book book;
    private Uri imageUri;
    private ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_image);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_photo_gallery = (Button) findViewById(R.id.photo_gallery);
        btn_photograph = (Button) findViewById(R.id.photograph);
        //picture = (ImageView) findViewById(R.id.test_image);
        time = getIntent().getStringExtra("TIME");

        books = DataSupport.where("start = ?",time).find(Book.class);
        for (Book b:books){
            book = b;
        }
        paths = book.getPath();
        if (book.getPath().size()!=0){
            refreshImages(paths);
        }

        btn_photo_gallery.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ChangeImageActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    gallery();
                }
            }
        });

        btn_photograph.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ChangeImageActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
                }else{
                    photo();
                }
            }
        });
    }

    public void refreshImages(List<String> paths){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.image_recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ImageAdapter(paths);
        recyclerView.setAdapter(adapter);
    }


    public void photo(){
        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        ContentValues values = new ContentValues();
        fileName = timeStampFormat.format(new Date());
        values.put(MediaStore.Images.Media.TITLE,fileName);
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intent,TAKE_PHOTO);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        switch (requestCode){
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK){
                    try{
                        Uri uri = null;
                        String path = "";
                        Cursor cursor = null;
                        uri = imageUri;
                        String start = time;
                        if (uri!=null){
                            String[] proj = {MediaStore.Images.Media.DATA};
                            cursor = managedQuery(uri,proj,null,null,null);
                            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                            cursor.moveToFirst();
                            path = cursor.getString(column_index);
                            Log.e("mmp",path);
                            Book book2 = new Book();
                            paths.add(path);
                            book2.setPath(paths);
                            book2.updateAll("start = ?",start);
                            refreshImages(paths);
                            if (cursor != null && !cursor.isClosed() && Build.VERSION.SDK_INT < 14) {
                                cursor.close();
                            }
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK){
                    if (Build.VERSION.SDK_INT>=19){
                        handleImageOnKitKat(data);
                    }else {
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data){
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this,uri)){
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID+"="+id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }
            else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);
            }
        }else if ("content".equalsIgnoreCase(uri.getScheme())){
            imagePath = getImagePath(uri,null);
        }else if ("file".equalsIgnoreCase(uri.getScheme())){
            imagePath = uri.getPath();
        }
        addImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        addImage(imagePath);
    }

    public String getImagePath(Uri uri,String selection){
        String path = null;
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if (cursor!=null){
            if (cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    public void addImage(String path){
        paths.add(path);
        Book book3 = new Book();
        book3.setPath(paths);
        book3.updateAll("start = ?",time);
        refreshImages(paths);
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                photo();
            }else{
                ShowToast("It's a pity that you didn't agree to use the album.");
            }
        }
        if (requestCode == 1){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                gallery();
            }else{
                ShowToast("It's a pity that you didn't agree to use the album.");
            }
        }
    }

    public void gallery(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }
}
