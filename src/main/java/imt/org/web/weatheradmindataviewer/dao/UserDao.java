package imt.org.web.weatheradmindataviewer.dao;

import imt.org.web.weatheradmindataviewer.crud.facade.IEntityFacade;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class UserDao {
    private IEntityFacade crudEntityFacade;


    public UserEntity getUserFromUsername(String username){
            StringBuilder sb = new StringBuilder();

            sb.append("Select user from UserEntity user");
            sb.append(" where ");
            sb.append("user.login = :username");


            Map<String, String> queryParameters = new HashMap<>();
            queryParameters.put("username", username);
            Object[] users = crudEntityFacade.customFinder(sb.toString(), queryParameters).toArray();
            if(users.length > 0)
                return (UserEntity) users[0];
            else
                return null;
    }

    public boolean isUserAuthorized(String username, String password){
        String encryptPassword = DigestUtils.sha1Hex(password);

        UserEntity user = getUserFromUsername(username);

        if(user == null || !user.getPassword().equals(encryptPassword))
            return false;
        else
            return true;

    }

}
