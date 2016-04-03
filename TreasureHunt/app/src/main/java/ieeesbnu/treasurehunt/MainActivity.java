package ieeesbnu.treasurehunt;

import android.content.DialogInterface;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button sound;
    EditText input;
    private Camera camera;
    Camera.Parameters params;
    MediaPlayer mediaPlayer;
    AlertDialog.Builder build;
    AlertDialog dia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sound= (Button) findViewById(R.id.sound);
        input= (EditText) findViewById(R.id.input);

//        getCamera();

        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = input.getText().toString();
                build = new AlertDialog.Builder(MainActivity.this);
                build.setMessage("Playing...")
                        .setCancelable(false);
                dia = build.create();
                dia.show();
                if (s.equalsIgnoreCase("e")) {
                    playfive();
                } else if (s.equalsIgnoreCase("third")) {
                    playseventeen();
                } else if (s.equalsIgnoreCase("ounce")) {
                    playnineteen();
                } else if (s.equalsIgnoreCase("stairs") || s.equalsIgnoreCase("staircase") || s.equalsIgnoreCase("temperature")) {
                    playeighteen();
                } else if (s.equalsIgnoreCase("energy")) {
                    playsixteen();
                } else if (s.equalsIgnoreCase("7.5")) {
                    playthirteen();
                } else if (s.equalsIgnoreCase("error") || s.equalsIgnoreCase("mistake")) {
                    playseven();
                } else if (s.equalsIgnoreCase("74658")) {
                    playeleven();
                } else {
                    dia.dismiss();
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Please Try Again")
                            .setTitle("Wrong Answer")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                dia.dismiss();
            }
        });

    }

    private void playSound(){
        mediaPlayer=MediaPlayer.create(this,R.raw.beep);
        mediaPlayer.start();
    }

    private void stopSound(){
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    private void playone(){
        Thread flash = new Thread(){
            @Override
            public void run() {
                try{
                    smallBeep();
                    longBeep();
                    longBeep();
                    longBeep();
                    longBeep();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        flash.start();
    }

    private void playtwo(){
        Thread flash = new Thread(){
            @Override
            public void run() {
                try{
                    smallBeep();
                    smallBeep();
                    longBeep();
                    longBeep();
                    longBeep();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        flash.start();
    }

    private void playthree(){
        Thread flash = new Thread(){
            @Override
            public void run() {
                try{
                    smallBeep();
                    smallBeep();
                    smallBeep();
                    longBeep();
                    longBeep();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        flash.start();
    }

    private void playfour(){
        Thread flash = new Thread(){
            @Override
            public void run() {
                try{
                    smallBeep();
                    smallBeep();
                    smallBeep();
                    smallBeep();
                    longBeep();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        flash.start();
    }

    private void playfive(){
        Thread flash = new Thread(){
            @Override
            public void run() {
                try{
                    smallBeep();
                    smallBeep();
                    smallBeep();
                    smallBeep();
                    smallBeep();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        flash.start();
    }

    private void playsix(){
        Thread flash = new Thread(){
            @Override
            public void run() {
                try{
                    longBeep();
                    smallBeep();
                    smallBeep();
                    smallBeep();
                    smallBeep();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        flash.start();
    }

    private void playseven(){
        Thread flash = new Thread(){
            @Override
            public void run() {
                try{
                    longBeep();
                    longBeep();
                    smallBeep();
                    smallBeep();
                    smallBeep();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        flash.start();
    }

    private void playeight(){
        Thread flash = new Thread(){
            @Override
            public void run() {
                try{
                    longBeep();
                    longBeep();
                    longBeep();
                    smallBeep();
                    smallBeep();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        flash.start();
    }

    private void playnine(){
        Thread flash = new Thread(){
            @Override
            public void run() {
                try{
                    longBeep();
                    longBeep();
                    longBeep();
                    longBeep();
                    smallBeep();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        flash.start();
    }

    private void playten(){
        Thread flash = new Thread(){
            @Override
            public void run() {
                try{
                    playone();
                    sleep(2000);
                    playzero();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        flash.start();
    }

    private void playeleven(){
        Thread flash = new Thread(){
            @Override
            public void run() {
                try{
                    smallBeep();
                    longBeep();
                    longBeep();
                    longBeep();
                    longBeep();
                    sleep(2000);
                    smallBeep();
                    longBeep();
                    longBeep();
                    longBeep();
                    longBeep();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        flash.start();
    }

    private void playtwelve(){
        Thread flash = new Thread(){
            @Override
            public void run() {
                try{
                    smallBeep();
                    longBeep();
                    longBeep();
                    longBeep();
                    longBeep();
                    sleep(2000);
                    smallBeep();
                    smallBeep();
                    longBeep();
                    longBeep();
                    longBeep();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        flash.start();
    }

    private void playthirteen(){
        Thread flash = new Thread(){
            @Override
            public void run() {
                try{
                    smallBeep();
                    longBeep();
                    longBeep();
                    longBeep();
                    longBeep();
                    sleep(2000);
                    smallBeep();
                    smallBeep();
                    smallBeep();
                    longBeep();
                    longBeep();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        flash.start();
    }

    private void playfourteen(){
        Thread flash = new Thread(){
            @Override
            public void run() {
                try{
                    smallBeep();
                    longBeep();
                    longBeep();
                    longBeep();
                    longBeep();
                    sleep(2000);
                    smallBeep();
                    smallBeep();
                    smallBeep();
                    smallBeep();
                    longBeep();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        flash.start();
    }

    private void playfiveteen(){
        Thread flash = new Thread(){
            @Override
            public void run() {
                try{
                    smallBeep();
                    longBeep();
                    longBeep();
                    longBeep();
                    longBeep();
                    sleep(2000);
                    smallBeep();
                    smallBeep();
                    smallBeep();
                    smallBeep();
                    smallBeep();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        flash.start();
    }

    private void playsixteen(){
        Thread flash = new Thread(){
            @Override
            public void run() {
                try{
                    smallBeep();
                    longBeep();
                    longBeep();
                    longBeep();
                    longBeep();
                    sleep(2000);
                    longBeep();
                    smallBeep();
                    smallBeep();
                    smallBeep();
                    smallBeep();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        flash.start();
    }

    private void playseventeen(){
        Thread flash = new Thread(){
            @Override
            public void run() {
                try{
                    smallBeep();
                    longBeep();
                    longBeep();
                    longBeep();
                    longBeep();
                    sleep(2000);
                    longBeep();
                    longBeep();
                    smallBeep();
                    smallBeep();
                    smallBeep();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        flash.start();
    }

    private void playeighteen(){
        Thread flash = new Thread(){
            @Override
            public void run() {
                try{
                    smallBeep();
                    longBeep();
                    longBeep();
                    longBeep();
                    longBeep();
                    sleep(2000);
                    longBeep();
                    longBeep();
                    longBeep();
                    smallBeep();
                    smallBeep();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        flash.start();
    }

    private void playnineteen(){
        Thread flash = new Thread(){
            @Override
            public void run() {
                try{
                    smallBeep();
                    longBeep();
                    longBeep();
                    longBeep();
                    longBeep();
                    sleep(2000);
                    longBeep();
                    longBeep();
                    longBeep();
                    longBeep();
                    smallBeep();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        flash.start();
    }

    private void playzero(){
        Thread flash = new Thread(){
            @Override
            public void run() {
                try{
                    longBeep();
                    longBeep();
                    longBeep();
                    longBeep();
                    longBeep();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        flash.start();
    }

    private void smallBeep() throws InterruptedException {
        playSound();
        Thread.currentThread().sleep(300);
        stopSound();
        Thread.currentThread().sleep(1000);
    }

    private void longBeep() throws InterruptedException {
        playSound();
        Thread.currentThread().sleep(1000);
        stopSound();
        Thread.currentThread().sleep(1000);
    }
}
