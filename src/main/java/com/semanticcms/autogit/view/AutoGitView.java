/*
 * semanticcms-autogit-view - SemanticCMS view of automatic Git status.
 * Copyright (C) 2016, 2017, 2019, 2020, 2021, 2022, 2023  AO Industries, Inc.
 *     support@aoindustries.com
 *     7262 Bull Pen Cir
 *     Mobile, AL 36695
 *
 * This file is part of semanticcms-autogit-view.
 *
 * semanticcms-autogit-view is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * semanticcms-autogit-view is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with semanticcms-autogit-view.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.semanticcms.autogit.view;

import com.aoapps.html.servlet.FlowContent;
import com.aoapps.servlet.http.Dispatcher;
import com.semanticcms.autogit.servlet.AutoGit;
import com.semanticcms.core.model.Page;
import com.semanticcms.core.servlet.Headers;
import com.semanticcms.core.servlet.SemanticCMS;
import com.semanticcms.core.servlet.View;
import java.io.IOException;
import java.util.Collections;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.SkipPageException;

/**
 * SemanticCMS view of automatic Git status.
 */
public final class AutoGitView extends View {

  public static final String NAME = "git-status";

  private static final String HEAD_INCLUDE = "/semanticcms-autogit-view/head.inc.jspx";

  private static final String JSPX_TARGET = "/semanticcms-autogit-view/view.inc.jspx";

  /**
   * Registers the "{@link #NAME}" view and "{@link #HEAD_INCLUDE}" head include in {@link SemanticCMS}.
   */
  @WebListener("Registers the \"" + NAME + "\" view and \"" + HEAD_INCLUDE + "\" head include in SemanticCMS.")
  public static class Initializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
      SemanticCMS semanticCms = SemanticCMS.getInstance(event.getServletContext());
      semanticCms.addView(new AutoGitView());
      semanticCms.addHeadInclude(HEAD_INCLUDE);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
      // Do nothing
    }
  }

  private AutoGitView() {
    // Do nothing
  }

  @Override
  public Group getGroup() {
    return Group.FIXED;
  }

  @Override
  public String getDisplay() {
    return "Git";
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public boolean getAppliesGlobally() {
    return false;
  }

  /**
   * Does not apply to exports.
   */
  @Override
  public boolean isApplicable(
      ServletContext servletContext,
      HttpServletRequest request,
      HttpServletResponse response,
      Page page
  ) throws ServletException, IOException {
    return !Headers.isExporting(request);
  }

  @Override
  public String getLinkId() {
    return "semanticcms-autogit-view-link";
  }

  @Override
  public String getLinkCssClass(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
    return AutoGit.getGitStatus(servletContext, request).getState().getCssClass();
  }

  @Override
  public String getTitle(
      ServletContext servletContext,
      HttpServletRequest request,
      HttpServletResponse response,
      Page page
  ) {
    String bookTitle = page.getPageRef().getBook().getTitle();
    if (bookTitle != null && !bookTitle.isEmpty()) {
      return "Git Status" + TITLE_SEPARATOR + bookTitle;
    } else {
      return "Git Status";
    }
  }

  @Override
  public String getDescription(Page page) {
    return null;
  }

  @Override
  public String getKeywords(Page page) {
    return null;
  }

  /**
   * No robots for transient Git status.
   */
  @Override
  public boolean getAllowRobots(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response, Page page) {
    return false;
  }

  @Override
  public <__ extends FlowContent<__>> void doView(
      ServletContext servletContext,
      HttpServletRequest request,
      HttpServletResponse response,
      __ flow,
      Page page
  ) throws ServletException, IOException, SkipPageException {
    Dispatcher.include(
        servletContext,
        JSPX_TARGET,
        request,
        response,
        Collections.singletonMap("page", page)
    );
  }
}
