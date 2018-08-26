package e.apple.starwartest.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeUtil {

    public static String convertToNewFormat(String dateStr)
    {
        String dataTime ="";
        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat destFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss aa");
        sourceFormat.setTimeZone(utc);
        Date convertedDate = null;
        try {
            convertedDate = sourceFormat.parse(dateStr);
            dataTime =    destFormat.format(convertedDate);
        }

        catch (Exception e)
        {
            e.printStackTrace();
            dataTime= "";
        }


        return dataTime;
    }

}
