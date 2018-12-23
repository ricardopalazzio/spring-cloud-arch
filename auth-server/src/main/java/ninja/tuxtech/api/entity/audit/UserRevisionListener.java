package ninja.tuxtech.api.entity.audit;

import org.hibernate.envers.RevisionListener;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;


public class UserRevisionListener implements RevisionListener , Serializable {

    public final static String USERNAME = "Palazzio";



    @Override
    public void newRevision(Object revisionEntity) {
        UserRevEntity entity = (UserRevEntity) revisionEntity;
        entity.setUsername(USERNAME);
        HttpServletRequest curRequest =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();
        entity.setIp(curRequest.getRemoteAddr());
    }
}