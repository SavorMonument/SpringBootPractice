var ws;

function connect() {
    var username = "user" + Math.floor(Math.random() * 1000);
    
    var host = document.location.host;
    var pathname = document.location.pathname;
    
    ws = new WebSocket("ws://" + host + pathname + '/' + username);

    ws.onmessage = function(event) {
    var log = document.getElementById("log");
        console.log(event.data);
        var message = JSON.parse(event.data);
        log.innerHTML += message.from + " : " + message.content + "\n";
    };
}

function send() {
    var content = document.getElementById("message").value;
    var json = JSON.stringify({
        "content":content
    });

    ws.send(json);
}

window.onload = connect();