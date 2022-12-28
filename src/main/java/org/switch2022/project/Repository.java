package org.switch2022.project;

import java.util.List;

/**
 * Class Repository is built to create and manipulate lists of Accounts and
 * Profiles (to be updated...).
 */
public class Repository {
    /**
     * Attributes of the class Repository, according to the Class Diagram.
     */
    private List<Account> accountsList;
    private List<Profile> profilesList;

    /**
     * Repository constructor
     */
    public Repository(List<Account> accountList, List<Profile> profilesList) {
        this.accountsList = accountList;
        this.profilesList = profilesList;
    }
    /**
     * Getter method for the attribute ACCOUNTS LIST.
     *
     * @return the list of Accounts in the Repository
     */
    public List<Account> getAccountsList() {
        return accountsList;
    }


    /**
     * This method checks if there is any account in the list that has the
     * e-mail passed by parameter.
     *
     * @param email that one must find out if exists in the Repository
     * @return TRUE if there is an account with given e-mail and FALSE otherwise
     */
    public boolean doesEmailAccountExist(String email) {
        boolean accountExistence = false;
        int index = 0;
        while (index < this.accountsList.size()) {
            if (accountsList.get(index).getEmail().contains(email)) {
                accountExistence = true;
                break;
            }
            index++;
        }
        return accountExistence;
    }
}
