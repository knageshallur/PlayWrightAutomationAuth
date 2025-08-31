package com.skillmineauth.projects.auth.admin.pages;
import com.java.auth.helpers.JsonHelpers;
import com.java.auth.utils.PlaywrightUtils;
import com.java.auth.helpers.RandomNumberHelpers;
import com.microsoft.playwright.Page;

import com.microsoft.playwright.Page;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ScopesPage extends PlaywrightUtils {

    protected final String menuSettings = "//span[text()='Settings']";
    protected final String menuOpenIdConnect="//a[.='Open Id Connect Settings']";
    protected final String menuScopes="//a[.='Scopes']";
    protected final String buttonCreateScope="//button[.=' Create Scope']";
    protected final String searchScopeKey="//input[@placeholder='Search By Key']";
    protected final String textFieldScopeKey="//input[@name='scope_key']";
    protected final String selectSecurityLevel="//select[@name='scopeedit']";
    protected final String toggleIsUserConsentRequired="//span[@class='slider round']";
    protected final String buttonSave="(//button[.=' Save '])[1]";
    protected final String buttonCancel="//button[.='Cancel ']";
    protected final String buttonCreateNewLocale="//span[.='Create New Locale']";
    protected final String selectLocales="//span[.='Select locale']";
    protected final String textFieldScopeTitle="//input[@placeholder='Enter scope title']";
    protected final String textFieldDescription="//textarea[@name='description']";
    protected final String buttonSavePopup="(//button[.=' Save '])[2]";
    protected final String buttonClosePopup="(//button[.=' Close '])[2]";
    protected final String scopesListTable="//table";
    protected final String iconFilter="//button[@class='btn btn-icon btn-warning mg-r-5']";
    protected final String iconGrid="//app-grid-btn";
    protected final String headerText="//h5[.='Client Scopes']";
    protected final String dropDownSearchSelectLocalesField = "(//input[@placeholder='Search'])[1]";
    protected final String buttonAddLocale="//a[.=' Add Locale']";
    public static final String LOCALE = "English (india)";
    public static final String LOCALE2 = "English (United States)";
    public static final String errorScopeNotFount="//span[.='Client Scope list not found!']";
    protected final String selectLocalesSearcedText = "(//ul[@class='lazyContainer'])[1]/li[1]/label";

    public final String scopeKey = JsonHelpers.getValue("CreateScope", "Scope_Key") + RandomNumberHelpers.randomNumberGenerator() ;
    public final String createScopeSuccessMessage = JsonHelpers.getValue("SuccessToast", "ScopeCreteSuccessMessage");
    public final String addLocalesSuccessMessage = JsonHelpers.getValue("SuccessToast", "LocaleAddSuccessMessage");
    public final String deleteLocalesSuccessMessage = JsonHelpers.getValue("SuccessToast", "DeleteLocaleSuccessMessage");
    public final String scopeUpdateSuccessMessage = JsonHelpers.getValue("SuccessToast", "ScopeUpdateSuccessMessage");
    public final String deleteScopeSuccessMessage = JsonHelpers.getValue("SuccessToast", "DeleteScopeSuccessMessage");
    public final String buttonDelete="//h6[.='Delete this Scope']/../..//button";
    public final String buttonDeletePopup="(//button[.=' Delete '])[2]";
    public final String scopeTitleName = JsonHelpers.getValue("Locale Details", "Scope Title");
    public final String scopeTitleNameDeleteOne = JsonHelpers.getValue("Locale Details", "ScopeTitleDelete");
    public final String descriptionText = JsonHelpers.getValue("Locale Details", "Description");
    protected final String searchedScope="//td[.='"+scopeKey+"']";
    protected final String addedLocale="//td[.='"+scopeTitleName+"']/..";
    protected final String iconDeleteLocale="//td[.='"+scopeTitleNameDeleteOne+"']/..//a";



    public ScopesPage(Page page) {
        super(page);
    }
    public Boolean isAllTheComponentsAreVisible(){
        click(menuSettings);
        click(menuOpenIdConnect);
        click(menuScopes);
        letsWaitForTime(2_000);
        boolean  header= isTextVisible(headerText);
        boolean search = isTextVisible(searchScopeKey);
        boolean createscope = isTextVisible(buttonCreateScope);
        boolean table = isTextVisible(scopesListTable);
        boolean filter = isButtonEnable(iconFilter);
        boolean gridicon = isButtonEnable(iconGrid);
        return search && createscope && table && filter && gridicon && header ;
    }
    public String createNewScope(){
        click(buttonCreateScope);
        fill(textFieldScopeKey,scopeKey);
        selectOption(selectSecurityLevel,"value","public");
        click(toggleIsUserConsentRequired);
        click(buttonSave);
        return alertMessage(createScopeSuccessMessage);
    }
    public String searchCreatedScope(){
        typeForSearch(searchScopeKey,scopeKey);
        return getText(searchedScope);
    }
    public String editScope(){
        clickOnEditScopeIcon(scopeKey);
        click(buttonCreateNewLocale);
        searchAndSelectTextInDropDownList(selectLocales,dropDownSearchSelectLocalesField,LOCALE,selectLocalesSearcedText);
        fill(textFieldScopeTitle,scopeTitleName);
        fill(textFieldDescription,descriptionText);
        click(buttonSavePopup);
        return alertMessage(addLocalesSuccessMessage);
    }
    public boolean verifyAddedLocaleIsVissble(){
        waitForLocator(addedLocale);
        return isTextVisible(addedLocale);
    }
    public String deleteLocalesInScope() {
        click(buttonAddLocale);
        searchAndSelectTextInDropDownList(selectLocales, dropDownSearchSelectLocalesField, LOCALE2, selectLocalesSearcedText);
        fill(textFieldScopeTitle, scopeTitleNameDeleteOne);
        fill(textFieldDescription, descriptionText);
        click(buttonSavePopup);
        click(iconDeleteLocale);
        return alertMessage(deleteLocalesSuccessMessage);
    }
    public String clickOnSaveForUpdateScope(){
        click(buttonSave);
        return alertMessage(scopeUpdateSuccessMessage);
    }
    public Boolean verifyUpdatedScope() {
        typeForSearch(searchScopeKey, scopeKey);
        clickOnEditScopeIcon(scopeKey);
        waitForLocator(addedLocale);
        List<String> locators = Arrays.asList(textFieldScopeKey);
        List<String> value = Arrays.asList(scopeKey);
        boolean scopeKetdata = verifyFieldsData(locators, value);
        boolean addedLocales = isTextVisible(addedLocale);
        return scopeKetdata && addedLocales;
    }
    public String deleteExistingScope(){
        if(isTextVisible(menuScopes)){
            click(menuScopes);
        } else if (isTextVisible(menuOpenIdConnect)) {
            click(menuOpenIdConnect);
            click(menuScopes);
        }else {
            click(menuSettings);
            click(menuOpenIdConnect);
            click(menuScopes);
        }
        typeForSearch(searchScopeKey,scopeKey);
        clickOnEditScopeIcon(scopeKey);
        waitForLocator(buttonDelete);
        click(buttonDelete);
        click(buttonDeletePopup);
        return alertMessage(deleteScopeSuccessMessage);
    }
    public boolean verifyScopeDeletedInTheList(){
        typeForSearch(searchScopeKey,scopeKey);
        waitForLocator(errorScopeNotFount);
        return isTextVisible(errorScopeNotFount);

    }
}
