package com.example.android.assignmenttask.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class University {

    @SerializedName("domains")
    @Expose
    private List<String> domains = new ArrayList<>();
    @SerializedName("web_pages")
    @Expose
    private List<String> webPages = new ArrayList<>();
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("alpha_two_code")
    @Expose
    private String alphaTwoCode;
    @SerializedName("state-province")
    @Expose
    private String stateProvince;
    @SerializedName("country")
    @Expose
    private String country;

    public University() {
    }

    public University(List<String> domains, List<String> webPages, String name, String alphaTwoCode, String stateProvince, String country) {
        this.domains = domains;
        this.webPages = webPages;
        this.name = name;
        this.alphaTwoCode = alphaTwoCode;
        this.stateProvince = stateProvince;
        this.country = country;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    public List<String> getWebPages() {
        return webPages;
    }

    public void setWebPages(List<String> webPages) {
        this.webPages = webPages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlphaTwoCode() {
        return alphaTwoCode;
    }

    public void setAlphaTwoCode(String alphaTwoCode) {
        this.alphaTwoCode = alphaTwoCode;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
