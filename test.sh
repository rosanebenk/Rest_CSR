
printf "\n*** INIT ***\n"

curl http://localhost:8124/voyageurs
Remove-item alias:curl


curl -X POST -H "Content-type:application/json" -H "Accept:application/json" -d "{}" http://localhost:8124/voyageurs
curl -X POST -H "Content-type:application/json" -H "Accept:application/json" -d "{}" http://localhost:8124/voyageurs

printf "\n*** Voyageurs ***\n"
curl http://localhost:8124/voyageurs

printf "\n*** ADD Train ***\n"

curl -X POST -H "Content-type:application/json" -H "Accept:application/json" -d "{}" http://localhost:8124/trains
curl -X POST -H "Content-type:application/json" -H "Accept:application/json" -d "{}" http://localhost:8124/trains

printf "\n*** GET Trains ***\n"

curl http://localhost:8124/trains
