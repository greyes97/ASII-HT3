let urlPath= window.location;
let urlScan = new URL(urlPath.href);
let searchParams = urlScan.searchParams
let keys = [...searchParams.keys()]


const object1 = keys
    .reduce((obj, key) =>({...obj, [key]: searchParams.get(key) }), {})

const url = 'http://localhost:7001/AntiguaBurger_VersionWEB_Servlets/order?order='+object1.id;

let dataGet = [];
let item = [];
let itemAdd = [];
axios.get(url).then((response)=>{
    dataGet = response.data
    item = dataGet.item;
    itemAdd = dataGet.extras;
    console.log(response.data)
        axios.post('http://localhost:7001/AntiguaBurger_VersionWEB_Servlets/add').then((response)=>{
            let itemM="";
            for(let itemMenu of item){
                itemM +=`
            ${itemMenu}<br>    `
            }
            let itemA = "";
            for (let items of itemAdd){
                itemA +=`
            ${items}<br>
            `
            }
            document.getElementById("infoOrderCustomer").innerHTML = `
        Cajero: ${response.data +" (" +dataGet.cashier+")"}<br>
        Fecha/Hora: ${dataGet.date}<br>
        Cliente: ${dataGet.customer}<br>
        Nit: ${dataGet.taxId}<br><br>
        ${dataGet.nameMenu}<br>
        ${itemM}<br>
        Extras<br>
        ${itemA}<br>
        `
            document.getElementById("totalOrder").innerHTML = "Q."+dataGet.total+".00"
        })


    document.getElementById("idOrder").innerHTML = dataGet.order
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

document.getElementById("inputBackMenu").addEventListener('click',ev => {
    window.location.href = "menus.php"
})

document.getElementById("buttonJson").addEventListener('click', ev=>{
    data = {
        "order":dataGet.order,
        "option":"json"
    }
    fileDownload(data)
})
document.getElementById("buttonCsv").addEventListener('click', ev=>{
    data = {
        "order":dataGet.order,
        "option":"csv"
    }
    fileDownload(data)
})
document.getElementById("buttonTxt").addEventListener('click', ev=>{
    data = {
        "order":dataGet.order,
        "option":"txt"
    }
    fileDownload(data)
})
document.getElementById("buttonXml").addEventListener('click', ev=>{
    data = {
        "order":dataGet.order,
        "option":"xml"
    }
    fileDownload(data)
})

function fileDownload(data){
    datos ={
        method: 'POST',
        url: 'http://localhost:7001/AntiguaBurger_VersionWEB_Servlets/download?id=f7984580-f946-11ea-ae34-036c96d2e2f1',
        responseType: 'blob',
        data:data
    }
    axios(datos).then((response)=>{
        console.log(response)

        let fileURL = window.URL.createObjectURL(new Blob([response.data]));
        let fileLink = document.createElement('a');

        fileLink.href = fileURL;
        fileLink.setAttribute('download', dataGet.order+'.'+data.option); //or any other extension
        document.body.appendChild(fileLink);
        console.log(document.body.appendChild(fileLink))
        fileLink.click()
    }).catch(error =>{
        console.log(error)
    })
}
