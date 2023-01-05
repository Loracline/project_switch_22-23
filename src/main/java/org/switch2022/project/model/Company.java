package org.switch2022.project.model;

/**
 * Class Repository is built to create and manipulate lists of Accounts and
 * Profiles (to be updated...).
 */
public class  Company {
  /**
   * Attributes of the class Repository, according to the Class Diagram.
   */
  private AccountContainer accountContainer;
  private ProfileContainer profileContainer;

  /**
   * Repository constructor
   */
  public Company(AccountContainer accountList, ProfileContainer profileContainer) {
    this.accountContainer = accountList;
    this.profileContainer = profileContainer;
  }

    public Company() {
        this.accountContainer = new AccountContainer();
        this.profileContainer = new ProfileContainer();
    }


    /**
     * Getter method for the attribute ACCOUNTS LIST.
     *
     * @return the list of Accounts in the Repository
     */
    public AccountContainer getAccountsList() {
        return accountContainer;
    }
    /**
     * Getter method for the attribute Profile Container.
     *
     * @return the container of profiles
     */
    public ProfileContainer getProfileContainer() {
        return profileContainer;
    }
    /**
     * This method checks if there is any account in the list that has the
     * e-mail passed by parameter.
     *
     * @param email that one must find out if exists in the Repository
     * @return TRUE if there is an account with given e-mail and FALSE otherwise
     */


    /**
     * Register new account method. After validating email is unique, creates new
     * account with given parameters and adds it to accountList
     *
     * @param name        of the new account
     * @param email       of the new account
     * @param phoneNumber of the new account
     * @param photo       of the new account
     * @param status      of the new account
     * @return instance of Account with given parameters if email is unique;
     * @return null if email already exists
     */
    public boolean registerAccount(String name, String email, long phoneNumber,
                             BufferedImage photo, boolean status) {
        boolean result = false;

        if (!doesEmailExist(email)) {
            Account account = new Account(name, email, phoneNumber, photo, status);
            this.addAccount(account);
            result = true;
        }
        return result;
    }

    /**
     * Add account method to save registered accounts in the attribute ACCOUNTS LIST
     *
     * @param acc instance of Account to be added to accountList
     */
    private void addAccount(Account acc) {
        this.accountContainer.add(acc);
    }

    /**
     * This method verify the existence of an account by email confirmation
     *
     * @return an object Account
     */
    public Account getAccount(String email) {
        Account requestedAccount = null;
        for (Account account : this.accountContainer) {
            if (account.getEmail().equals(email)) {
                requestedAccount = account;
                break;
            }
        }
        return requestedAccount;
    }
    //Possivel substituicao do teste Account getAccount (A aguardar validaçao do resto dos elementos)
      /*  Account requestedAccount = null;
        for (int i = 0; i < this.accountList.size() ; i++) {
            if (accountList.get(i).getEmail().equals(email)) {
                requestedAccount = accountList.get(i);
                break;
            }
        }
        return requestedAccount;
    */

    public void registerProfile(String profileName) {
        String profileNameLowerCase = profileName.toLowerCase();
        if (validateProfile(profileNameLowerCase)) {
            Profile profile = new Profile(profileName);
            addProfileToProfilesList(profile); // FUNDIR METODO addProfileToProfilesList AQUI.
        }
    }

    /**
     * This method identifies the requested profile by indication of profileName
     *
     * @return an object Profile
     */
    public Profile getProfile(String profileName) {
        String profileNameLowerCase = profileName.toLowerCase();
        Profile requestedProfile = null;
        for (int i = 0; i < this.profileList.size(); i++) {
            if (profileList.get(i).getProfileName().equals(profileNameLowerCase)) {
                requestedProfile = profileList.get(i);
                break;
            }
        }
        return requestedProfile;
    }

    /**
     * This method changes/updates the accountProfile
     *
     * @return an object Account
     */
    public void changeAccountProfile(String email, String profileName) {
        Profile profile = getProfile(profileName);
        Account account = getAccount(email);
        account.updateAccountProfile(profile);
    }
}
