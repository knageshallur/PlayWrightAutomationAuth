package com.skillmineauth.projects.auth.admin.pages;

import com.java.auth.constants.FrameworkConstants;
import com.java.auth.utils.PlaywrightUtils;
import com.microsoft.playwright.Page;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

public class BlueprintsPage extends PlaywrightUtils {
    public BlueprintsPage(Page page) {
        super(page);
    }
    protected final String menuBlueprints = "//span[.='Blueprints']";
    protected final String buttonCreateBlueprint="//button[.=' Create Blueprint']";
    protected final String fieldBlueprintName="[name='flow_name']";
    protected final String fieldDescription="[name='description']";
    protected final String buttonSave="//button[.=' Save ']";
    protected final String  searchByNameText =  "//div[@class='form-group col-11']/input[@type='text']";
    protected final String editBlueprints = "//i[@class='las la-pencil-alt iconClass']";
    protected final String selectClientTypeDropDown = "//span[.='Select Client Type']";
    protected final String selectClientSideText = "angular2-multiselect[name='client_type'] li:nth-child(1) label";
    protected final String selectClientDropDownSearchText = "(//input[@placeholder='Search'])[2]";//"//angular2-multiselect[@name='client_id_ref']//input[@placeholder='Search']";
    protected final String selectCreatedNewClientText = "(//ul[@class='lazyContainer'])[2]/li[1]/label";//"angular2-multiselect[name='client_id_ref'] div[class='ng-star-inserted'] label";
    protected final String selectClientDropDown = "//angular2-multiselect[@name='client_id_ref']//div[@class='c-btn']/span[1]";
    protected final String addButton = "//button[@class='btn btn-success']";
    protected final String editIconRegistrationSetting = "//div[.='Registration Settings']/..//span[1]";
    protected final String editIconCustomizeYourPreferredOnboardingMethods="//div[.='Customize Your Preferred Onboarding Methods']/..//span//i";
    protected final String toggleEnableRegistration="//label[.='Enable Registration ']/../..//span";
    protected final String toggleEnableRegistrationInvite="//label[.='Enable Registration Invite ']/../..//span";
    protected final String editIconCustomizeSchemaSettings="//div[.='Customize Schema Settings']/..//span[1]";
    protected final String searchSchemaField="input[name='field_key']";



    public String createNewBlueprint(){
        click(menuBlueprints);
        click(buttonCreateBlueprint);
        fill(fieldBlueprintName, FrameworkConstants.BLUEPRINT_NAME);
        fill(fieldDescription, FrameworkConstants.BLUEPRINT_DESCRIPTION);
        click(buttonSave);
        return alertMessage(FrameworkConstants.BLUEPRINT_CREATE_TOAST_MESSAGE);
    }

    public boolean mapClientToBlueprint(){
        typeForSearch(searchByNameText,FrameworkConstants.BLUEPRINT_NAME);
        click(editBlueprints);
        clickOnDropDownAndSelectText(selectClientTypeDropDown,selectClientSideText);
        searchAndSelectTextInDropDownList(selectClientDropDown,selectClientDropDownSearchText,FrameworkConstants.CLIENT_NAME,selectCreatedNewClientText);
        click(addButton);
        click(buttonSave);
        typeForSearch(searchByNameText,FrameworkConstants.BLUEPRINT_NAME);
        click(editBlueprints);
        return customTextVisible(FrameworkConstants.CLIENT_NAME);
    }
    public void enableRegistrationINRegistrationSettings(){
        click(editIconRegistrationSetting);
        click(editIconCustomizeYourPreferredOnboardingMethods);
        click(toggleEnableRegistration);
    }
    public void enableRegistrationInviteINRegistrationSettings(){
        click(editIconRegistrationSetting);
        click(editIconCustomizeYourPreferredOnboardingMethods);
        click(toggleEnableRegistration);
    }
    public void enableRequiredFieldInCustomizeSchemas(){
        click(editIconCustomizeSchemaSettings);
        clickOnSchemaToggle("given_name");
        clickOnSchemaToggle("family_name");
        clickOnSchemaToggle("email");
        clickOnSchemaToggle("username");
        clickOnSchemaToggle("phone_number");
        clickOnSchemaToggle("password");
        clickOnSchemaToggle("confirm_password");
        click(buttonSave);
    }







}

