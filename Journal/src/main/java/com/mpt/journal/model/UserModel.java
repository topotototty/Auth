package com.mpt.journal.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "user_model", uniqueConstraints = {
        @UniqueConstraint(columnNames = "login"),
        @UniqueConstraint(columnNames = "email")
})

@NoArgsConstructor
@Data
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Логин не может быть пустым")
    @Valid
    private String login;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 6, message = "Пароль должен быть не менее 6 символов")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*]).+$", message = "Пароль должен содержать хотя бы одну цифру или специальный символ")
    @Valid
    private String password;
    @Email(message = "Неправильный формат email")
    @NotBlank(message = "Email не может быть пустым")
    @Valid
    private String email;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_model_id")
    private RoleModel roleModel;

    public UserModel(String login, String password, String email, RoleModel roleModel) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.roleModel = roleModel;
    }
}
