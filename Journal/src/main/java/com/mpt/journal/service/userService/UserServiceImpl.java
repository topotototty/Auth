package com.mpt.journal.service.userService;

import com.mpt.journal.model.RoleModel;
import com.mpt.journal.model.UserModel;
import com.mpt.journal.repository.RoleRepository;
import com.mpt.journal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserModel createUser(UserModel user) {
        Optional<RoleModel> roleOptional = roleRepository.findByNameRole(user.getRoleModel().getNameRole());

        if (roleOptional.isPresent()) {
            user.setRoleModel(roleOptional.get());
        } else {
            roleRepository.save(user.getRoleModel());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserModel findByLogin(String login) {
        return userRepository.findByLogin(login).orElse(null);
    }

    @Override
    public UserModel findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
