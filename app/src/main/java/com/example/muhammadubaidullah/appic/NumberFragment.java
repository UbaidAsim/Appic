package com.example.muhammadubaidullah.appic;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class NumberFragment extends Fragment {
    private static final String TAG="NumbersTab";

    private MediaPlayer mediaplayer;
    private AudioManager numberAudioManager;
    private AudioManager.OnAudioFocusChangeListener afChangeListener=new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mediaplayer.pause();
                mediaplayer.seekTo(0);
            }
            else if(focusChange==AudioManager.AUDIOFOCUS_GAIN){
                mediaplayer.start();
            }
            else if (focusChange==AudioManager.AUDIOFOCUS_LOSS){
                mediaplayercheck();
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview =inflater.inflate(R.layout.word_list,container,false);

        numberAudioManager=(AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);


        final ArrayList<Word> numbers = new ArrayList<Word>();
        numbers.add(new Word("Zero","Cero",R.drawable.zero,R.raw.cero));
        numbers.add(new Word("One", "Uno",R.drawable.one, R.raw.uno));
        numbers.add(new Word("Two", "Dos", R.drawable.two, R.raw.dos));
        numbers.add(new Word("Three", "Tres",R.drawable.three,R.raw.tres));
        numbers.add(new Word("Four", "Cuatro",R.drawable.four,R.raw.cuatro));
        numbers.add(new Word("Five", "Cinco", R.drawable.five,R.raw.cinco));
        numbers.add(new Word("Six", "Seis", R.drawable.six,R.raw.seis));
        numbers.add(new Word("Seven", "Siete", R.drawable.seven,R.raw.siete));
        numbers.add(new Word("Eight", "Ocho", R.drawable.eight, R.raw.ocho));
        numbers.add(new Word("Nine", "Nueve", R.drawable.nine, R.raw.nueve));
        numbers.add(new Word("Ten", "Diez", R.drawable.ten, R.raw.diez));
        numbers.add(new Word("Eleven", "Once", R.drawable.eleven, R.raw.once));
        numbers.add(new Word("Twelve", "Doce", R.drawable.twelve,R.raw.doce));
        numbers.add(new Word("Thirteen", "Trece", R.drawable.thirteen, R.raw.trece));
        numbers.add(new Word("Fourteen", "Catorce", R.drawable.fourteen, R.raw.catorce));
        numbers.add(new Word("Fifteen", "Quince",R.drawable.fifteen,R.raw.quince));
        numbers.add(new Word("Sixteen", "Dieciséis", R.drawable.sixteen, R.raw.dieciseis));
        numbers.add(new Word("Seventeen", "Diecisiete",R.drawable.seventeen, R.raw.diecisiete));
        numbers.add(new Word("Eighteen", "Dieciocho", R.drawable.eighteen,R.raw.dieciocho));
        numbers.add(new Word("Ninteen", "Diecinueve", R.drawable.ninteen, R.raw.diecinueve));
        numbers.add(new Word("Twenty", "Veinte",R.drawable.twenty,R.raw.veinte));
        numbers.add(new Word("Hundred","Cien",R.drawable.hundred,R.raw.cien));
        numbers.add(new Word("Five Hundred","Quinientos",R.drawable.fivehundred,R.raw.quinientos));
        numbers.add(new Word("Thousand","Mil",R.drawable.thousand,R.raw.mil));


        WordAdapter numberAdapter = new WordAdapter(getActivity(), numbers);
        final ListView NumbersList = (ListView) rootview.findViewById(R.id.list);
        NumbersList.setAdapter(numberAdapter);

        NumbersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                mediaplayercheck();

                int AudioManagerResult= numberAudioManager.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (AudioManagerResult==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mediaplayer = MediaPlayer.create(getActivity(), numbers.get(position).getVoiceID());
                    mediaplayer.start();
                    mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaplayercheck();
                        }
                    });
                }

            }

        });

        return rootview;
    }

    public void mediaplayercheck(){
        if (mediaplayer !=null){
            mediaplayer.release();
            mediaplayer=null;

        }
        numberAudioManager.abandonAudioFocus(afChangeListener);

    }

    @Override
    public void onStop() {
        super.onStop();
        mediaplayercheck();
    }
}
