package com.js.sample;

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
    doNothing();
    mMessageModel = new Model();
    Label messageLabel = new Label("message", mMessageModel);
    add(messageLabel);
    mMessageComponent = messageLabel;
    mMessageComponent.setOutputMarkupId(true);

    Form form = new Form("login_form") {
      @Override
      protected void onConfigure() {
        super.onConfigure();
        boolean vis = !OurSession.get().isLoggedIn();
        mLoginComponent.setVisible(vis);
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
        if (!OurSession.get().logIn(value)) {
          setMessage(null, "User already logged in!");
        } else {
          setMessage(null, "Logged in as: " + value);
        }
      }
    });

    AjaxLink mLogoutButton = new AjaxLink("logout_button") {
      @Override
      public void onClick(AjaxRequestTarget target) {
        OurSession session = OurSession.get();
        session.logOut();
        setMessage(target, "");
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

    add(mLogoutButton);
    mLogoutComponent = mLogoutButton;
  }

  private void setMessage(AjaxRequestTarget target, String message) {
    if (message == null)
      message = "";
    mMessageModel.setObject(message);
    if (target != null) {
      target.add(mMessageComponent);
    }
  }

  private Model mMessageModel;
  private Model mUserIdModel;
  private Component mLogoutComponent;
  private Component mLoginComponent;
  private Component mMessageComponent;
}
