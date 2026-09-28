variable "resource_group_name" {
  description = "The name of the resource group in which to create the resources."
  type        = string
}

variable "location" {
  description = "The Azure region in which to create the resources."
  type        = string
}

variable "acr_name" {
  description = "The name of the Azure Container Registry (ACR) to create."
  type        = string
}
