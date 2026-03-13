package fr.eni.tp_article.ihm.converter;

import fr.eni.tp_article.bo.Genre;
import fr.eni.tp_article.bo.Participant;
import fr.eni.tp_article.service.MovieService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IdToParticipantConverter implements Converter<String, Participant> {

    private final MovieService movieService;

    public IdToParticipantConverter(MovieService movieService) {
        this.movieService = movieService;
    }

    @Nullable
    @Override
    public Participant convert(String source) {
        // Récupérer les participants via le services
        List<Participant> participants = movieService.getParticipantCatalog().data;

        // Retrouver le genret qui a le meme id
        Participant participant = participants.stream().filter((val -> val.id == Long.parseLong(source))).findFirst().orElse(null);

        return participant;
    }
}
