package com.mpt.journal.service.userService;

import com.mpt.journal.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserModel createUser(UserModel userModel);
    UserModel findByLogin(String login);
    UserModel findByEmail(String email);
}