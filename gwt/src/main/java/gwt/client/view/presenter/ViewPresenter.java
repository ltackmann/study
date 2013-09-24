package co.tackmann.gwt.client.view.presenter;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 * Interface for presenters that uses the window container. Used by presenters that backs
 * views with a history token.
 *
 * @see com.google.gwt.user.client.ui.HasWidgets 
 */
public interface ViewPresenter {
    void go(final HasWidgets container);
}
