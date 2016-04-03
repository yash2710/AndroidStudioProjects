package yashtrivedi.spi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parse.Parse;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Parse.enableLocalDatastore(this);
            Parse.initialize(this, "FrTgV7EjPzKIWwRhnj7n6sVMrib011dzKwSGy8o8", "bFH95NvXUptmVmo7h1wkwNU8TYlSxNk5rd11umuA");

        } catch (Exception e) {

        }
        startActivity(new Intent(this, Main.class));
        finish();
    }
}
