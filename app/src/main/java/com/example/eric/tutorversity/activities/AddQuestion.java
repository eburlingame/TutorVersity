package com.example.eric.tutorversity.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Response;
import com.example.eric.tutorversity.OurSingleton;
import com.example.eric.tutorversity.R;
import com.example.eric.tutorversity.models.Question;
import com.example.eric.tutorversity.models.Student;
import com.example.eric.tutorversity.models.api.request.NewQuestionRequest;
import com.example.eric.tutorversity.models.api.response.NewQuestionResponse;

import static com.example.eric.tutorversity.models.api.JSONConstants.USER;

public class AddQuestion extends AppCompatActivity {

    private Student student;

    private class DataHandler implements Response.Listener<NewQuestionResponse>
    {
        private Context context;
        DataHandler(Question question, Context context)
        {
            this.context = context;
            NewQuestionRequest request = new NewQuestionRequest(student, question);
            OurSingleton.getInstance(
                    getApplicationContext()).addToRequestQueue(getApplicationContext(),
                    request.makeRequest(this));
        }

        @Override
        public void onResponse(NewQuestionResponse response)
        {
            if (response.isSuccess())
            {
                showDialogExitAfterClose("Question successfully added!");
            }
            else
            {
                showDialog("Error creating the question" + response.getMessage());
            }
        }

        private void showDialogExitAfterClose(String message)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(message)
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            Intent intent = new Intent(getBaseContext(), StudentDashboard.class);
                            intent.putExtra(USER, student.toJSON().toString());
                            startActivity(intent);
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }

        private void showDialog(String message)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(message)
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        String json = getIntent().getExtras().getString(USER);
        student = new Student(json);
    }

    public void sendQuestion(View view) {
        EditText qLabel = (EditText) findViewById(R.id.questionText);
        Spinner spinner = (Spinner) findViewById(R.id.subjectSpinner);
        Question question = new Question(
            student.getEmail(),
            student.getName(),
            qLabel.getText().toString(),
            spinner.getSelectedItem().toString()
        );
        new DataHandler(question, this);
    }

    //need to figure out how to go back to previous activity properly
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

}
