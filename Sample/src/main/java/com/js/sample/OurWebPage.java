package com.js.sample;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

public class OurWebPage extends WebPage {

  public OurWebPage() {
    mLabel = new Label("message");
    add(mLabel);

    Form form = new Form("login_form");
    add(form);
    mLoginIdField = new TextField("login_id", new Model(""));
    form.add(mLoginIdField);
    form.add(new Button("login_button") {
      @Override
      public void onSubmit() {
        String value = (String) mLoginIdField.getModelObject();
        mLabel.setDefaultModelObject(value);
      }
    });

  }

  private Label mLabel;
  private TextField mLoginIdField;

}