variable "instance_name" {
  description = "The name to be used for the compute instance."
  type        = string
}

variable "network" {
  description = "The URI of the VPC network to be used from the compute instance."
  type        = string
}

variable "instance_type" {
  description = "The machine type to be associated with the compute instance."
  type        = string
  default     = "n1-standard-1"
}

variable "base_os" {
  description = "The image to be used for creating the boot disk."
  type = object({
    family = string
    project = string
    
  })

    default = {
        family = "debian-11"
        project = "debian-cloud"
    }
}

variable "project-tags" {
  type = map(string)
  default = {
  }
}

variable "zone" {
  type    = string

}

variable "project_id" {
  type = string
  
}

variable "instance_count" {
  type = number
  default = 3
}

variable "privatekeypath" {
    type = string
    default = "~/.ssh/id_rsa"
}

variable "publickeypath" {
    type = string
    default = "~/.ssh/id_rsa.pub"
}

variable "user" {
  type =string 
  default = "nones"
}

variable "deploy_version" {
  type = number
  default = 6
}

variable "instance_template_name" {
  type = string
  default = "default-instance-lt"
}

variable "instance_template_description" {
  type = string
  default = "default-template "
}

variable "instance_group_name" {
  type = string
  default = "default-groupname"
}


variable "instance_group_manager_description" {
  type = string
  default = "GM example"
}

variable "instance_group_target_size" {
  type = number
  default = 2
}

variable "instance_group_min_size" {
  type = number
  default = 1
}

variable "health_check_name" {
  type = string
  default = "health-check-name-web-server"
}


variable "autoscaler_name" {
  type = string
  default = "autoscaler-web-server"
}

variable "public_ipv4" {
  type = string
  default = "lb-public"
}



variable "http_proxy_name" {
  type = string
  default = "proxy-public"
}

variable "global_forwarding_rule" {
  type = string
  default = "global-forwarding-rule-public"
}


variable "backend_service" {
  type = string
  default = "backend-service-global"
}

variable "http_health_check" {
  type = string
  default = "http-health-check"
}

