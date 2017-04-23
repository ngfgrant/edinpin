package utility;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * Created by niallgrant on 20/04/17.
 */
public class SmsManager {

    // Find your Account Sid and Token at twilio.com/user/account
    public final String ACCOUNT_SID = "AC8958587a78d8792d3c03ad1c045af386";
    public final String AUTH_TOKEN = "1e6411856a904d3daf827a84f526ba6b";

    public void sendSms(String number, String message){
        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);

        Message smsMessage = Message.creator(new PhoneNumber(number),
                new PhoneNumber("+441745472080"), message).create();

    }



}
