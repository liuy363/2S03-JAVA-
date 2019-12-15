class Tag {
    //define enum class, where variables cannot change
    public enum Relation {
        SMALLER, LARGER, EQUAL
    }
    private Relation relation;
    private String name;
    private String value;

    Tag(String[] tokens) {
        //first element of tokens
        name = tokens[0];

        //different cases depend on first char of second element of tokens
        switch (tokens[1].charAt(0)) {
            case '<':
                relation = Relation.SMALLER;
                break;
            case '>':
                relation = Relation.LARGER;
                break;
            case '=':
                relation = Relation.EQUAL;
                break;
            default:
                //define an exception
                throw new BadCommandException("Invalid tag: ill-defined bad relation.");
        }
        //second element of tokens
        value = tokens[2];
    }

    /*
    methods that return the attributes
     */

    public Relation getRelation() {
        return relation;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
