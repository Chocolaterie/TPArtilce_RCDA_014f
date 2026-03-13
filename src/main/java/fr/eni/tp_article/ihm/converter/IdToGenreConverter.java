package fr.eni.tp_article.ihm.converter;

import fr.eni.tp_article.bo.Genre;
import fr.eni.tp_article.service.MovieService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IdToGenreConverter implements Converter<String, Genre> {

    private final MovieService movieService;

    public IdToGenreConverter(MovieService movieService) {
        this.movieService = movieService;
    }

    @Nullable
    @Override
    public Genre convert(String source) {
        // Récupérer les genres via le services
        List<Genre> genres = movieService.getGenreCatalog().data;

        // Retrouver le genret qui a le meme id
        Genre genre = genres.stream().filter((val -> val.id == Long.parseLong(source))).findFirst().orElse(null);

        return genre;
    }
}
