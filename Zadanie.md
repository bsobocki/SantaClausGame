# Zadanie - napisz gre

Dana jest prostokątna plansza podzielona na N wierszy i M kolumn, gdzie N i M są ustalonymi liczbami naturalnymi ≥ 10. Na planszy znajduje się więc N ·M pól. Plansza jest zawinięta,
czyli przeciwległe brzegi planszy stykają się. Pole sąsiednie to takie, z którym dane pole ma
wspólny bok — ponieważ plansza jest zawinięta, to każde pole ma czterech sąsiadów, również
pola znajdujące się na brzegach i w rogach. Pomiędzy polami można się poruszać przechodząc
w jednym kroku do pola sąsiedniego.
Na planszy tej jest rozgrywana gra, w której Mikołaj rozdaje prezenty śpiącym dzieciakom,
jednocześnie uciekając przed dziką gromadą szukających go i goniących za nim (a może raczej za
prezentami, które posiada w swojej magicznej torbie) rozbudzonych dzieciaków. Celem gry jest
obdarowanie wszystkich dzieciaków prezentami w taki sposób, aby każdy dzieciak dostał jeden
prezent i aby Mikołaj nie został przyłapany na podrzucaniu paczki.
Mikołaj nigdy nie śpi i porusza się dość szybko przechodząc z pola na pole. Jego celem jest
podejście do śpiącego dziecka i podrzucenie mu prezentu na sąsiednie pole. Mikołj wygrywa, gdy
każde dziecko otrzyma paczkę (czyli zostanie uziemione).
Dzieciak z kolei zajmuje się poszukiwaniem prezentu od Mikołaja. Kiedy wypatrzy na horyzoncie Mikołaja to biegnie do niego małymi kroczkami po wymarzoną paczkę. Kiedy się zmęczy,
zasypia na pewien losowy kwant czasu — to wtedy jest ten moment, kiedy Mikołaj może podrzucić dziecikowi prezent, zostawiając go na polu bezpośrednio sąsiadującym z małym śpiochem.
Dziecko po przebudzeniu sprawdza co znajduje się na polach sąsiednich — jeśli jest tam prezent
to dzieciak natychmiast zajmuje pole z prezentem i już się stamdąd nie rusza. Jeśli obudzone
dziecko najdzie na sąsiednim polu Mikołaja, to Mikołaj zostaje zdemaskowany i gra się kończy
przegraną Mikołaja. No i wreszcie, jeśli sąsiednie pola są puste, to dzieciak rozgląda się po
szerszej okolicy (na przykład na odległość 5 pól) w poszukiwaniu Mikołaja. Gdy wypatrzy go to
już wiemy co zrobi, ale gdy na horyzoncie jest pusto (nie licząc innych dzieciaków) to wykonuje
losowy ruch na wolne pole sąsiednie się tam się przez chwilę bawi, itd.
Napisz aplikację okienkową w technologii Swing, która pozwoli zagrać Mikołajowi w ucieczkę
przed ciekawską dzieciarnią. Po rozpoczęciu gry na planszy znajduje się Mikołaj i 12 dzieciaków
rozmieszczonych losowo w taki sposób, że na jednym polu jest tylko jedna osoba. Gracz steruje
Mikołajem za pomocą klawiatury a dzieciaki są sterowane za pomocą osobnych wątków. Wszystkie wątki powinny się synchronizować na obiekcie planszy. Osobny wątek będący obiektem klasy
javax.swing.Timer ma odświeżać rysunek planszy na obiekcie typu javax.swing.JPanel przynajmniej co 1/
20 sekundy.
