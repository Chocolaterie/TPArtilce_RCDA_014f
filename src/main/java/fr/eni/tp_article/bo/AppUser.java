package fr.eni.tp_article.bo;

public class AppUser extends Person {

    public String email;
    public String pseudo;
    public String password;
    public boolean isAdmin;

    public AppUser() {
    }

    public AppUser(String email, String pseudo) {
        this.email = email;
        this.pseudo = pseudo;
    }

    public AppUser(String email, String pseudo, String password) {
        this.email = email;
        this.pseudo = pseudo;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
