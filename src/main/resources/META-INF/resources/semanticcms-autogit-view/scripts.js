/*
 * semanticcms-autogit-view - SemanticCMS view of automatic Git status.
 * Copyright (C) 2016  AO Industries, Inc.
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

/*
 * After including this script, be sure to set the lastGitStatusCssClass to a correct value.
 */
semanticcms_autogit_view = {
	/*
	 * The most recently set Git status CSS class.  This is set by JSP just after this
	 * script is included.
	 */
	lastGitStatusCssClass : null,

	/*
	 * Updates the CSS class.
	 */
	updatePageTopLinkGitStatus : function(cssClass) {
		// console.log("cssClass = " + cssClass);
		if(cssClass !== semanticcms_autogit_view.lastGitStatusCssClass) {
			// Update class on element
			$("#pageTopLinkGitStatus").removeClass(semanticcms_autogit_view.lastGitStatusCssClass).addClass(cssClass);
			semanticcms_autogit_view.lastGitStatusCssClass = cssClass;
		}
	}
};

/*
 * Listen to the Git status polling.
 */
$(document).ready(function(){
	semanticcms_autogit_taglib.gitStatusListeners.push(
		{
			onComplete: function(result) {
				// console.log(result);
				semanticcms_autogit_view.updatePageTopLinkGitStatus(result.gitStatus.state.cssClass);
			},
			onError: function(textStatus, errorThrown) {
				semanticcms_autogit_view.updatePageTopLinkGitStatus("semanticcms-autogit-state-ajax-error");
			}
		}
	);
});
