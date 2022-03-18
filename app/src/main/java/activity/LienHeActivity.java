package activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.example.doanqx.R;

import ultil.CheckConnection;


public class LienHeActivity extends AppCompatActivity {
    Toolbar toolbarlienhe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lien_he);
        toolbarlienhe = findViewById(R.id.toolbarlienhe);
        ActionToolbar();
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarlienhe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarlienhe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}