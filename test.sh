
printf "\n*** INIT ***\n"

curl http://localhost:8124/trains
Remove-item alias:curl


curl -X POST -H "Content-type:application/json" -H "Accept:application/json" -d "{'name':'peter', 'age':30}" http://localhost:8124/trains
curl -X POST -H "Content-type:application/json" -H "Accept:application/json" -d "{'name':'carlos', 'age':25}" http://localhost:8124/trains

printf "\n*** Voyageurs ***\n"
curl http://localhost:8124/voyageurs

printf "\n*** ADD Voyageurs ***\n"

curl -X POST -H "Content-type:application/json" -H "Accept:application/json" -d "{'content':'bla'}" http://localhost:8124/voyageurs
curl -X POST -H "Content-type:application/json" -H "Accept:application/json" -d "{'content':'cronf'}" http://localhost:8124/voyageurs

printf "\n*** GET Voyageurs ***\n"

curl http://localhost:8124/voyageurs
curl http://localhost:8124/voyageurs