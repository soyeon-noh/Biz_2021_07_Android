package com.callor.cacao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.callor.cacao.Adapter.ChattAdapter;
import com.callor.cacao.Model.Chatt;
import com.callor.cacao.service.FirebaseServiceImplV1;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText txt_msg;
    private AppCompatButton btn_send;

    private RecyclerView chat_list_view;
    private ChattAdapter chattAdapter;
    private List<Chatt> chattList;

    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 기존액션바말고 내가 커스터마이징한 액션바를 가져오겠다.
        Toolbar main_toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(main_toolbar);

        /*
        새로운  Activity가 열렸을때
        이전 Activity(page)로 돌아가기 아이콘을 표시하기
        MainActivity에서는 의미가 없기 때문에 사용하지 않는다.
         */
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true); // 이전 페이지로가는 <- 뒤로가기 버튼이 생긴다!


        txt_msg = findViewById(R.id.txt_msg);
        btn_send = findViewById(R.id.btn_send);
        chat_list_view = findViewById(R.id.chatt_list_view);

        chattList = new ArrayList<Chatt>();

        chattAdapter = new ChattAdapter(chattList);

        chat_list_view.setAdapter(chattAdapter);

        RecyclerView.LayoutManager layoutManager
                = new LinearLayoutManager(this);
        chat_list_view.setLayoutManager(layoutManager);

        FirebaseDatabase dbConn = FirebaseDatabase.getInstance();

        dbRef = dbConn.getReference("chatting");

        ChildEventListener childEventListener
                = new FirebaseServiceImplV1(chattAdapter);

        dbRef.addChildEventListener(childEventListener);




        btn_send.setOnClickListener(view->{
            String msg = txt_msg.getText().toString();
            if(msg != null && !msg.isEmpty()){

                String toastMsg = String.format("메시지 : %s", msg);
                Toast.makeText(MainActivity.this, toastMsg, Toast.LENGTH_SHORT).show();

                Chatt chatVO = new Chatt();
                chatVO.setMsg(msg);
                chatVO.setName("노소노소");

                Log.d("클릭", chatVO.toString());

                dbRef.push().setValue(chatVO);
                txt_msg.setText("");
            }
        });
    }//end onCreate

    /*
    Custom 한 Toolbar가 (Main)Activity에 적용될때
    setSupportActionbar() method가 실행될때
    event가 발생하고 자동으로 호출되는 method

    Toolbar를 사용하여 ActionBar를 Custom하는 이유중에 하나가
    onCreateOptionMenu() method를 Override하여
    더욱 세밀한 Customiziong을 하기 위함이다.

    ToolBar에 사용자 정의형 menu를 설정하여
    다른 기능을 수행하도록 하는 UI를 구현할 수 있다.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_tool_menu, menu); // inflate해서 대신쓰겠다

        return true;
    }

    /*
    ActionBar에 설정된 Option Menu의 특정한 항목(item)을
    클릭하면 호출되는 method
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int menu_item = item.getItemId();
        if(menu_item == R.id.app_bar_settings){ //search 버튼이 클릭되면
            Toast.makeText(this, "설정메뉴 클릭됨", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}