package DataBase;

import User.UserProxy;

import java.util.List;

public interface IDataBase {

    List<UserProxy> LoadAllUsers();
    UserProxy LoadUser(int index);
    boolean SaveUser(UserProxy userToSave);
}
