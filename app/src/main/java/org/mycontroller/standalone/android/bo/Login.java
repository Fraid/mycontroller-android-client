package org.mycontroller.standalone.android.bo;

/**
 * Created by tibi on 28/09/16.
 */
public class Login {

    public boolean success;
    public User user;

    @Override
    public String toString() {
        return "IsSuccess: "+success+", "+user.toString();
    }
}
