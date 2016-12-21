package com.sokratis12gr.modfetcher.util;

public class HTMLUtils {

    private static final String DOCTYPE = "<!DOCTYPE>";

    private static final String[] HTML = new String[]{"<html", "</html>"};

    private static final String[] HEAD = new String[]{"<head", "</head>"};

    private static final String[] BODY = new String[]{"<body", "</body>"};

    private static final String[] P = new String[]{"<p", "</p>"};
    private static final String[] H1 = new String[]{"<h1", "</h1>"};
    private static final String[] H2 = new String[]{"<h2", "</h2>"};
    private static final String[] H3 = new String[]{"<h3", "</h3>"};
    private static final String[] H4 = new String[]{"<h4", "</h4>"};
    private static final String[] H5 = new String[]{"<h5", "</h5>"};
    private static final String[] H6 = new String[]{"<h6", "</h6>"};

    private static String writeElement(String[] element, boolean isClosed) {
        if (isClosed) {
            return element[1];
        }
        return (element[0] + ">");
    }

    private static String writeElement(String[] element, String htmlAttributes) {
        return (element[0] + " " + htmlAttributes + ">");
    }

    private static String writeElement(String[] element, String... elements) {
        return (element[0] + ">") + "\n" + (elements) + "\n" + element[1];
    }

    private static String writeElement(String[] element, String htmlAttributes, String... elements) {
        return (element[0] + " " + htmlAttributes + ">") + "\n" + (elements) + "\n" + element[1];
    }

    public static String writeDocument() {
        return DOCTYPE;
    }

    public static String writeHTML(String htmlAttributes, String... elements) {
        return writeElement(HTML, htmlAttributes, elements);
    }

    public static String writeHTML(String... elements) {
        return writeElement(HTML, elements);
    }

    public static String writeHTML(boolean isClosed) {
        return writeElement(HTML, isClosed);
    }

    public static String writeHead(String htmlAttributes, String... elements) {
        return writeElement(HEAD, htmlAttributes, elements);
    }

    public static String writeHead(String... elements) {
        return writeElement(HEAD, elements);
    }

    public static String writeHead(boolean isClosed) {
        return writeElement(HEAD, isClosed);
    }

    public static String writeBody(String htmlAttributes, String... elements) {
        return writeElement(BODY, htmlAttributes, elements);
    }

    public static String writeBody(String... elements) {
        return writeElement(BODY, elements);
    }

    public static String writeBody(boolean isClosed) {
        return writeElement(BODY, isClosed);
    }

    public static String writeP(String htmlAttributes, String... text) {
        return writeElement(P, htmlAttributes, text);
    }

    public static String writeP(String... text) {
        return writeElement(P, text);
    }

    public static String writeP(boolean isClosed) {
        return writeElement(P, isClosed);
    }

    public static String writeH1(String htmlAttributes, String... text) {
        return writeElement(H1, htmlAttributes, text);
    }

    public static String writeH1(String... text) {
        return writeElement(H1, text);
    }

    public static String writeH1(boolean isClosed) {
        return writeElement(H1, isClosed);
    }

    public static String writeH2(String htmlAttributes, String... text) {
        return writeElement(H2, htmlAttributes, text);
    }

    public static String writeH2(String... text) {
        return writeElement(H2, text);
    }

    public static String writeH2(boolean isClosed) {
        return writeElement(H2, isClosed);
    }

    public static String writeH3(String htmlAttributes, String... text) {
        return writeElement(H3, htmlAttributes, text);
    }

    public static String writeH3(String... text) {
        return writeElement(H3, text);
    }

    public static String writeH3(boolean isClosed) {
        return writeElement(H3, isClosed);
    }

    public static String writeH4(String htmlAttributes, String... text) {
        return writeElement(H4, htmlAttributes, text);
    }

    public static String writeH4(String... text) {
        return writeElement(H4, text);
    }

    public static String writeH4(boolean isClosed) {
        return writeElement(H4, isClosed);
    }

    public static String writeH5(String htmlAttributes, String... text) {
        return writeElement(H5, htmlAttributes, text);
    }

    public static String writeH5(String... text) {
        return writeElement(H5, text);
    }

    public static String writeH5(boolean isClosed) {
        return writeElement(H5, isClosed);
    }

    public static String writeH6(String htmlAttributes, String... text) {
        return writeElement(H6, htmlAttributes, text);
    }

    public static String writeH6(String... text) {
        return writeElement(H6, text);
    }

    public static String writeH6(boolean isClosed) {
        return writeElement(H6, isClosed);
    }
}
