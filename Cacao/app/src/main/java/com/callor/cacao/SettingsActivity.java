package com.callor.cacao;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int setting_item = item.getItemId();

        /**
         * MainActivity에서 호출되어 열린 새로운 Activity에 있는
         * optionMenu의 item은 "R.id.아이템" 으로 사용하지 않고
         * 앞에 android를 붙인 "android.R.id.아이템" 으로 사용해야 한다.
         */
        if(setting_item == android.R.id.home){


            /*
            onBackPressed() method는 이전 화면으로 이동하는 method
            현재 Activity를 닫고 이전 Activity를 열어주는 method
            이 method가 이전 Activity를 정확히 인지하도록
            Manifest.xml의 현재 Activity설정 부분에
            다음의 코드를 추가해주자

            android:parentActivityName=".MainActivity"
             */

            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }
}