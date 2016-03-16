package com.cam;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.util.logging.Filter;

public class MainActivity extends AppCompatActivity {
    private ImageView op_iv;
    private Button capture_btn;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        op_iv = (ImageView) findViewById(R.id.op_iv);
        capture_btn = (Button) findViewById(R.id.capture_btn);
        capture_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //folder creation
                File dir = new File(Environment.getExternalStorageDirectory() + "/Me");
                if (!dir.exists())
                    dir.mkdir();
                //file creation
                String fileName = "Me" + System.currentTimeMillis();
                try {
                    file = File.createTempFile(fileName, ".png", dir.getAbsoluteFile());
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startActivityForResult(takePictureIntent, 101);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 101) {
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                op_iv.setImageBitmap(bitmap);
            }
        } else {

        }
    }
}
