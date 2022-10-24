package com.konzik.auth.services;

import com.konzik.Common.User;
import com.konzik.auth.configs.Credentials;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class KeyCloakService {

    @Value("${keycloak.auth-server-url}")
    String serverUrl;
    @Value("${keycloak.realm}")
    String realm = "SpringBootKeycloak";
    @Value("${keycloak.resource}")
    String clientId = "login-app";

    // admin credentials
    final static String username = "admin";
    final static String password = "123+aze";

    public Keycloak getAdminKeycloakUser() {
        return KeycloakBuilder.builder().serverUrl(serverUrl)
                .grantType("password").realm(realm)
                .clientId(clientId)
                .username(username)
                .password(password)
                .resteasyClient(ClientBuilder.newBuilder().build())
                .build();
    }

    public RealmResource getRealm() {
        return getAdminKeycloakUser().realm(realm);
    }

    public void createUser(User user) {
        UserRepresentation userRepresentation = new UserRepresentation();

        userRepresentation.setUsername(user.getUsername());
        userRepresentation.setEmail(user.getEmail());

        Response response = getRealm().users().create(userRepresentation);
        //If user is created successfully 200 is returned for response status.

        //Set password flow
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        String userId = CreatedResponseUtil.getCreatedId(response);
        passwordCred.setTemporary(false);
        passwordCred.setType("password");
        passwordCred.setValue(user.getPassword());
        UserResource userResource = getRealm().users().get(userId);
        userResource.resetPassword(passwordCred);
    }

    public List<UserRepresentation> getUser(String userName) {
        UsersResource usersResource = getRealm().users();
        List<UserRepresentation> user = usersResource.search(userName, true);
        return user;
    }

    public void updateUser(String userId, User user_base) {
        CredentialRepresentation credential = Credentials
                .createPasswordCredentials(user_base.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(user_base.getUsername());
        user.setEmail(user_base.getEmail());
        user.setCredentials(Collections.singletonList(credential));

        UsersResource usersResource = getRealm().users();
        usersResource.get(userId).update(user);
    }

    public void deleteUser(String userId) {
        UsersResource usersResource = getRealm().users();
        usersResource.get(userId)
                .remove();
    }

    public void sendVerificationLink(String userId){
        UsersResource usersResource = getRealm().users();
        usersResource.get(userId)
                .sendVerifyEmail();
    }

    public void sendResetPassword(String userId){
        UsersResource usersResource = getRealm().users();

        usersResource.get(userId)
                .executeActionsEmail(Arrays.asList("UPDATE_PASSWORD"));
    }
}
