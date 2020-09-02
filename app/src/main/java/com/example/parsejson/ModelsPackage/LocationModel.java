package com.example.parsejson.ModelsPackage;

import java.util.List;

public class LocationModel {

    private List<Object> html_attributions = null;
    private String next_page_token;
    private Result[] results = null;
    private String status;

    public List<Object> getHtml_attributions() {
        return html_attributions;
    }

    public void setHtml_attributions(List<Object> html_attributions) {
        this.html_attributions = html_attributions;
    }

    public String getNext_page_token() {
        return next_page_token;
    }

    public void setNext_page_token(String next_page_token) {
        this.next_page_token = next_page_token;
    }

    public Result[] getResults() {
        return results;
    }

    public void setResults(Result[] results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}