package dtos;

import entities.User;

import java.util.UUID;

public class UserDTO {
    private UUID id;
    private String name;
    private String email;
    private User.Role role;
}
