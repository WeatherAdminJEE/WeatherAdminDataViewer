package dao;

import imt.org.web.commonmodel.entities.SensorDataEntity;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;

public class UserDao extends AbstractDao {
    public static UserDao getInstance(){ return new UserDao(); }

    private UserDao(){
    }

    public UserEntity getUserFromUsername(String username){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        UserEntity user = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            StringBuilder sb = new StringBuilder();

            sb.append("Select user from UserEntity user");
            sb.append(" where ");
            sb.append("user.login = :username");


            Query query = entityManager.createQuery(sb.toString());
            query.setParameter("username" , username);

            user = (UserEntity)query.getSingleResult();
            transaction.commit();
        } catch (PersistenceException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            System.out.println("getUserFromUserName");
            System.out.println(e);

        } finally {
            entityManager.close();
        }

        return user;
    }

    public boolean isUserAuthorized(String username, String password){
        String encryptPassword = DigestUtils.sha1Hex(password);

        UserEntity user = getUserFromUsername(username);

        if(user == null || user.getPassword() != encryptPassword)
            return false;
        else
            return true;

    }

}
