package com.Adamsbot.Adambbbot;
import java.util.*;
import net.dv8tion.jda.core.entities.MessageChannel;

public class Player {
	 private ArrayList<String> hand;
	  //combined arraylist of hand and table cards
	  private ArrayList<String> handtable;
	  private int chips;
	  private String name;
	  private int recentbet;
	  private int password;
	  //holds the pair
	  private String pair;
	  //tells whether they have folded this game and the round they have folded on
	  private int fold;
	  //This tells whether or not this player was the last one to raise the bet (not match the bet)
	  private int highbet;
	  //holds running tolal of bets this game
	  private int totalbet;
	  //all in tells the amount all in
	  private int allin;

	//same message channel as the Cardgame class
	  private MessageChannel objMsgCh;


	//WINNER IS DETERMINED USING HIGHEST FIVE CARDS
	//RULES USE KICKERS AND TEXAS HOLDEM STYLE

	  //1 or 0
	  public int royalflush;
	  //must know high card
	  public int straightflush;
	  //must know high card
	  public int fourofakind;
	  //stores three of a kind value       compare high cards of pairs if other person also has a full house
	  public int fullhouse;
	  //can be 1 or 0         compare high card if tie
	  public int flush;
	  //must know high card
	  public int straight;
	  //must know high card
	  public int threeofakind;

	  //must know high card
	  public int highpair;
	  public int lowpair;
	  public int highcard;
	  //low high card is the 2nd kicker
	  public int lowhighcard;

	  //3rd 4th and 5th high card tie breakers
	  public int lowhighcardA;
	  public int lowhighcardB;
	  public int lowhighcardC;


	  //constructor
	  public Player(){
	    hand = new ArrayList<String>();
	    //start players with 100 chips to bet
	    chips = 100;
	    recentbet = 0;
	    fold = 0;
	    totalbet =0;
	    highbet =0;

	    royalflush =0;
	    straightflush =0;
	    fourofakind =0;
	    fullhouse =0;
	    flush= 0;
	    straight=0;
	    threeofakind=0;
	    highpair=0;
	    lowpair=0;
	    highcard=0;
	    allin=0;
	    lowhighcard=0;

	  }

	  //void because it doesnt return anything, merely adds card to hand
	  public void addhand(String card){
	    hand.add(card);
	  }
	  public void clearhand(){
	    hand.clear();
	  }
	  //prints hand
	  public void gethand(){
	    for(String obj:hand){
	      System.out.println(obj);}
	  }

	  public void setName(String newname){
	    name = newname;
	  }
	  public String getName(){
	    return name;
	  }

	  public int getchips(){
	    return chips;
	  }
	  public void removechips(int bet){
	    chips = chips - bet;
	  }
	  public void setrecentbet(int bet){
	    recentbet = bet;
	  }
	  public int getrecentbet(){
	    return recentbet;
	  }
	  public void setpassword(int threedigits){
	    password = threedigits;
	  }
	  public int getpassword(){
	    return password;
	  }
	  public ArrayList<String> returnhand(){
	    return hand;
	  }
	  public void setfold(int round){
	    fold = round;
	  }
	  public void clearfold(){
	    fold = 0;
	  }
	  public int getfold(){
	    return fold;
	  }
	  public void sethighbet(){
	    highbet = 1;
	  }
	  public void clearhighbet(){
	    highbet = 0;
	  }
	  public int gethighbet(){
	    return highbet;
	  }
	  public void addchips(int chipz){
	    chips = chips + chipz;
	  }
	  public void addtotalbet(int bet){
	    totalbet = totalbet+bet;
	  }
	  public void cleartotalbet(){
	    totalbet = 0;
	  }
	  public int gettotalbet(){
	    return totalbet;
	  }
	  public void subtotalbet(int bet){
	    totalbet = totalbet-bet;
	  }





	  public void setroyalflush(){
	    royalflush=1;
	  }
	  public int getroyalflush(){
	    return royalflush;
	  }
	  public void clearroyalflush(){
	    royalflush=0;
	  }
	  public void setstraightflush(int high){
	    straightflush=high;
	  }
	  public int getstraightflush(){
	    return straightflush;
	  }
	  public void clearstraightflush(){
	    straightflush=0;
	  }
	  public void setfourofakind(int high){
	    fourofakind=high;
	  }
	  public int getfourofakind(){
	    return fourofakind;
	  }
	  public void clearfourofakind(){
	    fourofakind=0;
	  }
	  public void setfullhouse(int num){
	    fullhouse=num;
	  }
	  public int returnfullhouse(){
	    return fullhouse;
	  }
	  public void clearfullhouse(){
	    fullhouse=0;
	  }
	  public void setflush(){
	    flush=1;
	  }
	  public int returnflush(){
	    return flush;
	  }
	  public void clearflush(){
	    flush=0;
	  }

	  public void setstraight(int high){
	    straight=high;
	  }
	  public int getstraight(){
	    return straight;
	  }
	  public void clearstraight(){
	    straight=0;
	  }
	  public void setthreeofakind(int high){
	    threeofakind=high;
	  }
	  public int getthreeofakind(){
	    return threeofakind;
	  }
	  public void clearthreeofakind(){
	    threeofakind=0;
	  }
	  public void sethighpair(int high){
	    highpair=high;
	  }
	  public int gethighpair(){
	    return highpair;
	  }
	  public void clearhighpair(){
	    highpair=0;
	  }
	  public void setlowpair(int high){
	    lowpair=high;
	  }
	  public int getlowpair(){
	    return lowpair;
	  }
	  public void clearlowpair(){
	    lowpair=0;
	  }
	  public void sethighcard(int high){
	    highcard=high;
	  }
	  public int gethighcard(){
	    return highcard;
	  }
	  public void clearhighcard(){
	    highcard=0;
	  }
	  public void clearrecentbet(){
	    recentbet=0;
	  }

	  public void setallin(int amount){
	    allin=amount;
	  }
	  public int getallin(){
	    return allin;
	  }
	  public void clearallin(){
	    allin=0;
	  }

	  public void setlowhighcard(int amount){
	    lowhighcard=amount;
	  }
	  public int getlowhighcard(){
	    return lowhighcard;
	  }
	  public void clearlowhighcard(){
	    lowhighcard=0;
	  }

	  public void setlowhighcardA(int amount){
	    lowhighcardA=amount;
	  }
	  public int getlowhighcardA(){
	    return lowhighcardA;
	  }
	  public void clearlowhighcardA(){
	    lowhighcardA=0;
	  }

	  public void setlowhighcardB(int amount){
	    lowhighcardB=amount;
	  }
	  public int getlowhighcardB(){
	    return lowhighcardB;
	  }
	  public void clearlowhighcardB(){
	    lowhighcardB=0;
	  }

	  public void setlowhighcardC(int amount){
	    lowhighcardC=amount;
	  }
	  public int getlowhighcardC(){
	    return lowhighcardC;
	  }
	  public void clearlowhighcardC(){
	    lowhighcardC=0;
	  }

}
