package com.example.testtodo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class AddNoteViewModel extends AndroidViewModel {
    private NotesDao notesDao;
    private MutableLiveData<Boolean> shouldCase=new MutableLiveData<>();
    public AddNoteViewModel(@NonNull Application application) {
        super(application);
        notesDao=NotesDatabase.getInstance(application).notesDao();
    }

    public LiveData<Boolean> getShouldCase() {
        return shouldCase;
    }

    public void saveNote(Note note){
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                notesDao.add(note);
                shouldCase.postValue(true);
            }
        });
        thread.start();
    }
}
