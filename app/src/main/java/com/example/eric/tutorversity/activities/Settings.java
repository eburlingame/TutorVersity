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

    private Student student;
    private int seekStatus;

    @Override
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.settings_layout);

        String json = getIntent().getExtras().getString(USER);
        student = new Student(json);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        StudentSidebarMenu menu = new StudentSidebarMenu(this, toolbar, student);

        seekBar();
    }

    public void seekBar() {
        final TextView t1= (TextView) findViewById(R.id.textView12);
        SeekBar sk=(SeekBar) findViewById(R.id.seekBar);
        sk.setProgress(seekStatus);
        t1.setText(Integer.toString(seekStatus));
        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekStatus = seekBar.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                t1.setText(Integer.toString(progress));
            }
        });
    }
}
