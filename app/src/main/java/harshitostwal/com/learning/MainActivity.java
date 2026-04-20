package harshitostwal.com.learning;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        content = findViewById(R.id.editTextText2);
    }

    public void writeFile(View view) {
        try {
            FileOutputStream fileOutputStream = openFileOutput("data.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(content.getText().toString().getBytes());
            fileOutputStream.close();
            Toast.makeText(this, "Data Saved!", Toast.LENGTH_SHORT).show();
            content.setText("");
        }
        catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void readFile(View view) {
        try {
            FileInputStream fileInputStream = openFileInput("data.txt");
            int c ;
            String msg= "";
            while((c = fileInputStream.read())!= -1){
                msg += (char)c;
            }
            fileInputStream.close();
            content.setText(msg);
            Toast.makeText(this, "Data Retrieved!", Toast.LENGTH_SHORT).show();
        }catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
