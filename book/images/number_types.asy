import geometry;
import fontsize;

// document setup
unitsize(1cm);
defaultpen(fontsize(11)); 
texpreamble("\usepackage{amsfonts}");

// big box 
draw(box((0,0),(10,6)),linewidth(bp));
draw((4,0)--(4,6),linewidth(bp)); 
// small box
draw(box((0,0),(3,3)),linewidth(bp)); 
draw((0,1.5)--(3,1.5),linewidth(bp)); 

// big box markers
draw("Real Numbers ($\mathbb{R}$)", (5,6.75));
draw(minipage("\center{Algebraic\\ Numbers ($\mathbb{A}$)}"), (2,5.25));
draw(minipage("\center{Transcendental\\ Numbers ($\mathbb{T}$)}"), (7,5.25));
//small box markers
draw("Integers ($\mathbb{Z}$)", (1.5,2.25));
draw(minipage("\center{Rational\\ Numbers ($\mathbb{Q}$)}"), (1.5,0.75));

// numbers
draw("$\sqrt{2}$", (2,4.25));
draw("$\pi$", (7,4.25));
