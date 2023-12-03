package org.juheinz.appserver;

import org.juheinz.entities.AppUser;
import org.juheinz.entities.Package;
import org.juheinz.utility.AbstractRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserRepository extends AbstractRepository<AppUser> {

    private static final UserRepository userRepository = new UserRepository();
    private static final List<AppUser> allUsersInDB = new ArrayList<>();

    private UserRepository() {
        System.out.println("UserRepository Singleton created");
    }

    public static UserRepository getInstance() {

        getAllFromDatabase();
        return userRepository;
    }

    private static void getAllFromDatabase(){
        allUsersInDB.add(new AppUser(1));
        allUsersInDB.add(new AppUser(2));
        allUsersInDB.add(new AppUser(3));
        allUsersInDB.add(new AppUser(4));
        allUsersInDB.add(new AppUser(5));
    }

    public AppUser getUserForPackage(Package p) {
        //let's pretend every package's id corresponds to the user id of the user that is waiting for it
        return findUserByPackageID(allUsersInDB, p.getCode());
    }

    private AppUser findUserByPackageID(Collection<AppUser> list, int code) {
        return list.stream().filter(u -> (code == u.getUserID())).findFirst().orElse(null);
    }
}
