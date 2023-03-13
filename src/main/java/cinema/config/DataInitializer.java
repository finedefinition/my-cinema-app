package cinema.config;

import cinema.model.Role;
import cinema.model.User;
import cinema.service.RoleService;
import cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private RoleService roleService;

    private UserService userService;

    private PasswordEncoder encoder;

    public DataInitializer(RoleService roleService,
                           UserService userService, PasswordEncoder encoder) {
        this.roleService = roleService;
        this.userService = userService;
        this.encoder = encoder;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setName(Role.RoleName.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setName(Role.RoleName.USER);
        roleService.add(userRole);
        User userAlice = new User();
        userAlice.setEmail("adminalice@gmail.com");
        userAlice.setPassword(encoder.encode("admin123"));
        userAlice.setRoles(Set.of(adminRole));
        userService.add(userAlice);
        User userBob = new User();
        userBob.setEmail("userbob@gmail.com");
        userBob.setPassword(encoder.encode("user123"));
        userBob.setRoles(Set.of(userRole));
        userService.add(userBob);
    }
}
