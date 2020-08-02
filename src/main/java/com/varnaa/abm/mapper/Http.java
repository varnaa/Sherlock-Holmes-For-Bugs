package com.varnaa.abm.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

@Component
public class Http {
    public final String IN = "+in:file";
    public final String ISSUE_STATE = "+state:closed";
    public final String SEARCH_CODE = "/search/code?q=";
    public final String SEARCH_ISSUE = "/search/issues?q=";
    public final String ROOT_ENDPOINT = "https://api.github.com";
    public final String ACCEPT = "application/vnd.github.v3+json";
    public final String ACCEPT_ISSUE = "application/vnd.github.v3.text-match+json";

    public String language;
    public String user_name;
    public String extension;
    public String auth_token;
    public String repository_name;

    @Autowired
    private ProjectProperities projectProperities;

    public Http() {
    }

    public void initializeProperies() {
        this.language = projectProperities.language;
        this.extension = projectProperities.extension;
        this.user_name = projectProperities.user_name;
        this.auth_token = projectProperities.auth_token;
        this.repository_name = projectProperities.repository_name;
    }

    public HttpClient getHttp() {
        return HttpClient.newHttpClient();
    }

    public String constructRepoQualifier() {
        return "+repo:" + this.user_name + "/" + this.repository_name;
    }

    public String searchIssueQuery(String query) {
        return SEARCH_ISSUE + query + constructRepoQualifier() + ISSUE_STATE;
    }

    public String searchCodeQuery(String query) {
        return SEARCH_CODE + query + constructRepoQualifier() + IN + "+language:" + language + "+extension:" + extension;

    }

    public HttpRequest getIssueRequest(String query) {
        return HttpRequest.newBuilder()
                .GET()
                .header("Accept", ACCEPT_ISSUE)
                .header("Authorization", auth_token)
                .uri(URI.create(ROOT_ENDPOINT + searchIssueQuery(query)))
                .build();
    }

    public HttpRequest getCodeRequest(String query) {
        return HttpRequest.newBuilder()
                .GET()
                .header("Accept", ACCEPT)
                .header("Authorization", auth_token)
                .uri(URI.create(ROOT_ENDPOINT + searchCodeQuery(query)))
                .build();
    }


}
