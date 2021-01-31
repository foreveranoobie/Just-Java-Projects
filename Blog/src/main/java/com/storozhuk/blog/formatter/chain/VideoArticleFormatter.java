package com.storozhuk.blog.formatter.chain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VideoArticleFormatter extends ArticleFormatter {
    private final String videoPattern = "<video>((?:(?!</?video[ >]).)*)</video>";

    @Override
    public String formatInput(String input) {
        Pattern pattern = Pattern.compile(videoPattern, Pattern.MULTILINE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(input);
        StringBuilder currentUrl;
        String group;
        int youtubeIndex = 0;
        while (matcher.find()) {
            group = matcher.group();
            currentUrl = new StringBuilder(group.substring(7, group.length() - 8));
            youtubeIndex = currentUrl.indexOf("/watch?v=");
            if (youtubeIndex != -1) {
                currentUrl = currentUrl.replace(youtubeIndex + 1, youtubeIndex + 9, "embed/");
            }
            input = input.replace(matcher.group(), "<br/><iframe style='display: block; margin: 1% auto 2% auto; width: 43%; height: 51%;' src='" + currentUrl +
                    "'>" + "</iframe><br/>");
        }
        return (next != null ? next.formatInput(input) : input);
    }
}
