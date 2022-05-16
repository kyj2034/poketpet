package com.example.pocketpetlayout;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.io.InputStream;

public class ProfileFix extends AppCompatActivity {

    Bitmap bitmap;
    ImageView imageView;
    private static final int REQUEST_CODE = 0;
    private static final String TAG = "ProfileFix";

    // POPUPMENU 부분
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        getMenuInflater().inflate(R.menu.toolbar_checkmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            case R.id.toolbar_check: // 체크 버튼을 통해 반려동물 프로필로 이동
                Intent intent = new Intent(getApplicationContext(), ProfileFragment.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_fix);

        // 상단 툴바
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        // 이미지 클릭 하여 메뉴 보이기
        imageView = findViewById(R.id.img_user);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu pop = new PopupMenu(getApplicationContext(), view);
                getMenuInflater().inflate(R.menu.profile_menu, pop.getMenu());

                pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.one: //  카메라를 이용하여 프로필 변경
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                activityResultPicture.launch(intent);
                                break;
                            case R.id.two: // 갤러리를 이용하여 프로필 변경
                                Intent intent2 = new Intent();
                                intent2.setType("image/*");
                                intent2.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(intent2, REQUEST_CODE);
                                break;
                            case R.id.three: // 기본 이미지를 이용하여 프로필 변경
                                imageView.setImageResource(R.mipmap.ic_launcher);
                        }
                        return true;
                    }
                });
                pop.show();
            }
        });
    }
    // 카메라 사진
    ActivityResultLauncher<Intent> activityResultPicture = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null){
                        Bundle extras = result.getData().getExtras();

                        bitmap = (Bitmap) extras.get("data");

                        imageView.setImageBitmap(bitmap);
                    }
                }
            });

    @Override // 갤러리
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();

                    imageView.setImageBitmap(img);
                } catch (Exception e) {

                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }
}