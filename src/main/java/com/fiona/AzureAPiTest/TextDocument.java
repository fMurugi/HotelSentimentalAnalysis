package com.fiona.AzureAPiTest;

public class TextDocument {
    public String  text;
    private  String id;
    private String language;


    public TextDocument(String id, String text, String language) {
        this.text = text;
        this.id = id;
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
