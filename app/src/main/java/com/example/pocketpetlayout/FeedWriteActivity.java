package com.example.pocketpetlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

public class FeedWriteActivity extends AppCompatActivity {
    public static final String TAG = "FeedWriteActivity";

    // 등록, 수정, 삭제 버튼
    Button c_Btn;
    Button u_Btn;
    Button d_Btn;
    TextView getImgBtn;
    ImageView imgView;

    //
    EditText title;
    String titleStr;
    EditText content;
    String contentStr;
    String writer;
    String category;
    String imgName;
    String reg_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_write);


        //등록 수정 삭제 버튼
        c_Btn = findViewById(R.id.c_button);
        u_Btn = findViewById(R.id.u_button);
        d_Btn = findViewById(R.id.d_button);

        //
        MyDbHelper dbHelper = new MyDbHelper(getApplicationContext());
        title = findViewById(R.id.title);
        getImgBtn = findViewById(R.id.getImgBtn);
        imgView = findViewById(R.id.imgView);

        //사진 등록
        getImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 101);
            }
        });

        //글 등록
        c_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //제목
                titleStr = title.getText().toString();

                Log.i(TAG, "제목 :" + titleStr
                        + " , 이미지 이름 : " + imgName);

                ContentValues feed = new ContentValues();
                feed.put(Feed.FEED_TITLE, titleStr);
                feed.put(Feed.WRITER, "user");
                feed.put(Feed.IMAGE, imgName);

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                long newRowId = db.insert(Feed.TABLE_NAME, null, feed);
                Log.i(TAG, "new row id: " + newRowId);

                Toast.makeText(getApplicationContext(), "글 등록 성공", Toast.LENGTH_LONG).show();
                finish();

            }
        });

        //글 삭제
        d_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                title.setText("");

                try{
                    File file = getCacheDir();
                    File[] flist = file.listFiles();
                    for(int i=0; i< flist.length; i++) {
                        if (flist[i].getName().equals(imgName)) {
                            flist[i].delete(); //파일삭제
                            Toast.makeText(getApplicationContext(), "글 삭제 성공", Toast.LENGTH_SHORT).show();
                        }
                    }
                    finish();
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(), "글 삭제 실패", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        //갤러리
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101){
            if(resultCode == RESULT_OK){
                Uri fileUri = data.getData();
                String uri = data.getDataString();
                imgName = uri.substring(57);
                Log.i(TAG, "파일 Uri : " + fileUri);
                ContentResolver resolver = getContentResolver();
                try{
                    //파일URI inputstream
                    InputStream instream = resolver.openInputStream(fileUri);
                    Bitmap imgBitmap = BitmapFactory.decodeStream(instream);
                    // 이미지 저장
                    imgView.setImageBitmap(imgBitmap);
                    instream.close();
                    //Bitmap 을 내부저장소에 저장
                    saveBitmapToJpeg(imgBitmap);
                    Toast.makeText(getApplicationContext(), "파일 불러오기 성공", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "파일 불러오기 실패", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void saveBitmapToJpeg(Bitmap bitmap){ // 선택한 이미지 내부 저장소에 저장
        File tempFile = new File(getCacheDir(), imgName); // 파일경로와 이름 넣기
        try{
            tempFile.createNewFile(); //자동으로 빈 파일을 생성하기
            FileOutputStream out = new FileOutputStream(tempFile); // 파일을 쓸 수 있는 스트림을 준비하기
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out); //compress 함수를 사용해 스트림에 비트맵을 저장
            out.close(); //스트림 닫아주기
            Toast.makeText(getApplicationContext(), "파일 저장 성공",Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "파일 저장 실패",Toast.LENGTH_SHORT).show();
        }
    }
}