package com.example.eric.tutorversity.models.api.response;

import com.example.eric.tutorversity.models.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import static com.example.eric.tutorversity.models.api.JSONConstants.EMAIL;
import static com.example.eric.tutorversity.models.api.JSONConstants.QUESTION;
import static com.example.eric.tutorversity.models.api.JSONConstants.SUBJECT;

public class GetQuestionsResponse {

    private List<Question> questionList;

    private GetQuestionsResponse()
    {
        questionList = new LinkedList<>();
    }

    public GetQuestionsResponse(JSONObject body) throws JSONException {

        questionList = new LinkedList<>();
        JSONArray array = body.getJSONArray("questions");
        for (int i = 0; i < array.length(); i++)
        {
            JSONObject q = array.getJSONObject(i);
            questionList.add(new Question(
                    q.getString(EMAIL),
                    q.getString(QUESTION),
                    q.getString(QUESTION),
                    q.getString(SUBJECT)));
        }
    }

    public static GetQuestionsResponse failed()
    {
        return new GetQuestionsResponse();
    }

    public List<Question> getQuestions()
    {
        return questionList;
    }
}
