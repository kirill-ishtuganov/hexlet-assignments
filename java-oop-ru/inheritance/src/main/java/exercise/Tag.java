package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
class Tag {
    private final String name;
    private final Map<String, String> value;

    Tag(String name, Map<String, String> value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getValue() {
        return value;
    }

    public String getStringValue() {
        return getValue().entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=\"" + entry.getValue() + "\"")
                .collect(Collectors.joining(" "));
    }

    public String toString() {
        String formatter = getValue().isEmpty() ? "<%s>" : "<%s %s>";
        return String.format(formatter, getName(), getStringValue());
    }
}
// END
