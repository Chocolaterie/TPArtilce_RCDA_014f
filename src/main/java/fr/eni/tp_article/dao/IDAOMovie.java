package fr.eni.tp_article.dao;

import fr.eni.tp_article.bo.Avis;
import fr.eni.tp_article.bo.Genre;
import fr.eni.tp_article.bo.Movie;
import fr.eni.tp_article.bo.Participant;

import java.util.List;

public interface IDAOMovie {

    public List<Participant> selectAllParticipants();

    public List<Genre> selectAllGenres();

    public List<Movie> selectAll();

    public Movie selectById(Long id);

    public Movie save(Movie movie);

    public Avis save(Avis avis);
}

