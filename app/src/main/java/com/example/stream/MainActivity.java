package com.example.stream;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    Button save, load;
    final String FILENAME = "Test.txt";
    FileOutputStream fos =null;
    FileInputStream fis =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editText);
        textView=findViewById(R.id.textView);
        save=findViewById(R.id.save);
        load=findViewById(R.id.load);
    }

    @Override
    protected void onDestroy() {
        deleteFile(FILENAME);
        super.onDestroy();
    }

    public void save(View v){

        try{
            fos = openFileOutput(FILENAME, MODE_PRIVATE);
            fos.write(editText.getText().toString().getBytes());
            Toast.makeText(this, "Файл сохранён", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, "Файл не найден", Toast.LENGTH_SHORT).show();
        }finally {
            try {
                fos.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void load(View v){

        try{
            fis = openFileInput(FILENAME);
            byte[] bytes=new byte[fis.available()];
            fis.read(bytes);
            String text=new String(bytes);
            textView.setText(text);
        }catch (Exception e){
            Toast.makeText(this, "Файл не найден", Toast.LENGTH_SHORT).show();
        }finally {
            try {
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}