echo "server process will listen at -> $1"
git pull --rebase origin master
activator "start -Dhttp.port=$1"