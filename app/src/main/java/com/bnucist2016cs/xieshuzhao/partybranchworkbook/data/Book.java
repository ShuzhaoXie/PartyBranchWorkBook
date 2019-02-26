package com.bnucist2016cs.xieshuzhao.partybranchworkbook.data;

import android.net.Uri;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2018/6/4.
 */

public class Book extends DataSupport{
    private String label;
    private String spot;
    private String start;
    private String end;
    private String content;
    private String news;
    private List<String> path;

    public Book(){
        label = null;
        spot = null;
        start = null;
        end = null;
        content = null;
        news = null;
        path = new ArrayList<>();
    }

    public String getSpot(){
        return spot;
    }

    public void setSpot(String spot) {
        this.spot = spot;
    }

    public String getEnd() {
        return end;
    }

    public String getStart() {
        return start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setStart(String start) {
        this.start = start;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public List<String> getPath() {
        return path;
    }
}
