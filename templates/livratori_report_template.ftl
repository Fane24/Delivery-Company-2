<!DOCTYPE html>
<html>
<head>
    <title>Raport Livratori</title>
</head>
<body>
    <h1>Raport Livratori</h1>
    <#list livratori as livrator>
        <h2>Livrator ID: ${livrator.id}</h2>
        <p>Nume: ${livrator.nume}</p>
        <p>Număr Curse: ${livrator.numar_curse}</p>
        <p>Rute: ${livrator.rute}</p>
        <hr />
    </#list>
</body>
</html>
