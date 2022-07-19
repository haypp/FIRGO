package com.reyjunaaf.firgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Edit extends AppCompatActivity {
    EditText kegiatan, jumlah, note;
    TextView tvjenis, tvidl;
    RadioGroup RG;
    RadioButton rb1,rb2;
    Button edit;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        DB = new DBHelper(this);
        kegiatan = findViewById(R.id.TFnama);
        jumlah = findViewById(R.id.TFjumlah);
        note = findViewById(R.id.TFnote);
        tvidl = findViewById(R.id.TVidE);
        edit = findViewById(R.id.btnEDIT);
        rb1 = findViewById(R.id.RBplus);
        rb2 = findViewById(R.id.RBminus);
        Intent l = getIntent();
        kegiatan.setText(l.getStringExtra("nama").toString());
        String jenis = l.getStringExtra("jenis").toString();
        if (jenis.equals("+")){
            rb1.setChecked(true);
        }else {
            rb2.setChecked(true);
        }
        //tvjenis.setText("Jenis : "+l.getStringExtra("jenis").toString());
        //Toast.makeText(this, "ini " + l.getStringExtra("jenis").toString(), Toast.LENGTH_SHORT).show();
        jumlah.setText(l.getStringExtra("jumlah").toString());
        note.setText(l.getStringExtra("note").toString());
        tvidl.setText(l.getStringExtra("id").toString());
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaa = kegiatan.getText().toString();
                String jumlahh = jumlah.getText().toString();
                String idd = tvidl.getText().toString();
                String notee = note.getText().toString();
                String jeniss;
                String jenis = l.getStringExtra("jenis").toString();
                if (jenis.equals("+")){
                    jeniss = "+";
                }else {
                    jeniss = "-";
                }
                Boolean checkupdatedata = DB.updateuserdata(idd,namaa, jumlahh, notee,jeniss);
                if(checkupdatedata==true) {
                    Toast.makeText(Edit.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    Edit.this.finish();
                }
                else{
                    Toast.makeText(Edit.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }     }   });
    }
}
