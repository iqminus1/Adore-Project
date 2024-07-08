package uz.pdp.adoreproject.init;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.adoreproject.entity.Role;
import uz.pdp.adoreproject.entity.User;
import uz.pdp.adoreproject.enums.PermissionEnum;
import uz.pdp.adoreproject.enums.RoleTypeEnum;
import uz.pdp.adoreproject.repository.RoleRepository;
import uz.pdp.adoreproject.repository.UserRepository;

import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${app.admin.password}")
    private String adminPassword;
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        Role role = getRole();

        checkAdmin(role);

        checkUserRole();

        checkUser();
    }

    private void checkUser() {
        if (userRepository.findByUsername("User").isPresent()) {
            return;
        }
        Optional<Role> role = roleRepository.findByRoleType(RoleTypeEnum.USER);
        if (role.isEmpty()) {
            return;
        }
        User user = new User("User",
                "user@gmail.com",
                "+000000000001",
                "User",
                passwordEncoder.encode("123"),
                true,
                role.get());
        userRepository.save(user);
    }

    private void checkUserRole() {
        if (roleRepository.findByRoleType(RoleTypeEnum.USER).isPresent()) {
            return;
        }
        Role role = new Role(
                "User",
                "Created on run",
                RoleTypeEnum.USER,
                null
        );
        roleRepository.save(role);
    }

    private void checkAdmin(Role role) {
        Optional<User> userOptional = userRepository.findByUsername("Admin");

        if (userOptional.isPresent()) {
            return;
        }
        User user = new User();
        String password = passwordEncoder.encode(adminPassword);
        user.setPassword(password);
        user.setEnabled(true);
        user.setFullName("admin admin");
        user.setPhoneNumber("+000000000000");
        user.setUsername("Admin");
        user.setEmail("admin@gmail.com");
        user.setRole(role);
        userRepository.save(user);
    }

    private Role getRole() {
        return roleRepository.findByRoleType(RoleTypeEnum.ADMIN).orElseGet(() -> {
            Role r = new Role(
                    "admin role",
                    "Created on run",
                    RoleTypeEnum.ADMIN,
                    Arrays.stream(PermissionEnum.values()).toList());
            roleRepository.save(r);
            return r;
        });
    }
}
