package com.zzheads.volgofit.web.api;//

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zzheads.volgofit.model.User.Role;
import com.zzheads.volgofit.model.User.User;
import com.zzheads.volgofit.service.RoleService;
import com.zzheads.volgofit.service.UserService;
import com.zzheads.volgofit.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

//  created by zzheads on 18.02.17
//

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final Gson gson = new GsonBuilder().serializeNulls().create();

    @Autowired
    public UserController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(method = GET, produces = {APPLICATION_JSON_UTF8_VALUE}, consumes = {APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(OK)
    public String getAllUsers() {
        Collection<User> users = userService.findAll();
        return User.toJson(users);
    }

    @RequestMapping(value = "/{id}", method = GET, produces = {APPLICATION_JSON_UTF8_VALUE}, consumes = {APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(OK)
    public String getUser() {
        Collection<User> users = userService.findAll();
        return User.toJson(users);
    }

    @RequestMapping(method = POST, produces = {APPLICATION_JSON_UTF8_VALUE}, consumes = {APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(OK)
    public String addUser(@RequestBody String json) {
        User user = new User(json);
        if (user.getRole() == null) {
            user.setRole(new Role(null, Role.USER_ROLE));
        }
        return userService.save(user).toJson();
    }

    @RequestMapping(value = "/{id}", method = PUT, produces = {APPLICATION_JSON_UTF8_VALUE}, consumes = {APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(OK)
    public String updateUser(@RequestBody String json, @PathVariable Long id) {
        User user = new User(json);
        user.setId(id);
        return userService.save(user).toJson();
    }

    @RequestMapping(value = "/{id}", method = POST, produces = {APPLICATION_JSON_UTF8_VALUE}, consumes = {APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        User user = userService.findById(id);
        userService.delete(user);
    }

    @RequestMapping(value = "/current", method = GET, produces = {APPLICATION_JSON_UTF8_VALUE}, consumes = {APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(OK)
    public String loggedUser() {
        Authentication authentication = LoggedUser.getAuthentication();
        return gson.toJson(authentication);
    }

    @RequestMapping(value = "/login", method = POST, produces = {APPLICATION_JSON_UTF8_VALUE}, consumes = {APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(OK)
    public boolean login(@RequestBody String json) {
        User user = new User(json);
        UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
        return passwordEncoder.matches(user.getPassword(), userDetails.getPassword());
    }
}
