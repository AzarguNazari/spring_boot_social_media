package com.facebook.example.interfaces;

import com.facebook.example.models.Role;

import java.util.List;
import java.util.Optional;

public interface RoleServiceInterface {
    void saveRole(Role role);

    void deleteRole(Long roleID);

    Optional<Role> getRole(Long roleID);

    List<Role> getRoles();
}
