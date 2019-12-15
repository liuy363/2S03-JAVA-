import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

class Utils {

    //formatting, SimpleDateFormat has a default format
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    //this method take a string input and parse the string to SimpleDateFormat, returning type is date
    static Date convertDate(String input) throws ParseException {
        return formatter.parse(input);
    }
    //take the date and return string
    static String formattedDate(Date date) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }
}
