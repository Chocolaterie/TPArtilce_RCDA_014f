package fr.eni.tp_article.dao;

import fr.eni.tp_article.bo.AppUser;

public interface IDAOAuth {

    public AppUser selectByEmailAndPassword(String email, String password);
}
