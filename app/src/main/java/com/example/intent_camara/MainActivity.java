package com.example.intent_camara;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ImageView iv1;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv1=findViewById(R.id.iv1);
    }
    final int CAPTURA_IMAGEN=1;
    public void tomarFoto(View v){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,CAPTURA_IMAGEN);

//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        someActivityResultLauncher.launch(intent);
    }

//    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
  //                    if (result.getResultCode() == Activity.RESULT_CANCELED)
  //                          Toast.makeText(MainActivity.this, "Se finalizo por el boton cancelar", Toast.LENGTH_SHORT).show();

//                    if (result.getResultCode() == Activity.RESULT_OK) {
//                        Intent data = result.getData();
//                        Bundle extras = data.getExtras();
//                        Bitmap bitmap1= (Bitmap) extras.get("data");
//                        iv1.setImageBitmap(bitmap1);
//                        try{
//                            FileOutputStream fos= openFileOutput(crearNombreArchivoJPG(), Context.MODE_PRIVATE);
//                            bitmap1.compress(Bitmap.CompressFormat.JPEG,100,fos);
//                            fos.close();
//
//                        }catch (Exception e){}
//                    }
//
//                }
//            }
//    );

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CAPTURA_IMAGEN && resultCode==RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap bitmap1= (Bitmap) extras.get("data");
            iv1.setImageBitmap(bitmap1);
            try{
                FileOutputStream fos= openFileOutput(crearNombreArchivoJPG(), Context.MODE_PRIVATE);
                bitmap1.compress(Bitmap.CompressFormat.JPEG,100,fos);
                fos.close();

            }catch (Exception e){}
        }
    }

    private String crearNombreArchivoJPG() {
        String fecha = new SimpleDateFormat("yyMMdd_HHmmss").format(new Date());
        return fecha+".jpg";
    }

    public void verFoto(View v){
        Intent intent= new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
}