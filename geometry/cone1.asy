unitsize(1cm);
import solids;
import math;

// create 3D coordinate system
currentprojection=orthographic(0,100,25);

// cone properties
real a=4, b=7, y=4;
// use similar triangles (x : y = a : b) to calculate x
real x=a*y/b;

// draw cone
triple pO=(0,0,0); 
revolution c=cone(pO,a,b,-Z);
c.draw();

// draw line from top to buttom
triple pS=(0,0,-b);
draw(pS--pO, dashed);
label("$S$",pS,S);

// draw upper radius 
triple pA=(a,0,0);
draw("$a$",pO--pA, dashed);

// draw outer square
triple pB=(a,0,-b);
draw("$b$",pA--pB, dashed);
draw(pB--pS, dashed);

// draw lower radius and circle
real altitude = -(b-y);
triple pOprime=(0,0,altitude), pAprime=(x,0,altitude);
draw("$x$",pOprime--pAprime, dashed);
draw(circle(pOprime,x));

// draw inner square
triple pBprime=(x,0,-b);
draw("$y$", pAprime--pBprime, dashed);
