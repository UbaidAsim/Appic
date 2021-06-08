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

public class ColorFragment extends Fragment {
    private static final String TAG="ColorsTab";

    private MediaPlayer mediaplayer;
    private AudioManager ColorsAudioManager;
    private AudioManager.OnAudioFocusChangeListener afchangerlistener=new AudioManager.OnAudioFocusChangeListener(){
        @Override
        public void onAudioFocusChange(int focusChange){
            if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK || focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT){
                mediaplayer.pause();
                mediaplayer.seekTo(0);
            }
            else if (focusChange== AudioManager.AUDIOFOCUS_GAIN){
                mediaplayer.start();
            }
            else if (focusChange== AudioManager.AUDIOFOCUS_LOSS){
                mediaplayercheck();
            }
        }
    };


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview= inflater.inflate(R.layout.word_list,container,false);

        ColorsAudioManager=(AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> colors=new ArrayList<Word>();
        colors.add(new Word("White","Blanco",R.drawable.white,R.raw.blanco));
        colors.add(new Word("Red","Rojo",R.drawable.red, R.raw.rojo));
        colors.add(new Word("Yellow","Amarillo",R.drawable.yellow, R.raw.amarillo));
        colors.add(new Word("Green","Verde", R.drawable.green, R.raw.verde));
        colors.add(new Word("Blue","Azul",R.drawable.blue, R.raw.azul));
        colors.add(new Word("Pink","Rosado", R.drawable.pink,R.raw.rosado));
        colors.add(new Word("Orange","Naranja", R.drawable.orange, R.raw.naranja));
        colors.add(new Word("Purple","Púrpura", R.drawable.purple, R.raw.purpura));
        colors.add(new Word("Brown","Marrón",R.drawable.brown, R.raw.marron));
        colors.add(new Word("Black","Negro", R.drawable.black, R.raw.negro));

        WordAdapter colorWordAdapter= new WordAdapter(getActivity(),colors);
        ListView ColorsList= (ListView)rootview.findViewById(R.id.list);
        ColorsList.setAdapter(colorWordAdapter);

        ColorsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                mediaplayercheck();

                int AudioManagerResult=ColorsAudioManager.requestAudioFocus(afchangerlistener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(AudioManagerResult==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mediaplayer = MediaPlayer.create(getActivity(), colors.get(position).getVoiceID());
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
            ColorsAudioManager.abandonAudioFocus(afchangerlistener);
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        mediaplayercheck();
    }
}
