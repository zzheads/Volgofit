package com.zzheads.volgofit.web.api;//

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zzheads.volgofit.config.EmailConfig;
import com.zzheads.volgofit.dto.ApiResult;
import com.zzheads.volgofit.exceptions.ApiError;
import com.zzheads.volgofit.model.User.Role;
import com.zzheads.volgofit.model.User.User;
import com.zzheads.volgofit.service.RoleService;
import com.zzheads.volgofit.service.UserService;
import com.zzheads.volgofit.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static java.lang.Long.parseLong;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

//  created by zzheads on 18.02.17
//

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final Gson gson = new GsonBuilder().serializeNulls().create();

    @Autowired
    public UserController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(method = GET, produces = {APPLICATION_JSON_UTF8_VALUE}, consumes = {APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(OK)
    public String getAllUsers() {
        Collection<User> users = userService.findAll();
        return User.toJson(users);
    }

    @RequestMapping(value = "/{idOrName}", method = GET, produces = {APPLICATION_JSON_UTF8_VALUE}, consumes = {APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(OK)
    public String getUser(@PathVariable String idOrName) {
        try {
            Long userId = parseLong(idOrName);
            return userService.findById(userId).toJson();
        } catch (NumberFormatException exc) {
            return userService.findByName(idOrName).toJson();
        }
    }

    @RequestMapping(method = POST, produces = {APPLICATION_JSON_UTF8_VALUE}, consumes = {APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(OK)
    public String addUser(@RequestBody String json) {
        User user = new User(json);
        if (user.getRole() == null) user.setRole(new Role(null, Role.USER_ROLE));
        if (user.getEnabled() == null) user.setEnabled(true);
        userService.encodePassword(user);
        return userService.save(user).toJson();
    }

    @RequestMapping(value = "/{id}", method = PUT, produces = {APPLICATION_JSON_UTF8_VALUE}, consumes = {APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(OK)
    public String updateUser(@RequestBody String json, @PathVariable Long id) {
        if (userService.findById(id) == null) throw new ApiError(NOT_FOUND);
        User user = new User(json);
        userService.encodePassword(user);
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
    public String login(@RequestBody String json) {
        User user = new User(json);
        return userService.checkUserCreds(user).toJson();
    }

    @RequestMapping(value = "/confirm", method = GET)
    @ResponseStatus(OK)
    public String confirm(@RequestParam String username, @RequestParam String token) {
        User user = userService.findByName(username);
        if (userService.match(userService.getRawToken(user), token)) {
            user.setEnabled(true);
            userService.save(user);
            return new ApiResult(true, String.format("Email of new user %s confirmed, account is activated", username)).toJson();
        } else {
            return new ApiResult(false, String.format("Your token %s is incorrect", token)).toJson();
        }
    }

    @RequestMapping (path = "/sendmail", method = GET, produces = {APPLICATION_JSON_UTF8_VALUE}, consumes = {APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody public String sendMail (@RequestParam String username) {
        User user = userService.findByName(username);
        if (user != null) {
            String confirm = userService.getEncodedToken(user);
            String body = EmailConfig.emailBody(user.getUsername(), confirm);
            return userService.sendMail(user.getUsername(), "volgafit@gmail.com", body, "Please activate your VolgaFit account").toJson();
        }
        throw new UsernameNotFoundException(String.format("User %s not found", username));
    }

}
