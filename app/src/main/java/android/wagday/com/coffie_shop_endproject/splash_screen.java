package android.wagday.com.coffie_shop_endproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class splash_screen extends AppCompatActivity {
    ImageView image_logo,imge_coff;
    LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        image_logo = (ImageView) findViewById(R.id.log_shop);
        imge_coff = (ImageView) findViewById(R.id.img_log);
        lottieAnimationView = (LottieAnimationView) findViewById(R.id.animlogo);

        image_logo.animate().translationY(-1600).setDuration(2000).setStartDelay(4000);
        imge_coff.animate().translationY(2000).setDuration(2000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(1400).setDuration(2000).setStartDelay(4000);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(4800);
                    Intent intent = new Intent(splash_screen.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}