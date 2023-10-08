package org.randompage.bookmarking.frontend.helper;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import java.util.LinkedList;
import java.util.List;

@ManagedBean
@NoneScoped
public class SystemUtils {
    public List<String> getBannerMessages() {
        List<String> bannerMessages = new LinkedList<String>();
        bannerMessages.add("JSF! because YOU deserve it.");
        bannerMessages.add("ASP.NET have left the building.");
        bannerMessages.add("Paris Hilton would use JSF.");
        bannerMessages.add("No more slaving over Struts.");
        return bannerMessages;
    }
}
