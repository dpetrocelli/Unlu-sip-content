#!/bin/bash
echo "Starting container "
cron
echo "Service Cron started"

echo "Parameters recieved "
echo $BUCKET_NAME


/usr/local/bin/aws configure set aws_access_key_id $ACCESS_KEY_ID
/usr/local/bin/aws configure set aws_secret_access_key $SECRET_ACCESS_KEY
/usr/local/bin/aws configure set region $REGION

echo "AWS Cli credentials configured "


if [ -n "$BUCKET_NAME" ]; then
  echo "Setting up S3-Sync"

  echo "Doing initial sync with S3"
  /usr/local/bin/aws s3 sync s3://$BUCKET_NAME /var/jenkins_home --exclude "*caches/*"

  echo "starting jenkins server" 
  exec /usr/local/bin/jenkins.sh

fi
