package com.example.pocketpetlayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class ProfileFragment extends Fragment {

    Button ProfileFixBtn;
    Button CheckPetBtn;

    public ProfileFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override  // Inflate the layout for this fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        // 프로필 편집 , 반려동물 편집 버튼
        ProfileFixBtn = view.findViewById(R.id.profileButton1);
        ProfileFixBtn.setOnClickListener(this::onClick);
        CheckPetBtn = view.findViewById(R.id.profileButton2);
        CheckPetBtn.setOnClickListener(this::onClick);

        /*
         * 그리드뷰에 이미지 띄우기
         */
        //화면크기 얻기
        Display display = ((WindowManager)getActivity().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int displayWidth = display.getWidth();
        int displayHeight = display.getHeight();

        GridView gridView = (GridView) view.findViewById(R.id.profileGrid1);
        ProfileAdapter adapter = new ProfileAdapter(getActivity(),displayWidth); //가로크기의 정보를 같이 넘긴다.
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), position+"클릭함", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    //버튼 클릭 이벤트
    public void onClick(View view){
        switch (view.getId()){
            case R.id.profileButton1:
                Intent intent = new Intent(getActivity().getApplicationContext(), ProfileFix.class);
                startActivity(intent);
                break;
            case R.id.profileButton2:
                Intent intent2 = new Intent(getActivity().getApplicationContext(), PetProfileCheck.class);
                startActivity(intent2);
                break;
        }
    }
}