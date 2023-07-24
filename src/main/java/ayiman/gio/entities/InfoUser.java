package ayiman.gio.entities;

import io.quarkus.oidc.UserInfo;
import io.vertx.core.http.HttpServerRequest;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class InfoUser {
    
    @Inject
    HttpServerRequest request;

    @Inject
    UserInfo userInfo;

    public String getRemoteAddress(){
        return request.remoteAddress().hostAddress();
    }

    public Utilisateur userInfo() {
        Utilisateur user = new Utilisateur();


        // user.appId = userInfo.getString("azp");
        // user.userId = userInfo.getString("sub");
        // user.username = userInfo.getString("preferred_username");
        // user.ip = request.remoteAddress().hostAddress();

        
        user.userId = "6a19fd28-be90-4234-b4e9-e6b25554671e";
        user.username = "giovanni";
        user.ip = "127.0.0.1";

        return user;
    }
}
