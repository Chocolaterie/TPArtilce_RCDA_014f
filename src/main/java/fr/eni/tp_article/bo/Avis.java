package fr.eni.tp_article.bo;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class Avis {

    public Long id;

    @Min(1)
    @Max(5)
    public int note;

    public String comment;

    public AppUser user;

    public Movie movie;

    public Avis() {
    }

    public Avis(Long id, int note, String comment) {
        this.id = id;
        this.note = note;
        this.comment = comment;
    }

    public Avis(Long id, int note, String comment, AppUser user) {
        this.id = id;
        this.note = note;
        this.comment = comment;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
