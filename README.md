# empleado
Aplicacion para realizar operaciones crud de empleados

# swagger
http://localhost:8888/swagger-ui/index.html#/

# guardar
curl -X 'POST' \
  'http://localhost:8888/api/empleados' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "primerNombre": "test",
  "segundoNombre": "test",
  "apellidoPaterno": "test",
  "apellidoMaterno": "test",
  "edad": 50,
  "sexo": "M",
  "fechaNacimiento": "25-12-1960",
  "puesto": "desarrollador"
}'

curl -X 'POST' \
  'http://localhost:8888/api/empleados/saveAll' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '[
  {
    "primerNombre": "test",
    "segundoNombre": "test",
    "apellidoPaterno": "test",
    "apellidoMaterno": "test",
    "edad": 20,
    "sexo": "M",
    "fechaNacimiento": "25-12-1990",
    "puesto": "qa"
  }
]'

# consultar
curl -X 'GET' \
  'http://localhost:8888/api/empleados' \
  -H 'accept: */*'

curl -X 'GET' \
  'http://localhost:8888/api/empleados/2' \
  -H 'accept: */*'
  
# eliminar
curl -X 'DELETE' \
  'http://localhost:8888/api/empleados/1' \
  -H 'accept: */*'

# actualizar
curl -X 'PUT' \
  'http://localhost:8888/api/empleados/2' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "primerNombre": "test",
  "segundoNombre": "test",
  "apellidoPaterno": "test",
  "apellidoMaterno": "test",
  "edad": 10,
  "sexo": "M",
  "fechaNacimiento": "25-12-1990",
  "puesto": "ingeniero"
}'

# mysql - up
docker-compose -f mysql.yml up -d

# mysql - down
docker-compose -f mysql.yml down
