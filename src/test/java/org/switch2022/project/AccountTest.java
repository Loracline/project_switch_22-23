package org.switch2022.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    @Test
    void testGetName() {

        Account one = new Account("Juca", "12121212@isep.ipp.pt", "******", true, 912345678,null,new Profile() );

        assertEquals("Juca", one.getName());
    }
    @Test
    void testGetEmail() {

        Account one = new Account("Juca", "12121212@isep.ipp.pt", "******", true, 912345678,null,new Profile() );

        assertEquals("12121212@isep.ipp.pt", one.getEmail());
    }
    @Test
    void testGetPassword() {

        Account one = new Account("Juca", "12121212@isep.ipp.pt", "******", true, 912345678,null,new Profile() );

        assertEquals("******", one.getPassword());
    }
    @Test
    void testGetStatusAccount() {

        Account one = new Account("Juca", "12121212@isep.ipp.pt", "******", true,912345678, null,new Profile() );

        assertEquals(true, one.isStatusAccount());
    }
    @Test
    void testGetPhoneNumber() {

        Account one = new Account("Juca", "12121212@isep.ipp.pt", "******", true,912345678, null,new Profile() );

        assertEquals(912345678, one.getPhoneNumber());
    }
    @Test
    void testGetPhoto() {

        Account one = new Account("Juca", "12121212@isep.ipp.pt", "******", true,912345678, null,new Profile() );

        assertEquals(null, one.getPhoto());
    }

}