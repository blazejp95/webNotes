package pl.polkur.webnotes.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Note
{
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private Date date;
    private boolean privateNote;


    public Note(String title, String content, boolean privateNote) {
        this.title = title;
        this.content = content;
        this.privateNote = privateNote;
    }

    public Note() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isPrivateNote() {
        return privateNote;
    }

    public void setPrivateNote(boolean privateNote) {
        this.privateNote = privateNote;
    }

    public Note(Long id, String title, String content, Date date, boolean privateNote) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.privateNote = privateNote;
    }
}
