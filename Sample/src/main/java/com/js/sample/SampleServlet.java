package com.js.sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.*;
import javax.servlet.http.*;

public class SampleServlet extends HttpServlet {

  @Override
  public void init() throws ServletException {
    mMessage = "This is " + getServletDescription();
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html");

    PrintWriter out = response.getWriter();
    out.println("<h1>" + mMessage + "</h1>");
  }

  private String getServletDescription() {
    if (sConstructedMessage == null) {
      Calendar cal = Calendar.getInstance();
      java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(
          "h:mm:ss");
      String strTime = simpleDateFormat.format(cal.getTime());
      sConstructedMessage = this.getClass().getSimpleName() + " (time: "
          + strTime + ")";
    }
    return sConstructedMessage;
  }

  private static String sConstructedMessage;
  private String mMessage;
}
