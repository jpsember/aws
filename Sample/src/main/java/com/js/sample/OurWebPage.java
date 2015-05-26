package com.js.sample;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

import static com.js.basic.Tools.*;

public class OurWebPage extends WebPage {

  public OurWebPage() {
    clearConsole();

    mMessageModel = new Model();
    Label message = new Label("message", mMessageModel);
    add(message);

    Form form = new Form("login_form") {
      @Override
      protected void onConfigure() {
        super.onConfigure();
        boolean vis = !OurSession.get().isLoggedIn();
        setVisible(vis);
      }
    };
    add(form);
    mLoginComponent = form;
    mLoginComponent.setOutputMarkupId(true);
    mLoginComponent.setOutputMarkupPlaceholderTag(true);

    mUserIdModel = new Model("");
    TextField idField = new TextField("user_id", mUserIdModel);
    form.add(idField);

    form.add(new Button("login_button") {
      @Override
      public void onSubmit() {
        String value = (String) mUserIdModel.getObject();
        if (value == null || value.isEmpty())
          return;
        if (isUserLoggedIn(value)) {
          mMessageModel.setObject("User already logged in!");
        } else {
          mMessageModel.setObject("Logging in as: " + value);
          setUserLoggedIn(value, true);
        }
      }
    });
    form.setOutputMarkupId(true); // Not sure required
    form.setOutputMarkupPlaceholderTag(true);

    AjaxLink mLogoutButton = new AjaxLink("logout_button") {
      @Override
      public void onClick(AjaxRequestTarget target) {
        OurSession session = OurSession.get();
        setUserLoggedIn(session.getUserId(), false);
        session.logOut();
        mMessageModel.setObject("");
        if (target != null) {
          // TODO: why is this necessary?
          target.add(mLoginComponent);
          target.add(mLogoutComponent);
        }
      }

      @Override
      protected void onConfigure() {
        super.onConfigure();
        boolean vis = OurSession.get().isLoggedIn();
        setVisible(vis);
      }
    };
    mLogoutButton.setOutputMarkupId(true); // Not sure required
    mLogoutButton.setOutputMarkupPlaceholderTag(true);

    add(mLogoutButton);
    mLogoutComponent = mLogoutButton;
  }

  private void clearConsole() {
    Calendar cal = Calendar.getInstance();
    java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(
        "h:mm:ss");
    pr("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nStarting "
        + this.getClass().getSimpleName() + " (time: "
        + simpleDateFormat.format(cal.getTime()) + ")\n\n\n");
  }

  private static boolean isUserLoggedIn(String userId) {
    synchronized (sLoggedInUsersSet) {
      return sLoggedInUsersSet.contains(userId);
    }
  }

  private static boolean setUserLoggedIn(String userId, boolean loggedInState) {
    OurSession.get().logIn(userId);
    synchronized (sLoggedInUsersSet) {
      if (!loggedInState)
        return sLoggedInUsersSet.remove(userId);
      else
        return !sLoggedInUsersSet.add(userId);
    }
  }

  private static Set<String> sLoggedInUsersSet = new HashSet();

  private Model mMessageModel;
  private Model mUserIdModel;
  private Component mLogoutComponent;
  private Component mLoginComponent;
}
