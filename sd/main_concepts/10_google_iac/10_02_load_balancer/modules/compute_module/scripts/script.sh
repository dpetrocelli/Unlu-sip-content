#!/bin/bash
sudo apt update -y ;
sudo apt install -y nginx;
sudo nginx -v;

cat <<EOF > /var/www/html/index.nginx-debian.html
<html>
<body>
<h1>Hello from $(curl  -H "Metadata-Flavor: Google" "http://metadata.google.internal/computeMetadata/v1/instance/name")</h1>
</body>
</html>
