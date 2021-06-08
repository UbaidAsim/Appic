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

public class FamilyFragment extends Fragment {
    private static final String TAG="FamilyFragment";

    private MediaPlayer mediaplayer;
    private AudioManager FamilyAudioManager;
    private AudioManager.OnAudioFocusChangeListener afchangelistener= new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focuschange) {
            if(focuschange== AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focuschange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
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
       View rootview= inflater.inflate(R.layout.word_list,container,false);

        FamilyAudioManager=(AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> family= new ArrayList<Word>();
        family.add(new Word("Father","Padre", R.drawable.father,R.raw.padre));
        family.add(new Word("Mother","Madre", R.drawable.mother,R.raw.madre));
        family.add(new Word("Brother","Hermano", R.drawable.brother, R.raw.hermano));
        family.add(new Word("Sister","Hermana",R.drawable.sister, R.raw.hermana));
        family.add(new Word("Siblings","Hermanos",R.drawable.siblings,R.raw.hermanos));
        family.add(new Word("Baby","Bebé",R.drawable.baby,R.raw.bebe));
        family.add(new Word("Grandfather","Abuelo",R.drawable.grandpa,R.raw.abuelo));
        family.add(new Word("Grandmother","Abuela",R.drawable.grandma,R.raw.abuela));
        family.add(new Word("Friend","Amigo",R.drawable.friend,R.raw.amigo));
        family.add(new Word("Cousin","Prima",R.drawable.cousin,R.raw.prima));
        family.add(new Word("Bride","Novia",R.drawable.bride,R.raw.novia));
        family.add(new Word("Bride Groom","Novio",R.drawable.couple,R.raw.novio));
        family.add(new Word("Couple","Pareja",R.drawable.coupletwo,R.raw.pareja));


        WordAdapter familywordAdapter= new WordAdapter(getActivity(),family);
        ListView familyList= (ListView)rootview.findViewById(R.id.list);
        familyList.setAdapter(familywordAdapter);

        familyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                mediaplayercheck();
                int AudioManagerResult= FamilyAudioManager.requestAudioFocus(afchangelistener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (AudioManagerResult==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mediaplayer = MediaPlayer.create(getActivity(), family.get(position).getVoiceID());
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
            FamilyAudioManager.abandonAudioFocus(afchangelistener);

        }

    }

    @Override
    public void onStop() {
        super.onStop();
        mediaplayercheck();
    }
}
