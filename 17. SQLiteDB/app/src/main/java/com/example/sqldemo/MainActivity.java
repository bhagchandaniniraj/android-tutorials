package com.example.sqldemo;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kotlin.time.ExperimentalTime;

public class MainActivity extends AppCompatActivity {
    private static EditText id,nm, sn, sal;
    private static Button addBt, showBt, updateBt, deleteBt;
    private static DBDemo obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        id = (EditText) findViewById(R.id.et_id);
        nm = (EditText) findViewById(R.id.et_nm);
        sn = (EditText) findViewById(R.id.et_sn);
        sal =(EditText) findViewById(R.id.et_sal);
        addBt = (Button) findViewById(R.id.add_btn);
        showBt = (Button) findViewById(R.id.show_bt);
        updateBt = (Button) findViewById(R.id.btn_upd);
        deleteBt = (Button) findViewById(R.id.btn_dlt);

        obj = new DBDemo(this,"employees.db", null, 1);
        fetchData();
        addData();
        updateData();
        dltData();
    }
    public void dltData(){
        deleteBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int isDeleted = obj.deleteData(id.getText().toString());
                if(isDeleted > 0 ){
                    Toast.makeText(MainActivity.this, "Deleted Successfully Rows: " + isDeleted, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "No Rows Deleted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void updateData(){
        updateBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int isUpdated = obj.updateData(id.getText().toString(), nm.getText().toString(),sn.getText().toString(), Integer.parseInt(sal.getText().toString()));
                if(isUpdated > 0 ){
                    Toast.makeText(MainActivity.this, "Updated Successfully Rows: " + isUpdated, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "No Rows Updated", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void addData(){
        addBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nm.getText().toString();
                String sur = sn.getText().toString();
                Integer salary = Integer.parseInt(sal.getText().toString());
                boolean res = obj.addData(name, sur, salary);
                if(res){
                    Toast.makeText(MainActivity.this, "Inserted Successfully", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "Something went wrong...", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void fetchData(){
        showBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = obj.fetchData();
                if(res.getCount() == 0 ){
                    showMessage("EMP Table", "No Data Found!!!");
                    Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_LONG).show();
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID: "+ res.getString(0)+"\n");
                    buffer.append("Name: "+ res.getString(1)+"\n");
                    buffer.append("Surname: "+ res.getString(2)+"\n");
                    buffer.append("Salary: "+ res.getString(3)+"\n");
                    buffer.append("===================================\n");
                }
                showMessage("Show Data", buffer.toString());
            }
        });
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}