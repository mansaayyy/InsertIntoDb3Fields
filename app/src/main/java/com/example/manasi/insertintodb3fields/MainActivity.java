package com.example.manasi.insertintodb3fields;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DB_Controller db;
    private EditText e1,e2,e3;
    private TextView t1;
    String item;
    String cost,qty;
    int sum = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DB_Controller(this);
        e1 = (EditText)findViewById(R.id.item);
        e2 = (EditText)findViewById(R.id.cost);
        e3 = (EditText)findViewById(R.id.qty);
        t1 = (TextView)findViewById(R.id.result);
    }

    public void insert(View v) {
        item = e1.getText().toString();
        cost = e2.getText().toString();
        qty = e3.getText().toString();

        db.insertitem(item,cost,qty);

        e1.setText("");
        e2.setText("");
        e3.setText("");
        Toast.makeText(getApplicationContext(),"Here",Toast.LENGTH_SHORT).show();
    }

    public void list(View v) {
        t1.setText("");
        Cursor c = db.listitem();
        while(c.moveToNext()) {
            String s= c.getString(0)+ "    " + c.getString(1) + "      " + c.getString(2) + "\n";
            t1.append(s);
        }
    }

    public void getCost(View v) {
        t1.setText("");
        item = e1.getText().toString();
        String s = db.get_cost(item);
        int c = Integer.valueOf(s).intValue();
        sum+=c;

        t1.append(s);

        String q = db.get_qty(item);

        db.update_qty(item,s,q);

        Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_SHORT).show();
    }

    public void getTotal(View v) {
        String total = Integer.toString(sum);
        t1.setText("");

        t1.append(total);
    }
}


