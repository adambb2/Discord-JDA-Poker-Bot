package com.Adamsbot.Adambbbot;
//https://github.com/DV8FromTheWorld/JDA
//http://home.dv8tion.net:8080/job/JDA/javadoc/
//https://github.com/DV8FromTheWorld/JDA/wiki/8%29-List-of-Events#self-events


//changed from Java 1.5 to 1.8 in properties
//this is a MAVEN project using the Discord API
//Adam Bell
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class App extends ListenerAdapter
{
	//App objects
	public int count;
	public int countB;
	private Cardgame thisGame; //each bot app should only handle one game at a time
	
	// 6-19-18
	//app constructor 
	public App() {
		count = 0;
		countB = 0;
		thisGame = new Cardgame();
	}
	
//	public void handleEvent(Event evt) {
//		//edit this
//	}
    
	public Cardgame getGame() {
		return thisGame;
	}
	
//	@SuppressWarnings("InfiniteLoopStatement")
//    public void monitorVariable(Cardgame myGame) {
//        new Thread(() -> {
//            //log.info("Rotating Game Thread started");
//            while (true) {
//            	
//            	if(myGame.getmyChannel() != null && myGame.getStarttime() != 0 && count < 1) {
//            	long estimatedTime = (System.nanoTime() - myGame.getStarttime())/1000000000;
//            	if(estimatedTime > 60) {
//            		myGame.getmyChannel().sendMessage("60 seconds has passed... No more players can join. Time: "+estimatedTime).queue();
//            		myGame.getmyChannel().sendMessage("The first round of betting is about to begin.").queue();
//            		count++;
//            	}}
//                
//                try {
//                    Thread.sleep(1000);//edit the time
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "monitorVariable").start();
//    }
	
	//check if integer
	public boolean isInteger( String input ) {
	    try {
	        Integer.parseInt( input );
	        return true;
	    }
	    catch( Exception e ) {
	        return false;
	    }
	}
    
    @Override
    public void onMessageReceived(MessageReceivedEvent evt)
    {
    	//Objects
    	User objUser = evt.getAuthor();
    	MessageChannel objMsgCh = evt.getChannel();
    	Message objMsg = evt.getMessage();
    	
    	thisGame.gameSetup(evt); 
    	thisGame.addPlayers(evt);
    	thisGame.getPlayers(evt); //last player added calls
    	
    	//calls threads ONCE and doesnt call it again
    	if(countB == 0) {
    	thisGame.monitorVariable();
    	thisGame.bettingThread();
    	countB=1;
    	}
    	
    	thisGame.getBet(evt);
    	
    	
    	
    	
    	if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix+"endGame"))//how to endGame and clear everything
    	{
    	objMsgCh.sendMessage(objUser.getAsMention()+" Has ended this game..").queue();
    	thisGame = new Cardgame();//reset the game
    	}
    	
    	
//    	String[] myarray = objMsg.getContentRaw().split(" ");
//    	objMsgCh.sendMessage(objUser.getAsMention()+" myarray[0]: "+myarray[0]).queue();
//    	objMsgCh.sendMessage(objUser.getAsMention()+" myarray[1]: "+myarray[1]).queue();
//    	if(myarray[0] == Ref.prefix+"thisBet") {
//    		objMsgCh.sendMessage(objUser.getAsMention()+" SUCCESS").queue();
//	    	if(isInteger(myarray[1]))//how to endGame and clear everything
//	    	{
//	    	int X = Integer.parseInt(myarray[1]);
//	    	objMsgCh.sendMessage(objUser.getAsMention()+" the number is: "+X).queue();
//	    	}
//    	}
    	

    	
    	
    	
    	
    	//game admin set ante //if gamePhase == 0
    	//game admin append players //if gamePhase == 0
    	//game admin append chips //if gamePhase == 0
    	
    	//deal 2 cards (dm images to users) //use an a different event handler?
    	
    	//bet
    	//3 table card
    	//bet
    	//1 table card
    	//bet
    	//1 table card
    	//final betting round
    	
    	//https://github.com/DV8FromTheWorld/JDA/wiki/7)-Using-RestAction		info on queue and threads for messages and actions
    	
    	
    	
    	
    	
    	//Commands (these are just for test purposes)
    	if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix+"ping"))
    	{
    	objMsgCh.sendMessage(objUser.getAsMention()+" Pong!"+count).queue();
    	count=count+1;
    	}
    	
    	if(objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix+"ping") && count > 3)
    	{
    	objMsgCh.sendMessage(objUser.getAsMention()+" Pong! COUNT: "+count).queue();	
    	}
    }
    
    
    
//    @SuppressWarnings("InfiniteLoopStatement")
//    private static void monitorVariable(Cardgame myGame) {
//        new Thread(() -> {
//            //log.info("Rotating Game Thread started");
//            while (true) {
//            	long estimatedTime = (System.nanoTime() - startTime)/1000000000;
//            	if(estimateTime == 10 && myGame.getmyChannel() != null) {
//            		myGame.getmyChannel().sendMessage("10 seconds has been reached").queue();
//            	}
//                
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "monitorVariable").start();
//    }
    
    public static void main( String[] args ) throws Exception
    {	JDA jda = new JDABuilder(AccountType.BOT).setToken(Ref.token).buildBlocking();
        jda.addEventListener(new App());
        //I could use the .nanoTime to update a 
        
        
        
//        while(true) {//infinite loop for listening
//        	if(thisGame.getoldPhase() != thisGame.getgamePhase()) {//check for a change in gamePhase
//        		thisGame.setoldPhase();//set the oldPhase to the GamePhase
//        		System.out.println("The Game Phase has changed to: "+thisGame.getgamePhase());//notify in command prompt
//        		thisGame.getmyChannel().sendMessage("The Game Phase has changed to: "+thisGame.getgamePhase()).queue(); //notify in Discord
//        	}
//        }
    }
    
    
}

//reset count.. for some reason terminating the the code and restarting it does not reset its value

//make Player hold an objUser 
//assign message channel for player and table
//remove scanners