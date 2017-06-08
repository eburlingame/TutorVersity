package com.example.eric.tutorversity.models.api.request;

import com.android.volley.Response;
import com.example.eric.tutorversity.models.Student;
import com.example.eric.tutorversity.models.User;
import com.example.eric.tutorversity.models.api.response.GetUserQuestionsResponse;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;


@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class GetUserQuestionsRequestTest {
    @Test
    public void makeRequestNoQuestions() throws Exception {
        final User user = new Student("{'email':'email', 'bio':'bio', 'name':'name', 'token':'token'}");
        final GetUserQuestionsRequest request = new GetUserQuestionsRequest(user);

        request.makeRequest(new Response.Listener<GetUserQuestionsResponse>() {
            @Override
            public void onResponse(GetUserQuestionsResponse response) {
                assertEquals(0, response.getQuestions().size());
            }
        });

        request.onResponse(new JSONObject("{ 'questions': [  ] } "));
    }

    @Test
    public void makeRequestSingleQuestion() throws Exception {
        final User user = new Student("{'email':'email', 'bio':'bio', 'name':'name', 'token':'token'}");
        final GetUserQuestionsRequest request = new GetUserQuestionsRequest(user);

        request.makeRequest(new Response.Listener<GetUserQuestionsResponse>() {
            @Override
            public void onResponse(GetUserQuestionsResponse response) {
                assertEquals(1, response.getQuestions().size());
            }
        });

        request.onResponse(new JSONObject("{ 'questions': [ {  'subject': 'subject', 'question': 'test', 'email': 'email', 'latitude':'-120', 'longitude':'40' } ] } "));
    }

    @Test
    public void makeRequestThreeQuestions() throws Exception {
        final User user = new Student("{'email':'email', 'bio':'bio', 'name':'name', 'token':'token'}");
        final GetUserQuestionsRequest request = new GetUserQuestionsRequest(user);

        request.makeRequest(new Response.Listener<GetUserQuestionsResponse>() {
            @Override
            public void onResponse(GetUserQuestionsResponse response) {
                assertEquals(3, response.getQuestions().size());
            }
        });

        String questionJSON = "{  'subject': 'subject', 'question': 'test', 'email': 'email', 'latitude':'-120', 'longitude':'40' }";
        request.onResponse(new JSONObject("{ 'questions': [ "  + questionJSON + "," + questionJSON + "," + questionJSON + " ] } "));
    }

}