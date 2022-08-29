package com.example.testtodo;

import java.util.ArrayList;
import java.util.Random;

public class Database {
    private ArrayList<Note> notes=new ArrayList<>();

    private static Database instance=null;

    public static Database getInstance(){
        if (instance==null){
            instance=new Database();
        }
        return instance;
    }

    private Database(){
        Random random=new Random();
        for (int i=1;i<21;i++){
            Note note=new Note(i,"Note: "+i,random.nextInt(3)+1);
            notes.add(note);
        }
    }

    public void add(Note note){
        notes.add(note);
    }

    public void remove(int id){
        for (int i=0;i<notes.size();i++){
            Note note=notes.get(i);
            if (id==note.getId()){
                notes.remove(note);
            }
        }
    }

    public ArrayList<Note> getNotes() {
        return new ArrayList<>(notes);
    }
}
