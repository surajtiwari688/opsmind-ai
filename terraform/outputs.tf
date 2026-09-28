output "resource_group_name" {
  value = azurerm_resource_group.opsmind
}

output "acr_name" {
  value = azurerm_container_registry.opsmind.name
}

output "acr_login_server" {
  value = azurerm_container_registry.opsmind.login_server
}
