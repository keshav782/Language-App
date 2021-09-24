package com.example.miwok;

public class Word {
    private String mEnglishTransition;

    private String mMivokTransition;

    private int mImageid=NO_IMAGE;

    private static final int NO_IMAGE=-1;

    private  int maudioID;

    public Word (String englishTransition,String mivokTransition,int audioID){
        mEnglishTransition=englishTransition;
        mMivokTransition=mivokTransition;
        maudioID= audioID;
    }

    public Word (String englishTransition,String mivokTransition,int imageint,int audioID){
        mEnglishTransition=englishTransition;
        mMivokTransition=mivokTransition;
        mImageid=imageint;
        maudioID= audioID;
    }


    public String getenglishTransition() {
            return mEnglishTransition;
    }
    public String getmivokTransition() {
                return mMivokTransition;
    }
    public int getimageTransition() {
        return mImageid;
    }

    public boolean hasimage(){
        return mImageid!=NO_IMAGE;
    }

    public int getMaudioID(){
        return maudioID;
    }
}
