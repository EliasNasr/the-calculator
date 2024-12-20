const x1 = document.getElementById("1");
const x2 = document.getElementById("2");
const x3 = document.getElementById("3");
const x4 = document.getElementById("4");
const x5 = document.getElementById("5");
const x6 = document.getElementById("6");
const x7 = document.getElementById("7");
const x8 = document.getElementById("8");
const x9 = document.getElementById("9");
const x0 = document.getElementById("0");
const equal = document.getElementById("equal");
const restart = document.getElementById("reset");
const sum = document.getElementById("sum");
const sub = document.getElementById("sub");
const mul = document.getElementById("mul");
const div = document.getElementById("div");
let r = document.getElementById("result");
let status = document.getElementById("status");
let cal = [];

document.addEventListener("DOMContentLoaded", reset);
const buttons = [x0,x1,x2,x3,x4, x5, x6, x7, x8, x9];
    buttons.forEach(button =>{
        button.addEventListener("click", display);
    })
sum.addEventListener("click",display);
sub.addEventListener("click",display);
mul.addEventListener("click",display);
div.addEventListener("click",display);
equal.addEventListener("click",calcul);
restart.addEventListener("click",reset);

function display(event){
    let num = event.target.innerText;
    cal.push(num);
    r.innerHTML = cal.join('');
    if(cal.length == 1){
        statusUpdate();
    }
}

function statusUpdate(){  //done
    if(["+", "-", "*", "/"].includes(cal[0])){
        status.innerHTML = "prefix evaluation: ";
    }else{
        status.innerHTML = "postfix evaluation: ";
    }
}

function reset(){
    cal = [];
    r.innerHTML = "=";
    status.innerHTML = " ";
}

function calcul(){
    if(["+", "-", "*", "/"].includes(cal[0])){
        prefix(cal);
    }else{
        postfix(cal);
    }
}

function prefix(){ 
    let stack = [];
    for (let i = cal.length - 1; i >= 0; i--){
        let x = cal[i];
        if (!isNaN(x)) {   //isNaN return true if x if a number
            stack.push(Number(x));  //Number() return a 3 from "3" and false from "a"
        } else {
            let num2 = stack.pop();
            let num1 = stack.pop();
            let result = 0;
            if (x === "*")
                result = num1 * num2;
            else if (x === "/")
                result = num1 / num2;
            else if (x === "+") 
                result = num1 + num2;
            else if (x === "-")
                result = num1 - num2;

            stack.push(result);
        }
    }
    r.innerHTML = stack.pop();
}

function postfix() {
    let stack = [];
    for (let i = 0; i < cal.length; i++) {
        let x = cal[i];
        if (!isNaN(x)) {   //isNaN return true if x if a number
            stack.push(Number(x));
        } else {
            let num2 = stack.pop();
            let num1 = stack.pop();
            let result = 0;
            if (x === "*")
                result = num1 * num2;
            else if (x === "/")
                result = num1 / num2;
            else if (x === "+") 
                result = num1 + num2;
            else if (x === "-")
                result = num1 - num2;

            stack.push(result);
        }
    }
    r.innerHTML = stack.pop();
}
