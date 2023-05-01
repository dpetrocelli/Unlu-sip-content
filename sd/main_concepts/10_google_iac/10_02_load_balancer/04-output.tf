#output "rescatar_output_hijovm_external_ip" {
#  value = module.compute-engine.external_ip
#}
#
#output "rescatar_output_hijovm_id" {
#  value = module.compute-engine.id
#}
#
#output "rescatar_output_hijovm_name" {
#  value = module.compute-engine.name
#}

output "lb-public-ipv4" {
  description = "LB ipv4 output"
  value       = module.compute-engine.Loadbalancer-IPv4-Address
}

