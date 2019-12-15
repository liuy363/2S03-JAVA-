class RangeCriterion {
    private long maxValue = Long.MAX_VALUE;
    private long minValue = Long.MIN_VALUE;

    //get minimum or maximum value
    void addCriterion(Tag tag) {
        //if the relation is larger, modify minValue to be the bigger value between current minvalue and the value store in tag
        if (tag.getRelation() == Tag.Relation.LARGER) {
            minValue = Math.max(minValue, Long.parseLong(tag.getValue()));
        }
        //if the relation is smaller, modify maxValue to be the small value between current maxvalue and the value store in tag
        if (tag.getRelation() == Tag.Relation.SMALLER) {
            maxValue = Math.min(maxValue, Long.parseLong(tag.getValue()));
        }
    }

    //check if the long value is in range
    boolean isInRange(long value) {
        if (value < maxValue && value > minValue)
            return true;
        return false;
    }
}
