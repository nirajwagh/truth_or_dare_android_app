package in.nirajwaghtech.truthordare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView img_bottle, img_circle;
    private int lastPosition, newPosition, maxRotationPos, minRotationPos, rotationDurationPos, duration, minRotation, maxRotation;
    private Random random=new Random();
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int[] imgCircleIds={R.drawable.circle2, R.drawable.circle3, R.drawable.circle4, R.drawable.circle5, R.drawable.circle6};
    private final Integer[] minRotationsList ={1,3,5,7,9,11,13};
    private final Integer[] maxRotationsList ={2,4,6,8,10,12,14,16};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_bottle=findViewById(R.id.img_bottle);
        img_circle=findViewById(R.id.img_circle);
        navigationView=findViewById(R.id.navigationView);
        drawerLayout=findViewById(R.id.drawerLayout);

        sharedPreferences=getSharedPreferences("settings", MODE_PRIVATE);
        editor= sharedPreferences.edit();

        img_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newPosition=random.nextInt(maxRotation-minRotation)+minRotation;
                int pivoitX=img_bottle.getWidth()/2;
                int pivoitY=img_bottle.getHeight()/2;
                Animation rotate= new RotateAnimation(lastPosition, newPosition, pivoitX, pivoitY);
                rotate.setDuration(duration);
                rotate.setFillAfter(true);
                img_bottle.startAnimation(rotate);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.menuSettings:
                        Intent intentSettings=new Intent(MainActivity.this, AppSettings.class);
                        startActivity(intentSettings);
                        break;

                    case R.id.menuAbout:
                        Intent intentAbout=new Intent(MainActivity.this, AppAbout.class);
                        startActivity(intentAbout);
                        break;

                    case R.id.menuSourceCode:
                        Uri sourceCodeUri=Uri.parse("https://github.com/nirajwagh/truth_or_dare_android_app?files=1");
                        Intent intentSourceCode=new Intent(Intent.ACTION_VIEW, sourceCodeUri);
                        startActivity(intentSourceCode);
                        break;

                    case R.id.menuOtherApps:
                        Uri playStoreProfileUri=Uri.parse("https://play.google.com/store/apps/developer?id=The+Minimal+Developer");
                        Intent intentPlayStoreProfile=new Intent(Intent.ACTION_VIEW, playStoreProfileUri);
                        startActivity(intentPlayStoreProfile);
                        break;

                    case R.id.menuYoutube:
                        Uri youtubeChannelUri=Uri.parse("https://www.youtube.com/channel/UCG_1skZUBvz0MOjEe05TECQ/featured");
                        Intent youtubeChannelIntent=new Intent(Intent.ACTION_VIEW, youtubeChannelUri);
                        startActivity(youtubeChannelIntent);
                        break;
                }
                return false;
            }
        });
    }


    public void openMenu(View view) {
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    public void openSettings(View view) {
        Intent intent=new Intent(this, AppSettings.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        int playersCountPosition=sharedPreferences.getInt("playersCountPosition", 0);
        img_circle.setImageResource(imgCircleIds[playersCountPosition]);
        minRotationPos=sharedPreferences.getInt("minRotationPos", 1);
        maxRotationPos=sharedPreferences.getInt("maxRotationPos", 4);
        rotationDurationPos=sharedPreferences.getInt("rotationDurationPos", 1);
        duration=((rotationDurationPos+1)*2)*1000;
        minRotation=minRotationsList[minRotationPos]*360;
        maxRotation=maxRotationsList[maxRotationPos]*360;

    }
}
