var ws;

function connect() {
    //var username = "user" + Math.floor(Math.random() * 1000);
    
    var host = document.location.host;
    //var pathname = document.location.pathname;
    
    ws = new WebSocket("ws://" + host + '/' + "websocket");

    ws.onmessage = function(event) {
    // var log = document.getElementById("log");
    //     console.log(event.data);
    //     var message = JSON.parse(event.data);
    //     log.innerHTML += message.from + " : " + message.content + "\n";

        postMessage("idk", event.data);
    };
}

function send() {
    var content = document.getElementById("message").value;
    // var json = JSON.stringify({
    //     "content":content
    // });
    console.log("Messsage sent: \n" + content);
    ws.send(content);
}

function postMessage(author, message){

    // let child = document.createElement('div');
    // child.cla

    let payload = "<div class=\"messsage\">\n<p>" + author + ": " + message + "</p>\n</div>\n";
    console.log("Got message: " + payload);
    document.getElementById("messages")
    .insertAdjacentHTML("afterbegin", payload.toString())
}

window.onload = connect();