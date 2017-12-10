/**
   A message left by the caller.
*/
public class Message
{
   /**
      Construct a Message object.
      @param messageText the message text
   */
   public Message(String messageText, String sentUser)
   {
      text = messageText;
      user = sentUser;
   }

   /**
      Get the message text.
      @return message text
   */
   public String getText()
   {
      return text;
   }
   
   public String getUser()
   {
	   return user;
   }

   private String text;
   private String user;
}
