package co.edu.upc.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class StopWatch extends AppCompatActivity {
    private int seconds = 0;
    private boolean running = false;

    private int secondsLap = 0;

    private int lap = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");

        }
        Button startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickStart();
            }
        });
        Button stopButton = (Button) findViewById(R.id.stop_button);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickStop();
            }
        });
        Button resetButton = (Button) findViewById(R.id.reset_button);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickReset();
            }
        });
        Button lapButton = (Button) findViewById(R.id.lap_button);
        lapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLap();
            }
        });
        runTimer();
    }

    public void onClickStart() {
        running = true;
    }

    public void onClickStop() {
        running = false;
    }

    public void onClickReset() {
        running = false;
        seconds = 0;
        secondsLap = 0;
    }

    public void onClickLap() {
        if (lap <= 5) {
            final TextView timeViewLap = (TextView) findViewById(R.id.time_lap);
            timeViewLap.getText();
            timeViewLap.setText(timeViewLap.getText()+"vuelta: " + lap++ + "tiempo" + secondsLap + "\n");
            running = true;
            secondsLap = 0;
        }else{
            Toast.makeText(this, "vueltas alcanzadas", Toast.LENGTH_SHORT).show();
        }

    }

    public void runTimer() {
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                    secondsLap++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
    //public void onSanvanceInstanceState(Bundle savedInstanceState) {
    //  savedInstanceState.putInt("seconds", seconds);
    //savedInstanceState.putBoolean("running", running);
    //}
}