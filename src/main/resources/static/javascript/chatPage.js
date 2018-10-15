var ws;
let MAX_NUMBER_OF_MESSAGES = 20;


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
        postMessage(message.author, message.content);
    };
}

function send() {
    var content = document.getElementById("message").value;
    // var json = JSON.stringify({
    //     "content":content
    // });
    // console.log("Messsage sent: \n" + content);
    ws.send(content);
}

function postMessage(author, message){

    let messageContainer = document.getElementById("messages");
    let payload = "<div class=\"messsage\">\n<p>" + author + ": " + message + "</p>\n</div>\n";
    // console.log("Got message: " + payload);
    messageContainer.insertAdjacentHTML("afterbegin", payload.toString())

    let messageCount = messageContainer.childElementCount;
    console.log("children: " + messageContainer.children[0]);
    if (messageCount > MAX_NUMBER_OF_MESSAGES){

        removeFirstMessage(messageContainer);
    }
}

function removeFirstMessage(messageContainer){

    let firstChild = messageContainer.lastElementChild;
    messageContainer.removeChild(firstChild);
}

window.onload = connect();