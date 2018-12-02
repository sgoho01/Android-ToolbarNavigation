package com.apl.song.toolbarnavigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.findViewById(R.id.toolbar_email).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "아직 도착한 메세지가 없습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        // Navigator 등록 (toolbar에)
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // 네비게이션뷰 리스너 생성
        NavigationView navigationView = (NavigationView) findViewById(R.id.main_navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.first:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout, new FirstFragment()).commit();
                        Toast.makeText(MainActivity.this, "공지사항 클릭", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.second:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout, new SecondFragment()).commit();
                        Toast.makeText(MainActivity.this, "사진 클릭", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.third:
                        Toast.makeText(MainActivity.this, "정보수정 클릭", Toast.LENGTH_SHORT).show();
                        break;
                }

                // 네비게이션 창 닫기
                drawerLayout.closeDrawer(Gravity.START);

                return true;
            }
        });
    }
}
