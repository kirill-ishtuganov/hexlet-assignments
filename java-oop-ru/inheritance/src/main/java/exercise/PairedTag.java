package exercise;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class PairedTag extends Tag {

    private final String tagBody;

    private final ArrayList<Tag> tags;

    public PairedTag(String name, Map<String, String> value, String tagBody, List<Tag> tags) {
        super(name, value);
        this.tagBody = tagBody;
        this.tags = new ArrayList<>(tags);
    }

    public String getTagBody() {
        return tagBody;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public String getStringTags() {
        return tags.stream()
                .map(Tag::toString)
                .collect(Collectors.joining(""));
    }

    @Override
    public String toString() {

        var currentTag = new SingleTag(getName(), getValue()).toString();
        return String.format("%s%s%s</%s>", currentTag, getStringTags(), getTagBody(), getName());
    }
}
// END
