package com.skillmineauth.projects.auth.admin.pages;

import com.java.auth.Factory.PlaywrightFactory;
import com.java.auth.helpers.JsonHelpers;
import com.java.auth.utils.PlaywrightUtils;
import com.microsoft.playwright.Page;
import com.java.auth.helpers.RandomNumberHelpers;

import java.util.Arrays;
import java.util.List;

public class SchemasPage extends PlaywrightUtils {
    protected final String menuSettings = "//span[text()='Settings']";
    protected final String menuSchemas = "//span[.='Schemas']";
    protected final String buttonCreateVariable = "//button[text()=' Create Variable ']";
    protected final String textVariableName = "//input[@name='field_key']";
    protected final String selectDataType = "//span[.='Select data type']";
    protected final String selectScopes = "//angular2-multiselect[@name='scopes']//div[@class='c-btn']";
    protected final String selectLocales = "//angular2-multiselect[@name='locale']//div[@class='c-btn']";
    protected final String sliderEnabled = "//input[@name='enabled']/../span";
    protected final String sliderinternal = "//input[@name='internal']/../span";
    protected final String sliderRequired = "//input[@name='required']/../span";
    protected final String dropDownSearchSelectScopesField = "(//input[@placeholder='Search'])[2]";
    protected final String dropDownSearchSelectLocalesField = "(//input[@placeholder='Search'])[3]";
    protected final String selectText = "//label[normalize-space()='Text']";
    protected final String selectScopeSearcedText = "(//ul[@class='lazyContainer'])[2]/li[1]/label";
    protected final String selectLocalesSearcedText = "(//ul[@class='lazyContainer'])[3]/li[1]/label";
    protected final String textDisplayName = "//label[text()='Display Name ']/../input";
    protected final String textMinLength = "//label[text()=' Min Length ']/../input";
    protected final String textMaxLength = "//label[text()=' Max Length']/../input";
    protected final String textDisplayNameRequeredMessage = "(//label[text()='Required Message ']/../input)[1]";
    protected final String textMinLengthRequeredMessage = "(//label[text()='Error Message']/../input)[1]";
    protected final String textMaxLengthRequeredMessage = "(//label[text()='Error Message']/../input)[2]";
    protected final String buttonSave = "//button[contains(text(),'Save')]";
    String vriableName = JsonHelpers.getValue("create variable", "variavleName") + RandomNumberHelpers.randomNumberGenerator();


    public SchemasPage(Page page) {
        super(page);
    }




        public String createNewTextTypeField(){
            click(menuSettings);
            click(menuSchemas);
            click(buttonCreateVariable);
            fill(textVariableName,vriableName);
            System.out.println(vriableName);
            clickOnDropDownAndSelectText(selectDataType,selectText);
            searchAndSelectTextInDropDownList(selectScopes,dropDownSearchSelectScopesField,"profile",selectScopeSearcedText);
            click(selectScopes);
            searchAndSelectTextInDropDownList(selectLocales,dropDownSearchSelectLocalesField,"english (india)",selectLocalesSearcedText);
            String displayName= JsonHelpers.getValue("create variable","displayName");
            String requiredTest=JsonHelpers.getValue("create variable","requiredText");
            List<String> locators = Arrays.asList(textDisplayName,textDisplayNameRequeredMessage);
            List<String> values = Arrays.asList(displayName,requiredTest);
            fillMultipleTexts(locators,values);
            click(sliderRequired);
            click(buttonSave);
            return alertMessage(JsonHelpers.getValue("create variable","successMessage"));

    }
}



