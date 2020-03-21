package com.example.restservice.model;

public class Header {
    public int page;
    public int pages;
    public String per_page;

    public Header(int page, int pages, String per_page) {
        this.page = page;
        this.pages = pages;
        this.per_page = per_page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getPer_page() {
        return per_page;
    }

    public void setPer_page(String per_page) {
        this.per_page = per_page;
    }
}
