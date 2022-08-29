package com.example.testtodo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private NotesDatabase notesDatabase;
    private int count=0;
    private MutableLiveData<Integer> countLD=new MutableLiveData<>();
    public MainViewModel(@NonNull Application application) {
        super(application);
        notesDatabase=NotesDatabase.getInstance(application);
    }
    public void remove(Note note){
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                notesDatabase.notesDao().remove(note.getId());
            }
        });
        thread.start();
    }
    public void showCount(){
        count++;
        countLD.setValue(count);
    }
    public LiveData<Integer> getCount(){
        return countLD;
    }
    public LiveData<List<Note>> getNotes(){
        return notesDatabase.notesDao().getNotes();
    }
}
