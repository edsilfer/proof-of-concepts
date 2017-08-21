package br.com.edsilfer.lint_rules.resources;

/**
 * Created by edgar on 23/08/17.
 */

public class Strings {

    public static final String STR_ISSUE_001_ID = "SAN";
    public static final String STR_ISSUE_001_DESCRIPTION = "Argument should have more than one character";
    public static final String STR_ISSUE_001_EXPLANATION = "Arguments named with only one character do not pass any meaning to the reader. " +
            "Argument's name should clearly indicate the meaning for the value it is holding";


    public static final String STR_ISSUE_002_ID = "CWM";
    public static final String STR_ISSUE_002_DESCRIPTION = "Be careful when using this method.";
    public static final String STR_ISSUE_002_EXPLANATION = "This method has special conditions surrounding it's use," +
            " be careful when calling it and refer to its documentation.";
}
