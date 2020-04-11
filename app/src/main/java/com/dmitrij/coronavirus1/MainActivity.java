package com.dmitrij.coronavirus1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getFragmentManager()
                if (getSupportFragmentManager().findFragmentById(R.id.container) == null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.container,
                            ContactsFragment.newInstance())
                            .commit();
                }
            }
        });


    }
}
