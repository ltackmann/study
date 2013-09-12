package org.randompage.bookmarking.frontend.client.view;

import com.google.gwt.user.client.ui.HTML;

/**
 * Utility class for constructing HTML fragments
 *
 * @author Lars Tackmann
 */
public class HTMLHelper {
    public static HTML asH2(String content) {
        return new HTML("<h2>" + content + "</h2>");
    }

}
