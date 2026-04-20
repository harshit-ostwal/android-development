package harshitostwal.com.learning;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText content;
    private SQLiteDatabase db;
    private Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        content = findViewById(R.id.editTextText2);

        db = openOrCreateDatabase("Data", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS data(content VARCHAR)");
    }

    public void insertData(View view) {
        String text = content.getText().toString();
        if (text.trim().isEmpty()) {
            Toast.makeText(this, "Enter some content", Toast.LENGTH_SHORT).show();
            return;
        }
        db.execSQL("INSERT INTO data VALUES('" + text + "')");
        Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
        content.setText("");
    }

    public void updateData(View view) {
        String text = content.getText().toString();
        db.execSQL("UPDATE data SET content='" + text + "' WHERE content='" + text + "'");
        Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show();
    }

    public void deleteData(View view) {
        db.execSQL("DELETE FROM data");
        Toast.makeText(this, "Data Deleted", Toast.LENGTH_SHORT).show();
        content.setText("");
    }

    public void getData(View view) {
        String searchText = content.getText().toString();
        c = db.rawQuery("SELECT * FROM data WHERE content='" + searchText + "'", null);

        StringBuilder sb = new StringBuilder();
        if (c.moveToFirst()) {
            do {
                sb.append("Content: ").append(c.getString(0)).append("\n\n");
            } while (c.moveToNext());
            content.setText(sb.toString());
            Toast.makeText(this, "Data Retrieved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }
        c.close();
    }

    public void getAllData(View view) {
        c = db.rawQuery("SELECT * FROM data", null);
        
        StringBuilder sb = new StringBuilder();
        if (c.moveToFirst()) {
            do {
                sb.append("Content: ").append(c.getString(0)).append("\n\n");
            } while (c.moveToNext());
            content.setText(sb.toString());
            Toast.makeText(this, "All Data Retrieved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Database is empty", Toast.LENGTH_SHORT).show();
        }
        c.close();
    }

    @Override
    protected void onDestroy() {
        if (db != null) {
            db.close();
        }
        super.onDestroy();
    }
}
