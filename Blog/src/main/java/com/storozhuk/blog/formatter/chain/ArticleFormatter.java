package com.storozhuk.blog.formatter.chain;

import org.springframework.stereotype.Component;

@Component
public abstract class ArticleFormatter {
    ArticleFormatter next;

    public ArticleFormatter setNextFormatter(ArticleFormatter nextFormatter) {
        this.next = nextFormatter;
        return this.next;
    }

    public abstract String formatInput(String input);
    /*private final String imagePattern = "<image>((?:(?!</?image[ >]).)*)</image>";
    private final String videoPattern =
            "<video>((?:(?!</?video[ >]).)*)</video>";
    private final String headerPattern = "<header>((?:(?!</?header[ >]).)*)</header>";

    public ArticleFormatter() {
    }

    public String format(String input) {
        input = formatImages(input);
        input = formatVideos(input);
        input = formatHeaders(input);
        return "";
    }

    private String formatImages(String input) {
        Pattern pattern = Pattern.compile(imagePattern, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(input);
        StringBuilder currentUrl;
        String group;
        while (matcher.find()) {
            group = matcher.group();
            currentUrl = new StringBuilder(group.substring(7, group.length() - 8));
            input = input.replace(matcher.group(), "<img src='" + currentUrl + "'/>");
        }
        return input;
    }

    private String formatVideos(String input) {
        Pattern pattern = Pattern.compile(videoPattern, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(input);
        StringBuilder currentUrl;
        String group;
        while (matcher.find()) {
            group = matcher.group();
            currentUrl = new StringBuilder(group.substring(7, group.length() - 8));
            input = input.replace(matcher.group(), "<iframe src='" + currentUrl + "'/>");
        }
        return input;
    }

    private String formatHeaders(String input) {
        Pattern pattern = Pattern.compile(headerPattern, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(input);
        StringBuilder text;
        String group;
        while (matcher.find()) {
            group = matcher.group();
            text = new StringBuilder(group.substring(8, group.length() - 9));
            input = input.replace(matcher.group(), "<h2>" + text + "</h2>");
        }
        return input;
    }*/
}
