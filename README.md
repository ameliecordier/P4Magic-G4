#TP Puissance 4
## Description de notre travail
- Nous avons impl�ment� un effet (ChangeNeighborsColorEffect) qui permute la couleur des 7 cases voisines
de la case qui vient d'�tre jou�e, sans changer la couleur de jeton de la case elle m�me.
- Nous avons impl�ment� un second effet (CondemnedLineEffect) qui place la ligne compl�te en statut "neutre"
et qui rend inaccessible la partie inf�rieure de la grille inaccessible. La partie continue uniquement
au-dessus de cette ligne "condamn�e".
- Corrections diverses:
	- Correction d'une boucle de la classe Utils.java qui faussait les tests
	- Ajouts/Corrections de classes Board.java, Game.java, pour pouvoir impl�menter l'effet CondemnedLineEffect
	- Ajout de l'action de fermeture sur la croix de la boite de dialogue de confirmation de fermeture du jeu