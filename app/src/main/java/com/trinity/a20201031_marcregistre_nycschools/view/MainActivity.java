package com.trinity.a20201031_marcregistre_nycschools.view;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.trinity.a20201031_marcregistre_nycschools.R;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    @Override public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));
        Timber.d("MainActivity Loaded");
    }
}
