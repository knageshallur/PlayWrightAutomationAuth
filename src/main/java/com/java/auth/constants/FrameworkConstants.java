package com.java.auth.constants;


import com.java.auth.helpers.JsonHelpers;
import com.java.auth.helpers.RandomNumberHelpers;

public class FrameworkConstants {
    public static final String CLIENT_NAME=JsonHelpers.getValue("CreateClient", "client_name") + RandomNumberHelpers.randomNumberGenerator() ;
    public static final String LOGO_URL=JsonHelpers.getValue("CreateClient", "logo_URL");
    public static final String TEST_DATA_JSON_PATH = "src/test/resources/datajson/testdata.json";
    public static final String BLUEPRINT_NAME= JsonHelpers.getValue("blueprint", "blueprintName")+ RandomNumberHelpers.randomNumberGenerator();
    public static final String BLUEPRINT_DESCRIPTION= JsonHelpers.getValue("blueprint","description");
    public static final String BLUEPRINT_CREATE_TOAST_MESSAGE = JsonHelpers.getValue("blueprint","createToastMessage");
}
