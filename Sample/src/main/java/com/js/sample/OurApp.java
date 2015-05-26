package com.js.sample;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import static com.js.basic.Tools.*;

public class OurApp extends WebApplication {

  public OurApp() {
    doNothing();
    clearConsole();
  }

  public static OurApp get() {
    return (OurApp) WebApplication.get();
  }

  @Override
  public Session newSession(Request request, Response response) {
    return new OurSession(request);
  }

  @Override
  public Class<? extends WebPage> getHomePage() {
    return OurWebPage.class;
  }

  /**
   * Attempt to log user in; return true if successful, false if user is already
   * logged in
   */
  public boolean logInUser(String userId) {
    synchronized (sLoggedInUsersSet) {
      return sLoggedInUsersSet.add(userId);
    }
  }

  public void logOutUser(String userId) {
    synchronized (sLoggedInUsersSet) {
      sLoggedInUsersSet.remove(userId);
    }
  }

  private void clearConsole() {
    Calendar cal = Calendar.getInstance();
    java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(
        "h:mm:ss");
    pr("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nStarting "
        + this.getClass().getSimpleName() + " (time: "
        + simpleDateFormat.format(cal.getTime()) + ")\n\n\n");
  }

  private static Set<String> sLoggedInUsersSet = new HashSet();

}
