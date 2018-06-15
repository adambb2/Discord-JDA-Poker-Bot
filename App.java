package com.Adamsbot.Adambbbot;
//https://github.com/DV8FromTheWorld/JDA
//this is a MAVEN project using the Discord API
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
public class App extends ListenerAdapter
{
	//App objects
	public int count = 0;
	Cardgame thisGame; //each bot app should only handle one game at a time
    
    
    @Override
    public void onMessageReceived(MessageReceivedEvent evt)
    {
    	//Objects
    	User objUser = evt.getAuthor();
    	MessageChannel objMsgCh = evt.getChannel();
    	Message objMsg = evt.getMessage();
    	
    	
    	thisGame.gameSetup(evt);
    	
    	//Commands
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
    
    public static void main( String[] args ) throws Exception
    {	
    	//reset count.. for some reason terminating the the code and restarting it does not reset its value
    	JDA jda = new JDABuilder(AccountType.BOT).setToken(Ref.token).buildBlocking();
        jda.addEventListener(new App());
        
        
    }
}


//make Player hold an objUser 
//assign message channel for player and table
//remove scanners