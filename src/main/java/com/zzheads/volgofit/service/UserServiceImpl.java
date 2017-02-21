package com.zzheads.volgofit.service;

import com.zzheads.volgofit.config.EmailConfig;
import com.zzheads.volgofit.dao.RoleDao;
import com.zzheads.volgofit.dao.UserDao;
import com.zzheads.volgofit.dto.ApiResult;
import com.zzheads.volgofit.model.User.Role;
import com.zzheads.volgofit.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Collection;
import java.util.Properties;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final RoleDao roleDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Override
    public Collection<User> findAll() {
        return (Collection<User>) userDao.findAll();
    }

    @Override
    public User findById(Long id) {
        return userDao.findOne(id);
    }

    @Override
    public User save(User user) {
        if (user == null) return null;
        Role role = roleDao.findByName(user.getRole().getName());
        if (role != null) user.setRole(role);
        return userDao.save(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public Collection<String> getAllUsernames() {
        return findAll().stream().map(User::getUsername).collect(Collectors.toList());
    }

    @Override
    public Collection<String> getRegisteredUsernames() {
        return findAll().stream().map(User::getUsername).collect(Collectors.toList());
    }

    @Override
    public User findByName(String name) {
        return findAll().stream().filter(user -> user.getUsername().equals(name)).findFirst().orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return findAll().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public void encodePassword(User user) {
        if (user == null) return;
        String rawPassword = user.getPassword();
        user.setPassword(passwordEncoder().encode(rawPassword));
    }

    @Override
    public boolean match(String raw, String encoded) {
        return passwordEncoder().matches(raw, encoded);
    }

    @Override
    public ApiResult checkUserCreds(User user) {
        if (user == null) throw new UsernameNotFoundException("User not found");
        User storedUser = findByName(user.getUsername());
        if (!storedUser.isEnabled()) return new ApiResult(false, "User is disabled");
        boolean result = match(user.getPassword(), storedUser.getPassword());
        ApiResult apiResult = new ApiResult(result);
        if (result) apiResult.setMessage("Credintials is ok"); else apiResult.setMessage("Incorrect password");
        return apiResult;
    }

    @Override
    public String getRawToken(User user) {
        if (user == null) return null;
        return String.format("%d%s%s%s", user.getId(), user.getEmail(), user.getPassword(), user.getRole().getName());
    }

    @Override
    public String getEncodedToken(User user) {
        return passwordEncoder().encode(getRawToken(user));
    }

    @Override
    public ApiResult sendMail(String to, String from, String body, String subject) {
        Properties properties = System.getProperties();

        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EmailConfig.GMAIL[0], EmailConfig.GMAIL[1]);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session); // email message
            message.setFrom(new InternetAddress(from)); // setting header fields
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject); // subject line
            message.setContent(body, "text/html; charset=UTF-8");

            Transport.send(message);
            return new ApiResult(true, String.format("Email to %s sent", to));
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return new ApiResult(false, mex.toString());
        }
    }

}
