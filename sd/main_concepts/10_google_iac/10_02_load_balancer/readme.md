Requirements
Enable Compute Engine API if doesn't enable.
Create Service account with Editor role (or whatever you want) and export the key file.
Create Cloud Google Storage Bucket for keep the terraform state. If you want, you can add your service account as a member to bucket.
Create Compute Engine Image You can create a Debian 10 based VM and install nginx via sudo apt install -y nginx and test this terraform code. Don't forget to sudo systemctl enable nginx command before turn your VM to image.

# https://github.com/flightlesstux/gcp-instance-group-and-loadbalancer-w-terraform
