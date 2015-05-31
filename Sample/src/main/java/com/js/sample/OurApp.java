package com.js.sample;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.settings.ThemeProvider;
import de.agilecoders.wicket.less.BootstrapLess;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchTheme;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchThemeProvider;
import static com.js.basic.Tools.*;

public class OurApp extends WebApplication {

  public OurApp() {
    doNothing();
    clearConsole();
  }

  @Override
  public void init() {
    super.init();
    configureBootstrap();
  }

  private void configureBootstrap() {

    final IBootstrapSettings settings = new BootstrapSettings();
    settings.useCdnResources(true);

    final ThemeProvider themeProvider = new BootswatchThemeProvider(
        BootswatchTheme.Spacelab);
    settings.setThemeProvider(themeProvider);

    Bootstrap.install(this, settings);
    BootstrapLess.install(this);
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
