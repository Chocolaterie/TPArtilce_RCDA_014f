package fr.eni.tp_article.ihm;

import fr.eni.tp_article.bo.*;
import fr.eni.tp_article.service.AuthService;
import fr.eni.tp_article.service.MovieService;
import fr.eni.tp_article.service.ServiceResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@SessionAttributes({"loggedUser"})
@Controller
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * Pour envoyer dans le form les objects selectionnables
     * @param model
     */
    private void populateFormData(Model model, Movie movie, boolean assignDefaultValue){
        // Envoyer les genres selectionnables
        List<Genre> genres = movieService.getGenreCatalog().data;
        model.addAttribute("genres", genres);

        // Envoyer les participants selectionnables
        List<Participant> participants = movieService.getParticipantCatalog().data;
        model.addAttribute("participants", participants);

        // Si film vide (donc creation)
        if (assignDefaultValue){
            if (movie.id == null){
                movie.genre = genres.get(0);
                movie.director = participants.get(0);
            }
        }
    }

    @GetMapping("/")
    public String showMovieCatalog(Model model) {

        // Récupère les films depuis le service
        List<Movie> movies = movieService.getMovieCatalog().data;

        // Envoyer dans le front
        model.addAttribute("movies", movies);

        return "movie/movies-page";
    }

    @GetMapping("/show-movie/{id}")
    public String showMovieCatalog(@PathVariable("id") String id, Model model) {
        // Pass: Je suis connecté(e) ?
        AppUser loggedUser = (AppUser) model.getAttribute("loggedUser");
        // erreur pas connecté(e)
//        if (loggedUser == null) {
//            return "auth/user-not-logged-page";
//        }

        // Par defaut
        Long parsedId = 0L;

        // Parser le id
        try {
            parsedId = Long.parseLong(id);
        } catch (Exception e) {
            return "movie/movie-not-found-page";
        }

        // Récupère les films depuis le service
        Movie movie = movieService.getMovieDetail(parsedId).data;

        // ERREUR : Film existe pas
        if (movie == null) {
            return "movie/movie-not-found-page";
        }

        // Envoyer dans le front
        model.addAttribute("movie", movie);

        return "movie/movie-detail-page";
    }

    @GetMapping({"/movie-form", "/movie-form/{id}"})
    public String showMovieForm(@PathVariable(name="id", required = false) Long id, Model model) {
        // par défaut, fallback => film vide
        Movie movie = new Movie();

        populateFormData(model, movie, true);

        // Si id => récupérer un film existant pour le mettre dans le formulaire
        if (id != null){
            movie = movieService.getMovieDetail(id).data;
        }

        model.addAttribute("movie", movie);

        return "movie/movie-form-page";
    }

    @PostMapping("/movie-form-process")
    public String processMovieForm(@Valid @ModelAttribute("movie") Movie movie, BindingResult bindingResult, Model model) {

        populateFormData(model, movie, false);

        // =========================================================
        // Controle de surface
        // =========================================================
        if (bindingResult.hasErrors()) {
            return "movie/movie-form-page";
        }

        // =========================================================
        // Controle métier
        // =========================================================
        // TODO: Mettre dans le service/dao, etc
        ServiceResponse<Movie> serviceResponse = movieService.saveMovie(movie);

        // Envoyer le message (résultat du service)
        EniFlashMessage.sendSuccess(model, serviceResponse.message);

        return "movie/movie-form-page";
    }

    @PostMapping("/send-movie-avis/{id}")
    public String processAvisForm(@PathVariable("id") Long id, @Valid @ModelAttribute("avis") Avis avis, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        // Pass: Je suis connecté(e) ?
        AppUser loggedUser = (AppUser) model.getAttribute("loggedUser");
        // erreur pas connecté(e)
        if (loggedUser == null) {
            return "auth/user-not-logged-page";
        }
        avis.setUser(loggedUser);

        // relier le film
        avis.movie = movieService.getMovieDetail(id).data;

        // =========================================================
        // Controle de surface
        // =========================================================
        if (bindingResult.hasErrors()) {
            EniFlashMessage.sendErrorFlash(redirectAttributes, "Controle de surface invalide");
            return "movie/movie-form-page";
        }

        // =========================================================
        // Controle métier
        // =========================================================
        ServiceResponse<Avis> serviceResponse = movieService.saveAvis(avis);

        // Envoyer le message (résultat du service)
        EniFlashMessage.sendSuccess(model, serviceResponse.message);

        return "redirect:/show-movie/" + id;
    }

}
