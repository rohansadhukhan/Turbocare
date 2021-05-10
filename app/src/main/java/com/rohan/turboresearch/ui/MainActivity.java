package com.rohan.turboresearch.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rohan.turboresearch.databinding.ContentMainBinding;
import com.rohan.turboresearch.room.entity.Cars;
import com.rohan.turboresearch.utils.Constants;
import com.rohan.turboresearch.utils.LoadingAlertDialog;
import com.rohan.turboresearch.viewmodels.MainViewModel;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private FloatingActionButton fab;

    private ContentMainBinding _binding;
    private LoadingAlertDialog dialog;

    private MainViewModel viewModel;
    private String currentPhotoPath;

    private RecyclerView rcv;
    private VehicleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = ContentMainBinding.inflate(getLayoutInflater());
        setContentView(_binding.getRoot());

        Toolbar toolbar = _binding.toolbar;
        setSupportActionBar(toolbar);
        setRecyclerView();

        fab = _binding.fab;
        dialog = new LoadingAlertDialog(MainActivity.this);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.cars.observe(this, new Observer<List<Cars>>() {
            @Override
            public void onChanged(List<Cars> cars) {
                dialog.dismissDialog();
                adapter.setData(cars);
                adapter.notifyDataSetChanged();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
    }

    private void setRecyclerView() {
        rcv = _binding.rcv;
        adapter = new VehicleAdapter(this);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setAdapter(adapter);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
                Log.d(Constants.TAG, "image path: " + photoFile);
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.d(Constants.TAG, "Error occurred " + ex.getMessage());
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            dialog.loadDialog("Uploading file...");
            File f = new File(currentPhotoPath);
            viewModel.getAllCars();
            uploadData(f);
        }
    }

    private void uploadData(File file) {
//        RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        RequestBody body = RequestBody.create(MediaType.parse("image/jpg"), file);
        MultipartBody.Part data = MultipartBody.Part.createFormData("file", file.getName(), body);
        viewModel.uploadData(body, data).observe(this, s -> {
            Log.d(Constants.TAG, "vehicle number is " + s);
            dialog.dismissDialog();
            if (s.equals("Error")) {
                Toast.makeText(MainActivity.this, "Network error occurred, unable to send request", Toast.LENGTH_SHORT).show();
            } else {
                dialog.loadDialog("Saving data...");
                viewModel.insertData(currentPhotoPath);
            }
        });
    }
}