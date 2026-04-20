package harshitostwal.com.learning;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sp ;
    EditText fullName, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullName = findViewById(R.id.editTextText);
        email = findViewById(R.id.editTextText2);
    }

    public void saveData(View view){
        sp  = getSharedPreferences("Data",0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("fullName", fullName.getText().toString());
        editor.putString("email",email.getText().toString());
        editor.apply();
        fullName.setText("");
        email.setText("");
        Toast.makeText(this,"Data Saved", Toast.LENGTH_SHORT).show();
    }

    public void getData (View view){
        sp = getSharedPreferences("Data",0);
        if(sp.contains("fullName")){
            fullName.setText(sp.getString("fullName",""));
        }
        if(sp.contains("email")){
            email.setText(sp.getString("email",""));
        }

        Toast.makeText(this, "Data Retrieved", Toast.LENGTH_SHORT).show();
    }
}
