
const url = "http://localhost:7001/AntiguaBurger_VersionWEB_Servlets/login";


let form = document.getElementById('formLogin')
let apiD;
form.addEventListener('submit',function (e){
    e.preventDefault();

    let data = new FormData(form);

    console.log(data.get("nameCashier"))

    let datos = {
        userName:data.get("nameCashier")
    }

    console.log(datos)


    let error = document.querySelector('#formFooter')
    error.innerHTML ='';

    axios.post(url,datos).then((response) =>{

        if(data.get("nameCashier") === response.data.userName){
            window.location.href = "menus.php"
        }else {
            error.innerHTML = `
            <div class="alert alert-dismissible alert-danger">
              <button type="button" class="close" data-dismiss="alert">&times;</button>
              <strong>Oh no!</strong> Usuario no encontrado. Intentelo de nuevo. <a href="#" class="alert-link">Olvidaste tu contraseña?</a>
            </div>
            `
        }

    }).catch(error=>{
        console.log(error)
    });




})



