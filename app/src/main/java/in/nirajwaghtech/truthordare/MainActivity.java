package in.nirajwaghtech.truthordare;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView bottle;
    private int lastPosition;
    private Random random=new Random();
    private int newPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottle=findViewById(R.id.bottle);


        bottle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                newPosition=random.nextInt(1500)+450;
                int pivoitX=bottle.getWidth()/2;
                int pivoitY=bottle.getHeight()/2;
                Animation rotate= new RotateAnimation(lastPosition, newPosition, pivoitX, pivoitY);
                rotate.setDuration(3000);
                rotate.setFillAfter(true);
                showToast();
                bottle.startAnimation(rotate);
            }
        });


    }

    public void showToast(){
        LayoutInflater inflater= getLayoutInflater();
        View layout=inflater.inflate(R.layout.mytoast, (ViewGroup) findViewById(R.id.root));

        Toast toast=new Toast(getApplicationContext());
        toast.setView(layout);
        toast.setGravity(Gravity.BOTTOM, 0,100);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
