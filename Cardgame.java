package com.Adamsbot.Adambbbot;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Scanner;

//methods are no longer static because they are class specific
public class Cardgame {
	
//THESE ARE THE CARDGAME OBJECTS	
	//same message channel as the Cardgame class
	private MessageChannel objMsgCh;
	private ArrayList<Player> gamePlayers;
	private Table gameTable;
	
	
	
	//method that sets the current Messaging Channel for the Game(global) and for each player and table
	//also 
	public void gameSetup(MessageReceivedEvent evt) {
		User objUser = evt.getAuthor();
		Message objMsg = evt.getMessage();
		
		if(objMsg.getContentRaw().equalsIgnoreCase(">gameSetup")) {
			objMsgCh = evt.getChannel();//set the message channel for the game
			objMsgCh.sendMessage(objUser.getAsMention()+"Has started a new game of Poker! ").queue();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//method that shuffles the deck 3 times.
	public ArrayList<String> shuffle(ArrayList<String> deck){
	  for(int i = 0; i<3; i++){
	  for (int counter = 0; counter < deck.size(); counter++) {
	    		 int randomNumA = ThreadLocalRandom.current().nextInt(0, 51 + 1);
	        // int randomNumB = ThreadLocalRandom.current().nextInt(0, 51 + 1);
	        // objMsgCh.sendMessage(randomNumA+" "+randomNumB);
	         Collections.swap(deck, i, randomNumA);
	     }}
	  return deck;
	}


	//deal method that deals 1 card to a player from top of the deck
	public void deal(Player player, ArrayList<String> deck){
	  player.addhand(deck.get(deck.size()-1));
	  deck.remove(deck.size()-1);
	}

	// deal method that deals 1 card to the middle of the table from the top of the deck
	public void dealtable(Table table, ArrayList<String> deck){
	  table.addtablecards(deck.get(deck.size()-1));
	  deck.remove(deck.size()-1);
	}

	//clears screen
	


	                //poker game mode; need to create a number of players based on user input. Players will be stored in an arraylist
	                //RECURSIVE
	                public ArrayList<Player> betting(ArrayList<Player> players, Table table, ArrayList<String> deck, ArrayList<Player> foldplayers){
	                  /*
	                          //the ante is 4 chips
	                          //set fold is reset
	                          //previous bets are cleared
	                          for(int t=0; t<players.size();t++){
	                          players.get(t).removechips(4);
	                          players.get(t).setrecentbet(0);
	                          players.get(t).clearfold();
	                          }
	                          //2 cards are dealt
	                          for(int x=0; x<players.size();x++){
	                            deal(players.get(x), deck);
	                            deal(players.get(x), deck);
	                            //players.get(x).setrecentbet(0);
	                          }
	                          */
	                          //create array of players that didnt fold
	                          ArrayList<Player> nonfoldplayers = new ArrayList<Player>();
	                          int fix = 0;
	                          Scanner input = new Scanner(System.in);
	                        //This sequence should be repeated until all non-fold player bets are equivalent OR UNTIL THE LENGTH OF NON-FOLD PLAYERS IS 1
	                                      for(int z=0; z<players.size();z++){
	                                                                  objMsgCh.sendMessage("THIS IS A TEST: NONFOLDPLAYERS SIZE is "+nonfoldplayers.size()).queue();


	                                                                  //THIS SHOUDLNT DO ANYTHING!!!
	                                                                  if (nonfoldplayers.size()==0 && z == (players.size()-1)){
	                                                                    objMsgCh.sendMessage(players.get(players.size()-1).getName()+" has won the pot!").queue();
	                                                                    objMsgCh.sendMessage(table.getpot()+" chips have been added to "+players.get(players.size()-1).getName()+"'s account.").queue();
	                                                                    players.get(players.size()-1).addchips(table.getpot());
	                                                                    table.clearpot();
	                                                                    objMsgCh.sendMessage("ADAM NEEDS TO CHECK THIS ERROR").queue();
	                                                                    //return the winner?
	                                                                  //  objMsgCh.sendMessage("THIS IS A TEST YOOOOOOOOOOO").queue()
	                                                                    fix=1;
	                                                                    ArrayList<Player> nonfoldplayerz = new ArrayList<Player>();
	                                                                    nonfoldplayerz.add(players.get(players.size()-1));
	                                                                    return nonfoldplayerz;

	                                                                  }


	                                        objMsgCh.sendMessage((players.get(z)).getName() + "'s turn is commencing. All other players should temporarily leave the area.\n").queue();
	                                    //    int password = 0;
	                                      //  while(password != (players.get(z)).getpassword()){
	                                        //  objMsgCh.sendMessage("\nPlease enter your three digit password.").queue();
	                                          //password = input.nextInt();
	                                      //  }


	                                        //catch input mismatch exception
	                                        int password=0;
	                                          do {
	                                             try {
	                                                 objMsgCh.sendMessage("\nPlease enter your three digit password: ").queue();
	                                                 password = input.nextInt();
	                                             } catch (InputMismatchException e) {
	                                                 objMsgCh.sendMessage("Incorrect three digit passowrd. ").queue();
	                                             }
	                                             input.nextLine(); // clears the buffer
	                                         } while (password != (players.get(z)).getpassword());




	                                        objMsgCh.sendMessage("\nThe ante was "+ table.getante() +" chips this round.\n").queue();
	                                        objMsgCh.sendMessage("\nThere are "+ table.getpot()+ " chips in the pot.\n").queue();
	                                        if(table.gettablecards().size() != 0){
	                                        objMsgCh.sendMessage("The cards on the table are ").queue();
	                                        table.printtablecards();
	                                      }
	                                        objMsgCh.sendMessage("\nYour hand is ").queue();
	                                        players.get(z).gethand();
	                                        objMsgCh.sendMessage("\nYou have "+ (players.get(z)).getchips() +" chips in your possession.\n").queue();
	                                        objMsgCh.sendMessage("The most recent bets are as follows: ").queue();
	                                          for(int d=0; d<players.size();d++){
	                                            if(players.get(d).getallin() > 0){
	                                              objMsgCh.sendMessage(players.get(d).getName()+" is all in with "+ players.get(d).gettotalbet()+ " chips!").queue();
	                                              objMsgCh.sendMessage(players.get(d).getName() + " last bet " + players.get(d).getrecentbet() + " chips.").queue();
	                                            }
	                                              else if(players.get(d).getfold() == 0){
	                                            objMsgCh.sendMessage(players.get(d).getName() + " has " + players.get(d).getchips() + " chips in his account.").queue();
	                                            objMsgCh.sendMessage(players.get(d).getName() + " last bet " + players.get(d).getrecentbet() + " chips.").queue();
	                                            objMsgCh.sendMessage(players.get(d).getName() + "'s total bet is " + players.get(d).gettotalbet() + " chips.\n").queue();
	                                          }
	                                          }

	                                            if(foldplayers.size() != 0){
	                                              objMsgCh.sendMessage("The fold players recent bets are as follows: ").queue();
	                                              for(int tt=0; tt<foldplayers.size();tt++){
	                                                objMsgCh.sendMessage(foldplayers.get(tt).getName()+" FOLDED on round "+ foldplayers.get(tt).getfold());
	                                                objMsgCh.sendMessage(foldplayers.get(tt).getName() + " last bet " + foldplayers.get(tt).getrecentbet() + " chips.").queue();
	                                                objMsgCh.sendMessage(foldplayers.get(tt).getName() + "'s total bet is " + foldplayers.get(tt).gettotalbet() + " chips.\n").queue();

	                                              }
	                                              objMsgCh.sendMessage(" ").queue();
	                                            }

	                                          String betorder="unknown";
	                                          if(z==0){betorder="first";}
	                                          else if(z==1){betorder="second";}else if(z==2){betorder="third";}else if(z==3){betorder="fourth";}else if(z==4){betorder="fifth";}else if(z==5){betorder="sixth";}
	                                          else if(z==6){betorder="seventh";}else if(z==7){betorder="eighth";}else if(z==8){betorder="ninth";}else if(z==9){betorder="tenth";}
	                                        objMsgCh.sendMessage("You were "+betorder+" to bet this betting round.").queue();

	                                        //print the person that raised the bet
	                                        //this was changed from nonfoldplayers to just use players
	                                        for(int ticka=0; ticka<players.size();ticka++){
	                                          if(players.get(ticka).gethighbet() == 1 && players.get(ticka).gettotalbet() != 0){
	                                            objMsgCh.sendMessage(players.get(ticka).getName()+" has raised the bet!").queue();
	                                          }

	                                        }
	                                        objMsgCh.sendMessage("\nEnter the amount you want to bet. Enter 0 if you do not wish to bet or if you wish to fold. If your bet amount is not higher than\nthe previous player's TOTAL bet FROM THIS ROUND, then you fold (unless you are ALL IN).\n").queue();
	                                        //int newbet = -9999999;
	                                      //  while(newbet > players.get(z).getchips() || newbet < 0){
	                                        //objMsgCh.sendMessage("\nPlease enter a bet that is less than or equal to your total chips.").queue();
	                                      //  newbet = input.nextInt();
	                                    //  }

	                                      int newbet=-99999;
	                                        do {
	                                           try {
	                                               objMsgCh.sendMessage("\nPlease enter your bet: ").queue();
	                                               newbet = input.nextInt();
	                                           } catch (InputMismatchException e) {
	                                               objMsgCh.sendMessage("Please enter a bet that is less than or equal to your total chips.").queue();
	                                           }
	                                           input.nextLine(); // clears the buffer
	                                       } while (newbet > players.get(z).getchips() || newbet < 0);



	                                        players.get(z).addtotalbet(newbet);

	                                        //bet must match last persons bet or be ur total number of chips
	                                        //might need to track all in
	                                        /*
	                                        int poop=0;
	                                        //check if the current bet is less than all the previous highest bet
	                                        for(int poo; poo<players.size();poo++){
	                                          if(z != poo){
	                                            if(players.get(z).gettotalbet()<players.get(poo).gettotalbet()){
	                                              poo++;
	                                            }
	                                          }
	                                        }
	                                          */
	                                          //find the highest total bet
	                                          int poop=0;
	                                            for(int poo=0; poo<players.size();poo++){
	                                              if(players.get(poo).gethighbet()==1){

	                                              poop = players.get(poo).gettotalbet();
	                                              }
	                                            }


	                                        if(players.get(z).gettotalbet() < poop && newbet != players.get(z).getchips()){
	                                          objMsgCh.sendMessage("This bet is less than the previous bet THEREFORE, you have FOLDED!").queue();
	                                          //sets fold to 1
	                                          players.get(z).setfold(table.getbettinground());
	                                          players.get(z).subtotalbet(newbet);
	                                          foldplayers.add(players.get(z));
	                                        }
	                                        ///THERE SHOULD BE A CASE FOR ALL IN AND FOR SECOND ROUND FIRST POSITION
	                                        else{
	                                        players.get(z).setrecentbet(newbet);
	                                        players.get(z).removechips(newbet);
	                                        table.addpotchips(newbet);
	                                        }

	                                        //all in case
	                                        if(players.get(z).getchips() == 0){
	                                          objMsgCh.sendMessage(players.get(z).getName()+" has went all in with "+players.get(z).gettotalbet()+" chips!").queue();
	                                          players.get(z).setallin(players.get(z).gettotalbet());
	                                        }

	                                        objMsgCh.sendMessage("\n\nYour turn is now over, please exit the computer when the screen clears and allow the next player their turn.").queue();
	                                        // 6 second delay and clear screen
	                                        try {
	                                        TimeUnit.SECONDS.sleep(6);
	                                      }catch (InterruptedException e){
	                                        //handle the exception
	                                        objMsgCh.sendMessage("Please don't input while delaying").queue();
	                                      }
	                                      //  clearScreen();
	                                      
	                                      //non fold array list
	                                      if(players.get(z).getfold() == 0){
	                                            nonfoldplayers.add(players.get(z));
	                                            //set high better (person that originally raised the bet)
	                                            //the last added will bet size()-1
	                                            //doesnt work for cycle?
	                                            //more than one player didnt fold and not on the first player (cycle doesnt matter)
	                                            if(nonfoldplayers.size() != 1 && z!=0 && nonfoldplayers.get(nonfoldplayers.size()-1).gettotalbet() > nonfoldplayers.get(nonfoldplayers.size()-2).gettotalbet()){
	                                              nonfoldplayers.get(nonfoldplayers.size()-1).sethighbet();
	                                              nonfoldplayers.get(nonfoldplayers.size()-2).clearhighbet();
	                                             }
	                                             //back on first person condition after 1 cycle
	                                             else if(table.getbetcycle() != 0 && z==0 && nonfoldplayers.get(nonfoldplayers.size()-1).gettotalbet() > players.get(players.size()-1).gettotalbet()){
	                                               nonfoldplayers.get(nonfoldplayers.size()-1).sethighbet();
	                                               players.get(players.size()-1).clearhighbet();
	                                             }
	                                             //first thing on first cycle
	                                             else if(z==0 && table.getbetcycle()==0){
	                                                nonfoldplayers.get(nonfoldplayers.size()-1).sethighbet();
	                                             }

	                                      }


	                                      //if fold=1 remove them from the arraylist (if they were already added)
	                                      //maybe this is not necessary
	                                  //    else if(players.get(z).getfold() == 1 && table1.getbetcycle() >= 1){
	                                    //    nonfoldplayers.get(z).remove();
	                                      //}
	                                      /*


	                                       //index out of bounds here
	                                      //set high better (person that originally raised the bet)
	                                      if(z!=0 && nonfoldplayers.get(z).getrecentbet() > nonfoldplayers.get(z-1).getrecentbet()){
	                                        nonfoldplayers.get(z).sethighbet();
	                                        nonfoldplayers.get(z-1).clearhighbet();
	                                       }

	                                       else if(z==0 && nonfoldplayers.get(z).getrecentbet() > 0){
	                                         nonfoldplayers.get(z).sethighbet();
	                                       }
	                                       */

	                                      }
	                                      //adds 1 to cycle
	                                      table.addbetcycle();

	                                      //TO ACCOUNT FOR ALL IN, YOU ONLY NEED TO CHECK THAT THE NON-ALLINS ARE EQUIVALENT TO MOVE ON
	                                      ArrayList<Player> nonallinlist = new ArrayList<Player>();
	                                      nonallinlist.addAll(nonfoldplayers);

	                                      /*
	                                      ///TEST... THE LISTS ARE THE SAME HERE, SO THIS IS CORRECT
	                                      objMsgCh.sendMessage("THE NONALLINLIST IS:   (SHOULD EQUAL NONFOLDPLAYERS HERE)").queue();
	                                    for(int bruh=0;bruh<nonallinlist.size();bruh++){
	                                    objMsgCh.sendMessage(nonallinlist.get(bruh).getName());}
	                                    objMsgCh.sendMessage("\nTHE NONFOLDPLAYERS:").queue();
	                                  for(int bruhx=0;bruhx<nonfoldplayers.size();bruhx++){
	                                  objMsgCh.sendMessage(nonfoldplayers.get(bruhx).getName());}
	                                  objMsgCh.sendMessage("\n\n").queue();



	                                      //remove the allins.. THIS WONT WORK BECAUSE REMOVING SOMETHINGS CHANGES EVERYTHING ELSES INDEXES AND ALSO THE ARRAY SIZE
	                                      for(int fg=0; fg<nonallinlist.size(); fg++){
	                                                // for some reason this print line only grabs
	                                                objMsgCh.sendMessage(nonallinlist.get(fg).getName()+" has an allin value of "+nonallinlist.get(fg).getallin()+"\n").queue();

	                                              //  if(nonallinlist.get(fg).getallin() != 0){
	                                              //  nonallinlist.remove(nonallinlist.get(fg));}

	                                              }
	                                              */

	                                              Iterator<Player> myiterator =  nonallinlist.iterator();
	                                                    while(myiterator.hasNext())
	                                                    {
	                                                       if(myiterator.next().getallin() != 0)
	                                                       myiterator.remove();
	                                                    }

	                                                    /*

	                                                    objMsgCh.sendMessage("if nonzero they are removed").queue();
	                                      ///TEST... FOR SOME REASON ONE OF the all-in players isn't being removed from the
	                                      objMsgCh.sendMessage("\nTHE NONALLINLIST IS:").queue();
	                                    for(int bruh=0;bruh<nonallinlist.size();bruh++){
	                                    objMsgCh.sendMessage(nonallinlist.get(bruh).getName());}
	                                    objMsgCh.sendMessage("\nTHE NONFOLDPLAYERS ARE:").queue();
	                                  for(int bruhx=0;bruhx<nonfoldplayers.size();bruhx++){
	                                  objMsgCh.sendMessage(nonfoldplayers.get(bruhx).getName());}
	                                    */




	                                      int checking =0;
	                                      //checking if all players have the same bet
	                                      for(int jk=0; jk<nonallinlist.size();jk++){
	                                        if(nonallinlist.get(0).gettotalbet() == nonallinlist.get(jk).gettotalbet() ){
	                                          checking++;
	                                        }

	                                      }

	                                      //if one player left then he or she wins the pot
	                                      //there will 1 player in this array and a winnder if
	                                      //if size is 1 and you are out of the loop
	                                      if (nonfoldplayers.size()==1 ){
	                                        objMsgCh.sendMessage(nonfoldplayers.get(0).getName()+" has won the pot!").queue();
	                                        objMsgCh.sendMessage(table.getpot()+" chips have been added to "+nonfoldplayers.get(0).getName()+"'s account.").queue();
	                                        nonfoldplayers.get(0).addchips(table.getpot());
	                                        table.clearpot();
	                                        return nonfoldplayers;
	                                      }

	                                      //needs to handle ALL IN
	                                      //if all nonallin players are equal in bets then you can move on
	                                      else if(checking == nonallinlist.size() && fix == 0){
	                                        String roundstring = "NULL";
	                                        if(table.getbettinground() == 1){roundstring = "Three cards";}
	                                        else{roundstring = "One card";}
	                                        //return nonfoldplayers
	                                        objMsgCh.sendMessage("The first round of betting has commenced. "+roundstring+" will now be placed on the table\n").queue();
	                                        return nonfoldplayers;

	                                      }

	                                        else{
	                                          betting(nonfoldplayers, table, deck, foldplayers); //{



	                                        }

	                                        return null;

	                                        /*

	                          //HOW DO I USE WHILE LOOP AND HOW DO I GEt LAst person that didnt fold
	                          for(int b=0; b<players.size();b++){
	                          //WHILE LOOP NEEDED HERE
	                          While(y!==0 && players.get().getfold() != 0 && player.get(y).getrecentbet <= player.get(y-1).getrecentbet || ){
	                          //handle raising of bet and checking
	                          for(int y=0; y<players.size();y++){
	                            //must be greater than the last person THAT DID NOT FOLD
	                            if(y!==0 && players.get(y).getfold() != 0 && player.get(y).getrecentbet <= player.get(y-1).getrecentbet){
	                              //cant pinpoint who raised the bet??
	                              objMsgCh.sendMessage("The bet has been raised by. You must match the new bet or raise the bet, otherwise you fold.\n").queue();
	                            }
	                            //first person
	                            else if(y==1 && players.get(y).getfold() != 0 && player.get(y).getrecentbet <= last person that didnt fold){

	                            }

	                          }}}
	                          */

	                          // objMsgCh.sendMessage("The first round of betting has commenced. Three cards will now be placed on the table\n").queue();
	                        }




	      //VOID OR NOT?? in this form should be players.twopair()?? --nevermind
	      // form of checkpair(table1, players);
	        public void checkpair(Table table, ArrayList<Player> players){
	          
	            for(int q=0; q<players.size();q++){
	                //may need to clear arraylist at start??
	                //combine hand and tablecards into new arraylist
	            ArrayList<String> newlist = new ArrayList<String>(players.get(q).returnhand());
	                newlist.addAll(table.gettablecards());
	                for(int pair=2; pair<15;pair++){
	                  String pairstring = Integer.toString(pair);
	                  if(pair == 11){pairstring="Jack"; }
	                  else if(pair==12){pairstring="Queen";}
	                  else if(pair==13){pairstring="King";}
	                  else if(pair==14){pairstring="Ace";}

	                //reset paircount for next number
	                int paircount =0;
	                for(int h=0; h<newlist.size();h++){
	                if (newlist.get(h).toLowerCase().indexOf(pairstring.toLowerCase()) != -1 ) {
	                  paircount++;

	                if(paircount==2){

	                    objMsgCh.sendMessage(players.get(q).getName()+" has a pair of "+ pairstring+"'s").queue();
	                      players.get(q).setlowpair(pair);
	                      //set second pair which his the higher pair
	                    if(players.get(q).getlowpair() != 0 && players.get(q).getlowpair() != pair){
	                      players.get(q).sethighpair(pair);}

	                  }
	                if(paircount==3){
	                     objMsgCh.sendMessage(players.get(q).getName()+" has three "+ pairstring+"'s").queue();
	                    players.get(q).setthreeofakind(pair);

	                    if((players.get(q).getlowpair() != 0 || players.get(q).gethighpair() != 0) && players.get(q).getthreeofakind() != 0 && players.get(q).getthreeofakind() != players.get(q).gethighpair() && players.get(q).getthreeofakind() != players.get(q).getlowpair()){
	                      players.get(q).setfullhouse(players.get(q).getthreeofakind());
	                      objMsgCh.sendMessage(players.get(q).getName()+" has a FULLHOUSE!").queue();
	                    }
	                    /*
	                    if(players.get(q).gethighpair()==players.get(q).getthreeofakind() && players.get(q).returnfullhouse() == 0){
	                      players.get(q).clearhighpair();
	                    }
	                    if(players.get(q).getlowpair()==players.get(q).getthreeofakind() && players.get(q).returnfullhouse() == 0){
	                      players.get(q).clearlowpair();
	                    }
	                    */


	                   }
	                if(paircount==4){
	                     objMsgCh.sendMessage(players.get(q).getName()+" has four "+ pairstring+"'s").queue();
	                     players.get(q).setfourofakind(pair);
	                     if(players.get(q).getlowpair()==players.get(q).getfourofakind()){
	                       players.get(q).clearlowpair();
	                     }
	                     if(players.get(q).gethighpair()==players.get(q).getfourofakind()){
	                       players.get(q).clearhighpair();
	                     }
	                     if(players.get(q).getthreeofakind()==players.get(q).getfourofakind()){
	                       players.get(q).clearfourofakind();
	                     }


	                   }

	                        }
	                }

	                }

	                }
	            //return null;
	         }

	         public void flush(Table table, ArrayList<Player> players){
	             for(int q=0; q<players.size();q++){
	                 //may need to clear arraylist at start??
	                 //combine hand and tablecards into new arraylist
	             ArrayList<String> newlist = new ArrayList<String>(players.get(q).returnhand());
	                 newlist.addAll(table.gettablecards());

	                 for(int pair=2; pair<6;pair++){
	                   String pairstring = Integer.toString(pair);
	                   if(pair == 2){pairstring="Spades";}
	                   else if(pair==3){pairstring="Clubs";}
	                   else if(pair==4){pairstring="Diamonds";}
	                   else if(pair==5){pairstring="Hearts";}

	                 //reset paircount for next number
	                 int paircount =0;
	                 for(int h=0; h<newlist.size();h++){
	                 if (newlist.get(h).toLowerCase().indexOf(pairstring.toLowerCase()) != -1 ) {
	                   paircount++;
	                 if(paircount >=5){
	                      objMsgCh.sendMessage(players.get(q).getName()+" has a flush of "+ pairstring+"'s").queue();
	                      players.get(q).setflush();
	                    }

	                         }
	                 }

	                 }


	                 }
	             //return null;
	          }





	          public void straightflush(Table table, ArrayList<Player> players){
	                  for(int q=0; q<players.size();q++){
	                      //may need to clear arraylist at start??
	                      //combine hand and tablecards into new arraylist
	                      ArrayList<String> newlist = new ArrayList<String>();
	                      newlist.clear();
	                      newlist.addAll(table.gettablecards());
	                      newlist.addAll(players.get(q).returnhand());

	                      ArrayList<String> flushcards = new ArrayList<String>();
	                      int flush = 0;
	                      String pairstring ="ok";

	                          for(int pair=2; pair<6;pair++){

	                            if(pair == 2){pairstring="Spades";}
	                            else if(pair==3){pairstring="Clubs";}
	                            else if(pair==4){pairstring="Diamonds";}
	                            else if(pair==5){pairstring="Hearts";}
	                            //remake the array for each suit
	                             flushcards.clear();

	                          //reset paircount for next number
	                          int paircount =0;
	                                    for(int h=0; h<newlist.size();h++){
	                                    if (newlist.get(h).toLowerCase().indexOf(pairstring.toLowerCase()) != -1 ) {
	                                      paircount++;
	                                      flushcards.add(newlist.get(h));
	                                      if(paircount >=5){
	                                        flush = 1;
	                                                            int found=0;
	                                                  if(flush ==1 && h==newlist.size()-1){
	                                                  //  objMsgCh.sendMessage("FLUSH FOUND").queue();

	                                                            for(int m=2;m<14;m++ ){
	                                                            pairstring = Integer.toString(m);
	                                                            //sort the cards an check for 5 in a row
	                                                            if(m == 11){pairstring="Jack";}
	                                                            else if(m==12){pairstring="Queen";}
	                                                            else if(m==13){pairstring="King";}
	                                                            //else if(m==14){pairstring="Ace";}


	                                                            int check = found;
	                                                          //  objMsgCh.sendMessage("check is "+check);
	                                                            //objMsgCh.sendMessage("the flush cards are ").queue();
	                                                            //for(String obj:flushcards){
	                                                            //  objMsgCh.sendMessage(obj);}

	                                                                for(int k=0; k<flushcards.size();k++){

	                                                                    if (flushcards.get(k).toLowerCase().indexOf(pairstring.toLowerCase()) != -1 ){
	                                                                      found++;
	                                                                      //five continous cards found
	                                                                      // objMsgCh.sendMessage(players.get(q).getName()+" found = " +found);
	                                                                       //break so not to add more than once for one card

	                                                                       if(found>=5) {
	                                                                          objMsgCh.sendMessage(players.get(q).getName()+" has a straightflush of "+ pairstring+" high").queue();
	                                                                          players.get(q).setstraightflush(m);
	                                                                          if(pairstring=="Ace"){objMsgCh.sendMessage(players.get(q).getName()+" has a ROYAL FLUSH!").queue();
	                                                                          players.get(q).setroyalflush();
	                                                                        }
	                                                                     }
	                                                                       break;
	                                                                      }

	                                                                }
	                                                                //if found did not increase, then reset found
	                                                                if(found == check){found = 0;}

	                                                                //looking as if sorted??
	                                                            }

	                                                          }
	                                        //add all five or more cards to an arraylist
	                                           }  }   }

	                                    }


	                                  //  if(flush == 1 &&){
	                                      //check the order here
	                                  //       objMsgCh.sendMessage(players.get(q).getName()+" has a straightflush of "+ pairstring+" high").queue();
	                                    //   }

	                                    }
	                          }
	                      //return null;
	              // }


	              public void straight(Table table, ArrayList<Player> players){
	                  for(int q=0; q<players.size();q++){
	                      //may need to clear arraylist at start??
	                      //combine hand and tablecards into new arraylist
	                  ArrayList<String> newlist = new ArrayList<String>(players.get(q).returnhand());
	                  String pairstring ="ok";
	                      newlist.addAll(table.gettablecards());
	                      int found = 0;
	                      for(int m=2;m<15;m++ ){
	                      pairstring = Integer.toString(m);
	                      //sort the cards an check for 5 in a row
	                      if(m == 11){pairstring="Jack";}
	                      else if(m==12){pairstring="Queen";}
	                      else if(m==13){pairstring="King";}
	                      else if(m==14){pairstring="Ace";}


	                      int check = found;
	                    //  objMsgCh.sendMessage("check is "+check);
	                      //objMsgCh.sendMessage("the flush cards are ").queue();
	                      //for(String obj:flushcards){
	                      //  objMsgCh.sendMessage(obj);}

	                          for(int k=0; k<newlist.size();k++){

	                              if (newlist.get(k).toLowerCase().indexOf(pairstring.toLowerCase()) != -1 ){
	                                found++;
	                                //five continous cards found
	                                // objMsgCh.sendMessage(players.get(q).getName()+" found = " +found);
	                                 //break so not to add more than once for one card

	                                 if(found>=5) {
	                                    objMsgCh.sendMessage(players.get(q).getName()+" has a straight of "+ pairstring+" high").queue();
	                                    players.get(q).setstraight(m);

	                               }
	                                 break;
	                                }

	                          }
	                          //if found did not increase, then reset found
	                          if(found == check){found = 0;}

	                          //looking as if sorted??
	                      }



	                      }
	                  //return null;
	               }

	               public void highcard(Table table, ArrayList<Player> players){
	                  String newhighcard = "no card found";
	                  String newlowhighcard = "no card found";
	                   int position=0;
	                   int positionA=0;
	                   int positionB=0;
	                   int positionC=0;
	                   int positionD=0;
	                   int positionE=0;

	                   for(int q=0; q<players.size();q++){
	                     position=0;
	                       //may need to clear arraylist at start??
	                       //combine hand and tablecards into new arraylist
	                   ArrayList<String> newlist = new ArrayList<String>(players.get(q).returnhand());
	                   String pairstring ="ok";
	                       newlist.addAll(table.gettablecards());
	                       int found = 0;
	                       for(int m=2;m<15;m++ ){
	                       pairstring = Integer.toString(m);
	                       //sort the cards an check for 5 in a row
	                       if(m == 11){pairstring="Jack";}
	                       else if(m==12){pairstring="Queen";}
	                       else if(m==13){pairstring="King";}
	                       else if(m==14){pairstring="Ace";}

	                       //store the position of the highcard

	                         for(int k=0; k<newlist.size();k++){
	                               if (newlist.get(k).toLowerCase().indexOf(pairstring.toLowerCase()) != -1 ){
	                                newhighcard = pairstring;
	                                players.get(q).sethighcard(m);
	                                position=k;

	                               }


	                             }

	                               }


	                               for(int m=2;m<15;m++ ){
	                               pairstring = Integer.toString(m);
	                               //sort the cards an check for 5 in a row
	                               if(m == 11){pairstring="Jack";}
	                               else if(m==12){pairstring="Queen";}
	                               else if(m==13){pairstring="King";}
	                               else if(m==14){pairstring="Ace";}
	                               for(int k=0; k<newlist.size();k++){

	                                     //findlwohighcard
	                                     if (newlist.get(k).toLowerCase().indexOf(pairstring.toLowerCase()) != -1 && k != position){
	                                      newlowhighcard = pairstring;
	                                      players.get(q).setlowhighcard(m);
	                                      positionB=k;

	                                     }

	                                   }
	                            }

	                            for(int m=2;m<15;m++ ){
	                            pairstring = Integer.toString(m);
	                            //sort the cards an check for 5 in a row
	                            if(m == 11){pairstring="Jack";}
	                            else if(m==12){pairstring="Queen";}
	                            else if(m==13){pairstring="King";}
	                            else if(m==14){pairstring="Ace";}
	                            for(int k=0; k<newlist.size();k++){

	                                  //findlwohighcard
	                                  if (newlist.get(k).toLowerCase().indexOf(pairstring.toLowerCase()) != -1 && k != positionB && k != position){
	                                  // newlowhighcard = pairstring;
	                                   players.get(q).setlowhighcardA(m);
	                                   positionC=k;

	                                  }

	                                }
	                         }

	                         for(int m=2;m<15;m++ ){
	                         pairstring = Integer.toString(m);
	                         //sort the cards an check for 5 in a row
	                         if(m == 11){pairstring="Jack";}
	                         else if(m==12){pairstring="Queen";}
	                         else if(m==13){pairstring="King";}
	                         else if(m==14){pairstring="Ace";}
	                         for(int k=0; k<newlist.size();k++){

	                               //findlwohighcard
	                               if (newlist.get(k).toLowerCase().indexOf(pairstring.toLowerCase()) != -1 && k != positionC && k != positionB && k != position){
	                              //  newlowhighcard = pairstring;
	                                players.get(q).setlowhighcardB(m);
	                                positionD=k;

	                               }

	                             }
	                      }

	                      for(int m=2;m<15;m++ ){
	                      pairstring = Integer.toString(m);
	                      //sort the cards an check for 5 in a row
	                      if(m == 11){pairstring="Jack";}
	                      else if(m==12){pairstring="Queen";}
	                      else if(m==13){pairstring="King";}
	                      else if(m==14){pairstring="Ace";}
	                      for(int k=0; k<newlist.size();k++){

	                            //findlwohighcard
	                            if (newlist.get(k).toLowerCase().indexOf(pairstring.toLowerCase()) != -1 && k != positionD && k != positionC && k != positionB && k != position){
	                            // newlowhighcard = pairstring;
	                             players.get(q).setlowhighcardC(m);
	                             positionE=k;

	                            }

	                          }
	                   }

	                      objMsgCh.sendMessage(players.get(q).getName()+"'s high card is "+ newhighcard);
	                      objMsgCh.sendMessage(players.get(q).getName()+"'s lowhigh card is "+ newlowhighcard);




	                               }
	                           //return null;
	                        }









	          //this method resets all players quantities
	          public void resetplayersandtable(ArrayList<Player> players, Table table){
	            for(int b=0;b<players.size();b++){


	              players.get(b).clearhand();
	              table.cleartablecards();

	              players.get(b).clearflush();
	              players.get(b).clearhighbet();
	              players.get(b).clearlowpair();
	              players.get(b).clearhighpair();
	              players.get(b).clearroyalflush();
	              players.get(b).clearfourofakind();
	              players.get(b).clearstraightflush();
	              players.get(b).clearfullhouse();
	              players.get(b).clearthreeofakind();
	              players.get(b).clearstraight();
	              players.get(b).clearhighcard();
	              players.get(b).cleartotalbet();
	              players.get(b).clearfold();
	              players.get(b).clearrecentbet();
	              players.get(b).clearallin();
	              players.get(b).clearlowhighcard();
	              players.get(b).clearlowhighcardA();
	              players.get(b).clearlowhighcardB();
	              players.get(b).clearlowhighcardC();



	              table.clearbetcycle();
	              table.clearbettinground();


	            }

	          }



	          public void winnerselection(ArrayList<Player> players, Table table){



	            int l;
	            int d;
	            int straightflushcount =0;
	            int fourofakindcount = 0;
	            int fullhousecount =0;
	            int flushcount =0;
	            int threeofakindcount =0;
	            int paircount =0;
	            int straightcount=0;


	                ArrayList<Player> narrowedlist = new ArrayList<Player>();
	                int reward = 0;
	                int royalflushcount =0;
	                int winner=0;
	                //look for royal flush
	                  for(d=0;d<players.size();d++){
	                    if(players.get(d).getroyalflush() == 1){
	                      royalflushcount++;
	                      winner=d;
	                      narrowedlist.add(players.get(d));
	                    }
	                  }

	                  //if only 1 royal flush
	                  //should add potchips to him and remove them from table
	                  if(royalflushcount==1){
	                    objMsgCh.sendMessage(players.get(winner)+" has won with a ROYAL FLUSH!").queue();
	                    players.get(winner).addchips(table.getpot());
	                }

	                  //if more than 1 player has a royal flush
	                  else if(royalflushcount > 1){
	                  objMsgCh.sendMessage("The players below ALL have ROYAL FLUSHES! Therefore the pot will be split between these players:\n").queue();
	                  for(int p=0;p<narrowedlist.size();p++){
	                    reward = (table.getpot())/narrowedlist.size();
	                    narrowedlist.get(p).addchips(reward);
	                    objMsgCh.sendMessage(narrowedlist.get(p).getName() +" was rewarded "+ reward+ " chips").queue();
	                  }
	                  }



	if(royalflushcount==0){

	                  ArrayList<Player> narrowedlistA = new ArrayList<Player>();
	                   reward = 0;
	                  straightflushcount =0;
	                   winner=0;
	                   //hold value of highest straight flush
	                   int highsf =0;

	                   int changes =0;
	                   int winnerA =0;
	                   int maxstraightflush =0;
	                   int winnerK =0;
	                   int countL =0;
	                  //look for straight  flush
	                    for(d=0;d<players.size();d++){
	                      if(players.get(d).getstraightflush() > 1){
	                        straightflushcount++;
	                        winner=d;
	                      }
	                                                                                            /*
	                                                                                                      //if we dont get the same high value more than once than we can just use the winnerA
	                                                                                                    if(players.get(d).getstraightflush() == highsf){
	                                                                                                      changes = 1;
	                                                                                                      narrowedlistA.add(players.get(d));
	                                                                                                    }

	                                                                                                      if(players.get(d).getstraightflush() > highsf){
	                                                                                                        //if there is one greater than we clear all others
	                                                                                                          narrowedlistA.clear();
	                                                                                                            highsf = players.get(d).getstraightflush();
	                                                                                                            winnerA = d;
	                                                                                                            narrowedlistA.add(players.get(d));
	                                                                                                    }
	                                                                                                    */
	                      //corrected here
	                      if(players.get(d).getstraightflush() > maxstraightflush){
	                        maxstraightflush=players.get(d).getstraightflush();
	                        winnerK=d;
	                      }

	                    }


	                    for(d=0;d<players.size();d++){
	                      if(players.get(d).getstraightflush() == players.get(winnerK).getstraightflush()){
	                        countL++;
	                        narrowedlistA.add(players.get(d));
	                      }

	                    }



	                  //if only 1 player has a straight flush & thats the highest
	                  if(straightflushcount == 1){
	                    objMsgCh.sendMessage(players.get(winner).getName()+" has won with a STRAIGHT FLUSH!").queue();
	                    players.get(winner).addchips(table.getpot());

	                  }

	                  //need to make sure the flushes arent

	                  //case that there are multiple straight FLUSHES
	                  //use high straightflush.. if equal split pot
	                  else if(straightflushcount > 1 && countL==1){
	                    objMsgCh.sendMessage(players.get(winnerA).getName()+" has won with a STRAIGHT FLUSH that is higher than the other straight flushes.").queue();
	                    players.get(winnerK).addchips(table.getpot());
	                  }

	                  //case that there are multiple straight flushes that are all equal
	                  else if(straightflushcount > 1 && countL>1){

	                            objMsgCh.sendMessage("The players below ALL have equivalent STRAIGHT FLUSHES! Therefore the pot will be split between these players:\n").queue();
	                            for(int p=0;p<narrowedlistA.size();p++){
	                              reward = (table.getpot())/narrowedlistA.size();
	                              narrowedlistA.get(p).addchips(reward);
	                              objMsgCh.sendMessage(narrowedlistA.get(p).getName() +" was rewarded "+ reward+ " chips").queue();
	                            }
	                  }



	}



	if(royalflushcount==0 && straightflushcount==0){

	                  ArrayList<Player> narrowedlistC = new ArrayList<Player>();
	                   reward = 0;
	                  fourofakindcount =0;
	                   winner=0;
	                   //hold value of highest four of a kind
	                  int highsf =1;

	                  int  changes =0;
	                  int  winnerA =0;
	                  int countk=0;
	                  int countl=0;
	                  int highcardmaxZ=0;
	                  //look for four of a kind
	                    for(d=0;d<players.size();d++){
	                      if(players.get(d).getfourofakind() >= highsf){
	                        fourofakindcount++;
	                        winner=d;
	                        highsf=players.get(d).getfourofakind();
	                      }

	                        //if we dont get the same high value more than once than we can just use the winnerA
	                    //  if(players.get(d).getfourofakind() == highsf){
	                      //  changes = 1;
	                  //      narrowedlistB.add(players.get(d));
	                    //  }

	                    //    if(players.get(d).getfourofakind() > highsf){
	                          //if there is one greater than we clear all others
	                        //    narrowedlistB.clear();
	                          //    highsf = players.get(d).getfourofakind();
	                        //      winnerA = d;
	                          //    narrowedlistB.add(players.get(d));

	                    //  }
	                    //  }
	                    }

	                    for(d=0;d<players.size();d++){
	                      if(players.get(d).gethighcard() >= highcardmaxZ && players.get(d).getfourofakind() == highsf){
	                        highcardmaxZ=players.get(d).gethighcard();
	                        winnerA=d;

	                    }}

	                    //check for repetition
	                    for(d=0;d<players.size();d++){
	                      if(players.get(d).getfourofakind() == highsf){
	                        countk++;
	                      }
	                      if(players.get(d).gethighcard() == highcardmaxZ && players.get(d).getfourofakind() == highsf){
	                        countl++;
	                        narrowedlistC.add(players.get(d));
	                    }
	                  }





	                  //1 player has four of a kind
	                  if(fourofakindcount==1){
	                    objMsgCh.sendMessage(players.get(winner).getName()+" has won with FOUR OF A KIND!").queue();
	                    players.get(winner).addchips(table.getpot());
	                  }

	                  //Multiple players have four of a kind
	                  else if(fourofakindcount > 1 && countk == 1){
	                    objMsgCh.sendMessage(players.get(winnerA).getName()+" has won with a FOUR OF A KIND that is higher than the other FOUR OF A KINDS.").queue();
	                    players.get(winnerA).addchips(table.getpot());

	                  }

	                  else if(countl > 1 && fourofakindcount >1){
	                    objMsgCh.sendMessage("These players have the exact same FOUR OF A KIND and HIGH CARDS. THE POT WILL BE SPLIT").queue();
	                    for(l=0;l<narrowedlistC.size();l++){
	                      reward = table.getpot()/narrowedlistC.size();
	                      objMsgCh.sendMessage(narrowedlist.get(l).getName()+" has been awarded "+reward+" chips.").queue();
	                      narrowedlistC.get(l).addchips(reward);
	                  }}

	/*
	                  //Players have equivalent four of a kinds
	                  //use kicker to determine winner
	                  else if(fourofakindcount > 1 && changes==1){
	                                //find the player with the best high cards
	                                int maxhighcard =0;
	                                int maxhighcardB =0;
	                                int checkz =0;
	                                int checkzA =0;
	                                winnerA=0;
	                                ArrayList<Player> narrowedlistC = new ArrayList<Player>();
	                                winner=0;
	                                int k=0;
	                                for(d=0;d<narrowedlistB.size();d++){

	                                  if (narrowedlistB.get(d).gethighcard() > maxhighcard){
	                                    maxhighcard=narrowedlistB.get(d).gethighcard();
	                                    winner = d;
	                                  }

	                                }

	                                for(d=0;d<narrowedlistB.size();d++){
	                                  if (narrowedlistB.get(d).getlowhighcard() > maxhighcardB && narrowedlistB.get(d).gethighcard() == maxhighcard)){
	                                    maxhighcardB=narrowedlistB.get(d).getlowhighcard();
	                                    winnerA = d;
	                                  }
	                                }





	                                //check for repeated high cards
	                                for(d=0;d<narrowedlistB.size();d++){

	                                  if (narrowedlistB.get(d).gethighcard() == maxhighcard){
	                                    checkz++;
	                                  }
	                                  if (narrowedlistB.get(d).getlowhighcard() == maxhighcardB){
	                                    checkzA++;
	                                    narrowedlistC.add(narrowedlistB.get(d));
	                                  }
	                                }
	                                //ccase that the  highcard is NOT repeated
	                              if(checkz==1){
	                                objMsgCh.sendMessage(narrowedlistB.get(winner).getName() + " has won with a FOUR OF A KIND and a HIGH CARD.").queue();
	                                narrowedlistB.get(winner).addchips(table.getpot());
	                              }
	                              //case that highcards are the same so the lowhighcard is used
	                              //this case the low high card is not repeated

	                              //THIS WAS EDITED 12/20/2017 BECAUSE ONLY FIVE CARDS CAN BE USED
	                        //      else if(checkzA==1){
	                        //        objMsgCh.sendMessage(narrowedlistB.get(winnerA).getName() + " has won using a FOUR OF A KIND, a HIGH CARD and a 2nd HIGH CARD .").queue();
	                        //        narrowedlistB.get(winner).addchips(table.getpot());

	                          //    }
	                              //SAME EXACT HAND
	                              else if(checkz >1){
	                                objMsgCh.sendMessage("These players have the exact same FOUR OF A KIND and HIGH CARDS. THE POT WILL BE SPLIT").queue();
	                                for(l=0;l<narrowedlistC.size();l++){
	                                  reward = table.getpot()/narrowedlistC.size();
	                                  objMsgCh.sendMessage(narrowedlist.get(l).getName()+" has been awarded "+reward+" chips.").queue();
	                                  narrowedlistC.get(l).addchips(reward);
	                                }

	                              }




	                              }
	*/
	}

	if(royalflushcount==0 && fourofakindcount==0 && straightflushcount==0){

	                        fullhousecount =0;
	                        winner=0;
	                        int counta =0;
	                        int countj =0;
	                        int winnerB=0;
	                        int countD=0;
	                        int countq=0;
	                        ArrayList<Player> narrowedlistC = new ArrayList<Player>();

	                        for(d=0; d<players.size();d++){
	                          if(players.get(d).returnfullhouse() > 0){
	                            fullhousecount++;
	                            if(players.get(d).returnfullhouse() > players.get(winner).returnfullhouse() ){
	                            winner=d;

	                          }
	                          }
	                          //searches for highest pair
	                        //  if(players.get(d).gethighpair() > 0){
	                          //  countj++;
	                          //  if(players.get(d).gethighpair() > players.get(winnerB).gethighpair() ){}
	                          //  winnerB=d;
	                          //  countj++;
	                        //  }
	                          }

	                          for(d=0; d<players.size();d++){
	                            if(players.get(d).gethighpair() > players.get(winnerB).gethighpair() && players.get(d).returnfullhouse() == players.get(winner).returnfullhouse() ){
	                            winnerB=d;
	                            countj++;
	                          }
	                            }




	                        //check if the high value is repeated
	                        for(d=0; d<players.size();d++){
	                          if(players.get(d).returnfullhouse() == players.get(winner).returnfullhouse()){
	                            counta++;

	                          }
	                          //check for repeated highpair
	                          if(players.get(d).gethighpair() == players.get(winnerB).gethighpair()){
	                            countD++;
	                          }
	                          //if both pair an trio are the same as the max then add to array
	                          if(players.get(d).gethighpair() == players.get(winnerB).gethighpair() && players.get(d).returnfullhouse() == players.get(winner).returnfullhouse()){
	                            countq++;
	                            narrowedlistC.add(players.get(d));
	                          }
	                        }


	                        //FULLHOUSE & only one
	                        if(fullhousecount ==1){
	                          objMsgCh.sendMessage(players.get(winner).getName()+" has won with a FULLHOUSE!").queue();
	                          players.get(winner).addchips(table.getpot());
	                        }

	                        // multiple fullhouses but one higher trio
	                        else if(counta==1 && fullhousecount>1){
	                            objMsgCh.sendMessage(players.get(winner).getName()+" has won with a FULLHOUSE! (higher three of a kind)").queue();
	                            players.get(winner).addchips(table.getpot());
	                        }

	                        // multiple fullhouses but one higher pair
	                        else if(countD==1 && countj>1 && players.get(winnerB).returnfullhouse() == players.get(winner).returnfullhouse() && fullhousecount>1){
	                          objMsgCh.sendMessage(players.get(winnerB).getName()+" has won with a FULLHOUSE! (higher pair)").queue();
	                          players.get(winnerB).addchips(table.getpot());
	                        }

	                        // exact same fullhouses
	                        else if(countq > 1 && fullhousecount>1){
	                          objMsgCh.sendMessage("The following players have identical FULLHOUSES, THE POT WILL BE SPLIT BETWEEN THEM!").queue();
	                          for(int p=0;p<narrowedlistC.size();p++){

	                            reward = table.getpot()/narrowedlistC.size();
	                            objMsgCh.sendMessage(narrowedlistC.get(p).getName()+" has been awarded "+reward+" chip.").queue();
	                            narrowedlistC.get(p).addchips(reward);
	                          }


	                        }

	}

	if(royalflushcount==0 && fourofakindcount==0 && fullhousecount ==0 && straightflushcount==0){
	                          //SEARCHING FOR FLUSHES AND DETERMING winner
	                          flushcount=0;
	                          int flushmax=0;
	                          int highcard=0;
	                          int highcardA=0;
	                          int highcardB=0;
	                          int highcardC=0;
	                          int highcardD=0;
	                          int winnerA=0;
	                          int winnerB=0;
	                          int winnerC=0;
	                          int winnerD=0;
	                          int winnerE=0;
	                          int counta=0;
	                          int countb=0;
	                          int countc=0;
	                          int countd=0;
	                          int counte=0;
	                          reward=0;
	                          ArrayList<Player> narrowedlistD = new ArrayList<Player>();

	                          for(int k=0; k<players.size();k++){
	                            if(players.get(k).returnflush() ==1){
	                              flushcount++;
	                              winner = k;

	                              //find max of each card position

	                              }
	                            }
	                            //find max of each card position
	                        //    if(players.get(k).gethighcard() > highcard && players.get(k).returnflush()==1){
	                          //    highcard = players.get(k).gethighcard();
	                          //    winnerA=k;
	                          //  }
	                          /*
	                            if(players.get(k).getlowhighcard() > highcardA && players.get(k).returnflush()==1 && players.get(k).gethighcard() == highcard){
	                              highcardA = players.get(k).getlowhighcard();
	                              winnerB=k;
	                            }
	                            if(players.get(k).getlowhighcardA() > highcardB && players.get(k).returnflush()==1 && players.get(k).gethighcard() == highcard && players.get(k).getlowhighcard()==highcardA){
	                              highcardB = players.get(k).getlowhighcardA();
	                              winnerC=k;
	                            }
	                            if(players.get(k).getlowhighcardB() > highcardC && players.get(k).returnflush()==1 && players.get(k).gethighcard() == highcard && players.get(k).getlowhighcard()==highcardA && players.get(k).getlowhighcardA() == highcardB){
	                              highcardC = players.get(k).getlowhighcardB();
	                              winnerD=k;
	                            }
	                            if(players.get(k).getlowhighcardC() > highcardD && players.get(k).returnflush()==1 && players.get(k).gethighcard() == highcard && players.get(k).getlowhighcard()==highcardA && players.get(k).getlowhighcardA() == highcardB && players.get(k).getlowhighcardB() == highcardC){
	                              highcardD = players.get(k).getlowhighcardC();
	                              winnerE=k;
	                            }
	                            */



	                          for(int k=0; k<players.size();k++){
	                            if(players.get(k).gethighcard() > highcard && players.get(k).returnflush()==1){
	                              highcard = players.get(k).gethighcard();
	                              winnerA=k;
	                            }
	                          }
	                          for(int k=0; k<players.size();k++){
	                            if(players.get(k).getlowhighcard() > highcardA && players.get(k).returnflush()==1 && players.get(k).gethighcard() == highcard){
	                              highcardA = players.get(k).getlowhighcard();
	                              winnerB=k;
	                            }
	                          }
	                          for(int k=0; k<players.size();k++){
	                            if(players.get(k).getlowhighcardA() > highcardB && players.get(k).returnflush()==1 && players.get(k).gethighcard() == highcard && players.get(k).getlowhighcard()==highcardA){
	                              highcardB = players.get(k).getlowhighcardA();
	                              winnerC=k;
	                            }
	                          }
	                          for(int k=0; k<players.size();k++){
	                            if(players.get(k).getlowhighcardB() > highcardC && players.get(k).returnflush()==1 && players.get(k).gethighcard() == highcard && players.get(k).getlowhighcard()==highcardA && players.get(k).getlowhighcardA() == highcardB){
	                              highcardC = players.get(k).getlowhighcardB();
	                              winnerD=k;
	                            }
	                          }
	                          for(int k=0; k<players.size();k++){
	                            if(players.get(k).getlowhighcardC() > highcardD && players.get(k).returnflush()==1 && players.get(k).gethighcard() == highcard && players.get(k).getlowhighcard()==highcardA && players.get(k).getlowhighcardA() == highcardB && players.get(k).getlowhighcardB() == highcardC){
	                              highcardD = players.get(k).getlowhighcardC();
	                              winnerE=k;
	                            }
	                          }


	                          //check if the high value is repeated
	                          for(d=0; d<players.size();d++){
	                            if(players.get(d).gethighcard() == players.get(winnerA).gethighcard()){
	                              counta++;

	                            }
	                            //check for repeated lowhigh
	                            if(players.get(d).getlowhighcard() == players.get(winnerB).getlowhighcard()){
	                              countb++;
	                            }
	                            if(players.get(d).getlowhighcardA() == players.get(winnerC).getlowhighcardA()){
	                              countc++;
	                            }
	                            if(players.get(d).getlowhighcardB() == players.get(winnerD).getlowhighcardB()){
	                              countd++;
	                            }
	                            //all cards are equal
	                            if(players.get(d).getlowhighcardC() == players.get(winnerE).getlowhighcardC()){
	                              counte++;
	                              narrowedlistD.add(players.get(d));
	                            }

	                          }







	                          //THERE IS ONLY ONE FLUSH
	                          if(flushcount == 1){
	                            objMsgCh.sendMessage(players.get(winner).getName()+ " has won with a FLUSH!").queue();
	                            players.get(winner).addchips(table.getpot());
	                          }
	                          //multiple flush highcard1
	                        else if(flushcount > 1 && counta==1 ){
	                            objMsgCh.sendMessage(players.get(winnerA).getName()+ " has won with a FLUSH! (highcard)").queue();
	                            players.get(winnerA).addchips(table.getpot());
	                          }
	                          //multiple flush highcard2
	                        else if(flushcount > 1 && countb==1 && players.get(winnerB).gethighcard() ==players.get(winnerA).gethighcard()){
	                            objMsgCh.sendMessage(players.get(winnerB).getName()+ " has won with a FLUSH!").queue();
	                            players.get(winnerB).addchips(table.getpot());
	                          }
	                          //multiple flush highcard3
	                        else if(flushcount > 1 && countc==1 && players.get(winnerC).gethighcard() ==players.get(winnerA).gethighcard() && players.get(winnerC).getlowhighcard() == players.get(winnerB).getlowhighcard()){
	                            objMsgCh.sendMessage(players.get(winnerC).getName()+ " has won with a FLUSH!").queue();
	                            players.get(winnerC).addchips(table.getpot());
	                          }
	                          //multiple flush highcard4
	                        else if(flushcount > 1 && countd==1 && players.get(winnerD).gethighcard() ==players.get(winnerA).gethighcard() && players.get(winnerD).getlowhighcard() == players.get(winnerB).getlowhighcard() && players.get(winnerD).getlowhighcardA() == players.get(winnerC).getlowhighcardA()){
	                            objMsgCh.sendMessage(players.get(winnerD).getName()+ " has won with a FLUSH!").queue();
	                            players.get(winnerD).addchips(table.getpot());
	                          }
	                          //multiple flush highcard5
	                          else if(flushcount > 1 && counte==1 && players.get(winnerE).gethighcard() ==players.get(winnerA).gethighcard() && players.get(winnerE).getlowhighcard() == players.get(winnerB).getlowhighcard() && players.get(winnerE).getlowhighcardA() == players.get(winnerC).getlowhighcardA() && players.get(winnerE).getlowhighcardB() == players.get(winnerD).getlowhighcardB()){
	                            objMsgCh.sendMessage(players.get(winnerE)+ " has won with a FLUSH!").queue();
	                            players.get(winnerE).addchips(table.getpot());
	                          }
	                          //EXACT SAME FLUSH HAND
	                          else if(flushcount>1 && counte>1){
	                            objMsgCh.sendMessage("The following players have identical FLUSHES, THE POT WILL BE SPLIT BETWEEN THEM!").queue();
	                            for(int p=0;p<narrowedlistD.size();p++){
	                              reward = table.getpot()/narrowedlistD.size();
	                              objMsgCh.sendMessage(narrowedlistD.get(p).getName()+" has been awarded "+reward+" chips.").queue();
	                              narrowedlistD.get(p).addchips(reward);

	                          }}

	}
	if(royalflushcount==0 && fourofakindcount==0 && fullhousecount ==0 && straightflushcount==0 && flushcount==0){


	  ArrayList<Player> narrowedlistX = new ArrayList<Player>();
	   reward = 0;
	   straightcount =0;
	   winner=0;
	   //hold value of highest straight
	   int highsf =0;

	  
	   int winnerA =0;
	   int maxstraight =0;
	   int winnerK =0;
	   int countL =0;
	  //look for straight  flush
	    for(d=0;d<players.size();d++){
	      if(players.get(d).getstraight() > 1){
	        straightcount++;
	        winner=d;
	      }

	      //corrected here
	      if(players.get(d).getstraightflush() > maxstraight){
	        maxstraight=players.get(d).getstraight();
	        winnerK=d;
	      }

	    }


	    for(d=0;d<players.size();d++){
	      if(players.get(d).getstraight() == players.get(winnerK).getstraight()){
	        countL++;
	        narrowedlistX.add(players.get(d));
	      }

	    }



	  //if only 1 player has a straight & thats the highest
	  if(straightcount == 1){
	    objMsgCh.sendMessage(players.get(winner).getName()+" has won with a STRAIGHT!").queue();
	    players.get(winner).addchips(table.getpot());

	  }

	  //need to make sure the flushes arent

	  //case that there are multiple straight FLUSHES
	  //use high straightflush.. if equal split pot
	  else if(straightcount > 1 && countL==1){
	    objMsgCh.sendMessage(players.get(winnerA).getName()+" has won with a STRAIGHT that is higher than the other straights.").queue();
	    players.get(winnerK).addchips(table.getpot());
	  }

	  //case that there are multiple straight flushes that are all equal
	  else if(straightcount > 1 && countL>1){

	            objMsgCh.sendMessage("The players below ALL have equivalent STRAIGHTS! Therefore the pot will be split between these players:\n").queue();
	            for(int p=0;p<narrowedlistX.size();p++){
	              reward = (table.getpot())/narrowedlistX.size();
	              narrowedlistX.get(p).addchips(reward);
	              objMsgCh.sendMessage(narrowedlistX.get(p).getName() +" was rewarded "+ reward+ " chips").queue();
	            }
	  }











	}





	if(royalflushcount==0 && fourofakindcount==0 && fullhousecount ==0 && flushcount ==0 && straightflushcount==0 && straightcount==0){
	                                      ///checking for three of a kind
	                                      threeofakindcount=0;
	                                      int threeofakindmax=0;
	                                      int highcard=0;
	                                      int highcardA=0;
	                                      int highcardB=0;
	                                      int highcardC=0;
	                                      int highcardD=0;
	                                      int winnerA=0;
	                                      int winnerB=0;
	                                      int winnerC=0;
	                                      int winnerD=0;
	                                      int winnerE=0;
	                                      int counta=0;
	                                      int countb=0;
	                                      int countc=0;
	                                      int countd=0;
	                                      int counte=0;
	                                      int lowhighcard =0;
	                                      int countJ =0;
	                                      reward=0;
	                                      ArrayList<Player> narrowedlistJ = new ArrayList<Player>();
	                                      for(int k=0; k<players.size();k++){
	                                        //max threeofakind
	                                        if(players.get(k).getthreeofakind() >1){
	                                          threeofakindcount++;
	                                          winner = k;
	                                        }

	                                      }


	                                      //check if the high value is repeated
	                                      for(d=0; d<players.size();d++){
	                                        if(players.get(d).getthreeofakind() == players.get(winner).getthreeofakind()){
	                                          counta++;

	                                        }
	                                        //find max higcard
	                                        if(players.get(d).gethighcard() > highcard && players.get(d).getthreeofakind() == players.get(winner).getthreeofakind()){
	                                          highcard = players.get(d).gethighcard();
	                                          winnerA=d;
	                                        }

	                                      }

	                                      for(d=0; d<players.size();d++){
	                                        //find maxlowhigh higcard
	                                        if(players.get(d).getlowhighcard() > lowhighcard && players.get(d).getthreeofakind() == players.get(winner).getthreeofakind() && players.get(d).gethighcard() == highcard){
	                                          lowhighcard = players.get(d).getlowhighcard();
	                                          winnerB=d;
	                                        }

	                                        //check for repeated highcard
	                                        if(players.get(d).gethighcard() == players.get(winnerA).gethighpair()){
	                                          countb++;
	                                        }

	                                      }

	                                      for(d=0; d<players.size();d++){
	                                        //check for repeated lowhigh cards
	                                        if(players.get(d).getlowhighcard() == lowhighcard && players.get(d).getthreeofakind() == players.get(winner).getthreeofakind() && players.get(d).gethighcard() == highcard){
	                                          narrowedlistJ.add(players.get(d));
	                                          countJ++;
	                                        }

	                                      }


	                              //There is only one max three of a kind
	                              if(counta == 1 && threeofakindcount == 1){
	                                objMsgCh.sendMessage(players.get(winner).getName()+ " has won with a THREE OF A KIND!").queue();
	                                players.get(winner).addchips(table.getpot());
	                              }
	                              //highcard tie breaker
	                              else if(counta > 1 && countb == 1 && threeofakindcount > 1 && players.get(winnerA).getthreeofakind()==players.get(winner).getthreeofakind()){
	                                objMsgCh.sendMessage(players.get(winnerA).getName()+ " has won with a THREE OF A KIND! (using a high card)").queue();
	                                players.get(winnerA).addchips(table.getpot());
	                              }
	                              //case that a lowhighard breaks the tie
	                              else if(counta > 1 && countb >1 && countJ==1 && threeofakindcount > 1 && players.get(winnerB).getthreeofakind()==players.get(winner).getthreeofakind() && players.get(winnerB).gethighcard()==players.get(winnerA).gethighcard()){
	                                objMsgCh.sendMessage(players.get(winnerA).getName()+ " has won with a THREE OF A KIND! (using a high card)").queue();
	                                players.get(winnerB).addchips(table.getpot());
	                              }

	                              //same hand
	                              else if(flushcount>1 && counte>1){
	                                objMsgCh.sendMessage("The following players have identical THREE OF A KIND hands, THE POT WILL BE SPLIT BETWEEN THEM!").queue();
	                                for(int p=0;p<narrowedlistJ.size();p++){
	                                  reward = table.getpot()/narrowedlistJ.size();
	                                  objMsgCh.sendMessage(narrowedlistJ.get(p).getName()+" has been awarded "+reward+" chips.").queue();
	                                  narrowedlistJ.get(p).addchips(reward);

	                              }
	                            }


	}

	if(royalflushcount==0 && fourofakindcount==0 && fullhousecount ==0 && flushcount ==0 && straightflushcount==0 && straightcount==0 && threeofakindcount == 0){

	                              //maxhighpair is 1 because if 0, paircount will add on
	                              int maxhighpair = 1;
	                              int maxlowpair =0;
	                              paircount =0;
	                              winner = 0;
	                              int winnerA=0;
	                              int maxhighpairB=0;
	                              //check for pair
	                              //find max values for pair, low pair, highcard,     THEN IF THERE is not a low pair

	                              int maxlowhigh=0;

	                              int maxhighcard =0;
	                              int maxlowhighA=0;
	                              int countsame =0;
	                              reward =0;
	                              ArrayList<Player> narrowedlistH = new ArrayList<Player>();
	                              //CAREFUL... SOMEONES LOWPAIR COULD BE HIGHER THAN SOMEONE ELSES LOW PAIR
	                              for(int x=0;x<players.size();x++){
	                                if(players.get(x).gethighpair() >= maxhighpair){
	                                  maxhighpair = players.get(x).gethighpair();
	                                  paircount++;
	                                //  winner =x; maybe the end winner will be the actual winner
	                                }
	                              }

	                              for(int x=0;x<players.size();x++){
	                                if(players.get(x).getlowpair() >= maxlowpair && players.get(x).gethighpair() == maxhighpair){
	                                  maxlowpair = players.get(x).getlowpair();
	                                }
	                              }

	                              for(int x=0;x<players.size();x++){
	                                if(players.get(x).gethighcard() >= maxhighcard && players.get(x).gethighpair() == maxhighpair && maxlowpair == players.get(x).getlowpair()){
	                                  maxhighcard = players.get(x).gethighcard();
	                                }
	                              }

	                              for(int x=0;x<players.size();x++){
	                                if(players.get(x).getlowhighcard() >= maxlowhigh && players.get(x).gethighcard() == maxhighcard && players.get(x).gethighpair() == maxhighpair && maxlowpair == players.get(x).getlowpair()){
	                                  maxlowhigh = players.get(x).getlowhighcard();
	                                }
	                              }
	                              for(int x=0;x<players.size();x++){
	                                if(players.get(x).getlowhighcardA() >= maxlowhighA && players.get(x).getlowhighcard() == maxlowhigh && players.get(x).gethighcard() == maxhighcard && players.get(x).gethighpair() == maxhighpair && maxlowpair == players.get(x).getlowpair()){
	                                  maxlowhighA = players.get(x).getlowhighcardA();
	                                  winner = x;
	                                  countsame ++;
	                                  narrowedlistH.add(players.get(x));
	                                }
	                              }

	                              //maxhighpair is 1 for a reason
	                              if(paircount >= 1 && countsame == 1){
	                                objMsgCh.sendMessage(players.get(winner).getName()+ " has won with a HIGH PAIR!").queue();
	                                players.get(winner).addchips(table.getpot());
	                              }

	                              else if(countsame>1 && paircount >=1){
	                                objMsgCh.sendMessage("The following players have identical PAIR hands, THE POT WILL BE SPLIT BETWEEN THEM!").queue();
	                                for(int p=0;p<narrowedlistH.size();p++){
	                                  reward = table.getpot()/narrowedlistH.size();
	                                  objMsgCh.sendMessage(narrowedlistH.get(p).getName()+" has been awarded "+reward+" chips.").queue();
	                                  narrowedlistH.get(p).addchips(reward);
	                              }
	                            }

	}


	if(royalflushcount==0 && fourofakindcount==0 && fullhousecount ==0 && flushcount ==0 && straightflushcount==0 && straightcount==0 && threeofakindcount == 0 && paircount ==0){
	      //determine winner with only highcards

	      ArrayList<Player> narrowedlistT = new ArrayList<Player>();
	      int highcard=1;
	      int highcardA=0;
	      winner = 0;
	      int count =0;
	      int k=0;

	      for(k=0; k<players.size();k++){

	          //find max of each card position
	          if(players.get(k).gethighcard() >= highcard){
	            highcard = players.get(k).gethighcard();
	                  }
	                }



	                for(k=0; k<players.size();k++){
	                  if(players.get(k).getlowhighcard() >= highcardA && players.get(k).gethighcard() == highcard){
	                    highcardA = players.get(k).getlowhighcard();
	                    winner=k;
	                  }
	                }


	                for(k=0; k<players.size();k++){
	                  if(players.get(k).getlowhighcard() == highcardA && players.get(k).gethighcard() == highcard){
	                    narrowedlistT.add(players.get(k));
	                    count++;
	                  }
	                }


	                //only need to do two because the array will be narrowed and 3 cards are shared

	                if(count == 1){
	                  objMsgCh.sendMessage(players.get(winner).getName()+ " has won with a HIGH CARD!").queue();
	                  players.get(winner).addchips(table.getpot());
	                }

	                else if(count > 1){
	                  objMsgCh.sendMessage("The following players have identical HIGH CARD hands, THE POT WILL BE SPLIT BETWEEN THEM!").queue();
	                  for(int p=0;p<narrowedlistT.size();p++){
	                    reward = table.getpot()/narrowedlistT.size();
	                    objMsgCh.sendMessage(narrowedlistT.get(p).getName()+" has been awarded "+reward+" chips.").queue();
	                    narrowedlistT.get(p).addchips(reward);
	                }

	                }














	}
	                  //clear the pot at the end
	                  table.clearpot();

	          }






















	          // clipped off this part so that it could be recursive rather than reusing the whole game start up
	                public void gamecycle(ArrayList<Player> players, Table table1, ArrayList<String> deck){

	                                        //add cards back to the deck
	                                        for(int w=0; w<players.size();w++){
	                                            deck.addAll(players.get(w).returnhand());
	                                            }
	                                        deck.addAll(table1.gettablecards());

	                                        resetplayersandtable(players, table1);
	                                        //betting round should start at 1
	                                        table1.setbettinground();


	                                        deck = shuffle(deck);
	                                        deck = shuffle(deck);
	                                        objMsgCh.sendMessage("The deck was just reset and shuffled thoroughly.").queue();

	                                        //the ante is x chips
	                                        //set fold is reset
	                                        //previous bets are cleared
	                                        for(int t=0; t<players.size();t++){
	                                        players.get(t).removechips(table1.getante());
	                                        players.get(t).setrecentbet(0);
	                                        players.get(t).clearfold();
	                                        table1.addpotchips(table1.getante());
	                                        }

	                                        //2 cards are dealt
	                                        for(int x=0; x<players.size();x++){
	                                          deal(players.get(x), deck);
	                                          deal(players.get(x), deck);
	                                          //players.get(x).setrecentbet(0);
	                                        }

	                                        ArrayList<Player> nonfoldplayers = new ArrayList<Player>();
	                                        ArrayList<Player> foldplayers = new ArrayList<Player>();
	                                        nonfoldplayers.clear();

	                                        nonfoldplayers.addAll(betting(players, table1, deck, foldplayers));
	                                        table1.addbettinground();

	                                        if (nonfoldplayers.size() == 1){
	                                                      //check if any players have zero chips and remove them
	                                                      for(int kk=0; kk<players.size();kk++){

	                                                        if(players.get(kk).getchips()==0){
	                                                          objMsgCh.sendMessage("\n"+players.get(kk).getName()+" has run out of chips and is therefore being removed from the table.\n Now.. if you'd kindly get the h*#! outta of here.. \n"+"I always knew people named "+players.get(kk).getName()+" had about three too many chromosomes.").queue();
	                                                          players.remove(players.get(kk));
	                                                        }
	                                                      }

	                                                      objMsgCh.sendMessage("\n\nA NEW ROUND IS STARTING SHORTLY, please make yourselves comfortable \nand remember to tell Adam how cool you think his virtual Poker lounge is.").queue();
	                                                      // 15 second delay and clear screen
	                                                      try {
	                                                      TimeUnit.SECONDS.sleep(6);
	                                                    }catch (InterruptedException e){
	                                                      //handle the exception
	                                                      objMsgCh.sendMessage("Please don't input while delaying").queue();
	                                                    }
	                                                    //  clearScreen();
	                                                    

	                                               // a player has won so start a new game
	                                                   gamecycle(players, table1, deck);
	                                                    }

	                                       //this is the case that you move on to laying 3 cards on the table
	                                       else{
	                                         dealtable(table1, deck);
	                                         dealtable(table1, deck);
	                                         dealtable(table1, deck);


	                                         ArrayList<Player> nonfoldplayersB = new ArrayList<Player>();

	                                         nonfoldplayersB.addAll(betting(nonfoldplayers, table1, deck, foldplayers));

	                                         //clearnonfold players
	                                         nonfoldplayers.clear();

	                                         table1.addbettinground();

	                                             //this could be called as a method to reduce space
	                                             if (nonfoldplayersB.size() == 1){
	                                                           //check if any players have zero chips and remove them
	                                                           for(int kk=0; kk<players.size();kk++){

	                                                             if(players.get(kk).getchips()==0){
	                                                               objMsgCh.sendMessage("\n"+players.get(kk).getName()+" has run out of chips and is therefore being removed from the table.\nNow.. if you'd kindly get the h*#! outta of here.. \n"+"I always knew people named "+players.get(kk).getName()+" had about three too many chromosomes.").queue();
	                                                               players.remove(players.get(kk));
	                                                             }
	                                                           }
	                                                           objMsgCh.sendMessage("\n\nA NEW ROUND IS STARTING SHORTLY, please make yourselves comfortable \nand remember to tell Adam how cool you think his virtual Poker lounge is.").queue();
	                                                           // 15 second delay and clear screen
	                                                           try {
	                                                           TimeUnit.SECONDS.sleep(6);
	                                                         }catch (InterruptedException e){
	                                                           //handle the exception
	                                                           objMsgCh.sendMessage("Please don't input while delaying").queue();
	                                                         }
	                                                         //  clearScreen();
	                                                         

	                                                    // a player has won so start a new game
	                                                      gamecycle(players, table1, deck);
	                                                         }
	                                                 //1 more card dealt and another round of betting
	                                                   else{
	                                                       dealtable(table1, deck);


	                                                      ArrayList<Player> nonfoldplayersC = new ArrayList<Player>();

	                                                       nonfoldplayersC.addAll(betting(nonfoldplayersB, table1, deck, foldplayers));

	                                                       nonfoldplayersB.clear();

	                                                       table1.addbettinground();

	                                                       if (nonfoldplayersC.size() == 1){
	                                                                     //check if any players have zero chips and remove them
	                                                                     for(int kk=0; kk<players.size();kk++){

	                                                                       if(players.get(kk).getchips()==0){
	                                                                         objMsgCh.sendMessage("\n"+players.get(kk).getName()+" has run out of chips and is therefore being removed from the table.\nNow.. if you'd kindly get the h*#! outta of here.. \n"+"I always knew people named "+players.get(kk).getName()+" had about three too many chromosomes.").queue();
	                                                                         players.remove(players.get(kk));
	                                                                       }
	                                                                     }
	                                                                     objMsgCh.sendMessage("\n\nA NEW ROUND IS STARTING SHORTLY, please make yourselves comfortable \nand remember to tell Adam how cool you think his virtual Poker lounge is.").queue();
	                                                                     // 15 second delay and clear screen
	                                                                     try {
	                                                                     TimeUnit.SECONDS.sleep(6);
	                                                                   }catch (InterruptedException e){
	                                                                     //handle the exception
	                                                                     objMsgCh.sendMessage("Please don't input while delaying").queue();
	                                                                   }
	                                                                   //  clearScreen();
	                                                                   

	                                                              // a player has won so start a new game
	                                                                gamecycle(players, table1, deck);
	                                                                   }

	                                                                   else{
	                                                                     //last card delt to the table
	                                                                       dealtable(table1, deck);

	                                                                       ArrayList<Player> nonfoldplayersD = new ArrayList<Player>();


	                                                                       nonfoldplayersD.addAll(betting(nonfoldplayersC, table1, deck, foldplayers));

	                                                                       nonfoldplayersC.clear();

	                                                                       table1.addbettinground();

	                                                                       if (nonfoldplayersD.size() == 1){
	                                                                                     //check if any players have zero chips and remove them
	                                                                                     for(int kk=0; kk<players.size();kk++){

	                                                                                       if(players.get(kk).getchips()==0){
	                                                                                         objMsgCh.sendMessage("\n"+players.get(kk).getName()+" has run out of chips and is therefore being removed from the table.\nNow.. if you'd kindly get the h*#! outta of here.. \n"+"I always knew people named "+players.get(kk).getName()+" had about three too many chromosomes.").queue();
	                                                                                         players.remove(players.get(kk));
	                                                                                       }
	                                                                                     }
	                                                                                     objMsgCh.sendMessage("\n\nA NEW ROUND IS STARTING SHORTLY, please make yourselves comfortable \nand remember to tell Adam how cool you think his virtual Poker lounge is.").queue();
	                                                                                     // 15 second delay and clear screen
	                                                                                     try {
	                                                                                     TimeUnit.SECONDS.sleep(6);
	                                                                                   }catch (InterruptedException e){
	                                                                                     //handle the exception
	                                                                                     objMsgCh.sendMessage("Please don't input while delaying").queue();
	                                                                                   }
	                                                                                   //  clearScreen();
	                                                                                   



	                                                                              nonfoldplayersD.clear();
	                                                                              // a player has won so start a new game
	                                                                                gamecycle(players, table1, deck);
	                                                                                   }

	                                                                           else{

	                                                                           //  clearScreen();
	                                                                           

	                                                                         objMsgCh.sendMessage("All betting rounds and cycles are over.").queue();
	                                                                         objMsgCh.sendMessage("The remaining players hands are about to be shown and a winner will be determined.").queue();

	                                                                         // 15 second delay and clear screen
	                                                                         try {
	                                                                         TimeUnit.SECONDS.sleep(10);
	                                                                       }catch (InterruptedException e){
	                                                                         //handle the exception
	                                                                         objMsgCh.sendMessage("Please don't input while delaying").queue();
	                                                                       }
	                                                                       //  clearScreen();
	                                                                       

	                                                                             //print the addtablecards
	                                                                             objMsgCh.sendMessage("The table cards are:").queue();
	                                                                             table1.printtablecards();
	                                                                             objMsgCh.sendMessage("\n").queue();

	                                                                             //print everyones hands
	                                                                             for(int i=0; i<nonfoldplayersD.size(); i++){
	                                                                               objMsgCh.sendMessage(nonfoldplayersD.get(i).getName() + "'s hand is:").queue();
	                                                                               nonfoldplayersD.get(i).gethand();
	                                                                               objMsgCh.sendMessage("\n").queue();
	                                                                             }


	                                                                             straightflush(table1, nonfoldplayersD);
	                                                                             //need to check for four of a kind and fullhouse here before clearing the cards array
	                                                                             flush(table1, nonfoldplayersD);
	                                                                             checkpair(table1, nonfoldplayersD);
	                                                                             straight(table1, nonfoldplayersD);
	                                                                             highcard(table1, nonfoldplayersD);

	                                                                             objMsgCh.sendMessage("\n").queue();
	                                                                             //THIS IS WHERE A WINNER MUST BE SESLECTED BASED ON CARDS
	                                                                             winnerselection(nonfoldplayersD, table1);

	                                                                             nonfoldplayersD.clear();

	                                                                             //check if any players have zero chips and remove them
	                                                                             for(int kk=0; kk<players.size();kk++){

	                                                                               if(players.get(kk).getchips()==0){
	                                                                                 objMsgCh.sendMessage("\n"+players.get(kk).getName()+" has run out of chips and is therefore being removed from the table.\nNow.. if you'd kindly get the h*#! outta of here.. \n"+"I always knew people named "+players.get(kk).getName()+" had about three too many chromosomes.").queue();
	                                                                                 players.remove(players.get(kk));
	                                                                               }
	                                                                             }

	                                                                             objMsgCh.sendMessage("\n\nA NEW ROUND IS STARTING SHORTLY, please make yourselves comfortable \nand remember to tell Adam how cool you think his virtual Poker lounge is.").queue();
	                                                                             // 15 second delay and clear screen
	                                                                             try {
	                                                                             TimeUnit.SECONDS.sleep(30);
	                                                                           }catch (InterruptedException e){
	                                                                             //handle the exception
	                                                                             objMsgCh.sendMessage("Please don't input while delaying").queue();
	                                                                           }
	                                                                           //  clearScreen();
	                                                                           

	                                                                             // a player has won so start a new game
	                                                                              gamecycle(players, table1, deck);

	                                                                           }


	                                                                   }


	                                                   }




	                                       }
	                                     }








	         public void pokergame(){

	                       ArrayList<String> deck = new ArrayList<String>();
	                       // deck.add("Jack").queue(); adds to tail end
	                       // deck.get(3); returns item at index 3
	                       // length = deck.size();

	                       deck.add("2 of Spades (black)");deck.add("2 of Clubs (black)");deck.add("2 of Diamonds (red)");deck.add("2 of Hearts (red)");
	                       deck.add("3 of Spades (black)");deck.add("3 of Clubs (black)");deck.add("3 of Diamonds (red)");deck.add("3 of Hearts (red)");
	                       deck.add("4 of Spades (black)");deck.add("4 of Clubs (black)");deck.add("4 of Diamonds (red)");deck.add("4 of Hearts (red)");
	                       deck.add("5 of Spades (black)");deck.add("5 of Clubs (black)");deck.add("5 of Diamonds (red)");deck.add("5 of Hearts (red)");
	                       deck.add("6 of Spades (black)");deck.add("6 of Clubs (black)");deck.add("6 of Diamonds (red)");deck.add("6 of Hearts (red)");
	                       deck.add("7 of Spades (black)");deck.add("7 of Clubs (black)");deck.add("7 of Diamonds (red)");deck.add("7 of Hearts (red)");
	                       deck.add("8 of Spades (black)");deck.add("8 of Clubs (black)");deck.add("8 of Diamonds (red)");deck.add("8 of Hearts (red)");
	                       deck.add("9 of Spades (black)");deck.add("9 of Clubs (black)");deck.add("9 of Diamonds (red)");deck.add("9 of Hearts (red)");
	                       deck.add("10 of Spades (black)");deck.add("10 of Clubs (black)");deck.add("10 of Diamonds (red)");deck.add("10 of Hearts (red)");
	                       deck.add("Jack of Spades (black)");deck.add("Jack of Clubs (black)");deck.add("Jack of Diamonds (red)");deck.add("Jack of Hearts (red)");
	                       deck.add("Queen of Spades (black)");deck.add("Queen of Clubs (black)");deck.add("Queen of Diamonds (red)");deck.add("Queen of Hearts (red)");
	                       deck.add("King of Spades (black)");deck.add("King of Clubs (black)");deck.add("King of Diamonds (red)");deck.add("King of Hearts (red)");
	                       deck.add("Ace of Spades (black)");deck.add("Ace of Clubs (black)");deck.add("Ace of Diamonds (red)");deck.add("Ace of Hearts (red)");
	                       
	                       Table table1 = new Table();

	                       //asking for number of players; creating number of player objects based on that input
	                       //placing these objects in an arraylist
	                       ArrayList<Player> players = new ArrayList<Player>();
	                      //   int numOfPlayers;
	                          // objMsgCh.sendMessage("Greetings gentlemen, how many players will be participating? ").queue();
	                             Scanner input = new Scanner(System.in);
	                            //   numOfPlayers = input.nextInt();

	                               //catch input mismatch exception
	                               int numOfPlayers=0;
	                                 do {
	                                    try {
	                                        objMsgCh.sendMessage("Greetings gentlemen, how many players will be participating: ").queue();
	                                        numOfPlayers = input.nextInt();
	                                    } catch (InputMismatchException e) {
	                                        objMsgCh.sendMessage("Invalid number of players. ").queue();
	                                    }
	                                    input.nextLine(); // clears the buffer
	                                } while (numOfPlayers <= 0);


	                       for(int i = 0; i < numOfPlayers; i++)
	                       {
	                           objMsgCh.sendMessage("What is Player " + (i + 1) + "'s name? ").queue();
	                           String name = input.next();
	                          // objMsgCh.sendMessage("What is Player " + (i + 1) + "'s three digit password? ").queue();
	                          // int password = input.nextInt();

	                           //catch input mismatch exception
	                           int password=0;
	                             do {
	                                try {
	                                    objMsgCh.sendMessage("What is Player " + (i + 1) + "'s three digit password? ").queue();
	                                    password = input.nextInt();
	                                } catch (InputMismatchException e) {
	                                    objMsgCh.sendMessage("Invalid three digit passowrd. ").queue();
	                                }
	                                input.nextLine(); // clears the buffer
	                            } while (password <= 99 || password >= 1000);

	                           Player plr = new Player();
	                           plr.setName(name);
	                           plr.setpassword(password);
	                           players.add(plr);
	                           //  clearScreen();
	                           
	                       }
	                    //   objMsgCh.sendMessage("What is the ante for this poker game?").queue();
	                    //   table1.setante(input.nextInt());

	                       int ante=0;
	                         do {
	                            try {
	                                objMsgCh.sendMessage("What is the ante for this poker game?").queue();
	                                ante = input.nextInt();
	                            } catch (InputMismatchException e) {
	                                objMsgCh.sendMessage("Invalid ante. Max ante is 30 chips. ").queue();
	                            }
	                            input.nextLine(); // clears the buffer
	                        } while (ante <= 1 || ante >= 30);

	                        table1.setante(ante);




	                       ///WHERE THE CHANGE HAPPENS


	                       gamecycle(players, table1, deck);




	                     }







	

}
