function saveMessage() {
    const message = document.getElementById("inputText").value;
    axios.post('http://localhost:8080/add', message);
}

function getMessages(){
    const messages = axios.get('http://localhost:8080/messages').then(messages => {
        console.log(messages);
        for (let i = 0; i < messages.data.length; i++) {
            document.getElementById("item"+(i+1)).innerHTML = "Id: "+ messages.data[i].id +
                "  -  Message: " + messages.data[i].message +
                "  -  Date: " + messages.data[i].date;
        }
    });
}