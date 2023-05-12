#!/bin/bash
echo "Start syncing files to s3..."

# ...

/usr/local/bin/aws s3 sync /var/jenkins_home s3://jenkins-lts-admin-pipe --exclude "*caches/*"

echo "Sync to s3 has been finished."