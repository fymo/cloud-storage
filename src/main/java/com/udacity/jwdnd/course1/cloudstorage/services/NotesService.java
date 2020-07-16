package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UserNote;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {

    private NotesMapper notesMapper;

    public NotesService(NotesMapper notesMapper) {
        this.notesMapper = notesMapper;
    }

    public List<UserNote> getNotesByUserId(Integer userId) {
        return notesMapper.getNotesByUserId(userId);
    }

    public Integer saveNote(UserNote note) {
        Integer noteId = note.getNoteId();
        if(noteId == null){
            noteId = notesMapper.saveNote(note);
        }else{
            notesMapper.updateNote(note);
        }
        return noteId;
    }

    public void deleteNote(Integer noteId) {
        notesMapper.deleteNoteByNoteId(noteId);
    }
}
