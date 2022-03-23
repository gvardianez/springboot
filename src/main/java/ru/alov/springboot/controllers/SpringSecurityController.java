package ru.alov.springboot.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alov.springboot.converters.ProductConverter;
import ru.alov.springboot.dto.ProductDto;
import ru.alov.springboot.entities.Role;
import ru.alov.springboot.entities.User;
import ru.alov.springboot.services.IProductService;
import ru.alov.springboot.services.impl.RoleService;
import ru.alov.springboot.services.impl.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class SpringSecurityController {

    private final IProductService productService;
    private final ProductConverter productConverter;
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping()
    public String homePage() {
        return "home";
    }

    @GetMapping("/all_products")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts().stream().map(productConverter::entityToDto).collect(Collectors.toList());
    }

    @GetMapping("/auth_page")
    public String authenticatedPage() {
        return "authenticated";
    }

    @GetMapping("/admin_panel")
    public List<Role> adminPage() {
        return roleService.getAllRoles();
    }

    @GetMapping("/user_info")
    public String daoTestPage(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Unable to find user by username: " + principal.getName()));
        return "Authenticated user info: " + user.getUsername();
    }

}
