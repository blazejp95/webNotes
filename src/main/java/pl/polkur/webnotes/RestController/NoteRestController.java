package pl.polkur.webnotes.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.polkur.webnotes.Model.Note;
import pl.polkur.webnotes.Service.NoteService;
import java.net.URI;
import java.security.Principal;
import java.util.Optional;

@RestController
public class NoteRestController
{
    @Autowired
    private NoteService noteService;

    @DeleteMapping("/remove")
    ResponseEntity<?>  removeNote(@RequestParam(name = "id") Long id)
    {
        if (noteService.removeNote(id))
        {
            return ResponseEntity.ok().build();
        }
        else
        {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/getAll")
    ResponseEntity<?>  getAllNotes(Principal principal)
    {
        System.out.println("jestem tu!");
        if(principal!=null)
        {
            return ResponseEntity.ok(noteService.getAllNotes());
        }
        else
        {
            return ResponseEntity.ok(noteService.getPublicNotes());
        }
    }

    @PutMapping(value = "/update",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> updateNote(@RequestBody Note note)
    {
        if(noteService.updateNote(note))
        {
            return ResponseEntity.ok().build();
        }
        else
        {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/get")
    ResponseEntity<?>  getNote(@RequestParam(name = "id") Long id, Principal principal)
    {
        Optional<Note> noteById = noteService.getNoteById(id);
        if(principal!=null)
        {
            if(noteById.isPresent())
            {
                return ResponseEntity.ok(noteById.get());
            }
            else
            {
                return ResponseEntity.notFound().build();
            }
        }
        else
        {
            if (noteById.isPresent())
            {
                if(noteById.get().isPrivateNote())
                {
                    return ResponseEntity.notFound().build();
                }
                else
                {
                    return ResponseEntity.notFound().build();
                }
            }
            else
            {
                return ResponseEntity.notFound().build();
            }
        }
    }

    @PostMapping(value = "/add",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> addNote(@RequestBody Note note)
    {
        Note savedNote = noteService.saveNote(note);
        URI savedCompanyUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedNote.getId())
                .toUri();
        return ResponseEntity.created(savedCompanyUri).body(savedNote);
    }
}
