package com.js.sample;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class OurWebPage extends WebPage {
  public OurWebPage() {
    add(new Label("message", "Hello World!"));
    }
}