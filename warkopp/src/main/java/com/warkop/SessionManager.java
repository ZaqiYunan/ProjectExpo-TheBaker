package com.warkop;
import java.util.HashMap;
import java.util.Map;

public class SessionManager {
    private static SessionManager instance;
    private Map<String, User> loggedInUsers; // Map to store loggedInUserID and corresponding User object

    private SessionManager() {
        loggedInUsers = new HashMap<>();
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void loginUser(String userID, User user) {
        loggedInUsers.put(userID, user);
    }

    public void logoutUser(String userID) {
        loggedInUsers.remove(userID);
    }

    public User getLoggedInUser(String userID) {
        return loggedInUsers.get(userID);
    }

    public boolean isLoggedIn(String userID) {
        return loggedInUsers.containsKey(userID);
    }
}
