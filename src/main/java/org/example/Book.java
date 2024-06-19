package org.example;

import java.util.List;

public class Book {
    private String title;
    private String author;
    private String blurb;
    private String image_url;
    private String published_date;
    private List<String> categories;
    private String loaned_by_id;
    private String return_date;

    // --------------------------- //


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getLoaned_by_id() {
        return loaned_by_id;
    }

    public void setLoaned_by_id(String loaned_by_id) {
        this.loaned_by_id = loaned_by_id;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }
}
