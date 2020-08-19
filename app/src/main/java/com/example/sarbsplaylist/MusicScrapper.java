package com.example.sarbsplaylist;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MusicScrapper {


public static ArrayList<String> MusicNames = new ArrayList<>();

public static ArrayList<String> MusicLinks = new ArrayList<>();


public static void  Scrapper() throws InterruptedException {

    Connector obj = new Connector();
    obj.start();
    obj.join();
    Document doc = obj.getdoc();

    Elements temp=doc.select("a");


    int i=0;
    for (Element music:temp) {

        try{


            Element link = doc.select("a").get(i);
            i++;
            String songName = music.getElementsByTag("a").first().text();



            String songURL= link.absUrl("href");
            MusicNames.add(songName);
            MusicLinks.add(songURL);



        }catch(NullPointerException e) {
            System.out.println("");
        }

    }


}





















}
