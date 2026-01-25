package test_data;

import models.User;

import java.util.stream.Stream;

//Test data class
public class UserTestData {
    //Test data with valid users
    public static Stream<User> validUsers(){
        return Stream.of(
                User.builder()
                        .name("Alex")
                        .gender("male")
                        .email("alex" + System.currentTimeMillis() + "@mail.com")
                        .status("active")
                        .build(),
                User.builder()
                        .name("Maria")
                        .gender("female")
                        .email("maria" + System.currentTimeMillis() + "@mail.ru")
                        .status("active")
                        .build()
        );
    }
}
