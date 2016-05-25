/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.gov.ida.kyc.util;

import static java.lang.String.format;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.http.ParseException;

/**
 *
 * @author ftbs
 */
public class DateUtil {
    
    public static String format(String formatStr, Date date)throws ParseException
    {
        SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
        String strDate = formatter.format(date);
        return strDate;
    }
    
}
