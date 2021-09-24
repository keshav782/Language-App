package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Family extends AppCompatActivity {

    private AudioManager audioManager;
    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }
            else if(focusChange==AudioManager.AUDIOFOCUS_GAIN){
                mediaPlayer.start();

            }
            else if (focusChange==AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }
        }
    };

    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

       final ArrayList<Word> word = new ArrayList<Word>();
        // word.add("one");
//        Word w = new Word("one","lutti");
//        word.add(w);
        word.add(new Word("father","әpә",R.drawable.family_father,R.raw.family_father));
        word.add(new Word("mother","әṭa",R.drawable.family_mother,R.raw.family_mother));
        word.add(new Word("son","angsi",R.drawable.family_son,R.raw.family_son));
        word.add(new Word("daughter","tune",R.drawable.family_daughter,R.raw.family_daughter));
        word.add(new Word("older brother","taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        word.add(new Word("young brother","chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        word.add(new Word("older sister","teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        word.add(new Word("young sister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        word.add(new Word("grand mother","ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        word.add(new Word("grand father","paapa",R.drawable.family_grandfather,R.raw.family_grandfather));



        WordAdapter adapter = new WordAdapter(this,word,R.color.category_family);
        ListView listView = (ListView) findViewById(R.id.list2);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Word word1 = word.get(position);
                releaseMediaPlayer();

                int result = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(Family.this, word1.getMaudioID());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(completionListener);
                }
            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();

        releaseMediaPlayer();
    }
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }
}