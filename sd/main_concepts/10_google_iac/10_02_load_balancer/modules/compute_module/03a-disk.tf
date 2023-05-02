#data "google_compute_image" "debian_image" {
#  family  = var.base_os.family
#  project = var.base_os.project
#}
#
#output "debian_iso" {
#  value = "${data.google_compute_image.debian_image.self_link}"
#}


data "google_compute_image" "my_debian_image" {
  name = "custom-linux-test"
  # could also use family = "family-name"
} 