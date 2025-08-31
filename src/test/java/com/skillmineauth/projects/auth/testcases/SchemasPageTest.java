package com.skillmineauth.projects.auth.testcases;

import com.java.auth.annotations.FrameworkAnnotation;
import com.java.auth.enums.AuthorType;
import com.java.auth.enums.CategoryType;
import com.java.auth.helpers.JsonHelpers;
import com.skillmineauth.common.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;


public class SchemasPageTest extends BaseTest {
    @FrameworkAnnotation(author = {AuthorType.Nageshkumar}, category = {CategoryType.SMOKE})
    @Test(priority = 0,description = "Perform Create variable Test",groups = {"Smoke Test"})
    public void verifyUserAbleToCreateVariable() {
        asserts.assertEquals(schemasPage.createNewTextTypeField(), JsonHelpers.getValue("create variable","successMessage"));
        log.info("New Variable created successfully");
    }

}
