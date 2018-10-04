# Discord-JDA-Poker-Bot
This is my first Maven project using Eclipse. My goal is to convert the turn based, command prompt poker game that I coded into a Discord Poker bot. A large portion of the code needs to be re-written since the it will be driven by Event Listeners rather than simple step based input output using the Scanner/Print methods.

Some of the Cardgame class methods can be used without being re-written entirely however these methods need some serious debugging.


Progress as of 6/22/2018:
Users are able to start a game and the Poker bot can deal cards to users who join. The cards are displayed in a private channel while betting and all other game activites occur in the public chat channel. The Poker bot also keeps up with the game time in seconds and gives users a certain amount of time for their turns.

  Working Commands List:
  >gameSetup
  >joinGame
  >getPlayers
  >Fold
  >FoldH
  >Bet
  >endGame

  Command Descritpion:
  >gameSetup starts a new game
  >joinGame joins the current game if the game is still in the start up phase (first 40 seconds) and there are less than 10 players
  >getPlayers returns a list of players in the game
  >Fold folds and shows your hand in the public channel
  >FoldH folds without showing hand
  >Bet allows the player to bet some of his chips as long as he/she has enough
  >endGame ends the currentgame by creating a fresh game object
