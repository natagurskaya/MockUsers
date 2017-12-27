package ua.dp.gurskaya.mockusers.dao;

import ua.dp.gurskaya.mockusers.entity.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class UsersDao {

    public List<Users> generateUsers(int numberOfUsers) {

        List<Users> users = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numberOfUsers; i++) {
            Users user = new Users();
            user.setId(i);
            user.setGender(random.nextBoolean() ? "male" : "female");
            user.setName(generateName(user.getGender()));
            user.setAge(random.nextInt(61) + 20);
            user.setDrivingLicense(random.nextBoolean());

            users.add(user);
        }
        return users;
    }

    private String generateName(String gender) {
        String name;
        String[] maleNames = {"Noah", "Liam", "Mason", "Jacob", "William", "Ethan", "James", "Alexander", "Michael"};
        String[] femaleNames = {"Emma", "Olivia", "Sophia", "Ava", "Isabella", "Mia", "Abigail", "Emily", "Charlotte"};
        if (Objects.equals(gender, "male")) {
            int randomValue = new Random().nextInt(maleNames.length);
            name = (maleNames[randomValue]);
        } else {
            int randomValue = new Random().nextInt(femaleNames.length);
            name = (femaleNames[randomValue]);
        }
        return name;
    }
}