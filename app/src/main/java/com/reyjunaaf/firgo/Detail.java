package com.reyjunaaf.firgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Detail extends AppCompatActivity {
    TextView tvnama,tvjenis,tvjumlah,tvnote,tvid;
    Button delete,edit;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvjenis =findViewById(R.id.TVjenis);
        tvnama =findViewById(R.id.TVnama);
        tvjumlah =findViewById(R.id.TVjumlah);
        tvnote =findViewById(R.id.TVnote);
        tvid = findViewById(R.id.TVid);
        delete = findViewById(R.id.BTNdel);
        edit = findViewById(R.id.BTNedit);
        DB = new DBHelper(this);
        Intent i = getIntent();
        tvnama.setText(i.getStringExtra("nama").toString());
        String jenis = i.getStringExtra("jenis").toString();
        if (jenis.equals("+")){
            tvjenis.setText("Income");
        }else {
            tvjenis.setText("Outcome");
        }
        tvjumlah.setText("Rp. "+i.getStringExtra("jumlah").toString());
        tvnote.setText(i.getStringExtra("note").toString());
        tvid.setText(i.getStringExtra("id").toString());
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = i.getStringExtra("id").toString();
                int jumlah = Integer.parseInt(i.getStringExtra("jumlah"));
                String jenis = i.getStringExtra("jenis");
                Boolean checkudeletedata = DB.deletedata(id,jumlah,jenis);
                if(checkudeletedata==true){
                    Toast.makeText(Detail.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    Detail.this.finish();}
                else{
                    Toast.makeText(Detail.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }        }});
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent l = new Intent(getApplicationContext(),Edit.class);
                l.putExtra("id",i.getStringExtra("id").toString());
                l.putExtra("nama",i.getStringExtra("nama").toString());
                l.putExtra("jenis",i.getStringExtra("jenis").toString());
                l.putExtra("jumlah",i.getStringExtra("jumlah").toString());
                l.putExtra("note",i.getStringExtra("note").toString());
                startActivity(l);
            }
        });

    }
}
