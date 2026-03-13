package fr.eni.tp_article.dao.mock;

import fr.eni.tp_article.bo.AppUser;
import fr.eni.tp_article.dao.IDAOAuth;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Profile("mock")
@Component
public class DAOAuthMock implements IDAOAuth {

    public List<AppUser> DBUsers;

    public DAOAuthMock() {

        DBUsers = new ArrayList<>();

        DBUsers.add(new AppUser("chocolatine@gmail.com", "Hérésie", "123"));
        DBUsers.add(new AppUser("crevette-nutella@gmail.com", "Best Repas", "1234"));
    }

    @Override
    public AppUser selectByEmailAndPassword(String email, String password){
        AppUser foundUser = DBUsers.stream()
                .filter((user) ->  user.email.equals(email) && user.password.equals(password))
                .findFirst()
                .orElse(null);

        return foundUser;
    }
}
