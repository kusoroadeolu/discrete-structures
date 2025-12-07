package relations;

import settheory.sets.QuerySet;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RelationParser {

    private static final String BRACE_START = "{";
    private static final String BRACE_END = "}";
    private static final String EMPTY = "";
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");


    private static final Pattern PAIR_PATTERN =
            Pattern.compile("\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)");

    public static Relation parseStringToRelation(String val) {
        final QuerySet<Pair> pairs = new QuerySet<>();
        if (val == null || val.isEmpty()) return new Relation(pairs);

        val = val.replace(BRACE_START, EMPTY).replace(BRACE_END, EMPTY);

        final Matcher matcher = PAIR_PATTERN.matcher(val);
        while (matcher.find()) {
            final int first = Integer.parseInt(matcher.group(1));
            final int second = Integer.parseInt(matcher.group(2));
            pairs.add(new Pair(first, second));
        }

        return new Relation(pairs);
    }

    public static QuerySet<Integer> parseStringToSet(String val){
        final QuerySet<Integer> set = new QuerySet<>();
        if (val == null || val.isEmpty()) return set;

        val = val.replace(BRACE_START, EMPTY).replace(BRACE_END, EMPTY);
        final Matcher m = NUMBER_PATTERN.matcher(val);
        while (m.find()){

            try{
                set.add(Integer.parseInt(m.group()));
            }catch (NumberFormatException _){

            }
        }

        return set;
    }
}
