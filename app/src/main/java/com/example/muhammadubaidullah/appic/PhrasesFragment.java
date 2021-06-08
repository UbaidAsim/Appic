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

public class PhrasesFragment extends Fragment {

    private final static String TAG = "PhrasesTab";

    private MediaPlayer mediaplayer;
    private AudioManager PhrasesAudioManager;
    private AudioManager.OnAudioFocusChangeListener afchangelistener= new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focuschange) {
            if (focuschange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK || focuschange== AudioManager.AUDIOFOCUS_LOSS_TRANSIENT){
                mediaplayer.pause();
                mediaplayer.seekTo(0);
            }
            else if (focuschange==AudioManager.AUDIOFOCUS_GAIN){
                mediaplayer.start();
            }
            else if (focuschange==AudioManager.AUDIOFOCUS_LOSS){
                mediaplayercheck();
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View rootview =inflater.inflate(R.layout.word_list,container,false);

        PhrasesAudioManager=(AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> phrases= new ArrayList<Word>();
        phrases.add(new Word("Hello!","¡Hola!", R.raw.hola));
        phrases.add(new Word("How are you?","¿Cómo estás?",R.raw.como_estas));
        phrases.add(new Word("I'm fine.","Estoy bien.",R.raw.estoybein));
        phrases.add(new Word("And you?","¿Y usted?",R.raw.usted));
        phrases.add(new Word("I'm also fine.","También estoy bien.",R.raw.tambien));
        phrases.add(new Word("What's your name?","¿Cuál es tu nombre?",R.raw.cual_es_su_nombre));
        phrases.add(new Word("My name is _____","Me llamo _____",R.raw.me_nombre));
        phrases.add(new Word("Where are you going","¿A dónde vas?",R.raw.a_donde_vas));
        phrases.add(new Word("I'm going to school.","Voy a la escuela.",R.raw.voy_a_la_escuela));
        phrases.add(new Word("That's Great!","¡Eso es genial!",R.raw.genial));
        phrases.add(new Word("See you later.","Nos vemos más tarde.",R.raw.nos_vemos));
        phrases.add(new Word("Bye!","¡Adiós!",R.raw.adios));
        phrases.add(new Word("Thank You!","Gracias!",R.raw.gracias));
        phrases.add(new Word("You are welcome.","De nada.",R.raw.denada));
        phrases.add(new Word("Okay.","Bueno.",R.raw.bueno));
        phrases.add(new Word("Welcome.","Bienvenido.",R.raw.bienvenido));
        phrases.add(new Word("I am sorry.","Lo siento.",R.raw.losiento));
        phrases.add(new Word("I love you.","Te Amo",R.raw.teamo));
        phrases.add(new Word("I hate you.","Te odio",R.raw.teodio));
        phrases.add(new Word("Water","Agua",R.raw.agua));
        phrases.add(new Word("Milk","Leche",R.raw.leche));
        phrases.add(new Word("Dog","Perro",R.raw.perro));
        phrases.add(new Word("Cat","Gato",R.raw.gato));

        WordAdapter phraseswordAdapter= new WordAdapter(getActivity(),phrases);
        ListView PhrasesList= (ListView)rootview.findViewById(R.id.list);
        PhrasesList.setAdapter(phraseswordAdapter);

        PhrasesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                mediaplayercheck();
                int AudioManagerResult=PhrasesAudioManager.requestAudioFocus(afchangelistener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (AudioManagerResult== AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mediaplayer = MediaPlayer.create(getActivity(), phrases.get(position).getVoiceID());
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
            PhrasesAudioManager.abandonAudioFocus(afchangelistener);
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        mediaplayercheck();
    }

}
