package fr.eni.tp_article.dao.mock;

import fr.eni.tp_article.bo.*;
import fr.eni.tp_article.dao.IDAOMovie;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Profile("mock")
@Component
public class DAOMovieMock implements IDAOMovie {

    List<Movie> movies = new ArrayList<>();
    List<Genre> genres = new ArrayList<>();
    List<Participant> participants = new ArrayList<>();

    public DAOMovieMock(){
        participants.add(new Participant (1L, "Toto", "JeSaispas"));
        participants.add(new Participant (2L, "Tata", "jeSais"));
        participants.add(new Participant (3L, "Titi", "CpasBien"));
        participants.add(new Participant (4L, "Tutu", "UTorrent"));

        genres.add(new Genre(1L, "Sci-Fi"));
        genres.add(new Genre(2L, "Fantasy"));

        // Avis
        AppUser appUser1 = new AppUser("sdqds", "qsdqsdq", "sdqsdqsds");
        AppUser appUser2 = new AppUser("qdqsdqsdqsd", "jyuiyuiyuiuy", "yuiyuiyuiyui");

        List<Avis> movieAvis1 = new ArrayList<>();
        movieAvis1.add(new Avis(1L, 3, "C'est nul", appUser1));
        movieAvis1.add(new Avis(2L, 5, "C'est cool", appUser2));

        // Movie 1
        Movie movie1 = new Movie(1L, "Velocipastor", 2002, 120, "Un film très bien fait", genres.get(0), participants.get(0));
        movie1.setAvis(movieAvis1);

        movies.add(movie1);

        // Movie 2
        movies.add(new Movie(2L, "Robocop", 1998, 116, "Un vieux film", genres.get(1), participants.get(3)));
    }

    @Override
    public List<Participant> selectAllParticipants() {
        return participants;
    }

    @Override
    public List<Genre> selectAllGenres(){
        return genres;
    }

    @Override
    public List<Movie> selectAll(){
        return movies;
    }

    @Override
    public Movie selectById(Long id){

        Movie findMovie = movies.stream().filter((movie -> movie.id == id)).findFirst().orElse(null);

        return findMovie;
    }

    @Override
    public Movie save(Movie movie){

        // si movie existe alors ecrasé
        Movie findMovie = movies.stream().filter((value -> value.id == movie.id)).findFirst().orElse(null);
        if (findMovie != null){
            findMovie.title = movie.title;
            findMovie.year = movie.year;
            findMovie.duration = movie.duration;
            findMovie.synopsis = movie.synopsis;
            findMovie.genre = movie.genre;
            findMovie.director = movie.director;
            findMovie.actors = movie.actors;

            return findMovie;
        }

        // sinon ajout dans la liste
        // PS: Pour le moment je genere un FAUX ID
        movie.id = (movies.size() + 1L);

        movies.add(movie);

        return movie;
    }

    @Override
    public Avis save(Avis avis) {
        return null;
    }

}
