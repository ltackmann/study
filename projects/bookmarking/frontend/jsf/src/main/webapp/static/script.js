function checkPassword() {
    var form = document.forms["signupForm"];
    var password = form["signupForm:signupPassword"].value;
    var passwordConfirm = form["signupForm:signupConfirmPassword"].value;

    if (password == passwordConfirm) {
        return true;
    } else {
        alert("Password and password confirm fields don't match");
        // clear fields
        form["signupForm:signupPassword"].value = "";
        form["signupForm:signupConfirmPassword"].value = "";
        return false;
    }
} 
