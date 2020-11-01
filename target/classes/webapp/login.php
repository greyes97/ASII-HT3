<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" content="application/json">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add</title>
    <link href="css/login.css" rel="stylesheet" type="text/css">
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
    <link href="https://stackpath.bootstrapcdn.com/bootswatch/4.5.2/materia/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <title>Bienvenido</title>
</head>
<body>

<div class="wrapper fadeInDown">
    <div id="formContent">
        <!-- Tabs Titles -->

        <!-- Icon -->
        <div class="formFooterHeader">
            <div>
                <img src="https://image.flaticon.com/icons/svg/2922/2922037.svg" width="100" height="100" class="d-inline-block align-top" align="center" alt="">
            </div>
            <p>Antigua burger</p>
        </div>

        <!-- Login Form -->
        <form action="menus.html"  method="post" id="formLogin" >
            <input type="text" id="login" class="fadeIn second" name="nameCashier" placeholder="User Name" required>
            <input type="submit" class="fadeIn fourth" value="Ingresar">
        </form>

        <!-- Remind Passowrd -->
        <div id="formFooter">

        </div>

    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

<script src="js/login.js"></script>

</body>
</html>