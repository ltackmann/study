unitsize(1.5cm);
import markers;
import geometry;

// sides in triangle
int a = 3, b = 4, c = 5;
// altitude from C (sin(angle(B)) * a)
real h = (a/c) * a;
// base of altitude from vertex C  
real x = sqrt((b*b) + (h*h)); 
pair X=(x,0);
// vertices in triangle
pair A=0, B=(c,0), C=(x, h);

// draw triangle
draw(A--B--C--cycle);
// draw altitude
draw("$h$",C--X,dashed);

// label sides
label("$a$",B--C);
label("$b$",C--A);
label("$c$",A--B);
// label vertices
label("$A$",A,SW);  
label("$B$",B,SE);  
label("$C$",C,N);
// label helper line
label("$x$",B--X);
// altitude base
label("$X$",X,S);
perpendicular(X,NW);
// right angle at vertex C
perpendicular(C,SE,B-C);
