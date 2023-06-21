package org.switch2022.project.ddd.domain.value_object;


import org.switch2022.project.ddd.domain.shared.ValueObject;

public enum AccountStatus implements ValueObject<AccountStatus> {
    ACTIVE("active"), INACTIVE("inactive");

    private final String value;

    AccountStatus(String value) {
        this.value = value.toLowerCase().trim();
    }

    /**
     * This getter method returns a String with the Account status.
     *
     * @return a String with the Account status.
     */
    public String getAccountStatus() {
        return value;
    }

    @Override
    public boolean sameValueAs(AccountStatus other) {
        return this.equals(other);
    }
}
