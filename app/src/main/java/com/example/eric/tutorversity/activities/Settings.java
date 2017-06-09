package com.example.eric.tutorversity.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.eric.tutorversity.R;
import com.example.eric.tutorversity.models.Student;

import static com.example.eric.tutorversity.models.api.JSONConstants.USER;

public class Settings extends AppCompatActivity {

    private int seekStatus;

    @Override
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.settings_layout);

        String json = getIntent().getExtras().getString(USER);
        Student student = new Student(json);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        new StudentSidebarMenu(this, toolbar, student);

        seekBar();
    }

    public void seekBar() {
        final TextView t1= (TextView) findViewById(R.id.textView12);
        SeekBar sk=(SeekBar) findViewById(R.id.seekBar);
        seekStatus/=5;
        sk.setProgress(seekStatus);
        t1.setText(Integer.toString(seekStatus));
        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekStatus = (seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Nothing to change on start
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                t1.setText(Integer.toString(progress/5));
            }
        });
    }
}
