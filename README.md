# 2018-vn-group-03

## Deploy

* Plataforma: Heroku
* Tipo: PaaS

## Comandos

1. mvn heroku:deploy
2. heroku ps:scale web=1
3. heroku ps:scale cron=1
4. heroku run:detached bootstrap --app sdge-dds-2018-vn-group-03

## Link

* https://sdge-dds-2018-vn-group-03.herokuapp.com

## Pendientes

* Error al commitear las transacciones (agregar dispositivo - activar apagado automatico)
* Error de conexion con ClearDB (https://stackoverflow.com/questions/24046843/connection-closed-error-in-heroku-cleardb-addon)