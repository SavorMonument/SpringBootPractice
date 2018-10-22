var ws;
let MAX_NUMBER_OF_MESSAGES_PER_PAGE = 20;

class ChatMessage {
    constructor(user, content) {

        this.user = user;
        this.content = content;
    }
}

class User {
    constructor(username, textColor) {

        this.username = username;
        this.textColor = textColor;
    }
}

var pageUser;

window.onload = connect();
window.onload = checkUserAvailable();

function checkUserAvailable() {

    username = getCookie("username");
    textColor = getCookie("textColor");

    if (null != username && null != textColor) {

        pageUser = new User(username, textColor);
        submitButton = document.getElementById("submitMessageButton");
        submitButton.disabled = false;

        sendUserToServer(pageUser);
    }
}

function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
}

function sendUserToServer(pageUser) {

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/chat/set-user", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("username=" + pageUser.username + "&textColor=" + pageUser.textColor);
}

function connect() {
    var host = document.location.host;
    //var pathname = document.location.pathname;

    ws = new WebSocket("ws://" + host + '/' + "websocket");

    ws.onmessage = function (event) {
        // var log = document.getElementById("log");
        //     console.log(event.data);
        //     var message = JSON.parse(event.data);
        //     log.innerHTML += message.from + " : " + message.content + "\n";

        let message = JSON.parse(event.data)
        message.user = JSON.parse(message.user)
        console.log("Got message: " + message.user.toString() + "\n" + message.content);
        postMessage(message.user.username, message.user.textColor, message.content);
    };
}

function send() {

    let textArea = document.getElementById("message");
    if (textArea.value != "") {

        let content = textArea.value;

        var json = JSON.stringify(new ChatMessage(pageUser, content));
        console.log("Messsage sent: \n" + json);
        ws.send(json);
        textArea.value = "";
    }
}

function postMessage(author, textColor, message) {

    let messageContainer = document.getElementById("messages");
    let payload = "<div class=\"messsage\" style=\"color: " + textColor + ";\"> \n<p>" +
        author + ": " + message + "</p>\n</div>\n";
    messageContainer.insertAdjacentHTML("afterbegin", payload.toString())

    let messageCount = messageContainer.childElementCount;
    if (messageCount > MAX_NUMBER_OF_MESSAGES_PER_PAGE) {

        removeFirstMessage(messageContainer);
    }
}

function removeFirstMessage(messageContainer) {

    let lastChild = messageContainer.lastElementChild;
    messageContainer.removeChild(lastChild);
}

function clearElem(elem) {

    elem.value = "";
}
