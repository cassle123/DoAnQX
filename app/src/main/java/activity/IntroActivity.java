package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.doanqx.R;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
    }

    public void btnbatdau(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void btnthoat(View view) {
        finish();
    }
}