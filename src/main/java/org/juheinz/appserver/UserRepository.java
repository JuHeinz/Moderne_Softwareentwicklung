package org.juheinz.appserver;

import org.juheinz.entities.Parcel;
import org.juheinz.entities.User;
import org.juheinz.utility.AbstractRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides read access to the user database.
 */
public class UserRepository extends AbstractRepository<User> {

    private static final UserRepository userRepository = new UserRepository();
    private static final List<User> allUsersInDB = new ArrayList<>();

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        getAllUsersFromDatabase();
        return userRepository;
    }

    private static void getAllUsersFromDatabase() {
        allUsersInDB.add(new User(1));
        allUsersInDB.add(new User(2));
        allUsersInDB.add(new User(3));
        allUsersInDB.add(new User(4));
        allUsersInDB.add(new User(5));
    }

    public User getUserForParcel(Parcel parcel) {
        //let's assume every parcels's id corresponds to the user id of the user that is waiting for it
        return UserRepository.allUsersInDB.stream().filter(u -> (parcel.getId() == u.getId())).findFirst().orElse(null);
    }

}
