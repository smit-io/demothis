package io.smit.demothis.session;

import android.content.Context;
import android.content.SharedPreferences;

import io.smit.demothis.rest.pojo.Customer;

public class SessionManagement
{
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SessionManagement(Context context) {
        sharedPreferences = context.getSharedPreferences(Session.SHARED_PREFRENCES_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    public void saveSession(Customer customer){
        editor.putString(Session.SHARED_PREFRENCES_SESSION_KEY, customer.getName()).commit();
    }

    public String getSession(Customer customer) {
        return sharedPreferences.getString(Session.SHARED_PREFRENCES_SESSION_KEY, "");
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public SharedPreferences.Editor getEditor() {
        return editor;
    }

    public void setEditor(SharedPreferences.Editor editor) {
        this.editor = editor;
    }
}
