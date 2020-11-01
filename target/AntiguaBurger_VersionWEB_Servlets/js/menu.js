const url = "http://localhost:7001/AntiguaBurger_VersionWEB_Servlets/menus";
const url2 = "http://localhost:7001/AntiguaBurger_VersionWEB_Servlets/login.html";

let json;
let dataUser;



axios.get(url).then((response) =>{
    json = response.data;
    console.log(response.data)

    let idCard = document.querySelector('#carMenu')
    idCard.innerHTML = '';

    for(let datas of json){
        function NumerosAleatorios(min, max) {
            return Math.round(Math.random() * (max - min) + min);
        }
        idCard.innerHTML +=`
        <div>
        <form method="get" action="add.html">
            <div class="wrimagecard wrimagecard-topimage">
                <div>
                    <a>
                        <div class="wrimagecard-topimage_header imagenFondo"  style="background-image: url(${datas.img}")>
                        </div>
                        <div class="wrimagecard-topimage_title">
                            <h4>${datas.nameMenu}
                            <span class="badge badge-primary badge-pill justify-content-between" id="spanEdit">${NumerosAleatorios(1,30)}</span>
                            </h4>
                            <div>
                                <h5>Q${datas.price}.00</h5>
                            </div>
                            <div  style=" text-align: center">
                                 <input hidden type="text" value="${datas.nameMenu}" name="id">
                                 <input type="submit" class="btn btn-outline-secondary" value="Buy">
                            </div>
                        </div>
                    </a>
                </div>
            </div>
        </form>
        `
    }

});


axios.post(url).then((response) =>{
    if(response.data != null){
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

