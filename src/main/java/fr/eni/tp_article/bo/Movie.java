package fr.eni.tp_article.bo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    public Long id;

    @NotBlank
    public String title;

    @Min(1800)
    public int year;

    @Min(1)
    public int duration;

    @NotBlank
    public String synopsis;

    public Genre genre;

    public Participant director;

    public List<Participant> actors;

    public List<Avis> avis;

    public Movie() {
        this.actors = new ArrayList<>();
        this.avis = new ArrayList<>();
    }

    public Movie(Long id, String title, int year, int duration, String synopsis) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.synopsis = synopsis;
        this.actors = new ArrayList<>();
        this.avis = new ArrayList<>();
    }

    public Movie(Long id, String title, int year, int duration, String synopsis,
                 Genre genre, Participant director) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.synopsis = synopsis;
        this.genre = genre;
        this.director = director;
        this.actors = new ArrayList<>();
        this.avis = new ArrayList<>();
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Participant getDirector() {
        return director;
    }

    public void setDirector(Participant director) {
        this.director = director;
    }

    public List<Participant> getActors() {
        return actors;
    }

    public void setActors(List<Participant> actors) {
        this.actors = actors;
    }

    public List<Avis> getAvis() {
        return avis;
    }

    public void setAvis(List<Avis> avis) {
        this.avis = avis;
    }

    public boolean actorIsSelected(Participant participant) {
        return actors.stream()
                .anyMatch(actor -> actor.getId().equals(participant.getId()));
    }
}
