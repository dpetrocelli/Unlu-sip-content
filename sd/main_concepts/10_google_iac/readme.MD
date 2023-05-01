# obtain the key
Google Cloud Platform -> API Manager -> Credentials -> Create Credentials -> Service account key.

There are several ways to authenticate Terraform with Google Cloud. The first is using a service
account. For this, you create a service account and download a key file

gcloud projects list
gcloud config set project sd-2023-384422
gcloud iam service-accounts create terraform \
 --description="Terraform Service Account" \
 --display-name="terraform"
export GOOGLE_SERVICE_ACCOUNT=`gcloud iam service-accounts \
 list --format="value(email)" --filter=name:"terraform@"`
export GOOGLE_CLOUD_PROJECT=`gcloud info \
--format="value(config.project)"`
gcloud projects add-iam-policy-binding $GOOGLE_CLOUD_PROJECT \
 --member="serviceAccount:$GOOGLE_SERVICE_ACCOUNT" \
 --role="roles/editor"
gcloud iam service-accounts keys create "./terraform.json" \
 --iam-account=$GOOGLE_SERVICE_ACCOUNT

bucket_name="unlu-tf-state"
gcloud storage buckets create gs://$bucket_name || echo " Bucket already exists"

# Relevant links
* How to manage references to local host -> self -> https://stackoverflow.com/questions/62488959/how-do-i-access-the-nat-ip-of-a-counted-google-compute-instance-in-a-connection

* How to manage packages
* How to create services