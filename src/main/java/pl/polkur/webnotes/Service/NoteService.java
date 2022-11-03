package pl.polkur.webnotes.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polkur.webnotes.Model.Note;
import pl.polkur.webnotes.Repository.NoteRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class NoteService
{
    @Autowired
    private NoteRepository noteRepository;

    public boolean removeNote(Long id)
    {
        boolean removed = false;
        Optional<Note> byId = noteRepository.findById(id);
        if(byId.isPresent())
        {
            noteRepository.delete(byId.get());
            removed=true;
        }
        return removed;
    }
    public boolean updateNote(Note note)
    {
        boolean saved = false;
        Optional<Note> byId = noteRepository.findById(note.getId());
        if(byId.isPresent())
        {
            noteRepository.save(note);
            saved=true;
        }
        return saved;
    }

    public Optional<Note> getNoteById(Long id)
    {
        return noteRepository.findById(id);
    }
    public Note saveNote(Note note)
    {
        note.setDate(new Date());
        return noteRepository.save(note);
    }

    public List<Note> getPublicNotes()
    {
        return noteRepository.getPublicNotes();
    }

    public List<Note> getAllNotes()
    {
        return noteRepository.findAll();
    }
}
