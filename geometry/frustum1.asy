unitsize(1.5cm);
import markers;
import geometry;
import three;

// create 3D coordinate system
//currentprojection=orthographic(0,100,25);

// frustum properties
int a=4, b=2, h=6;

// draw frustum base
//patch f=unitfrustum(4,2);
draw((0,0,0)..(a,0,0)..(a,0,a)..(0,0,a)..cycle);


// draw frustum top
//draw((1,h,1)--(3,h,1)--(3,h,3)--(1,h,3)--cycle);
