import java.util.Scanner;

/**
   A telephone that takes simulated keystrokes and voice input
   from the user and simulates spoken text.
*/
public class Console
{
   /**
      Construct phone object.
      @param aScanner that reads text from a character-input stream
   */
   public Console(Scanner aScanner)
   {
      scanner = aScanner;
      mailSys = new MailSystem();
      login();
   }

   /**
      Speak a message to System.out.
      @param output the text that will be "spoken"
   */
   public void speak(String output)
   {
      System.out.println(output);
   }
   
   public void login()
   {
	   speak("Welcome to TextChat.");
	   boolean more = true;
	   while (more)
	   {
		   speak("[L]ogin  [N]ew user  [Q]uit:");
		   String input = scanner.nextLine();
		   if (input.equalsIgnoreCase("N"))
		   {
			   speak("Select a user name:");
			   String name = scanner.nextLine();
			   speak("Select a password:");
			   String pass = scanner.nextLine();
			   if (!mailSys.findMailbox(name))
			   {
				   mailSys.addMailbox(name, pass);
				   speak("User added.");
			   }
			   else
			   {
				   speak("User not added.");
			   }
			   login();
			   break;
		   }
		   else if (input.equalsIgnoreCase("L"))
		   {
			   speak("User name:");
			   String name = scanner.nextLine();
			   speak("Password:");
			   String pass = scanner.nextLine();
			   currentBox = mailSys.loginMailbox(name, pass);
			   if (currentBox == null) 
			   {
				   login();
				   break;
			   }
			   else
			   {
				   speak("Welcome " + name + ".");
				   use(currentBox);
				   login();
				   break;
			   }
		   }
		   else if (input.equalsIgnoreCase("Q")) { more = false; }
		   else { more = true; }
	   }
   }
   
   public void use(Mailbox currentBox)
   {
	   boolean more = true;
	   while (more)
	   {
		   speak("[S]end message  [R]ead messages  [L]ogout:");
		   String input = scanner.nextLine();
		   if (input.equalsIgnoreCase("L")) { more = false; }
		   else if (input.equalsIgnoreCase("S"))
		   {
			   speak("To:");
			   String reciever = scanner.nextLine();
			   speak("Message text, . to end:");
			   String msgTxt = "";
			   String thisLine = scanner.nextLine();
			   msgTxt = msgTxt + thisLine + "\n";
			   while (!thisLine.equals("."))
			   {
				   thisLine = scanner.nextLine();
				   msgTxt = msgTxt + thisLine + "\n";
			   }
			   msgTxt = msgTxt.substring(0,msgTxt.length()-3);
			   Message msg = new Message(msgTxt, currentBox.getUser().getUserName());
			   boolean has = mailSys.findMailbox(reciever);
			   if (has) 
			   { 
				   mailSys.sendMsg(reciever, msg);
				   speak("Message sent."); 
			   }
			   else { speak("Message not sent."); }
		   }
		   else if (input.equalsIgnoreCase("R"))
		   {
			   more = false;
			   read(currentBox);
		   }
		   else { speak("Invalid operation."); }
	   }
   }
   
   public void read(Mailbox currentBox)
   {
	   Message msg = currentBox.getCurrentMessage();
	   speak("From: " + msg.getUser());
	   speak(msg.getText());
	   speak("[K]eep  [E]rase:");
	   boolean done = false;
	   while (!done)
	   {
		   String r = scanner.nextLine();
		   if (r.equalsIgnoreCase("K")) 
		   {
			   currentBox.saveCurrentMessage();
			   done = true;
		   }
		   else if (r.equalsIgnoreCase("E")) 
		   {
			   currentBox.removeCurrentMessage();
			   done = true;
		   }
		   else { speak("Invalid operation."); }
	   }
	   speak("[N]ext  [D]one with messages:");
	   boolean finish = false;
	   while (!finish)
	   {
		   String s = scanner.nextLine();
		   if (s.equalsIgnoreCase("N"))
		   {
			   if (currentBox.getCurrentMessage() != null)
			   { read(currentBox); }
			   else { use(currentBox); }
			   break;
		   }
		   else if (s.equalsIgnoreCase("D"))
		   {
			   use(currentBox);
			   break;
		   }
		   else { speak("Invalid operation."); }
	   }
   }

   private Scanner scanner;
   private MailSystem mailSys;
   private Mailbox currentBox;
}
