package org.switch2022.project.ddd.datamodel_jpa.assemblers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.datamodel_jpa.AccountJpa;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.model.account.IAccountFactory;
import org.switch2022.project.ddd.utils.Utils;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = AccountDomainDataAssembler.class)
class AccountDomainDataAssemblerTest {

    @InjectMocks
    AccountDomainDataAssembler accountDomainDataAssembler;
    @MockBean
    IAccountFactory accountFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("From Domain Model to Data Model/Schema")
    @Test
    public void ensureConversionToDataModelIsSuccessful() {
        //ARRANGE
        BufferedImage defaultImage = new BufferedImage(5, 5, BufferedImage.TYPE_3BYTE_BGR);

        Account account = mock(Account.class);
        when(account.getAccountName()).thenReturn("josh");
        when(account.getAccountEmail()).thenReturn("josh@email.pt");
        when(account.getPhoneNumber()).thenReturn(921036438);
        when(account.getAccountStatus()).thenReturn("active");
        when(account.getProfileId()).thenReturn("prf001");
        when(account.getPhoto()).thenReturn(defaultImage);

        //ACT
        AccountJpa accountJpa = accountDomainDataAssembler.toData(account);

        //ASSERT
        int phoneNumberInt = Integer.parseInt(accountJpa.getPhoneNumber());
        BufferedImage bufferedImage = Utils.convertByteArrayToBufferedImage(accountJpa.getPhoto());

        assertEquals(accountJpa.getName(), account.getAccountName());
        assertEquals(accountJpa.getEmail(), account.getAccountEmail());
        assertEquals(phoneNumberInt, account.getPhoneNumber());
        assertEquals(accountJpa.getAccountStatus(), account.getAccountStatus());
        assertImagesAreEqual(bufferedImage, account.getPhoto());
        assertEquals(accountJpa.getProfileId(), account.getProfileId());
    }

    @DisplayName("From Data Model to Domain Model/Schema")
    @Test
    public void ensureConversionToDomainModelIsSuccessful() {
        //ARRANGE
        BufferedImage defaultImage = new BufferedImage(5, 5, BufferedImage.TYPE_3BYTE_BGR);
        byte[] bytes = Utils.convertBufferedImageToByteArray(defaultImage);

        AccountJpa accountJpa = mock(AccountJpa.class);
        when(accountJpa.getName()).thenReturn("josh");
        when(accountJpa.getEmail()).thenReturn("josh@email.pt");
        when(accountJpa.getPhoneNumber()).thenReturn("921036438");
        when(accountJpa.getAccountStatus()).thenReturn("active");
        when(accountJpa.getProfileId()).thenReturn("prf001");
        when(accountJpa.getPhoto()).thenReturn(bytes);

        Account accountToBeCreated = mock(Account.class);
        when(accountToBeCreated.getAccountName()).thenReturn("josh");
        when(accountToBeCreated.getAccountEmail()).thenReturn("josh@email.pt");
        when(accountToBeCreated.getPhoneNumber()).thenReturn(921036438);
        when(accountToBeCreated.getAccountStatus()).thenReturn("active");
        when(accountToBeCreated.getProfileId()).thenReturn("prf001");
        when(accountToBeCreated.getPhoto()).thenReturn(defaultImage);

        when(accountFactory.create(any(), any(), any(), any())).thenReturn(accountToBeCreated);

        //ACT
        Account account = accountDomainDataAssembler.toDomain(accountJpa);

        //ASSERT
        int phoneNumberInt = Integer.parseInt(accountJpa.getPhoneNumber());
        BufferedImage bufferedImage = Utils.convertByteArrayToBufferedImage(accountJpa.getPhoto());

        assertEquals(account.getAccountName(), accountJpa.getName());
        assertEquals(account.getAccountEmail(), accountJpa.getEmail());
        assertEquals(account.getPhoneNumber(), phoneNumberInt);
        assertEquals(account.getAccountStatus(), accountJpa.getAccountStatus());
        assertImagesAreEqual(account.getPhoto(), bufferedImage);
        assertEquals(account.getProfileId(), accountJpa.getProfileId());
    }

    /**
     * Asserts that two BufferedImages are equal, pixel by pixel.
     *
     * @param expected the expected BufferedImage
     * @param actual   the actual BufferedImage
     * @throws AssertionError if the BufferedImages are not equal
     */
    private void assertImagesAreEqual(BufferedImage expected, BufferedImage actual) {
        // Compares the dimensions of the BufferedImages
        assertEquals(expected.getWidth(), actual.getWidth());
        assertEquals(expected.getHeight(), actual.getHeight());

        // Compares the RGB values of each pixel in the BufferedImages
        for (int x = 0; x < actual.getWidth(); x++) {
            for (int y = 0; y < actual.getHeight(); y++) {
                assertEquals(expected.getRGB(x, y), actual.getRGB(x, y));
            }
        }
    }
}