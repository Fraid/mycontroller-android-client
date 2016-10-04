package org.mycontroller.standalone.android.bo;

/**
 * Created by tibi on 28/09/16.
 */
public class User {

    public int id;
    public boolean enabled;
    public String username;
    public String fullName;
    public String email;
    public String permission;

    @Override
    public String toString() {
        return "id: "+id+ ", username: "+username;
    }
}
