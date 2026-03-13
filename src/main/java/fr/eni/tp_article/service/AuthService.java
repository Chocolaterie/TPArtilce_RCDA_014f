package fr.eni.tp_article.service;

import fr.eni.tp_article.bo.AppUser;
import fr.eni.tp_article.dao.IDAOAuth;
import fr.eni.tp_article.locale.LocaleHelper;
import org.springframework.stereotype.Component;

@Component
public class AuthService {

    private final IDAOAuth daoAuth;
    private final LocaleHelper lh;

    public AuthService(IDAOAuth daoAuth, LocaleHelper lh) {
        this.daoAuth = daoAuth;
        this.lh = lh;
    }

    public ServiceResponse<AppUser> login(String email, String password){
        // essayer de retrouver user
        AppUser loggedUser = daoAuth.selectByEmailAndPassword(email, password);

        // Si je trouve pas => erreur
        if (loggedUser == null)
        {
            return new ServiceResponse<AppUser>("7025", lh.i18n("Msg_Auth_Error"));
        }

        // Success
        return new ServiceResponse<AppUser>("2002", lh.i18n("Msg_Auth_Success"), loggedUser);
    }
}
