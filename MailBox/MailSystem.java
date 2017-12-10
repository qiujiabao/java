import java.util.ArrayList;

/**
   A system of voice mail boxes.
*/
public class MailSystem
{
   /**
      Constructs a mail system with a given number of mailboxes
      @param mailboxCount the number of mailboxes
   */
   public MailSystem()
   {
      mailboxes = new ArrayList<>();
   }
   
   public void addMailbox(String aUserName, String aPasscode)
   {
	   User newUser = new User(aUserName, aPasscode);
	   Mailbox newMailbox = new Mailbox(newUser);
	   mailboxes.add(newMailbox);
   }

   /**
      Locate a mailbox.
      @param aUserName the user name
      @param aPasscode the passcode for the user name
      @return the mailbox or null if not found
   */
   public Mailbox loginMailbox(String aUserName, String aPasscode)
   {
	   for (int i = 0; i < mailboxes.size(); i++)
	   {
		   User r = mailboxes.get(i).getUser();
		   if (r.getUserName().equals(aUserName))
		   {
			   if (r.getPasscode().equals(aPasscode))
			   {
				   return mailboxes.get(i);
			   }
		   }
	   }
	   return null;
   }
   
   public boolean findMailbox(String name)
   {
	   for (int i = 0; i < mailboxes.size(); i++)
	   {
		   Mailbox s = mailboxes.get(i);
		   if (s.getUser().getUserName().equals(name))
		   {
			   return true;
		   }
	   }
	   return false;
   }
   
   public void sendMsg(String reciever, Message msg)
   {
	   for (int i = 0; i < mailboxes.size(); i++)
	   {
		   Mailbox s = mailboxes.get(i);
		   if (s.getUser().getUserName().equals(reciever))
		   {
			   s.addMessage(msg);
		   }
	   }
   }

   private ArrayList<Mailbox> mailboxes;
}
