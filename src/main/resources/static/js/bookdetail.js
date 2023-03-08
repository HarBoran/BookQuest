function increment() {
    var value = parseInt(document.getElementById("number").value, 10);
    value++;
    document.getElementById("number").value = value;
}

function decrement() {
    var value = parseInt(document.getElementById("number").value, 10);
    if (value > 0) {
        value--;
    }
    document.getElementById("number").value = value;
}

function incrementbook() {
    var value = parseInt(document.getElementById("bookquantity").value, 10);
    value++;
    document.getElementById("bookquantity").value = value;
}

function decrementbook() {
    var value = parseInt(document.getElementById("bookquantity").value, 10);
    if (value > 0) {
        value--;
    }
    document.getElementById("bookquantity").value = value;
}