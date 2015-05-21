package com.js.sample;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;

public class SampleServlet extends HttpServlet {

  @Override
  public void init() throws ServletException {
    mMessage = "This is com.js.sample.SampleServlet";
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html");

    PrintWriter out = response.getWriter();
    out.println("<h1>" + mMessage + "</h1>");
  }

  private String mMessage;
}

