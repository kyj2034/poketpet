package com.example.pocketpetlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class JoinActivity3 extends AppCompatActivity {

    String nickSt;
    String gender;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join3);

        MyDbHelper myDbHelper = new MyDbHelper(getApplicationContext());
        database = myDbHelper.getWritableDatabase();

        Button nextbtn2 = (Button) findViewById(R.id.nextbtn2); // 다음페이지로 넘기는 버튼
        EditText nickname = (EditText) findViewById(R.id.nickname); // 닉네임
        Spinner spinner_year = (Spinner)findViewById(R.id.spinner_year); // 월 선택
        Spinner spinner_month = (Spinner)findViewById(R.id.spinner_month); // 년 선택
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup); // 성별 선택


        nextbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext(), JoinActivity4.class);
                String spinnerYear = spinner_year.getSelectedItem().toString(); // 선택한 년도 spinnerYear에 넣음
                String spinnerMonth = spinner_month.getSelectedItem().toString(); // 선택한 월 spinnerMonth에 넣음

                int birthDay = Integer.parseInt(spinnerYear + spinnerMonth);
                nickSt= nickname.getText().toString(); // 닉네임 넣음

                if(nickSt.length() == 0){
                    Toast toast = Toast.makeText(JoinActivity3.this, "닉네임은 필수입력 사항입니다",Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId(); // 선택한 성별 넣음
                if (checkedRadioButtonId == -1) {
                    // No item selected
                }

                else{
                    if (checkedRadioButtonId == R.id.woman) {
                        gender= "woman";
                    }else if(checkedRadioButtonId == R.id.man){
                        gender= "man";
                    }
                }

                intent.putExtra("NICKNAME",nickSt); // 보내고
                intent.putExtra("BIRTHDAY",birthDay);
                intent.putExtra("SEX", gender);

                String emailSt = getIntent().getStringExtra("MEMBER_ID"); //받고
                String passwordSt2 =getIntent().getStringExtra("PASSWORD");

                intent.putExtra("MEMBER_ID",emailSt); // 2에서 받은 걸 다시 보내고
                intent.putExtra("PASSWORD", passwordSt2);

                startActivity(intent);

            }
        });
    }


}