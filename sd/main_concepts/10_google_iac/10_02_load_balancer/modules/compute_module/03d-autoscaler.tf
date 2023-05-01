resource "google_compute_autoscaler" "autoscaler" {
  name   = "${var.autoscaler_name}-autoscaler"
  zone   = var.zone
  target = google_compute_instance_group_manager.instance_group.id

  autoscaling_policy {
    max_replicas    = 8
    min_replicas    = var.instance_group_min_size
    cooldown_period = 30

    cpu_utilization {
      target = 0.72
    }
  }

  depends_on = [ google_compute_instance_group_manager.instance_group ]
}
