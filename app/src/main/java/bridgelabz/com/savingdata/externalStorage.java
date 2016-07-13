package bridgelabz.com.savingdata;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class externalStorage extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 23;
    EditText editTextFileName,editTextData;
    Button saveButton,readButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);

        editTextFileName=(EditText)findViewById(R.id.editText1);
        editTextData=(EditText)findViewById(R.id.editText2);
        saveButton=(Button)findViewById(R.id.button1);
        readButton=(Button)findViewById(R.id.button2);

        //Performing action on save button
        saveButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                String filename=editTextFileName.getText().toString();
                String data=editTextData.getText().toString();

                try {
                    if(Build.VERSION.SDK_INT>=23){
                        ActivityCompat.requestPermissions(externalStorage.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                        Manifest.permission.READ_EXTERNAL_STORAGE},
                                MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                    }
                    File myFile = new File(Environment.getExternalStorageDirectory().getPath()+"/"+filename);
                    myFile.createNewFile();
                    FileOutputStream fOut = new FileOutputStream(myFile);
                    OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                    myOutWriter.append(data);
                    myOutWriter.close();
                    fOut.close();

                    Toast.makeText(getApplicationContext(),filename + "saved",Toast.LENGTH_LONG).show();


                } catch (FileNotFoundException e) {e.printStackTrace();}
                catch (IOException e) {e.printStackTrace();}
            }

        });

        //Performing action on Read Button
        readButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                String filename=editTextFileName.getText().toString();
                String aDataRow;
                String aBuffer = "";
                try {
                    File myFile = new File(Environment.getExternalStorageDirectory().getPath()+"/"+filename);
                    FileInputStream fIn = new FileInputStream(myFile);
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));

                    while ((aDataRow = myReader.readLine()) != null) {
                        aBuffer += aDataRow + "\n";
                    }
                    myReader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(externalStorage.this,aBuffer, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)   {
        if (requestCode==MY_PERMISSIONS_REQUEST_READ_CONTACTS) {
            if(grantResults.length==1 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                try {
                    String filename=editTextFileName.getText().toString();
                    String data=editTextData.getText().toString();

                    File myFile = new File(Environment.getExternalStorageDirectory().getPath() + "/" + filename);
                    myFile.createNewFile();
                    FileOutputStream fOut = new FileOutputStream(myFile);
                    OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                    myOutWriter.append(data);
                    myOutWriter.close();
                    fOut.close();

                Toast.makeText(getApplicationContext(),filename + "saved",Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
