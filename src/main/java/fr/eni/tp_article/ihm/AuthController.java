package fr.eni.tp_article.ihm;

import fr.eni.tp_article.bo.AppUser;
import fr.eni.tp_article.locale.LocaleHelper;
import fr.eni.tp_article.service.AuthService;
import fr.eni.tp_article.service.ServiceResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@SessionAttributes({"loggedUser"})
@Controller
public class AuthController {

    private final AuthService authService;
    private final LocaleHelper lh;

    public AuthController(AuthService authService, LocaleHelper lh) {
        this.authService = authService;
        this.lh = lh;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        // Instancier un user par defaut dans le formulaire
        AppUser appUser = new AppUser();

        model.addAttribute("user", appUser);

        return "auth/login-form-page";
    }

    @PostMapping("/login-process")
    public String loginProcess(@ModelAttribute("user") AppUser user, Model model, RedirectAttributes redirectAttributes) {
        // Appel le service
        ServiceResponse<AppUser> serviceResponse = authService.login(user.email, user.password);

        // Si tentative erreur
        if (!serviceResponse.code.equals("2002")) {
            // Envoyer un message d'erreur temporaire
            EniFlashMessage.sendError(model, serviceResponse.message);

            // reafficher le  formulaire
            return "auth/login-form-page";
        }

        // récupérer le user connecté(e)
        AppUser loggedUser = serviceResponse.data;

        // Ajouter dans une session un user
        model.addAttribute("loggedUser", loggedUser);

        EniFlashMessage.sendSuccessFlash(redirectAttributes, serviceResponse.message);

        // Rediriger sur la page d'accueil
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String loginProcess(SessionStatus sessionStatus, RedirectAttributes redirectAttributes) {

        sessionStatus.setComplete();

        EniFlashMessage.sendSuccessFlash(redirectAttributes, lh.i18n("Msg_Logout_Success"));

        // Rediriger sur la page d'accueil
        return "redirect:/";
    }
}
