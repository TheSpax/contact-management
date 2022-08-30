package com.example.contactsproject;

import com.example.contactsproject.controller.exceptions.FileEmptyException;
import com.example.contactsproject.service.services.ContactService;
import com.example.contactsproject.service.services.ContactsImportService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ContactsProjectApplicationTests {

    @Mock
    ContactService contactService;

    @InjectMocks
    ContactsImportService contactsImportService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testContactsImportSuccess() throws FileEmptyException {

        MockMultipartFile sampleFile = new MockMultipartFile(
                "file",
                ("firstName,lastName,email,phoneNumber,contactTypeUid\n" +
                        "Andrija,Stojanovic,astojanovic321@gmail.com,691134201,b75c1ea8-3753-4c07-a969-a0c04d50e3c9\n" +
                        "Jovan,Spasic,jovan@gmail.com,06646446,b75c1ea8-3753-4c07-a969-a0c04d50e3c9\n" +
                        "Stefan,Gogic,gogic@gmail.com,67687786,b75c1ea8-3753-4c07-a969-a0c04d50e3c9\n").getBytes()
        );

        Mockito.doNothing().when(contactService).saveByUser(Mockito.any());
        Map<String, String> message = new HashMap<>();
        message.put("message", "All contacts were imported.");
        Assertions.assertEquals(ResponseEntity.ok().body(message), contactsImportService.importContactsFromFile(sampleFile));

    }

    @Test
    void testContactsImportEmptyFile() {
        MockMultipartFile sampleFile = new MockMultipartFile(
                "file",
                ("").getBytes()
        );

        Mockito.doNothing().when(contactService).saveByUser(Mockito.any());
        Assertions.assertThrows(FileEmptyException.class, () -> {
            contactsImportService.importContactsFromFile(sampleFile);
        });

    }

    @Test
    void testContactsImportFileWithNoContacts() throws FileEmptyException {
        MockMultipartFile sampleFile = new MockMultipartFile(
                "file",
                ("asd").getBytes()
        );

        Mockito.doNothing().when(contactService).saveByUser(Mockito.any());
        Map<String, String> message = new HashMap<>();
        message.put("message", "No contacts provided in the file.");
        Assertions.assertEquals(ResponseEntity.badRequest().body(message), contactsImportService.importContactsFromFile(sampleFile));
    }

    @Test
    void testContactsImportPartialImport() throws FileEmptyException {
        MockMultipartFile sampleFile = new MockMultipartFile(
                "file",
                ("firstName,lastName,email,phoneNumber,contactTypeUid\n" +
                        "Andrija,Stojanovic,astojanovic321.com,691134201,b75c1ea8-3753-4c07-a969-a0c04d50e3c9\n" +
                        "Jovan,Spasic,jovan@gmail.com,06646446,b75c1ea8-3753-4c07-a969-a0c04d50e3c9\n" +
                        "Stefan,Gogic,gogic@gmail.com,67687786,b75c1ea8-3753-4c07-a969-a0c04d50e3c9\n").getBytes()
        );

        Mockito.doNothing().when(contactService).saveByUser(Mockito.any());
        Assertions.assertEquals(HttpStatus.PARTIAL_CONTENT, contactsImportService.importContactsFromFile(sampleFile).getStatusCode());
    }

}
