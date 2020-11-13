package Latch.Money4Mobs;

import java.util.UUID;

public class UserModel {
    protected String userName;
    protected String userId;
    //protected Boolean showMessage;
    protected String language;

    public UserModel(String userName, String userId, String language) {
        this.userName = userName;
        this.userId = userId;
        //this.showMessage = showMessage;
        this.language = language;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language){
        this.language = language;
    }

}
