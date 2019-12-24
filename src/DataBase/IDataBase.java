package DataBase;

import User.IUser;

import java.util.List;

public interface IDataBase {

    List<IUser> LoadAllUsers();
    IUser LoadUser(int index);
    boolean SaveUser(IUser userToSave);
}
