package com.example.eric.tutorversity.models;

import com.example.eric.tutorversity.models.api.request.AuthRequestTest;
import com.example.eric.tutorversity.models.api.request.GetQuestionsRequestTest;
import com.example.eric.tutorversity.models.api.request.GetUserQuestionsRequestTest;
import com.example.eric.tutorversity.test.UserTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@SuiteClasses({ AuthRequestTest.class, GetQuestionsRequestTest.class, GetUserQuestionsRequestTest.class })
public class EricSuite {

}
