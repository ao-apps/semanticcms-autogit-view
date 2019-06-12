/*
 * semanticcms-autogit-view - SemanticCMS view of automatic Git status.
 * Copyright (C) 2016, 2017, 2019  AO Industries, Inc.
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
 * along with semanticcms-autogit-view.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.semanticcms.autogit.view;

import com.aoindustries.servlet.http.Dispatcher;
import com.semanticcms.autogit.servlet.AutoGitContextListener;
import com.semanticcms.core.model.Page;
import com.semanticcms.core.servlet.Headers;
import com.semanticcms.core.servlet.View;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.SkipPageException;

public class AutoGitView extends View {

	static final String VIEW_NAME = "git-status";

	private static final String JSPX_TARGET = "/semanticcms-autogit-view/view.inc.jspx";

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
		return VIEW_NAME;
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
		return AutoGitContextListener.getGitStatus(servletContext, request).getState().getCssClass();
	}

	@Override
	public String getTitle(
		ServletContext servletContext,
		HttpServletRequest request,
		HttpServletResponse response,
		Page page
	) {
		String bookTitle = page.getPageRef().getBook().getTitle();
		if(bookTitle != null && !bookTitle.isEmpty()) {
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

	@Override
	public Map<String, String> getScripts() {
		return Collections.singletonMap("jquery", "/webjars/jquery/" + Maven.properties.getProperty("jquery.version") + "/jquery.min.js");
	}

	/**
	 * No robots for transient Git status.
	 */
	@Override
	public boolean getAllowRobots(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response, Page page) {
		return false;
	}

	@Override
	public void doView(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException, SkipPageException {
		Dispatcher.include(
			servletContext,
			JSPX_TARGET,
			request,
			response,
			Collections.singletonMap("page", page)
		);
	}
}
