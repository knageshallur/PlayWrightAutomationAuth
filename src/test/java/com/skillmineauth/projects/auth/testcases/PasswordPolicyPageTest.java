package com.skillmineauth.projects.auth.testcases;

import com.java.auth.annotations.FrameworkAnnotation;
import com.java.auth.enums.AuthorType;
import com.java.auth.enums.CategoryType;
import com.java.auth.helpers.JsonHelpers;
import com.skillmineauth.common.BaseTest;

import org.testng.annotations.Test;

public class PasswordPolicyPageTest extends BaseTest {
    @FrameworkAnnotation(author = {AuthorType.Nageshkumar}, category = {CategoryType.SMOKE})
    @Test(priority = 0,description = "set password policy Test",groups = {"Smoke Test"})
    public void verifyUserAbleToSetPasswordPolicy() {
        asserts.assertEquals(passwordPolicyPage.createNewPasswordPolicy(), JsonHelpers.getValue("passwordpolicy","successMessage"));
        log.info("password policy set successfully");
    }
}
