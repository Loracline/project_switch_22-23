package org.switch2022.project;

public class Profile {
    private String profile;/* administrator, manager, user */
    public static final String Adm= "Administrator"; /*é uma variável constante, por isso final, ou será que não?*/
    public static final String Man= "Manager";
    public static final String Us= "User";

    public Profile() {

        this.profile = Us;
    }
}
