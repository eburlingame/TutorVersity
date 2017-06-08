package com.example.eric.tutorversity.models.api.request;

import com.android.volley.Response;
import com.example.eric.tutorversity.models.Student;
import com.example.eric.tutorversity.models.User;
import com.example.eric.tutorversity.models.api.response.AuthResponse;
import com.example.eric.tutorversity.models.api.response.GetQuestionsResponse;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;


@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class GetQuestionsRequestTest {
    @Test
    public void makeRequestNoQuestions() throws Exception {
        final User user = new Student("{'email':'email', 'bio':'bio', 'name':'name', 'token':'token'}");
        final GetQuestionsRequest request = new GetQuestionsRequest(user);

        request.makeRequest(new Response.Listener<GetQuestionsResponse>() {
            @Override
            public void onResponse(GetQuestionsResponse response) {
                assertEquals(0, response.getQuestions().size());
            }
        });

        request.onResponse(new JSONObject("{ 'questions': [  ] } "));
    }

    @Test
    public void makeRequestSingleQuestion() throws Exception {
        final User user = new Student("{'email':'email', 'bio':'bio', 'name':'name', 'token':'token'}");
        final GetQuestionsRequest request = new GetQuestionsRequest(user);

        request.makeRequest(new Response.Listener<GetQuestionsResponse>() {
            @Override
            public void onResponse(GetQuestionsResponse response) {
                assertEquals(1, response.getQuestions().size());
            }
        });

        request.onResponse(new JSONObject("{ 'questions': [ {  'subject': 'subject', 'question': 'test', 'email': 'email', 'latitude':'-120', 'longitude':'40' } ] } "));
    }

    @Test
    public void makeRequestThreeQuestions() throws Exception {
        final User user = new Student("{'email':'email', 'bio':'bio', 'name':'name', 'token':'token'}");
        final GetQuestionsRequest request = new GetQuestionsRequest(user);

        request.makeRequest(new Response.Listener<GetQuestionsResponse>() {
            @Override
            public void onResponse(GetQuestionsResponse response) {
                assertEquals(3, response.getQuestions().size());
            }
        });

        String questionJSON = "{  'subject': 'subject', 'question': 'test', 'email': 'email', 'latitude':'-120', 'longitude':'40' }";
        request.onResponse(new JSONObject("{ 'questions': [ "  + questionJSON + "," + questionJSON + "," + questionJSON + " ] } "));
    }

}