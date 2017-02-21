package com.zzheads.volgofit.web.api;//

//  created by zzheads on 21.02.17
//

import com.zzheads.volgofit.exceptions.ApiError;
import com.zzheads.volgofit.model.User.Profile;
import com.zzheads.volgofit.model.User.User;
import com.zzheads.volgofit.service.ProfileService;
import com.zzheads.volgofit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/api/profile")
public class ProfileController {
    private final ProfileService profileService;
    private final UserService userService;

    @Autowired
    public ProfileController(ProfileService profileService, UserService userService) {
        this.profileService = profileService;
        this.userService = userService;
    }

    @RequestMapping(value = "/{userId}", method = GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getProfileById(@PathVariable Long userId) {
        User user = userService.findById(userId);
        if (user == null) throw new ApiError(HttpStatus.NOT_FOUND);
        Profile profile = user.getProfile();
        return profile.toJson();
    }
}
