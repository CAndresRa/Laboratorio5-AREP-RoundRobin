function saveMessage() {
    var  message = document.getElementById("inputText").value;
    axios.post('http://localhost:8081/add', message);
}