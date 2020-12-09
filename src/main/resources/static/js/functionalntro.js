//procedure paradigm: PL/SQL
//procedure/function return  -> js OO
let aux = "txt";
const data = "TOMA";

function sayHi() {
    console.log("yupi!")
}
//oo paradigm
let obj = {
    key: "value",
    sayHi: function () {
        console.log("yeai!")
    }
};
obj.sayHi();
//functional programming
let arrData = ["asd","123",123,456];
for (let i = 0; i < arrData.length; i++) {
    console.log(arrData[i]);
}
arrData.forEach( item => {
   console.log(item);
});
let printer = (elem) =>{
  console.log(elem);
};
arrData.forEach(printer);
//Real Utility

//f1
console.log("initContent");
console.log("myPart");
console.log("endContent");
//f2
console.log("initContent");
console.log("otherPart");
console.log("endContent");
//genFunction(console.log("doWhatYouWant");)
//console.log("initContent");
//executeCode();
//console.log("endContent");
function genericFunction(f){
    console.log("initContent");
    f.apply();
    console.log("endContent");
}
function f1(){
    genericFunction(()=>{ console.log("myPart")});
}
function f2(){
    genericFunction(()=>{ console.log("otherPart")});
}