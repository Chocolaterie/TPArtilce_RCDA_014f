package fr.eni.tp_article.service;

import fr.eni.tp_article.bo.Avis;
import fr.eni.tp_article.bo.Genre;
import fr.eni.tp_article.bo.Movie;
import fr.eni.tp_article.bo.Participant;
import fr.eni.tp_article.dao.IDAOMovie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieService {

    private final IDAOMovie daoMovie;

    public MovieService(IDAOMovie daoMovie) {
        this.daoMovie = daoMovie;
    }

    public ServiceResponse<List<Participant>> getParticipantCatalog() {

        List<Participant> participants = daoMovie.selectAllParticipants();

        return ServiceResponse.buildResponse("202","Participants récupérés avec succès", participants);
    }

    public ServiceResponse<List<Genre>> getGenreCatalog(){

        List<Genre> genres = daoMovie.selectAllGenres();

        return ServiceResponse.buildResponse("202","Genres récupérés avec succès", genres);
    }

    public ServiceResponse<List<Movie>> getMovieCatalog(){

        List<Movie> movies = daoMovie.selectAll();

        return ServiceResponse.buildResponse("202","Movies récupérés avec succès", movies);
    }

    public ServiceResponse<Movie> getMovieDetail(Long id){
        Movie movie = daoMovie.selectById(id);

        return ServiceResponse.buildResponse("202","Movie récupéré avec succès", movie);
    }

    public ServiceResponse<Movie> saveMovie(Movie movie){
        // Est-ce il faut le modifier ?
        // Trouver un film qui existe déjà (avec le même id)
        Movie foundMovie = daoMovie.selectById(movie.id);

        // Appel DAO qui va persister dans les données
        daoMovie.save(movie);

        // Si Modification
        if (foundMovie != null){
            return new ServiceResponse<Movie>("2003", "Film modifié avec succès", movie);
        }

        // Le creer ?
        return new ServiceResponse<Movie>("2002", "Film créer avec succès", movie);
    }

    public ServiceResponse<Avis> saveAvis(Avis avis){

        // Appel DAO qui va persister dans les données
        daoMovie.save(avis);

        // Le creer ?
        return new ServiceResponse<Avis>("2002", "Avis envoyé avec succès", avis);
    }
}
