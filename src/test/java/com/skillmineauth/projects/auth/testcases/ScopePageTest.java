package com.skillmineauth.projects.auth.testcases;
import com.java.auth.annotations.FrameworkAnnotation;
import com.java.auth.enums.AuthorType;
import com.java.auth.enums.CategoryType;
import com.skillmineauth.common.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ScopePageTest extends BaseTest {
    @FrameworkAnnotation(author = {AuthorType.Nageshkumar}, category = {CategoryType.SMOKE})
    @Test(priority = 1,description = "scope page element check",groups = {"Smoke Test"})
    public void verifyAllTheComponentsAreVisible(){
        Assert.assertTrue(scopesPage.isAllTheComponentsAreVisible(),
                "Test Case Failed: One or more components are not visible on the Scopes page.");
        log.info("Test Case Passed: All components are visible on the Scopes Page as expected.");
    }
    @FrameworkAnnotation(author = {AuthorType.Nageshkumar}, category = {CategoryType.SMOKE})
    @Test(priority = 2,description = "Creating scope",groups = {"Smoke Test"})
    public void verifyCreatingNewScope(){
        Assert.assertEquals(scopesPage.createNewScope(),scopesPage.createScopeSuccessMessage,
                "Test Case Failed: Scope creation was unsuccessful.");
        log.info("Test Case Passed: Scope was created successfully and Verified Toast Message");
        Assert.assertEquals(scopesPage.searchCreatedScope(),scopesPage.scopeKey,"Test Case Failed:");
        log.info("Test Case Passed: Successfully verified Created scope in the List of scopes");
    }
    @FrameworkAnnotation(author = {AuthorType.Nageshkumar}, category = {CategoryType.SMOKE})
    @Test(priority = 3,description = "edit Created scope",groups = {"Smoke Test"})
    public void verifyEditAndAddingLocalesInTheScopes(){
        Assert.assertEquals(scopesPage.editScope(),scopesPage.addLocalesSuccessMessage,"Test Case Failed: Adding locales into the scope");
        log.info("Test Case Passed:Successfully verified Edit icon And Adding Locales In The Scopes");
        Assert.assertTrue(scopesPage.verifyAddedLocaleIsVissble(),"Test Case Failed: added locale is not visible in the list of locales");
        log.info("Test Case Passed: Successfully verified added Locales in the list");
    }

    @FrameworkAnnotation(author = {AuthorType.Nageshkumar}, category = {CategoryType.SMOKE})
    @Test(priority = 4,description = "Deletion Of Locales In Scope",groups = {"Smoke Test"})
    public void verifyDeletionOfLocalesInScope(){
        Assert.assertEquals(scopesPage.deleteLocalesInScope(),scopesPage.deleteLocalesSuccessMessage,"Test Case Failed:unable to delete the locales in scopes");
        log.info("Test Case Passed:Successfully deleted locales from the scopes");
    }

    @FrameworkAnnotation(author = {AuthorType.Nageshkumar}, category = {CategoryType.SMOKE})
    @Test(priority = 5,description = "checking scope updation ",groups = {"Smoke Test"})
    public void verifyScopeUpdateByClickingOnSave(){
        Assert.assertEquals(scopesPage.clickOnSaveForUpdateScope(),scopesPage.scopeUpdateSuccessMessage,"Test Case Failed: Scope updation failed");
        log.info("Test Case Passed:Successfully updated the Scope");
    }
    @FrameworkAnnotation(author = {AuthorType.Nageshkumar}, category = {CategoryType.SMOKE})
    @Test(priority = 6,description = "checking scope updation ",groups = {"Smoke Test"})
    public void verifyDeletingScope(){
        asserts.assertEquals(scopesPage.deleteExistingScope(),scopesPage.deleteScopeSuccessMessage);
        log.info("scope deleted successfully ");
        Assert.assertTrue(scopesPage.verifyScopeDeletedInTheList());
        log.info("deleted scope verified in the scope list");
    }
}
