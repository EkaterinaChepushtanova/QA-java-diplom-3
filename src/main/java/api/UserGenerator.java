package api;

import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {

    public User randomData() {
        return new User(RandomStringUtils.randomAlphanumeric(8) + "@mail.ru", "qwerty", "Star123");
    }
}
