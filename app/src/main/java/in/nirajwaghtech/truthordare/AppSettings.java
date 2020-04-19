package in.nirajwaghtech.truthordare;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class AppSettings extends AppCompatActivity {

    private Spinner spinnerPlayers, spinnerDuration, spinnerMinRotations, spinnerMaxRotations;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int minRotationPos, maxRotationPos;
    private final Integer[] minRotationsList ={1,3,5,7,9,11,13};
    private final Integer[] maxRotationsList ={2,4,6,8,10,12,14,16};
    private final String[] durationsList={"2 sec", "4 sec", "6 sec", "8 sec", "10 sec"};
    final String[] playersCount ={"Two", "Three", "Four", "Five", "Six"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings);

        spinnerPlayers=findViewById(R.id.spinnerPlayers);
        spinnerDuration=findViewById(R.id.spinnerDuration);
        spinnerMinRotations=findViewById(R.id.spinnerMinRotations);
        spinnerMaxRotations=findViewById(R.id.spinnerMaxRotations);

        sharedPreferences=getSharedPreferences("settings", MODE_PRIVATE);
        editor= sharedPreferences.edit();

        setPlayersSpinner();
        setMinRotationsSpinner();
        setMaxRotationsSpinner();
        setDurationSpinner();

    }

    private void setMaxRotationsSpinner() {

        ArrayAdapter<Integer> arrayAdapter=new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, maxRotationsList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMaxRotations.setAdapter(arrayAdapter);

        spinnerMaxRotations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(maxRotationsList[position]<=minRotationsList[minRotationPos]){

                    Toast.makeText(AppSettings.this, "Max Rotations cannot be smaller than min Rotations", Toast.LENGTH_SHORT).show();
                    spinnerMaxRotations.setSelection(maxRotationPos);

                }else{
                    maxRotationPos=position;
                    editor.putInt("maxRotationPos", position);
                    editor.apply();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setMinRotationsSpinner() {

        ArrayAdapter<Integer> arrayAdapter=new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, minRotationsList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMinRotations.setAdapter(arrayAdapter);

        spinnerMinRotations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(minRotationsList[position]>=maxRotationsList[maxRotationPos]){

                    Toast.makeText(AppSettings.this, "Min Rotations cannot be greater than Max Rotations", Toast.LENGTH_SHORT).show();
                    spinnerMinRotations.setSelection(minRotationPos);

                }else{
                    minRotationPos=position;
                    editor.putInt("minRotationPos", position);
                    editor.apply();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setPlayersSpinner() {

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, playersCount);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPlayers.setAdapter(arrayAdapter);

        spinnerPlayers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editor.putInt("playersCountPosition", position);
                editor.apply();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setDurationSpinner() {

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, durationsList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDuration.setAdapter(arrayAdapter);

        spinnerDuration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editor.putInt("rotationDurationPos", position);
                editor.apply();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        minRotationPos=sharedPreferences.getInt("minRotationPos", 1);
        maxRotationPos=sharedPreferences.getInt("maxRotationPos", 4);
        int rotationDurationPos = sharedPreferences.getInt("rotationDurationPos", 1);
        int playersCountPosition = sharedPreferences.getInt("playersCountPosition", 0);

        spinnerPlayers.setSelection(playersCountPosition);
        spinnerMinRotations.setSelection(minRotationPos);
        spinnerMaxRotations.setSelection(maxRotationPos);
        spinnerDuration.setSelection(rotationDurationPos);
    }

    public void settingsDone(View view) {
        finish();
    }
}
