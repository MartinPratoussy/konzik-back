package com.konzik.Ticketmaster.payloads.request;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

public class ApiRequest {
    @NotBlank
    private Map<String, String> filters = new HashMap<>();


    public Map<String, String> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, String> filters) {
        this.filters = filters;
    }
}
