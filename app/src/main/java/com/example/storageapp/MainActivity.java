
package com.example.storageapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileFilter;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    /**deskripsi string nama file untuk menyimpan**/
    public static final String FILENAME="namafile.txt" ;
    public static final String PREFNAME="prefname.txt" ;

    TextView textBaca;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textBaca=findViewById(R.id.textBaca);
    }


    public void buatFile(View view) {
        simpanEx();
    }

    public void ubahFile(View view) {
        ubahEx();
    }

    public void bacaFile(View view) {
        bacaEx();
    }

    public void hapusFile(View view) {
        hapusEx();
    }

            void simpanPref(){
                String isiFile="Coba Isi Data File";
                SharedPreferences sharedPreferences=
                        getSharedPreferences(PREFNAME, MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(FILENAME,isiFile);
                editor.commit();
            }

            void ubahPref(){
               String isiFile="Update Isi Data File";
               SharedPreferences sharedPreferences=
                     getSharedPreferences(PREFNAME, MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(FILENAME,isiFile);
                editor.commit();
            }

            /**Perlu di cek terlebih dahulu isi dari file **/
            void  bacaPref(){
                SharedPreferences sharedPreferences =
                        getSharedPreferences(PREFNAME, MODE_PRIVATE);
                if(sharedPreferences.contains(FILENAME)){
                    String mytext=sharedPreferences
                            .getString(FILENAME,"");
                    textBaca.setText(mytext);
                }else {
                    textBaca.setText("");
                }
            }

            void hapusPref(){
                SharedPreferences sharedPreferences=
                        getSharedPreferences(PREFNAME, MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.commit();

            }


     /** MENYIMPAN DATA DI MEMORI INERNAL persistance**/
    void simpanIn(){
        String isiFile="Coba Isi data internal";
        File file=new File(getFilesDir(), FILENAME);
        FileOutputStream outputStream=null;

        /**ketik file.createNewFile(); kemudian alt+enter
         * jadinya seperti di bawah ini**/
        try {
            file.createNewFile();
            outputStream=new FileOutputStream(file, true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void ubahIn(){
        String isiFile="Update Isi data internal";
        File file=new File(getFilesDir(), FILENAME);
        FileOutputStream outputStream=null;

        /**ketik file.createNewFile(); kemudian alt+enter
         * jadinya seperti di bawah ini**/
        try {
            file.createNewFile();
            outputStream=new FileOutputStream(file, false); /**jika true tidak REWRITE tapi menambahkan**/
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void bacaIn(){
        /**definisikan file nya**/
        File file = new File(getFilesDir(),FILENAME);
        /**Lakukan pengecekan**/
        if(file.exists()) {
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new
                        BufferedReader (new FileReader(file)); /**alt+enter**/
                String line = br.readLine();
                while (line != null) {
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            textBaca.setText(text.toString());
        }else {
            textBaca.setText("");
        }
    }

    void hapusIn(){
        File file=new File(getFilesDir(), FILENAME);
        if (file.exists()){
            file.delete();
        }
    }


    /**menyimpan di external**/
        void simpanEx(){
        File path= Environment.getExternalStorageDirectory(); /** untuk menyimpan di direktori tertentu, getExternalStoragePublicDirectory
                                                                    (Enviorenment.DIRECTORY_PICTURE); >> menyimpan di dir picture**/
        String isiFile="Coba isi data ex";
        File file=new  File(path.toString(), FILENAME);
        FileOutputStream outputStream=null;
        try {
            file.createNewFile();
            outputStream=new FileOutputStream(file, true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        void ubahEx(){
        File path= Environment.getExternalStorageDirectory();
        String isiFile="Coba update isi data ex";
        File file=new  File(path.toString(), FILENAME);
        FileOutputStream outputStream=null;
        try {
            file.createNewFile();
            outputStream=new FileOutputStream(file, false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        void bacaEx(){
        File path= Environment.getExternalStorageDirectory();
        /**definisikan file nya**/
        File file = new File(path.toString(),FILENAME);
        /**Lakukan pengecekan**/
        if(file.exists()) {
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new
                        BufferedReader (new FileReader(file)); /**alt+enter**/
                String line = br.readLine();
                while (line != null) {
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            textBaca.setText(text.toString());
        }else {
            textBaca.setText("");
        }
    }

        void hapusEx(){
        File path= Environment.getExternalStorageDirectory();
        File file=new File(path.toString(), FILENAME);
        if (file.exists()){
            file.delete();
        }
    }
}
