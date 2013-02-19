package org.randompage.bookmarking.frontend.action;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.randompage.bookmarking.frontend.helper.SystemUtils;

@ManagedBean
@ApplicationScoped
public class BannerAction {
    private List<String> bannerMessages;
    private int index;

    // managed properties
    @ManagedProperty(value = "#{systemUtils}")
    private SystemUtils systemUtils;

    public String getBannerMessage() {
        if (index == bannerMessages.size())
            startOver();
        return bannerMessages.get(index++);
    }

    public void setSystemUtils(SystemUtils systemUtils) {
        this.systemUtils = systemUtils;
    }

    @PostConstruct
    protected void startOver() {
        bannerMessages = systemUtils.getBannerMessages();
        index = 0;
    }
}
