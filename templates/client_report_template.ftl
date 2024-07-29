<!DOCTYPE html>
<html>
<head>
    <title>Raport Clienți</title>
</head>
<body>
    <h1>Raport Clienți</h1>
    <#list clienti as client>
        <h2>Client ID: ${client.id}</h2>
        <p>Nume: ${client.nume}</p>
        <p>Adresă: ${client.adresa}</p>
        <p>Telefon: ${client.telefon}</p>
        <p>Email: ${client.email}</p>
        <hr />
    </#list>
</body>
</html>
