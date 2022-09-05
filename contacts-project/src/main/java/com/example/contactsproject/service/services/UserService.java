package com.example.contactsproject.service.services;

import com.example.contactsproject.controller.dto.user.UserRequestDTO;
import com.example.contactsproject.controller.dto.user.UserResponseDTO;
import com.example.contactsproject.entity.Role;
import com.example.contactsproject.entity.User;
import com.example.contactsproject.repository.RoleRepository;
import com.example.contactsproject.repository.UserRepository;
import com.example.contactsproject.service.email.EmailCustomService;
import com.example.contactsproject.service.mappers.UserMapper;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final String TWILIO_ACCOUNT_SID = "Insert twilio account sid here";
    private final String TWILIO_AUTH_TOKEN = "inser twilio auth token here";
    private final String TWILIO_SERVICE_SID = "insert twilio service sid here";

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final RoleRepository roleRepository;

    private final EmailCustomService emailCustomService;

    @Transactional(readOnly = true)
    public Page<UserResponseDTO> getAll(Pageable pageable) {
        return userMapper.mapUsersToPageUsersDTO(userRepository.findAll(pageable));
    }

    @Transactional(readOnly = true)
    public UserResponseDTO getByUid(UUID uid) {
        return userMapper.mapUserToUserDTO(this.getUserByUid(uid));
    }

    @Transactional(readOnly = true)
    public User getUserByUid(UUID uid) {
        return userRepository.findByUid(uid).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Transactional
    public void save(UserRequestDTO userRequestDTO) {
        User user = userMapper.mapUserFromUserDTO(userRequestDTO, this.getRole(userRequestDTO));
        userRepository.save(user);
        Map<String, String> model = new HashMap<>();
        model.put("firstName", user.getFirstName());
        model.put("lastName", user.getLastName());
        emailCustomService.sendSimpleMessage(userRequestDTO.getEmail(), model);
    }

    @Transactional
    public void update(UUID userUid, UserRequestDTO userRequestDTO) {
        User user = this.getUserByUid(userUid);
        mapAndUpdateUser(userRequestDTO, user);
    }

    @Transactional
    public void userProfileUpdate(UserRequestDTO userRequestDTO) {
        User user = this.getLoggedUser();
        this.mapAndUpdateUser(userRequestDTO, user);
    }

    @Transactional
    public void mapAndUpdateUser(UserRequestDTO userRequestDTO, User user) {
        user = userMapper.mapUserFromDTOUpdate(user, userRequestDTO, this.getRole(userRequestDTO));
        userRepository.save(user);
    }

    @Transactional
    public void deleteByUid(UUID uid) {
        userRepository.deleteByUid(uid);
    }

    public User getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return user;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @Transactional(readOnly = true)
    public Role getRole(UserRequestDTO userRequestDTO) {
        return roleRepository.findByUid(userRequestDTO.getRoleUid()).orElseThrow(() -> new EntityNotFoundException("Role not found"));
    }

    @Transactional
    public ResponseEntity<String> sendVerificationMessage() {
        User user = this.getLoggedUser();
        Twilio.init(TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN);
        if(user.getStatus().equals("Not verified")){
            Verification.creator(TWILIO_SERVICE_SID, user.getPhoneNumber(),
                    "sms").create();
            return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("Message not sent, Account already verified", HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<String> verify(String code) {
        User user = getLoggedUser();
        Twilio.init(TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN);
        VerificationCheck verificationCheck =
                VerificationCheck.creator(TWILIO_SERVICE_SID)
                        .setTo(user.getPhoneNumber())
                        .setCode(code).create();
        if(verificationCheck.getValid()){
            user.setStatus("Verified");
            userRepository.save(user);
            return new ResponseEntity<String>(verificationCheck.getStatus(), HttpStatus.OK);
        }else{
            return new ResponseEntity<String>(verificationCheck.getStatus(), HttpStatus.BAD_REQUEST);
        }
    }

}