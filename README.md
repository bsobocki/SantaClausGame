# <img src=https://github.com/bsobocki/SantaClausGame/blob/master/santa.png/> Santa Claus Game <img src=https://github.com/bsobocki/SantaClausGame/blob/master/santa.png/>
A simple platform game wrote on a Java Course at University of Wroclaw  

---  
## Table of contents
- [A bit about the game](#a-bit-about-the-game) 
- [How to play](#how-to-play)
- [Technology](#technology)
- [How it works](#how-it-works)

## A bit about the game
It's Christmas time. Who loves this special time? Kids, of course!  <img src=https://github.com/bsobocki/SantaClausGame/blob/master/kid.png align=right/>  
They always want to see Santa!  

Santa have a mission.  He must give a gift to every child, but it is not so simple. Children watch him and try to catch him in the act, so he must run away.  

However, every child gets tired one moment and goes to sleep.  <img src=https://github.com/bsobocki/SantaClausGame/blob/master/sleep.png align=right/>

In that moment Santa can be invisible for sleeping child, so it's the one moment, when he can give a present to him.  

Help Santa give out gifts! <img src=https://github.com/bsobocki/SantaClausGame/blob/master/present.png />

## How to play
You are Santa and you must give out gifts.  

After sleeping kids are looking around them.  
1. They are looking for a gift. Is it on the next board tile? Great! Now they are happy, that they have a new toy, so they don't chasing you!
2. If there's no gift, they try to catch Sanda. If you are on the next board tile when the kid is awakened... You lose :(
3.If you are not in the next board tile, but you are close, they will fillow you!
4. You can teleport from the right board edge to the left one, from the left one to the rigt one and analogously the top edge and bottom.

## Technology
Language: Java  
Library: Swing

## How it works
Each instance of the Kid class (running children) is a separate thread, which is moving in random directions in the moment, when Santa isn't around. When Santa is around them they follow him, until he is far from them.
