package org.juheinz.appserver;

import org.juheinz.entities.Parcel;
import org.juheinz.entities.User;
import org.juheinz.utility.AbstractRepository;

import java.util.ArrayList;
import java.util.Collection;
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

    private static void getAllUsersFromDatabase(){
        allUsersInDB.add(new User(1));
        allUsersInDB.add(new User(2));
        allUsersInDB.add(new User(3));
        allUsersInDB.add(new User(4));
        allUsersInDB.add(new User(5));
    }


    //TODO: beide methods vereinen
    public User getUserForParcel(Parcel parcel) {
        //let's pretend every package's id corresponds to the user id of the user that is waiting for it
        return findUserByParcelID(allUsersInDB, parcel.getId());
    }

    private User findUserByParcelID(Collection<User> list, int id) {
        return list.stream().filter(u -> (id == u.getId())).findFirst().orElse(null);
    }
}
