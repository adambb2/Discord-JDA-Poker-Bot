package com.Adamsbot.Adambbbot;
import java.util.*;
import net.dv8tion.jda.core.entities.MessageChannel;

public class Table {
	public ArrayList<String> tablecards;
	  public int potchips;
	  public String name;
	  public int ante;
	  //stores the number of betting cycles
	  int betcycle;
	  //betting round stores the round (not cycles). when new cards are laid on the table a round is added
	  int bettinground;
	  
	  //same message channel as the Cardgame class
	  MessageChannel objMsgCh;
	  
	  
	  //constructor
	  public Table(){
	    tablecards = new ArrayList<String>();
	    //start players with 100 chips to bet
	    potchips = 0;
	    betcycle = 0;
	    ante = 0;
	    bettinground = 1;
	  }

	  public int getpot(){
	    return potchips;
	  }
	  public void addpotchips(int bets){
	    potchips = potchips + bets;
	  }
	  public void clearpot(){
	   potchips=0;
	  }
	  public ArrayList<String> gettablecards(){
	    return tablecards;
	  }
	  public void addtablecards(String card){
	    tablecards.add(card);
	  }
	  public void printtablecards(){
	    for(String obj:tablecards){
	      objMsgCh.sendMessage(obj).queue();}
	  }
	  public void cleartablecards(){
	    tablecards.clear();
	  }
	  public int getbetcycle(){
	    return betcycle;
	  }
	  public void addbetcycle(){
	    betcycle= betcycle +1;
	  }
	  public void clearbetcycle(){
	    betcycle =0;
	  }
	  public void setante(int xante){
	    ante = xante;
	  }
	  public int getante(){
	    return ante;
	  }

	  public int getbettinground(){
	    return bettinground;
	  }
	  public void addbettinground(){
	    bettinground= bettinground +1;
	  }
	  public void clearbettinground(){
	    bettinground =0;
	  }
	  public void setbettinground(){
	    bettinground =1;
	  }

}
