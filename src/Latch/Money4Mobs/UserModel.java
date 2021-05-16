package Latch.Money4Mobs;

import java.util.UUID;

public class UserModel {
    protected String userName;
    protected String userId;
    protected Boolean showMessage;
    protected String language;
    protected String ipAddress;

    public UserModel(String userName, String userId, Boolean showMessage, String language, String ipAddress) {
        this.userName = userName;
        this.userId = userId;
        this.showMessage = showMessage;
        this.language = language;
        this.ipAddress = ipAddress;
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

    public Boolean getShowMessage() {
        return showMessage;
    }

    public void setShowMessage(Boolean showMessage){
        this.showMessage = showMessage;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language){
        this.language = language;
    }

    public String getIPAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress){
        this.ipAddress = ipAddress;
    }


}
