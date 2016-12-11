open System

let tuple = (1, false, "text");;
let getNumberInfo (x : int) = (x, x.ToString(), x * x);;
getNumberInfo 42;;


// Functions can even take tuples as arguments.

let printBlogInfo (owner, title, url) = printfn "%s’s blog [%s] is online at ‘%s'" owner title url;; 
let myBlog = ("Chris", "Completely Unique View", "http://blogs.msdn.com/chrsmith");;
printBlogInfo myBlog;;