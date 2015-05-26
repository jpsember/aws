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
    mUserId = null;
  }

  public void logIn(String userId) {
    if (userId == null)
      die("null user");
    mUserId = userId;
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

  private String mUserId;
}
