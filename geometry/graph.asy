import graph;

size(10cm,0);

real xmin=-4,xmax=4;
real ymin=-2,ymax=10;

real f(real x) {return x^2;}

draw(graph(f,xmin,xmax,n=400),linewidth(1bp),cross);
