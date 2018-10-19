var ws;
let MAX_NUMBER_OF_MESSAGES_PER_PAGE = 20;

let pageUser = {username: "placeholder"};

class ChatMessage {
    constructor(user, content) {

        this.user = user;
        this.content = content;
    }
}


function connect() {
    var host = document.location.host;
    //var pathname = document.location.pathname;
    
    ws = new WebSocket("ws://" + host + '/' + "websocket");

    ws.onmessage = function(event) {
    // var log = document.getElementById("log");
    //     console.log(event.data);
    //     var message = JSON.parse(event.data);
    //     log.innerHTML += message.from + " : " + message.content + "\n";
        
        var message = JSON.parse(event.data)
        postMessage(message.username, message.content);
    };
}

function send() {

    let textArea = document.getElementById("message");
    if (textArea.value != ""){

        let content = textArea.value;

        var json = JSON.stringify(new ChatMessage(pageUser, content));
        console.log("Messsage sent: \n" + json);
        ws.send(json);
        textArea.value = "";
    }
}

function postMessage(author, message){

    console.log("should post now");
    let messageContainer = document.getElementById("messages");
    let payload = "<div class=\"messsage\">\n<p>" + author + ": " + message + "</p>\n</div>\n";
    // console.log("Got message: " + payload);
    messageContainer.insertAdjacentHTML("afterbegin", payload.toString())

    let messageCount = messageContainer.childElementCount;
    console.log("children: " + messageContainer.children[0]);
    if (messageCount > MAX_NUMBER_OF_MESSAGES_PER_PAGE){

        removeFirstMessage(messageContainer);
    }
}

function removeFirstMessage(messageContainer){

    let lastChild = messageContainer.lastElementChild;
    messageContainer.removeChild(lastChild);
}

function clearElem(elem){

    elem.value = "";
}

window.onload = connect();