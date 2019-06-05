/*
 * semanticcms-autogit-view - SemanticCMS view of automatic Git status.
 * Copyright (C) 2016, 2017  AO Industries, Inc.
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

import com.semanticcms.core.servlet.SemanticCMS;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener("Registers the \"" + AutoGitView.VIEW_NAME + "\" view and \"" + AutoGitViewContextListener.HEAD_INCLUDE + "\" head include in SemanticCMS.")
public class AutoGitViewContextListener implements ServletContextListener {

	static final String HEAD_INCLUDE = "/semanticcms-autogit-view/head.inc.jspx";

	@Override
	public void contextInitialized(ServletContextEvent event) {
		SemanticCMS semanticCMS = SemanticCMS.getInstance(event.getServletContext());
		semanticCMS.addView(new AutoGitView());
		semanticCMS.addHeadInclude(HEAD_INCLUDE);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// Do nothing
	}
}
