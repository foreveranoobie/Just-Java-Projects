package com.zhlob.auto.formatter.chain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HeaderArticleFormatter extends ArticleFormatter {
    private final String headerPattern = "<header>((?:(?!</?header[ >]).)*)</header>";

    @Override
    public String formatInput(String input) {
        Pattern pattern = Pattern.compile(headerPattern, Pattern.MULTILINE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(input);
        StringBuilder text;
        String group;
        while (matcher.find()) {
            group = matcher.group();
            text = new StringBuilder(group.substring(8, group.length() - 9));
            input = input.replace(matcher.group(), "<h2>" + text + "</h2>");
        }
        return (next != null ? next.formatInput(input) : input);
    }
}
