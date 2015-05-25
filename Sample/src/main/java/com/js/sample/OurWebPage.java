package com.js.sample;

import java.util.Calendar;

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

    Form form = new Form("login_form");
    add(form);

    mUserIdModel = new Model("");
    TextField idField = new TextField("user_id", mUserIdModel);
    form.add(idField);

    form.add(new Button("login_button") {
      @Override
      public void onSubmit() {
        String value = (String) mUserIdModel.getObject();
        mMessageModel.setObject(value);
      }
    });

  }

  private void clearConsole() {
    Calendar cal = Calendar.getInstance();
    java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(
        "h:mm:ss");
    pr("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nStarting "
        + this.getClass().getSimpleName() + " (time: "
        + simpleDateFormat.format(cal.getTime()) + ")\n\n\n");
  }

  private Model mMessageModel;
  private Model mUserIdModel;
}
