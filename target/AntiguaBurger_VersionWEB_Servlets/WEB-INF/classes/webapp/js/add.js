
let urlPath= window.location;
console.log(urlPath.href)

let orderNumber =uuid.v1();

let urlScan = new URL(urlPath.href);

let searchParams = urlScan.searchParams

let keys = [...searchParams.keys()]

const object1 = keys
    .reduce((obj, key) =>({...obj, [key]: searchParams.get(key) }), {})

const url = 'http://localhost:7001/AntiguaBurger_VersionWEB_Servlets/add?id='+object1.id;

console.log(url);

let datas;
let addData;
    axios.get(url)
    .then((response) => {

        datas = response.data;

        let imagen = document.querySelector('#orderHeader')
        imagen.innerHTML = `
            <div class="card-body text-white text-center py-5 px-5 " style="background-image: url(${datas.img}); background-size: cover;background-position: center;background-origin: content-box;white-space: pre-line; ">
                <h1 class="mb-1" style="font-size: 400%">
                      <strong id="nameCombo" style="color: #FFFFFF"></strong>
                </h1>
                <p>
                    <strong id="price">Bienvenido a AntiguaBurger</strong>
                </p>
            </div>
            <p class="card-body mb-1">
                Por favor eliga como quiere su <strong> menu.</strong>
            </p>
            <ul id="alternativas" class="list-group list-group-flush" >
        </ul>
            `

        let nameC = document.querySelector('#nameCombo')
        nameC.innerHTML = datas.nameMenu

        let pre = document.querySelector('#price')
        pre.innerHTML = `Q. ${datas.price}.00`

        let alt = document.querySelector('#alternativas')
        let altData = datas.itemFood;

        let addView = document.querySelector('#selectAdd')
        addView.innerHTML = '';

        addData = datas.add;

        for (let items of altData) {

            let option = [];

            if (items.alternative == null) {
                alt.innerHTML += `
                <li class="list-group-item"><strong>${items.name}</strong></li>`
            } else {
                for (let alter of items.alternative) {

                    option += `
                        <option value="${alter}">${alter}</option>
                        `
                }
                alt.innerHTML += `
                <li class="list-group-item" ><strong>${items.name}</strong>
                <div class="form-row align-items-center " data-style="btn-outline-secondary" >
                <select class="btn btn-outline-secondary btn-block" style="color: #0883ff;border-color: #eee;" id="check${items.name}">
                <option disabled selected>Elegir opcion</option>${option}
                </select>
                </div>
                </li>
                `
            }
        }

        let i=20;
        for (let add of addData) {

            addView.innerHTML += `
            <div class="form-group">
              <div class="form-group">
                <div class="input-group mb-3" id="formulario">
                  <div class="btn-group" role="group" aria-label="Basic example">
                  <div class="custom-control custom-switch">
                      <input type="checkbox" class="custom-control-input" id="${add.description}" onchange="disab('${add.idExtra}','${add.description}','${add.idExtra+i}','inputNumberAdd${i}')">
                      <label class="custom-control-label" for="${add.description}"></label>
                    </div>
                    <div class="btn-group" role="group" aria-label="Basic example" >
                    <button type="button"  class="btn btn-outline-primary" id="${add.idExtra}" disabled="true">${add.description}</button>
                    <button type="button" id="${add.idExtra+i}" class="btn btn-outline-danger" disabled="true">Q. ${add.price}.00</button>
                    <select class="btn btn-success" id="inputNumberAdd${i}" disabled>
                    <option disabled selected>0</option>
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                    </select>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            `
            i++;
        }



    }).catch(error=>{
            console.log(error)
        })
let userName;
axios.post('http://localhost:7001/AntiguaBurger_VersionWEB_Servlets/menus').then((response) =>{
    if(response.data != null){
        userName = response.data
        let nameCashier = document.querySelector('#nameCashier');
        nameCashier.innerHTML = response.data;
        let iconCheck = document.querySelector('#iconUserCheck');
        iconCheck.innerHTML = `<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-check-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                      <path fill-rule="evenodd" d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm9.854-2.854a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 0 1 .708-.708L12.5 7.793l2.646-2.647a.5.5 0 0 1 .708 0z"/>
                    </svg>`
    }else {
        let iconCheck = document.querySelector('#iconUserCheck');
        iconCheck.innerHTML = `
        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-x-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
          <path fill-rule="evenodd" d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm6.146-2.854a.5.5 0 0 1 .708 0L14 6.293l1.146-1.147a.5.5 0 0 1 .708.708L14.707 7l1.147 1.146a.5.5 0 0 1-.708.708L14 7.707l-1.146 1.147a.5.5 0 0 1-.708-.708L13.293 7l-1.147-1.146a.5.5 0 0 1 0-.708z"/>
        </svg>
        `
    }
}).catch(error=>{
    console.log(error)
});



function disab(campo,campo2,campo3,campo4){
        let estado = document.getElementById(campo);
        let estado2 = document.getElementById(campo2)
        let estado3 = document.getElementById(campo3);
        let estado4 = document.getElementById(campo4);


    if(estado2.checked === true){
        estado.disabled = false
        estado3.disabled = false
        estado4.disabled = false
        estado4.selectedIndex = [1]

        estado4.addEventListener("change",function (e){

            console.log(e.target.value)
        })
    }else {
        estado.disabled = true
        estado3.disabled = true
        estado4.disabled = true
        estado4.selectedIndex="None"
    }
}

let totalAdd = 0;
let itemsMenu = [];
let addOrder =[];
function editMenu(){
    let dataAdd = datas.itemFood;
    let dataAuxiliar=[];


    for(let alt of dataAdd){

        if(alt.alternative == null){
            console.log("no hacer nada")
            dataAuxiliar.push(alt.name)
        }else {
            let select = document.getElementById("check"+alt.name)
            let valueSelect = select.value
            console.log(valueSelect)
            dataAuxiliar.push(alt.name+" ["+valueSelect+"]")
        }
    }
    itemsMenu = dataAuxiliar;

    if(validarJson(dataAuxiliar)!==false){

        totalAdd += parseInt(datas.price);
        let i = 20;
        for(let add of addData){
            let idSelect = "inputNumberAdd"+i;

            if(validarCheckAdd(add.description)===true){
                totalAdd += parseInt(getTotalSelect(add.price,getValueSelect(idSelect)))
                addOrder.push(add.description+" ("+getValueSelect(idSelect)+")")
            }
            i++
        }
        let numberOrder = document.getElementById("inputIdOrder")
        numberOrder.innerHTML = `
        <input type="text" name="id" hidden value="${orderNumber}">
        `
        closeAlert()
        nextStepAdd()

        document.getElementById("menuTotal").innerHTML = `Q. ${totalAdd}.00` ;
    }else {
        let errorAlternativa = document.querySelector("#errores")
        errorAlternativa.innerHTML = `
        <div class="alert alert-dismissible alert-danger mb-4 fade show" id="myAlert" >
          <button type="button" class="close" data-dismiss="alert">&times;</button>
          <strong>Oh no!</strong> Debe de selecionar como quiere su menu, por favor intentelo de nuevo.
        </div>
        `
    }
}



function nextStepAdd(){

    function mostrar(){
        $('#orderHeader').show(500)
        $('#extras').show(500)
        document.getElementById("carSumbit").style.display = "block";
        document.getElementById("stepInfoCustomer").style.display = "none"

    }
    function ocultar(){
        $('#orderHeader').toggle(500);
        $('#extras').toggle(500);
        $('#carSumbit').toggle(100);
        $('#stepInfoCustomer').show(500)
    }

    let div = document.getElementById("orderHeader");

    if(div.style.display === 'none'){
        mostrar()
    }else {
        ocultar()
    }
}

function createOrder(){

    let nameCustomer = document.getElementById("nameCustomer").value
    let nitCustomer = document.getElementById("nitCustomer").value



        if(addOrder.length === 0){
            addOrder.push("No tiene extras")
        }
        let jsonOrder = {
            "order": orderNumber,
            "date": new Date(),
            "customer": nameCustomer,
            "taxId": nitCustomer,
            "nameMenu": datas.nameMenu,
            "item": itemsMenu,
            "extras": addOrder,
            "total": parseInt(totalAdd)
        }

        return jsonOrder

}

function sendOrder(){
    axios.post('http://localhost:7001/AntiguaBurger_VersionWEB_Servlets/order',createOrder()).then((response) => {
        console.log(response)
    })
}


(function () {
    'use strict';
    window.addEventListener('load', function () {
        var form = document.getElementById('stepInfoCustomer');
        form.addEventListener('submit', function (event) {
            if (form.checkValidity() === false) {
                event.preventDefault();
                event.stopPropagation();
            }else {
                sendOrder()
            }
            form.classList.add('was-validated');
        }, false);
    }, false);
})();




document.getElementById("inputBack").addEventListener('click', ev => {
    nextStepAdd()
    addOrder =[];
    totalAdd = 0;
})



function closeAlert(){
    $("#myAlert").alert("close");
}

function validarJson(dato){
    for(let aux of dato){
        if(aux === "Elegir opcion"){
            return false
        }
    }
}

function validarCheckAdd(idCheck){
    let checkBox = document.getElementById(idCheck)
    if(checkBox.checked === true){
        console.log(idCheck)
        return true
    }
}

function getValueSelect(idCheck){
    let checkBox = document.getElementById(idCheck)
    return checkBox.value
}

function getTotalSelect(price, count){
    let precio = parseInt(price)
    let cantidad = parseInt(count)
    return precio * cantidad;
}






