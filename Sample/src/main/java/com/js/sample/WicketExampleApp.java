package com.js.sample;

import org.apache.wicket.protocol.http.WebApplication;

public class WicketExampleApp extends WebApplication {
  public WicketExampleApp() {
  }

  @Override
  public Class getHomePage() {
    return HelloWorld.class;
  }
}