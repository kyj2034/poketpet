package com.example.pocketpetlayout;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase database;
    String sql;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDbHelper myDbHelper = new MyDbHelper(getApplicationContext());
        database = myDbHelper.getWritableDatabase();

        EditText id1 = (EditText) findViewById(R.id.id1);
        EditText password1 = (EditText) findViewById(R.id.password1);
        Button join1 = (Button) findViewById(R.id.join1);
        Button login1 = (Button) findViewById(R.id.login1);



        login1.setOnClickListener(new View.OnClickListener() { //로그인 버튼을 누르면 메인페이지로 이동
            @Override
            public void onClick(View view) {
                String id = id1.getText().toString();
                String pw = password1.getText().toString();
                SQLiteDatabase db = myDbHelper.getReadableDatabase();
                Cursor c = db.rawQuery("SELECT * FROM " + Member.TABLE_NAME, null);

                // id , pw를 입력하지 않았을 경우
                if(id.length() == 0 || pw.length() == 0){
                    Toast toast = Toast.makeText(MainActivity.this, "아이디와 비밀번호는 필수 입력사항입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                sql = "SELECT member_id FROM "+ Member.TABLE_NAME + " WHERE member_id = '" + id + "'";
                cursor = database.rawQuery(sql, null);

                // id를 잘못입력했을 경우
                if(c.getCount() != 1){

                    Toast toast = Toast.makeText(MainActivity.this, "존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                sql = "SELECT password FROM "+ Member.TABLE_NAME + " WHERE password = '" + id + "'";
                cursor = database.rawQuery(sql, null);

                c.moveToFirst();
                if(c!=null && !pw.equals(c.getString(0))){

                    Toast toast = Toast.makeText(MainActivity.this, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    //로그인성공
                    Toast toast = Toast.makeText(MainActivity.this, "로그인성공", Toast.LENGTH_SHORT);
                    toast.show();
                    //인텐트 생성 및 호출
                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                c.close();

            }
        });

        // DB에 저장되어 있지 않은 아이디, 비밀번호를 입력할 경우 재입력을 요구하는 Toast 메시지


        join1.setOnClickListener(new View.OnClickListener() { //회원가입 버튼을 누르면 회원가입페이지로 이동
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), JoinActivity2.class);
                startActivity(intent);



            }
        });

    }

}

