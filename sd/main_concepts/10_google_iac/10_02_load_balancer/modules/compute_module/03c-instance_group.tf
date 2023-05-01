resource "google_compute_instance_group_manager" "instance_group" {
  name        = var.instance_group_name
  #provider    = google-beta
  description = var.instance_group_manager_description

  base_instance_name = "webservers"
  zone               = var.zone

  version {
    name               = var.deploy_version
    instance_template  = google_compute_instance_template.instance_template.id
  }

  target_size = var.instance_group_target_size

  named_port {
    name = "web"
    port = 80
  }

  auto_healing_policies {
    health_check      = google_compute_health_check.autohealing.id
    initial_delay_sec = 60
  }

  update_policy {
    type                  = "PROACTIVE"
    minimal_action        = "REPLACE"
    max_surge_percent     = 100
    max_unavailable_fixed = 0
    #min_ready_sec         = 0
    replacement_method    = "SUBSTITUTE"
  }
}

resource "google_compute_health_check" "autohealing" {
  name                = "${var.health_check_name}-autohealing"
  check_interval_sec  = 5
  timeout_sec         = 5
  healthy_threshold   = 2
  unhealthy_threshold = 10

  http_health_check {
    request_path = "/"
    port         = "80"
  }
}
