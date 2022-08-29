package com.example.testtodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {
    EditText edText;
    
    RadioButton radioLow;
    RadioButton radioMedium;
    RadioButton radioHigh;
    private AddNoteViewModel addNoteViewModel;
    
    Button btSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initViews();
        addNoteViewModel=new ViewModelProvider(this).get(AddNoteViewModel.class);
        addNoteViewModel.getShouldCase().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean shouldCase) {
                if (shouldCase){
                    finish();
                }
            }
        });
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkNote();
            }
        });
    }

    private void checkNote() {
        String text=edText.getText().toString().trim();
        if (text.isEmpty()){
            Toast.makeText(this, "Добавьте текст заметки", Toast.LENGTH_SHORT).show();
        }
        else {
            int priority=getPriority();
            Note note=new Note(text,priority);
            addNoteViewModel.saveNote(note);
        }
    }

    private int getPriority() {
        int priority;
        if (radioLow.isChecked()){
            priority=1;
        }
        else if (radioMedium.isChecked()){
            priority=2;
        }
        else{
            priority=3;
        }
        return priority;
    }

    private void initViews() {
        edText=findViewById(R.id.edText);
        radioLow=findViewById(R.id.radioLow);
        radioMedium=findViewById(R.id.radioMedium);
        radioHigh=findViewById(R.id.radioHigh);
        btSave=findViewById(R.id.btSave);
    }

    public static Intent newIntent(Context context){
        return new Intent(context,AddNoteActivity.class);
    }
}