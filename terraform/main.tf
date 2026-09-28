resource "azurerm_resource_group" "opsmind" {
  name     = var.resource_group_name
  location = var.location
}

resource "azurerm_container_registry" "opsmind" {
  name                          = var.acr_name
  resource_group_name           = azurerm_resource_group.opsmind.name
  location                      = azurerm_resource_group.opsmind.location
  sku                           = "Basic"
  admin_enabled                 = false
  public_network_access_enabled = true
}
