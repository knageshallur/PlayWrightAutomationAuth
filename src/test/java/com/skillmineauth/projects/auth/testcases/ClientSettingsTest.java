package com.skillmineauth.projects.auth.testcases;

import com.java.auth.annotations.FrameworkAnnotation;
import com.java.auth.enums.AuthorType;
import com.java.auth.enums.CategoryType;
import com.skillmineauth.common.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class ClientSettingsTest extends BaseTest {
    @FrameworkAnnotation(author = {AuthorType.Nageshkumar}, category = {CategoryType.REGRESSION})
    @Test(priority = 0,description = "Perform Create Client Test",groups = {"Smoke Test"})
    public void verifyUserAbleToCreateClientForm() throws IOException {
        asserts.assertTrue(clientsSettingsPage.isNewClientCreated());
        log.info("New client form created successfully");
    }
//    @FrameworkAnnotation(author = {AuthorType.Nageshkumar}, category = {CategoryType.REGRESSION})
//    @Test(priority = 1,description = "Perform delete Client Test",groups = {"Smoke Test"})
//    public void verifyUserAbleToDeleteClient(){
//        clientsSettingsPage.isNewClientDeleted();
//        log.info("New client form deleted successfully");
//    }
}

