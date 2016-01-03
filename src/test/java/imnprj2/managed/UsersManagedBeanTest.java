package imnprj2.managed;

import imnprj2.dao.entity.UsersEntity;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by iman on 1/3/16.
 *
 */
public class UsersManagedBeanTest {
    UsersManagedBean usersManagedBean;

    @Before
    public void setUp() throws Exception {
        usersManagedBean = new UsersManagedBean();
        usersManagedBean.setUsername("testUsername");
        usersManagedBean.setFullname("testFullname");
        usersManagedBean.setPasswordHash("testPassword");
        usersManagedBean.setEmail("testEmail");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSignUpAction() throws Exception {
        usersManagedBean.signUpAction();
        List<UsersEntity> usersList = usersManagedBean.getUsersService().usersList();
    }

    @Test
    public void testDeleteAction() throws Exception {

    }
}