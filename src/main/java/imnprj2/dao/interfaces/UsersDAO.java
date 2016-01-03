package imnprj2.dao.interfaces;

import imnprj2.dao.entity.UsersEntity;

import java.util.List;

/**
 * Created by iman on 12/18/15.
 *
 */
public interface UsersDAO {
    List<UsersEntity> getUsers();
    UsersEntity getUserByUsername(String username);
    void insert(UsersEntity usersEntity);
    void delete(UsersEntity usersEntity);
}
