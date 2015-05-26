package com.js.sample;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import static com.js.basic.Tools.*;

public class OurApp extends WebApplication {

  public OurApp() {
    doNothing();
  }

  @Override
  public Session newSession(Request request, Response response) {
    return new OurSession(request);
  }

  @Override
  public Class<? extends WebPage> getHomePage() {
    return OurWebPage.class;
  }
}
