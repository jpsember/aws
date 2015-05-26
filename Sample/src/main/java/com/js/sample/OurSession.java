package com.js.sample;

import java.io.Serializable;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import static com.js.basic.Tools.*;

public class OurSession extends WebSession implements Serializable {

  protected OurSession(Request request) {
    super(request);
  }

  public boolean isLoggedIn() {
    return mUserId != null;
  }

  public void logOut() {
    OurApp.get().logOutUser(mUserId);
    mUserId = null;
  }

  /**
   * Attempt to log user in
   * 
   * @return true if successfully logged in; false if was already
   */
  public boolean logIn(String userId) {
    if (userId == null)
      die("null user");
    if (OurApp.get().logInUser(userId)) {
      mUserId = userId;
      return true;
    }
    return false;
  }

  public String getUserId() {
    return mUserId;
  }

  /**
   * Use Java's covariance feature to avoid casting (see Wicket in Action, p.
   * 27)
   */
  public static OurSession get() {
    return (OurSession) Session.get();
  }

  // Required for serialization to work properly across machines
  // (even on my machine, viewing site from both Chrome and Safari was producing
  // mismatches)
  private static final long serialVersionUID = 42L;

  private String mUserId;
}
