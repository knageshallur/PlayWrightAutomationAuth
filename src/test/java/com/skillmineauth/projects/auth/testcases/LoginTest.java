package com.skillmineauth.projects.auth.testcases;

import com.java.auth.annotations.FrameworkAnnotation;
import com.java.auth.enums.AuthorType;
import com.java.auth.enums.CategoryType;
import com.skillmineauth.common.BaseTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @FrameworkAnnotation(author = {AuthorType.Nageshkumar}, category = {CategoryType.REGRESSION})
    @Test(priority = 0,description = "Perform Login Test",groups = {"Smoke Test"})
    public void verifyUserLoginIntoApplication(){
        asserts.assertTrue(loginPage.isLoginSuccessful());
        log.info("User successfully logged into application");
    }
}

