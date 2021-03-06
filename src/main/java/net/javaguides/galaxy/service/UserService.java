package net.javaguides.galaxy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.javaguides.galaxy.dto.UserRegistrationDto;
import net.javaguides.galaxy.entities.User;
import net.javaguides.galaxy.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void saveUser(UserRegistrationDto userRegistrationDto){
        User user = new User();
        
        StringBuilder name = new StringBuilder(
                            userRegistrationDto.getFirstName());
        name.append(" "+userRegistrationDto.getLastName());
        user.setName(name.toString());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(
                            userRegistrationDto
                            .getPassword()));
        user.setPhoneNumber(userRegistrationDto.getPhoneNumber());
        user.setGender(userRegistrationDto.getGender());
        user.setRoles(userRegistrationDto.getRoles());
        user.setGroups(userRegistrationDto.getGroups());

        userRepository.save(user);
    }

    public User findByEmail(String email){
        User user=null;
        try {
            user =userRepository.
                    findByEmail(email).get();
    
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
                            
    }


                         
}