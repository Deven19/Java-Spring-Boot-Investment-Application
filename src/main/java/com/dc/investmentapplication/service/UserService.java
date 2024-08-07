package com.dc.investmentapplication.service;

import com.dc.investmentapplication.InvestmentApplication;
import com.dc.investmentapplication.entity.User;
import com.dc.investmentapplication.helper.GlobalHelper;
import com.dc.investmentapplication.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLDataException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InvestmentApplication investmentApplication;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public String createUser(User user) throws Exception {

        try {
            String dateTime = GlobalHelper.threadSafeDatetimeFormat.get().format(new Date());
            user.setCreatedAt(dateTime);
            userRepository.save(user);
            return "User created!";
        }catch (Exception e) {
            GlobalHelper.logger.error("Unexpected error occurred", e);
            throw new Exception("Caught some error - Could not create user");
        }

    }

    public User updateUser(Long id, User userDetails) {


        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
