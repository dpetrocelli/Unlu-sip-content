

resource "google_compute_instance_template" "instance_template" {
  name        = "${var.instance_template_name}-${var.deploy_version}"
  description = var.instance_template_description
  
  #tags = var.project-tags

  labels = {
    service = var.instance_template_name
    version = var.deploy_version
  }

  metadata = {
    version = var.deploy_version
    #block-project-ssh-keys = true
    ssh-keys = "${var.user}:${file(var.publickeypath)}"
  }

  instance_description    = var.instance_name
  machine_type            = var.instance_type
  can_ip_forward          = false
  #metadata_startup_script = "${file("${path.module}/scripts/script.sh")}"

  scheduling {
    automatic_restart   = true
    on_host_maintenance = "MIGRATE"
    #preemptible                 = false
    #provisioning_model          = "SPOT"
  }

  disk {
    source_image = data.google_compute_image.my_debian_image.self_link
    boot         = true
    disk_type    = "pd-balanced"
  }

  network_interface {
    network    = var.network
    #network = "default"
    /* access_config { 
      // Ephemeral public IP
    } */
    #subnetwork = data.google_compute_subnetwork.subnet.name
  }

  /* service_account {
    # Google recommends custom service accounts that have cloud-platform scope and permissions granted via IAM Roles.
    email  = data.google_service_account.this.email
    scopes = ["cloud-platform"]
  } */

  lifecycle {
    create_before_destroy = true
  } 
}
