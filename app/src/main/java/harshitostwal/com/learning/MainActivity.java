package harshitostwal.com.learning;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    int count = 0;

    int fontSize = 18;
    int fontColor = 0;




    String[] languages = {"Tamil", "English","Hindi","Marathi","Telugu","Malayalam","Urdu"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        TextView txtView = (TextView) findViewById(R.id.textView);
        txtView.setText("Welcome, Harshit Ostwal");

        Button btn1 = (Button) findViewById(R.id.button);
        Button btn2 = (Button) findViewById(R.id.button1);
        TextView counter = (TextView) findViewById(R.id.txtCounter);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                counter.setText("Counter - " + count);
                Toast.makeText(MainActivity.this, "Increased By +1", Toast.LENGTH_SHORT).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count > 0 ){
                    count--;
                }
                counter.setText("Counter - " + count);
                Toast.makeText(MainActivity.this, "Decrease By -1", Toast.LENGTH_SHORT).show();
            }
        });


        Button btn3 = (Button) findViewById(R.id.button3);
        Button btn4 = (Button) findViewById(R.id.button4);
        TextView txt2 = (TextView) findViewById(R.id.textView1);


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt2.setTextSize(fontSize);
                fontSize += 5;
                if(fontSize == 38) {
                    fontSize = 18;
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fontColor++;
                if(fontColor == 5) {
                    fontColor = 1;
                }

                switch (fontColor){
                    case 1:
                        txt2.setTextColor(Color.RED);
                        break;
                    case 2:
                        txt2.setTextColor(Color.BLUE);
                        break;
                    case 3:
                        txt2.setTextColor(Color.GREEN);
                        break;
                    case 4:
                        txt2.setTextColor(Color.YELLOW);
                        break;
                    case 5:
                        txt2.setTextColor(Color.CYAN);
                        break;
                    default:
                        txt2.setTextColor(Color.BLACK);
                }
            }
        });


        EditText editText = (EditText) findViewById(R.id.editTextText);
        TextView txt3 =(TextView) findViewById(R.id.textView3);
        Button btn5 = (Button) findViewById(R.id.button5);

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt3.setText(editText.getText());
                Toast.makeText(MainActivity.this, "Changed Full Name", Toast.LENGTH_SHORT).show();
            }
        });

        ToggleButton tgBtn1 = (ToggleButton) findViewById(R.id.toggleButton);
        ToggleButton tgBtn2 = (ToggleButton) findViewById(R.id.toggleButton2);

        Button btn6 = (Button) findViewById(R.id.button6);

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Toogle Status : \n"+ "Lights - "+tgBtn1.getText() +", Fans - "+ tgBtn2.getText() + "\n"  , Toast.LENGTH_SHORT).show();
            }
        });

        RadioGroup rg1 = (RadioGroup) findViewById(R.id.radioGroup);

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
                RadioButton rb1 = (RadioButton) findViewById(R.id.radioButton);
                RadioButton rb2 = (RadioButton) findViewById(R.id.radioButton2);
                RadioButton rb3 = (RadioButton) findViewById(R.id.radioButton3);

                if(checkedId == rb1.getId()){
                    Toast.makeText(MainActivity.this, "Coding", Toast.LENGTH_SHORT).show();
                }
                else if(checkedId == rb2.getId()){
                    Toast.makeText(MainActivity.this, "Disky Jockey", Toast.LENGTH_SHORT).show();
                }
                else if(checkedId == rb3.getId()){
                    Toast.makeText(MainActivity.this, "Photography", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, languages);
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setThreshold(1);

        CheckBox chk1 = (CheckBox) findViewById(R.id.checkBox);
        CheckBox chk2 = (CheckBox) findViewById(R.id.checkBox1);

        Button btn7 = (Button) findViewById(R.id.button8);

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder option = new StringBuilder();
                if(chk1.isChecked())
                {
                    option.append("Apple");
                }
                else if(chk2.isChecked())
                {
                    option.append("Orange");
                }
                else {
                    option.append("Nothing");
                }
                Toast.makeText(MainActivity.this, "Check Box Submitted :- "+ option.toString() , Toast.LENGTH_SHORT).show();
            }
        });

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        SeekBar seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        SeekBar seekBar3 = (SeekBar) findViewById(R.id.seekBar3);

        seekBar.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) this);
        seekBar2.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener)this);
        seekBar3.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) this);

    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}