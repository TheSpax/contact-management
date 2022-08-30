package com.example.contactsproject.service.services;

import com.example.contactsproject.controller.dto.contact.ContactRequestDTO;
import com.example.contactsproject.controller.exceptions.FileEmptyException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ContactsImportService {

    private final ContactService contactService;

    private ResponseEntity getResponseBasedOnErrors(List<String> errors, List<ContactRequestDTO> contacts, int contactsImported) {
        Map<String, String> message = new HashMap<>();
        if (!errors.isEmpty()) {
            message.put("message", "Some records were incomplete. Contacts imported: " + contactsImported);
            message.put("errors", errors.toString());
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(message);
        } else if (contacts.isEmpty()) {
            message.put("message", "No contacts provided in the file.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        message.put("message", "All contacts were imported.");
        return ResponseEntity.ok().body(message);
    }

    private int getNumberOfContactsImported(int contactsImported, List<ContactRequestDTO> contacts, List<String> errors, int i) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        for (ContactRequestDTO c : contacts) {
            if (validator.validate(c).size() != 0) {
                String s = "Contact " + i + ": ";
                validator.validate(c).forEach(cv -> {
                    errors.add(s + cv.getMessage());
                });
            } else {
                contactService.saveByUser(c);
                contactsImported++;
            }
            i++;
        }
        return contactsImported;
    }

    public ResponseEntity importContactsFromFile(MultipartFile file) throws FileEmptyException {
        int contactsImported = 0;
        if (file.isEmpty())
            throw new FileEmptyException("Empty file provided.");
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<ContactRequestDTO> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(ContactRequestDTO.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .build();
            List<ContactRequestDTO> contacts = csvToBean.parse();
            List<String> errors = new ArrayList<>();
            int i = 1;
            contactsImported = getNumberOfContactsImported(contactsImported, contacts, errors, i);
            return this.getResponseBasedOnErrors(errors, contacts, contactsImported);

        } catch (Exception ex) {
            Map<String, String> message = new HashMap<>();
            message.put("message", "File wasn't read. Either the format was incorrect or the file was corrupt.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

}
