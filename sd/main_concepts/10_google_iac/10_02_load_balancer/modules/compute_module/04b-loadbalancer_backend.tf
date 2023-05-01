resource "google_compute_backend_service" "this" {
  name        = var.backend_service
  port_name   = "http"
  protocol    = "HTTP"
  timeout_sec = 10

  health_checks = [google_compute_http_health_check.healtcheck.id]

  backend {
   group                 = google_compute_instance_group_manager.instance_group.instance_group
   balancing_mode        = "RATE"
   capacity_scaler       = 1.0
   max_rate_per_instance = 500
  }
}

resource "google_compute_http_health_check" "healtcheck" {
  name               = "${var.http_health_check}-healthcheck"
  request_path       = "/"
  check_interval_sec = 1
  timeout_sec        = 1
}
