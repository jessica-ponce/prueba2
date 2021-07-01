package ues.edu.sv.almacenamientointerno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {


    EditText editArchivotxt, editTxtDatos;
    Button btnGuardar, btnLeer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editArchivotxt = (EditText) findViewById(R.id.editText1);
        editTxtDatos = (EditText) findViewById(R.id.editText2);
        btnGuardar = (Button) findViewById(R.id.btn1);
        btnLeer = (Button) findViewById(R.id.btn2);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreArchivo = editArchivotxt.getText().toString();
                String datos = editTxtDatos.getText().toString();

                FileOutputStream fos;
                try{
                    fos=MainActivity.this.openFileOutput(nombreArchivo, Context.MODE_PRIVATE);
                    fos.write(datos.getBytes());
                    fos.close();

                    Toast.makeText(MainActivity.this,nombreArchivo + " Guardado con éxito", Toast.LENGTH_LONG).show();
                }catch (FileNotFoundException e){e.printStackTrace();}
                catch (IOException e){e.printStackTrace();

                }

            }
        });
        btnLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreArchivo = editArchivotxt.getText().toString();
                StringBuffer stringBuffer = new StringBuffer();

                try {
                    BufferedReader inputReader = new BufferedReader(new InputStreamReader(MainActivity.this.openFileInput(nombreArchivo)));
                    String inputString;
                    while ((inputString = inputReader.readLine()) != null) {
                        stringBuffer.append(inputString + "\n");
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, stringBuffer.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}