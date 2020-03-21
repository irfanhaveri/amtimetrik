package com.example.restservice.model;

import java.util.List;

public class Root {

    public List<Header> header;

    public List<Header> getHeader() {
        return header;
    }

    public void setHeader(List<Header> header) {
        this.header = header;
    }

    public Root(List<Header> header) {
        this.header = header;
    }
}
