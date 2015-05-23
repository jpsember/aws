package com.js.sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.*;
import javax.servlet.http.*;

public class SampleServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html");

    PrintWriter w = response.getWriter();
    w.println("<h1>" + "This is " + getServletDescription() + "</h1>");

    // If this is the first request serviced by this servlet, emphasize that
    // fact by printing something
    synchronized (this) {
      if (mInstanceCount++ == 0)
        w.println("<h1>***************************</h1>");
    }
  }

  private String getServletDescription() {
    if (mConstructedMessage == null) {
      Calendar cal = Calendar.getInstance();
      java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(
          "h:mm:ss");
      String strTime = simpleDateFormat.format(cal.getTime());
      mConstructedMessage = this.getClass().getSimpleName() + " (time: "
          + strTime + ")";
    }
    return mConstructedMessage;
  }

  private String mConstructedMessage;
  private int mInstanceCount;
}
