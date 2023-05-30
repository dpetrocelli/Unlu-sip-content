

|                                         | Workspaces | Branches | Terragrunt |
|-----------------------------------------|------------|----------|------------|
| Minimize code duplication               | ■■■■■      | □□□□□    | ■■■■□      |
| See and navigate environments           | □□□□□      | ■■■□□    | ■■■■■      |
| Different settings in each environment  | ■■■■■      | ■■■■□    | ■■■■■      |
| Different backends for each environment | □□□□□      | ■■■■□    | ■■■■■      |
| Easy to manage multiple backends        | □□□□□      | ■■■■□    | ■■■■■      |
| Different versions in each environment  | □□□□□      | ■■□□□    | ■■■■■      |
| Share data between modules              | ■■□□□      | ■■□□□    | ■■■■■      |
| Work with multiple modules concurrently | □□□□□      | □□□□□    | ■■■■■      |
| No extra tooling to learn or use        | ■■■■■      | ■■■■■    | □□□□□      |
| Works with Terraform Cloud              | ■■■■■      | ■■■■■    | ■□□□□      |

Terragrunt
https://terragrunt.gruntwork.io/docs/getting-started/quick-start/
https://faun.pub/building-multi-environment-infrastructure-with-terragrunt-7ee1074004aa
https://github.com/pmalmirae/terragrunt-demo/blob/main/deployments/terragrunt.hcl
to be used for documentation
https://medium.com/@man-wai/why-use-terragrunt-in-2022-5e97c61cc539
https://www.padok.fr/en/blog/terraform-code-terragrunt
https://github.com/antonputra/tutorials/tree/main/lessons/160/infrastructure-live-v4
https://blog.gruntwork.io/how-to-manage-multiple-environments-with-terraform-using-terragrunt-2c3e32fc60a8

https://github.com/brikis98/terraform-up-and-running-code/tree/master/code/terraform/10-terraform-team
página 612 

BUEN ORDEN: 
https://github.com/startuplcoud/infra-multi-account-region-startup-kit
https://medium.com/geekculture/terragrunt-cheat-sheet-bedafbf9d61f
https://github.com/bschaatsbergen/minimal-terragrunt

Workflow
https://github.com/Block-Farms/GCP-Github-Actions-Terragrunt/tree/main
https://automationadmin.com/2023/01/terragrunt-repo-structure-v1


```
infra
└── module
    ├── alb # application load balancer module
    │   ├── loadbalancer.tf
    │   ├── security.tf
    │   └── vars.tf
    ├── autoscale # autoscaling module
    │   ├── autoscale.tf
    │   ├── config
    │   │   └── init-config.yaml
    │   ├── data.tf
    │   ├── security.tf
    │   └── vars.tf
    ├── rds
    │   ├── main.tf
    │   ├── outputs.tf
    │   ├── security.tf
    │   └── vars.tf
    └── vpc # vpc module
        ├── data.tf
        ├── main.tf
        ├── outputs.tf
        └── vars.tf
```

### Terragrunt layout

Terragrunt is a thin wrapper around Terraform that provide remote state management, terraform modules dependencies, reduce
repetition and also support multiple AWS accounts and regions.

```
├── common # common configuration and input variables both can be used for the different aws account environment
│ ├── alb.hcl
│ ├── autoscale.hcl
│ ├── rds.hcl
│ └── vpc.hcl
├── dev # development account environment folder
│ └── us-east-1 # only provisioning AWS resources in us-east-1
│     ├── env.yaml global configuration parameters
│     ├── alb
│     │ └── terragrunt.hcl
│     ├── autoscale
│     │ └── terragrunt.hcl
│     ├── rds
│     │ └── terragrunt.hcl
│     └── vpc
│         └── terragrunt.hcl
├── prod
│ └── cn-north-1
│     ├── env.yaml
│     ├── alb
│     │ └── terragrunt.hcl
│     ├── autoscale
│     │ └── terragrunt.hcl
│     ├── rds
│     │ └── terragrunt.hcl
│     └── vpc
│         └── terragrunt.hcl
├── stage
│ └── ap-southeast-1
│     ├── env.yaml
│     ├── rds
│     │ └── terragrunt.hcl
│     └── vpc
│         └── terragrunt.hcl
├── secrets.global.yaml # sops generate secret values in global conguration
├── .sops.yaml # sops configuration
└── terragrunt.hcl # Global terragrunt configuration

```


Where:

Account: At the top level are each of your AWS accounts, such as stage-account, prod-account, mgmt-account, etc. If you have everything deployed in a single AWS account, there will just be a single folder at the root (e.g. main-account).

Region: Within each account, there will be one or more AWS regions, such as us-east-1, eu-west-1, and ap-southeast-2, where you've deployed resources. There may also be a _global folder that defines resources that are available across all the AWS regions in this account, such as IAM users, Route 53 hosted zones, and CloudTrail.

Environment: Within each region, there will be one or more "environments", such as qa, stage, etc. Typically, an environment will correspond to a single AWS Virtual Private Cloud (VPC), which isolates that environment from everything else in that AWS account. There may also be a _global folder that defines resources that are available across all the environments in this AWS region, such as Route 53 A records, SNS topics, and ECR repos.

Resource: Within each environment, you deploy all the resources for that environment, such as EC2 Instances, Auto Scaling Groups, ECS Clusters, Databases, Load Balancers, and so on. Note that the Terraform code for most of these resources lives in the terragrunt-infrastructure-modules-example repo.



azure-kubernetes
├── k8s-to-akv
│   ├── certificates
│   │   ├── main.tf
│   │   ├── outputs.tf
│   │   └── variables.tf
│   └── secrets
│       ├── main.tf
│       ├── outputs.tf
│       └── variables.tf
├── cluster
│   ├── main.tf
│   ├── outputs.tf
│   └── variables.tf
├── helm-release
│   ├── main.tf
│   ├── outputs.tf
│   └── variables.tf
├── kubernetes-addons
│   ├── cert-manager
│   │   ├── main.tf
│   │   ├── outputs.tf
│   │   ├── provider.tf
│   │   └── variables.tf
│   ├── ingress-nginx
│   │   ├── main.tf
│   │   ├── outputs.tf
│   │   ├── provider.tf
│   │   └── variables.tf
│   ├── linkerd
│   │   ├── main.tf
│   │   ├── outputs.tf
│   │   ├── provider.tf
│   │   └── variables.tf
│   └── linkerd-viz
│       ├── main.tf
│       ├── outputs.tf
│       ├── provider.tf
│       └── variables.tf
└── kubernetes-manifest
    ├── main.tf
    ├── outputs.tf
    └── variables.tf


export AWS_ACCESS_KEY_ID= ;export AWS_SECRET_ACCESS_KEY=

terragrunt run-all validate --terragrunt-non-interactive