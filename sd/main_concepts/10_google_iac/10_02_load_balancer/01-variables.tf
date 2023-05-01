variable "region" {
  type    = string
  default = "us-central1"

}

variable "zone" {
  type    = string
  default = "us-central1-a"

}

variable "vpc" {
  type    = string
  default = "unlu-exercise-2"

}

variable "project-tags" {
  type = map(string)
  default = {
    "key" = "unlu"
    "magic" = "unlu2"
  }
}


variable "credentials_file_path" {
  description = "Path to GCP service account credentials file"
  default     = "./terraform.json"
}

variable "project_id" {
  type    = string
  default = "sd-2023-384422"
}

variable "instance_type" {
  type    = string
  default = "e2-standard-2"
}

variable "instance_name" {
  type    = string
  default = "lb-instance"
}

variable "bucket_name" {
    default = "unlu-tf-state"
}

variable "prefix" {
    default = "exercise2/state"
}

variable "privatekeypath" {
    type = string
    default = "~/.ssh/id_rsa"
}

variable "publickeypath" {
    type = string
    default = "~/.ssh/id_rsa.pub"
}

variable "instance_count" {
  type = number
  default = 3 
}

variable "user" {
  type =string 
  default = "dmpetrocelli"
}