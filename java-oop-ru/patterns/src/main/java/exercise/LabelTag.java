package exercise;

// BEGIN
class LabelTag implements TagInterface {
    private final String type;
    private final TagInterface value;

    public LabelTag(String type, TagInterface value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String render() {
        return "<label>" + type + value.render() + "</label>";
    }
}
// END
