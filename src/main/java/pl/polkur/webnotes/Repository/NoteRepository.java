package pl.polkur.webnotes.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.polkur.webnotes.Model.Note;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>
{
    @Query(value = "select c from Note c where c.privateNote = false")
    public List<Note> getPublicNotes();
}
