package com.example.sarbsplaylist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static ListView list;
    private MediaPlayer mediaPlayer;
    public static int play = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        list = (ListView) findViewById(R.id.list);
        final MusicAdapter adapter = new MusicAdapter(this);
        list.setAdapter(adapter);

        try {
            MusicScrapper.Scrapper();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

              // play = position;
                adapter.selectedItem(position);
adapter.notifyDataSetChanged();
                String url = MusicScrapper.MusicLinks.get(position); // your URL here

                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    mediaPlayer.release();
                }


                mediaPlayer = MediaPlayer.create(parent.getContext(), Uri.parse(url));

                mediaPlayer.start();

                /**   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                 mediaPlayer.setAudioAttributes(
                 new AudioAttributes.Builder()
                 .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                 .setUsage(AudioAttributes.USAGE_MEDIA)
                 .build()
                 );
                 }
                 try {
                 mediaPlayer.setDataSource(url);
                 } catch (IOException e) {
                 e.printStackTrace();
                 }
                 try {
                 mediaPlayer.prepare(); // might take long! (for buffering, etc)
                 } catch (IOException e) {
                 e.printStackTrace();
                 }



                 mediaPlayer.start();
                 **/
            }


        });

    }

    private class MusicAdapter extends ArrayAdapter<String> {
        int position = -1;

        public MusicAdapter(Context ctx) {
            super(ctx, 0);
        }

        public int getCount() {
            return MusicScrapper.MusicNames.size();
        }
        public void selectedItem(int position)
        {
            this.position = position; //position must be a global variable
        }
        public String getItem(int position) {
            return MusicScrapper.MusicNames.get(position);
        }
        LayoutInflater inflater;
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                inflater = (LayoutInflater) getContext().getSystemService(
                        Activity.LAYOUT_INFLATER_SERVICE);
            }
                if (this.position == position) {

                    View view2 = inflater.inflate(R.layout.item_playing, null);
                    ImageView playing = (ImageView) view2.findViewById(R.id.imageView3);
                    Glide.with(getApplicationContext()).asGif().load(R.drawable.playing).into(playing);
                    TextView song = (TextView) view2.findViewById(R.id.textView);
                    song.setText(MusicScrapper.MusicNames.get(position).toString());
                    return view2;
                }
                // set convertView Background
                convertView = inflater.inflate(R.layout.item_layout, null);
                TextView song = (TextView) convertView.findViewById(R.id.textView);
                song.setText(MusicScrapper.MusicNames.get(position).toString());
                return convertView;





        }

    }

}



