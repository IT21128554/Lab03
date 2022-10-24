package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

     EditText txtID , txtName , txtAdd , txtConNo;
     Button btnSave , btnShow , btnUpdate , btnDelete;
     DatabaseReference dbRef;
     Student std;



     private void clearControls(){
         txtID.setText("");
         txtName.setText("");
         txtAdd.setText("");
         txtConNo.setText("");

     }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        txtID = findViewById(R.id.EtID);
        txtName = findViewById(R.id.EtName);
        txtAdd = findViewById(R.id.EtAddress);
        txtConNo = findViewById(R.id.EtConNo);

        btnSave = findViewById(R.id.BtnSave);
        btnShow = findViewById(R.id.BtnShow);
        btnUpdate = findViewById(R.id.BtnUpdate);
        btnDelete = findViewById(R.id.BtnDelete);


        std = new Student();


        dbRef = FirebaseDatabase.getInstance().getReference().child("Student");

        try {

            if(TextUtils.isEmpty(txtID.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please Enter An Id" , Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(txtName.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please Enter a Name" , Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(txtAdd.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please Enter a Address" , Toast.LENGTH_SHORT).show();

                    else{

                        std.setID(txtID.getText().toString().trim());
                        std.setName(txtName.getText().toString().trim());
                        std.setAddress(txtAdd.getText().toString().trim());
                        std.setConNo(Integer.parseInt(txtConNo.getText().toString().trim()));

                        dbRef.push().setValue(std);

                Toast.makeText(getApplicationContext(), "Data Saved" , Toast.LENGTH_SHORT).show();
                clearControls();

                        }
        }

 catch (NumberFormatException e){
     Toast.makeText(getApplicationContext(), "Invalid Contact No" , Toast.LENGTH_SHORT).show();
 }




    }
}