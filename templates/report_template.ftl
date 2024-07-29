<!DOCTYPE html>
<html>
<head>
    <title>Raport Comenzi</title>
</head>
<body>
    <h1>Raport Comenzi</h1>
    <#list comenzi as comanda>
        <h2>Comanda ID: ${comanda.id}</h2>
        <p>Nume Client: ${comanda.nume_client}</p>
        <p>Nume Livrator: ${comanda.nume_livrator}</p>
        <p>Detalii Produs: ${comanda.detalii_produs}</p>
        <p>Cantitate: ${comanda.cantitate}</p>
        <p>Pret: ${comanda.pret}</p>
        <p>Status Comanda: ${comanda.status_comanda}</p>
        <hr />
    </#list>
</body>
</html>
