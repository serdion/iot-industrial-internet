/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.views;

/**
 *
 * @author ooppa
 */
public class ViewParams {

    private String pagetitle;
    private String headertype;
    private String footertype;
    private String content;

    public ViewParams() {
    }

    /**
     * Creates a new ViewParams object with default header and footer.
     * @param pagetitle
     * @param content
     */
    public ViewParams(String pagetitle, String content) {
        this.pagetitle = pagetitle;
        this.headertype = "defaultheader";
        this.footertype = "defaultfooter";
        this.content = content;
    }

    public String getPagetitle() {
        return pagetitle;
    }

    public void setPagetitle(String pagetitle) {
        this.pagetitle = pagetitle;
    }

    public String getHeadertype() {
        return headertype;
    }

    public void setHeadertype(String headertype) {
        this.headertype = headertype;
    }

    public String getFootertype() {
        return footertype;
    }

    public void setFootertype(String footertype) {
        this.footertype = footertype;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
