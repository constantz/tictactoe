# tictactoe
exercise in Java, an (as yet) unsuccessful attempt at creating an unwinnable tictactoe game

Made for fun during a few days in november 2018 while at home, studying html and css

In dire need of refactoring!

Game algorithm:
	- human move is implemented in screen display and stored / represented in arrays;
	- enter a loop: computer move -> check if game is won -> human move -> check if game is won -> etc.;
	- computer move is decided according to algorithm;
	- computer move is implemented in screen display and stored / represented in arrays just like human move;


Algorithm computerplay:
	- Check if game can be won -> play move;
	- Check if opponent can win -> play preventing move;
	- If center field is free -> play center field;
	- If first opponent move is center field -> play corner field;
	- ...
	
	(etc., add more and more rules according to need if game proves winnable...)
	

Ideas for refactoring:
	- Cleaner code!
	- Better seperation between: display methods, input methods, representation of game data in program, and computer play algorithm
	
	
Comment: proved more interesting than initially thought, began without a plan, stumbled upon a sort of "model, view,
control" design by accident, learned a lot, got lots of ideas about computer play algorithms and expanding to other board games but forgot about it all because of lack of time and the more pressing needs to learn JavaScript and PHP...

Rediscovered and put on Github almost four months later.
	
