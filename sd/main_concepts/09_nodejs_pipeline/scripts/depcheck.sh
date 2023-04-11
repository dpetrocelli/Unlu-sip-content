
dependency-check ./package.json --verbose &> file.txt
exec_result=$?

echo 'command result:' $(cat file.txt)
echo "execution code: $exec_result"
exit $exec_result