package com.js.sample;

import org.apache.wicket.protocol.http.WebApplication;

public class OurApp extends WebApplication {
  public OurApp() {
  }

  @Override
  public Class getHomePage() {
    return OurWebPage.class;
  }
}