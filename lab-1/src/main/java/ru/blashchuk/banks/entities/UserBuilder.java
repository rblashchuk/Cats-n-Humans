package ru.blashchuk.banks.entities;

import ru.blashchuk.banks.interfaces.User;

public interface UserBuilder {
    UserBuilder withAddress(String address);

    UserBuilder withPassportNumber(int passport);

    User build();
}
