package in.nirajwaghtech.truthordare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AppAbout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_about);
    }

    public void clickedOk(View view) {
        finish();
    }
}
