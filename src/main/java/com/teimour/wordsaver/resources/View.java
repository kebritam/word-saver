package com.teimour.wordsaver.resources;

/**
 * @author kebritam
 * Project word-saver
 * Created on 25/11/2020
 */

public class View {
    private static final String CATEGORY_DIR = "category/";
    private static final String DEFINITION_DIR = "definition/";
    private static final String EXCEPTION_DIR = "exceptions/";
    private static final String FIRST_PAGE_DIR = "first-page/";
    private static final String WORD_DIR = "word/";

    public static final String INDEX = FIRST_PAGE_DIR + "index";
    public static final String LOGIN = FIRST_PAGE_DIR + "login";
    public static final String WORD = WORD_DIR + "word";
    public static final String WORD_FORM = WORD_DIR + "wordForm";
    public static final String NEW_WORD_FORM = WORD_DIR + "newWordForm";
    public static final String HOME = WORD_DIR + "home";
    public static final String DEFINITION_FORM = DEFINITION_DIR + "definitionForm";
    public static final String NEW_DEFINITION_FORM = DEFINITION_DIR + "newDefinitionForm";
    public static final String CATEGORY_FORM = CATEGORY_DIR + "categoryForm";
    public static final String NEW_CATEGORY_FORM = CATEGORY_DIR + "newCategoryForm";
    public static final String CATEGORY = CATEGORY_DIR + "category";
    public static final String NOT_FOUND = EXCEPTION_DIR + "404Error";
}
