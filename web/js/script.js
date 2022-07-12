const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');
var mess = document.getElementsByClassName('alert');
signUpButton.addEventListener('click', () => {
	container.classList.add("right-panel-active");
        document.getElementByC('mess').innerHTML = "";
        mess.innerHTML = "";
});

signInButton.addEventListener('click', () => {
	container.classList.remove("right-panel-active");
        document.getElementById('mess').innerHTML = "";
         mess.innerHTML = "";
});

var check = function() {
    if (document.getElementById('password').value ==
      document.getElementById('confirm_password').value) {
      document.getElementById('message').style.color = 'green';
      document.getElementById('message').innerHTML = 'Password matching';
    } else {
      document.getElementById('message').style.color = 'red';
      document.getElementById('message').innerHTML = 'Password does not matching';
    }
}

