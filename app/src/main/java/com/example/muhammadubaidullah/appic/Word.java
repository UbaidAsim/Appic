package com.example.muhammadubaidullah.appic;

public class Word {
    private String Default="";
    private String Mivok="";
    private int iconID;
    private int voiceID;
    public Word(String Default,String Mivok,int iconID,int voiceID){
        this.Default=Default;
        this.Mivok=Mivok;
        this.iconID=iconID;
        this.voiceID=voiceID;
    }
    public Word(String Default, String Mivok,int voiceID){
        this.Default=Default;
        this.Mivok=Mivok;
        this.voiceID=voiceID;
    }


    public String getDefault(){
        return Default;
    }
    public String getMivok(){
        return Mivok;
    }
    public int getIconID(){
        return iconID;
    }
    public int getVoiceID(){
        return this.voiceID;
    }
}

