package android.wagday.com.coffie_shop_endproject;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class home_type_pro extends AppCompatActivity {
TextView name_acc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_type_pro);
        name_acc=findViewById(R.id.name_acc);
        name_acc.setText(MainActivity.users.getFirst_name()+" "+MainActivity.users.getLast_name());
    }
}