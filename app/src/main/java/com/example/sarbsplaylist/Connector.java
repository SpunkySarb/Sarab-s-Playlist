package com.example.sarbsplaylist;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Connector extends Thread {
  public static  Document document;
    @Override
    public  void run() {

        try {
             Document doc = Jsoup.connect("https://sarbsagar143.com/MusicIndex.html").userAgent("mozilla/17.0").get();
             setdoc(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public  void setdoc( Document docc){

       document = docc;

    }
    public Document getdoc(){
        return document;
    }
}
