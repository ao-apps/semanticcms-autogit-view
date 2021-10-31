/*
 * semanticcms-autogit-view - SemanticCMS view of automatic Git status.
 * Copyright (C) 2016, 2019  AO Industries, Inc.
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

/*
 * After including this script, be sure to set the lastViewLinkCssClass to a correct value.
 */
semanticcms_autogit_view = {
	/*
	 * The most recently set Git status CSS class.  This is set by JSP just after this
	 * script is included.
	 */
	lastViewLinkCssClass : null,

	/*
	 * Updates the CSS class.
	 */
	updateViewLinkClass : function(cssClass) {
		// console.log("cssClass = " + cssClass);
		if(cssClass !== semanticcms_autogit_view.lastViewLinkCssClass) {
			// Update class on element
			jQuery("#semanticcms-autogit-view-link").removeClass(semanticcms_autogit_view.lastViewLinkCssClass).addClass(cssClass);
			semanticcms_autogit_view.lastViewLinkCssClass = cssClass;
		}
	}
};

/*
 * Listen to the Git status polling.
 */
jQuery(document).ready(function(){
	semanticcms_autogit_taglib.gitStatusListeners.push(
		{
			onComplete: function(result) {
				// console.log(result);
				semanticcms_autogit_view.updateViewLinkClass(result.gitStatus.state.cssClass);
			},
			onError: function(textStatus, errorThrown) {
				semanticcms_autogit_view.updateViewLinkClass("semanticcms-autogit-state-ajax-error");
			}
		}
	);
});
