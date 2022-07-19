package com.reyjunaaf.firgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Add extends AppCompatActivity {
    Button add;
    DBHelper DB;
    EditText kegiatan, jumlah, note;
    RadioGroup RG;
    RadioButton rb1,rb2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        add = findViewById(R.id.btnEDIT);
        kegiatan = findViewById(R.id.TFnama);
        jumlah = findViewById(R.id.TFjumlah);
        note = findViewById(R.id.TFnote);
        RG = findViewById(R.id.Radiogrup);
        rb1=findViewById(R.id.RBplus);
        rb2=findViewById(R.id.RBminus);
        DB = new DBHelper(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = kegiatan.getText().toString(); 
                String jumlahl = jumlah.getText().toString();
                String notel = note.getText().toString();
                String jeniss;
                int RadioID = RG.getCheckedRadioButtonId();
                switch (RadioID) {
                    case R.id.RBplus: {
                        jeniss="+";
                        break;
                    }
                    case R.id.RBminus: {
                        jeniss="-";
                        break;
                    }
                    default:
                        throw new IllegalStateException("Unexpected value: " + RadioID);
                }

                Boolean checkinsertdata = DB.insertuserdata(nameTXT, jeniss, jumlahl, notel);
                if(checkinsertdata==true) {
                    Toast.makeText(Add.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    Add.this.finish();
                }
                else {
                    Toast.makeText(Add.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        Add.this.finish();
    }
}