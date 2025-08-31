package com.skillmineauth.projects.auth.testcases;

import com.java.auth.annotations.FrameworkAnnotation;
import com.java.auth.constants.FrameworkConstants;
import com.java.auth.enums.AuthorType;
import com.java.auth.enums.CategoryType;
import com.skillmineauth.common.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class BlueprintsPageTest extends BaseTest {
        @FrameworkAnnotation(author = {AuthorType.Nageshkumar}, category = {CategoryType.REGRESSION})
        @Test(priority = 0,description = "Perform Create blueprint Test",groups = {"Smoke Test"})
        public void verifyUserAbleToCreateBlueprint() throws IOException {
            asserts.assertEquals(blueprintsPage.createNewBlueprint(), FrameworkConstants.BLUEPRINT_CREATE_TOAST_MESSAGE);
            log.info("New Blueprint created successfully");
        }
        @FrameworkAnnotation(author = {AuthorType.Nageshkumar}, category = {CategoryType.REGRESSION})
        @Test(priority = 1,description = "Perform Create blueprint Test",groups = {"Smoke Test"})
        public void verifyUserAbleToMapClientToBlueprint() throws IOException {
            asserts.assertTrue(blueprintsPage.mapClientToBlueprint(),"client not mapped to the blueprint");
            log.info("client Mapped successfully into the blueprint");
        }
    @FrameworkAnnotation(author = {AuthorType.Nageshkumar}, category = {CategoryType.REGRESSION})
    @Test(priority = 2,description = "Perform Create blueprint Test",groups = {"Smoke Test"})
    public void verifyRegistrationSettings() throws IOException {
        blueprintsPage.enableRegistrationINRegistrationSettings();
        blueprintsPage.enableRegistrationInviteINRegistrationSettings();
        blueprintsPage.enableRequiredFieldInCustomizeSchemas();
    }

}
